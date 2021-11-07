package ustc.nftchainv1.controller.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@ApiModel("search asserts list")
public class ListAssetsForm {
    private String owner;
    private String categories;
    private String status;
    private String orderBy;
    @Min(0)
    private int offset;
    @Min(20)
    private int limit;
    @Min(0)
    private Double minPrice;
    private Double maxPrice;
}
