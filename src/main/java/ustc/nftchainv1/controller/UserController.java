package ustc.nftchainv1.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ustc.nftchainv1.common.R;
import ustc.nftchainv1.controller.form.InsertUserForm;
import ustc.nftchainv1.domain.pojo.User;

import java.util.*;

@Api(tags = "用户管理")
@RestController      //Controller + ResponseBody
@RequestMapping("/users")
public class UserController {
    long id = -1;
    // 创建线程安全的Map，模拟users信息的存储
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @GetMapping("/getUserList")
    @ApiOperation("获取用户列表")
    public R getUserList(){
        List<User> r = new ArrayList<>(users.values());
        return R.ok().put("result",r);
    }

    @PostMapping("/insertUser")
    @ApiOperation("插入用户")
    public R insertUser(@RequestBody InsertUserForm form){
        User user = new User(form.getName(),form.getAge());
        user.setId(++id);
        users.put(id,user);
        return R.ok().put("result",id);
    }

    @GetMapping("getUserById")
    @ApiOperation("根据id获取用户")
    public R getUserById(@RequestParam int id){
        User user = users.get(id);
        return R.ok().put("result",user);
    }
}
