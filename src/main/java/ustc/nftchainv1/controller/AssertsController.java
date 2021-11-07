package ustc.nftchainv1.controller;

import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ustc.nftchainv1.common.R;
import ustc.nftchainv1.controller.form.CreateAssetForm;
import ustc.nftchainv1.controller.form.ListAssetsForm;
import ustc.nftchainv1.controller.form.SellAssetForm;
import ustc.nftchainv1.exception.MyException;
import ustc.nftchainv1.service.AssetService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Api(tags = "assets")
@RestController
@RequestMapping("/assets")
@Slf4j
public class AssertsController {

    @Autowired
    private AssetService assetService;


    @GetMapping("/Owner")
    public R getByOwner(@RequestParam("username")  String username){
        //todo username的所有account下的assets list
        return R.ok();
    }

    @PostMapping("/retrieveSingle")
    @ApiOperation("retrieve a single asset by tokenId")
    public R retrieveSingle(@RequestParam("tokenId") String tokenId){
        //todo tokenId,imageUrl,name,externalLink,owner,traits,last_sale
        return R.ok();
    }

    @PostMapping("/sell")
    public R sellAsset(@Valid @RequestBody SellAssetForm form){
        return R.ok();
    }



    @GetMapping("/list")
    public R listAsserts(@RequestBody ListAssetsForm form){
        return R.ok();
    }

    @PostMapping("/create")
    @ApiOperation("crate asset")
    public R createAssert(@Valid CreateAssetForm form,@RequestParam("file")MultipartFile file){
        List<HashMap> traits;
        if(form.getTraits()!=null){
            if(!JSONUtil.isJsonArray(form.getTraits())){
                throw new MyException("traits不是有JSON数组");
            }
            traits = JSONUtil.parseArray(form.getTraits()).toList(HashMap.class);
            log.info(traits.toString());
        }
        assetService.createAssert();


        return R.ok();
    }

    @PostMapping("/events")
    @ApiOperation("events list occur on a asset")
    public R retrieveEvents(@RequestParam("tokenId")String tokenId){
        return R.ok();
    }


}
