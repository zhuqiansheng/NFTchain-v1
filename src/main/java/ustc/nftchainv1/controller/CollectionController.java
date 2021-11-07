package ustc.nftchainv1.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ustc.nftchainv1.common.R;
import ustc.nftchainv1.controller.form.CreateCollectionForm;

import javax.validation.Valid;

@Api(tags = "collection")
@RestController
@RequestMapping("/collection")
@Slf4j
public class CollectionController {

    @PostMapping("/create")
    public R create(@Valid @RequestBody CreateCollectionForm form, @RequestParam("logo")MultipartFile logoImage,MultipartFile featuredImage){
        return R.ok();
    }
}
