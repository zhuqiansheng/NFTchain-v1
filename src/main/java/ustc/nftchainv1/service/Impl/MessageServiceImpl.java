package ustc.nftchainv1.service.Impl;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ustc.nftchainv1.common.util.MessageTask;
import ustc.nftchainv1.domain.dao.MessageDao;
import ustc.nftchainv1.domain.dao.MessageRefDao;
import ustc.nftchainv1.domain.pojo.MessageEntity;
import ustc.nftchainv1.domain.pojo.MessageRefEntity;
import ustc.nftchainv1.service.MessageService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;
    @Autowired
    private MessageRefDao messageRefDao;

    @Autowired
    private MessageTask messageTask;


    /**
     * 发送系统消息
     * @param msg 消息内容
     * @param id 接收者id
     */
    @Override
    public void sendSystemMsg(int id, String msg) {
        MessageEntity entity = new MessageEntity();
        entity.setSenderId(0);
        entity.setSendTime(new Date());
        entity.setMsg(msg);
        entity.setSenderName("系统消息");
        entity.setUuid(IdUtil.simpleUUID());
        messageTask.send(id + "", entity);   //以接收者id作为topic
    }

    @Override
    public String insertMessage(MessageEntity entity) {
        return messageDao.insert(entity);
    }


    @Override
    public String insertRef(MessageRefEntity entity) {
        return messageRefDao.insert(entity);
    }

    @Override
    public long searchUnreadCount(int userId) {
        return messageRefDao.searchUnreadCount(userId);
    }

    @Override
    public long searchLastCount(int userId) {
        return messageRefDao.searchLastCount(userId);
    }

    @Override
    public List<HashMap> searchMessageByPage(int userId, long start, int length) {
        return messageDao.searchMessageByPage(userId, start, length);
    }

    @Override
    public HashMap searchMessageById(String id) {
        return messageDao.searchMessageById(id);
    }

    @Override
    public long updateUnreadMessage(String id) {
        return messageRefDao.updateUnreadMessage(id);
    }

    @Override
    public long deleteMessageRefById(String id) {
        return messageRefDao.deleteMessageRefById(id);
    }

    @Override
    public long deleteUserMessageRef(int userId) {
        return messageRefDao.deleteUserMessageRef(userId);
    }
}
