package ustc.nftchainv1.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateCollectionForm {
    @NotBlank
    private String name;
    private String description;
    private String category;            //json array
}
