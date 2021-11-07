package ustc.nftchainv1.controller.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ApiModel("account profile settings")
public class ProfileSettingsForm {
    @NotBlank
    private String userName;            //不能与其他人相同
    private String bio;                 //个人简介
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")
    private String email;
}
