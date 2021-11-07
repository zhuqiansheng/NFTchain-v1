package ustc.nftchainv1.domain.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "message")
public class MessageEntity {
    @Id
    private String _id;

    @Indexed(unique = true)
    private String uuid;

    @Indexed
    private Integer senderId;
    //todo 发送者照片暂时写死
    private String senderPhoto = "https://static-1258386385.cos.ap-beijing.myqcloud.com/img/System.jpg";

    private String senderName;

    @Indexed
    private Date sendTime;

    private String msg;
}