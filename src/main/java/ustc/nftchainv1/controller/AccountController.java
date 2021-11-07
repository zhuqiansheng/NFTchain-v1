package ustc.nftchainv1.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ustc.nftchainv1.common.R;
import ustc.nftchainv1.controller.form.LoginForm;
import ustc.nftchainv1.controller.form.ProfileSettingsForm;

import javax.validation.Valid;


@Api(tags = "account manage")
@RestController      //Controller + ResponseBody
@RequestMapping("/account")
public class AccountController {
    @PostMapping("login")
    public R login(@Valid @RequestBody LoginForm form){
        return R.ok();
    }

    @GetMapping("/activity")
    @ApiOperation("history activities")
    public R getActivity(@RequestHeader("token") String token){
        return R.ok();
    }

    @GetMapping("created")
    @ApiOperation("created assert list")
    public R created(@RequestHeader("token") String token){
        return R.ok();
    }

    @GetMapping("/favorites")
    @ApiOperation("favorites assert list")
    public R favorites(@RequestHeader("token") String token){
        return R.ok();
    }

    @GetMapping("/private")
    @ApiOperation("private assert list")
    public R privateAssert(@RequestHeader("token") String token){
        return R.ok();
    }

    @GetMapping("/bids")
    @ApiOperation("bids")
    public R bids(@RequestHeader("token") String token){
        return R.ok();
    }

    @GetMapping("/bidsMade")
    @ApiOperation("bids made by me")
    public R bidsMade(@RequestHeader("token") String token){
        return R.ok();
    }

    @PostMapping("settings")
    @ApiOperation("account settings")
    public R settings(@RequestHeader("token") String token,@Valid @RequestBody ProfileSettingsForm form,MultipartFile profileImage,MultipartFile bannerImage){
        return R.ok();
    }

    @PostMapping("/notifications")
    @ApiOperation("set my notifications")
    public R setNotifications(@RequestHeader("token") String token,@RequestParam("subNotify") String subNotify){
        //subNotify is jsonArray
        return R.ok();
    }
}
