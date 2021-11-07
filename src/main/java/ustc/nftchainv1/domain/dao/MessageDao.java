package ustc.nftchainv1.domain.dao;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.mongodb.client.result.DeleteResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ustc.nftchainv1.domain.pojo.MessageEntity;
import ustc.nftchainv1.domain.pojo.MessageRefEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class MessageDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 插入消息实体，MongoDB默认使用格林尼治时间，要把北京时间进行转换
     *
     * @param entity
     * @return 消息记录的id
     */
    public String insert(MessageEntity entity) {
        Date sendTime = entity.getSendTime();
        sendTime = DateUtil.offset(sendTime, DateField.HOUR, 8);
        entity.setSendTime(sendTime);
        entity = mongoTemplate.save(entity);
        return entity.get_id();
    }

    /**
     * ref :  [{"_id": {"$oid": "600beaf0d6310000830036f3"}, "messageId": "600bea9ab5bafb311f147506",
     * "receiverId": 1, "readFlag": "false", "lastFlag": "true"}]
     *
     * @return 用户的分页消息
     */
    public List<HashMap> searchMessageByPage(int userId, long start, int length) {
        JSONObject jo = new JSONObject();
        jo.set("$toString", "$_id");        //表1(message)里的主键值id是Object类型，要转为String型
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.addFields().addField("id").withValue(jo).build(),       //转换后别名"id"
                Aggregation.lookup("message_ref", "id", "messageId", "ref"),
                Aggregation.match(Criteria.where("ref.receiverId").is(userId)),     //where message_ref.receiverId = message.userId
                Aggregation.sort(Sort.by(Sort.Direction.DESC, "sendTime")), //按发送时间逆序
                Aggregation.skip(start),
                Aggregation.limit(length)
        );
        //message 和 message_ref 联合查询
        AggregationResults<HashMap> results = mongoTemplate.aggregate(aggregation, "message", HashMap.class);
        List<HashMap> list = results.getMappedResults();
        list.forEach(one -> {
            //每一个用户的消息列表
            List<MessageRefEntity> refList = (List<MessageRefEntity>) one.get("ref");
            MessageRefEntity entity = refList.get(0);
            boolean readFlag = entity.getReadFlag();
            String refId = entity.get_id();
            one.remove("ref");
            one.put("readFlag", readFlag);
            one.put("refId", refId);
            one.remove("_id");
            //把格林尼治时间转换成北京时间
            Date sendTime = (Date) one.get("sendTime");
            sendTime = DateUtil.offset(sendTime, DateField.HOUR, -8);

            String today = DateUtil.today();
            //如果是今天的消息，只显示发送时间，不需要显示日期
            if (today.equals(DateUtil.date(sendTime).toDateStr())) {
                one.put("sendTime", DateUtil.format(sendTime, "HH:mm"));
            }
            //如果是以往的消息，只显示日期，不显示发送时间
            else {
                one.put("sendTime", DateUtil.format(sendTime, "yyyy/MM/dd"));
            }
        });
        return list;
    }

    public HashMap searchMessageById(String id) {
        HashMap map = mongoTemplate.findById(id, HashMap.class, "message");
        Date sendTime = (Date) map.get("sendTime");
        //把格林尼治时间转换成北京时间
        sendTime = DateUtil.date(sendTime).offset(DateField.HOUR, -8);
        map.replace("sendTime", DateUtil.format(sendTime, "yyyy-MM-dd HH:mm"));
        return map;
    }

    /**
     * 删除用户后，同时删除其消息
     *
     * @param receiverId
     * @return
     */
    public long deleteUserMessage(int receiverId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("receiverId").is(receiverId));
        DeleteResult result = mongoTemplate.remove(query, "message");
        long rows = result.getDeletedCount();
        return rows;
    }


}
