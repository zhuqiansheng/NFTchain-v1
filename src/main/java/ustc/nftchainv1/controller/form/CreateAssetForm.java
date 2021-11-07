package ustc.nftchainv1.controller.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("create assert form")
public class CreateAssetForm {
    @NotBlank
    private String name;
    private String externalLink;            //NFT的外部链接，如果用户没有提供，就把文件上传到IPFS后自动生成
    private String description;
    @NotNull
    private String collection;              //collection name
    private String traits;                  //properties,jsonArray
    private String unLockableContent;      
}
