package ustc.nftchainv1.domain.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "message_ref")
public class MessageRefEntity {
    @Id
    private String _id;

    @Indexed
    private String messageId;

    @Indexed
    private Integer receiverId;

    @Indexed
    private Boolean readFlag;

    @Indexed
    private Boolean lastFlag;
}