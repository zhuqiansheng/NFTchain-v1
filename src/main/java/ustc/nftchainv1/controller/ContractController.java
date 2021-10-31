package ustc.nftchainv1.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ustc.nftchainv1.common.R;
import ustc.nftchainv1.config.XuperClientConfig;
import ustc.nftchainv1.service.XChainService;

import java.util.HashMap;
import java.util.Map;


@Api(tags = "合约管理")
@RestController
@RequestMapping("/contract")
@Slf4j
public class ContractController {

    @Autowired
    private XChainService xchainService;

    @Autowired
    private XuperClientConfig xuperClientConfig;


    @PostMapping("/deploy")
    public R deploy(String contractName, Map<String,String> initArgs){
        log.error(String.valueOf(initArgs));
        if (initArgs.size() ==0){
            log.error("args 空");
            initArgs = null;
        }

        String txId = xchainService.deployContract(contractName,initArgs);
        return R.ok().put("result",txId);
    }

    @PostMapping("/invoke")
    public R invoke(String contractName,String methodName,Map<String,String> args){
        log.error(String.valueOf(args));
        if (args.size() ==0){
            log.error("args 空");
            args = null;
        }

        HashMap resp = xchainService.invokeContract(contractName,methodName,args);
        return R.ok().put("result",resp);
    }

    @PostMapping("/query")
    public R query(String contractName,String methodName,HashMap args){
        log.error(String.valueOf(args));

        args = new HashMap<>();
        args.put("key","aaa");
        HashMap resp = xchainService.queryContract(contractName,methodName,args);
        return R.ok().put("result",resp);
    }

    @PostMapping("/newAccount")
    public R newAccount(){
        return null;
    }
}
