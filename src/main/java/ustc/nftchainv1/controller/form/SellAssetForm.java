package ustc.nftchainv1.controller.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@ApiModel("sell asset form")
public class SellAssetForm {

    @NotBlank
    private Integer tokenId;
    @NotBlank
    private String type;            //交易类型
    @NotBlank
    @Min(0)
    private String price;          //要转换成 BigDecimal  最小0.000000000000000001
    @NotNull
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$")
    private String start;           //上架/开始拍卖时间

    @NotNull
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$")
    private String end;             //下架/结束拍卖时间

}
