package ustc.nftchainv1.domain.dao;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ustc.nftchainv1.domain.pojo.MessageRefEntity;


@Repository
public class MessageRefDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 插入一条消息
     */
    public String insert(MessageRefEntity entity) {
        entity = mongoTemplate.save(entity);
        return entity.get_id();
    }

    /**
     * 查询用户未读消息数量
     */
    public long searchUnreadCount(int userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("readFlag").is(false)
                .and("receiverId").is(userId));
        return mongoTemplate.count(query, "message_ref");
    }

    /**
     * 查询用户新接收消息的数量，并且标记为非新消息
     */
    public long searchLastCount(int userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("lastFlag").is(true)
                .and("receiverId").is(userId));
        Update update = new Update();
        update.set("lastFlag", false);
        //对查询到的记录将lastFlag置为false,同时获得更新的数量
        UpdateResult result = mongoTemplate.updateMulti(query, update, "message_ref");
        long rows = result.getModifiedCount();
        return rows;
    }

    /**
     * 把用户的某条未读消息变更为已读消息
     *
     * @param id message_ref的id。
     * @return
     */
    public long updateUnreadMessage(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("readFlag", true);
        UpdateResult result = mongoTemplate.updateFirst(query, update, "message_ref");
        long rows = result.getModifiedCount();
        return rows;
    }

    /**
     * 根据id删除ref消息
     */
    public long deleteMessageRefById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        DeleteResult result = mongoTemplate.remove(query, "message_ref");
        return result.getDeletedCount();
    }

    /**
     * 删除某个用户全部消息
     */
    public long deleteUserMessageRef(int userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("receiverId").is(userId));
        DeleteResult result = mongoTemplate.remove(query, "message_ref");
        return result.getDeletedCount();
    }

}
