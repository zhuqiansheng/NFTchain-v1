package ustc.nftchainv1.controller.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel("用户表单")
public class InsertUserForm {
    @ApiModelProperty("用户姓名")
    @Size(min = 18,max = 100)
    private String name;
    @ApiModelProperty("用户年龄")
    @NotNull
    private int age;

}
