package ustc.nftchainv1.service.Impl;

import com.baidu.xuper.api.ContractResponse;
import com.baidu.xuper.api.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ustc.nftchainv1.common.util.FileUtil;
import ustc.nftchainv1.config.XuperClientConfig;
import ustc.nftchainv1.service.XChainService;

import java.util.HashMap;
import java.util.Map;


@Service
public class XChainServiceImpl implements XChainService {

    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private XuperClientConfig xuperClientConfig;



    @Override
    public String deployContract(String contractName, Map<String,String> initArgs) {
        String bin = fileUtil.readFile(contractName+".bin");
        String abi = fileUtil.readFile(contractName+".abi");
        Transaction tx = xuperClientConfig.client().deployEVMContract(xuperClientConfig.account(), bin.getBytes(), abi.getBytes(), contractName, initArgs);
        return tx.getTxid();
    }

    @Override
    public HashMap invokeContract(String contractName, String methodName, Map<String, String> args) {
        Transaction tx = xuperClientConfig.client().invokeEVMContract(xuperClientConfig.account(), contractName, methodName, args, null);
        ContractResponse response = tx.getContractResponse();
        HashMap map = new HashMap<>();
        map.put("status",response.getStatus());
        map.put("message",response.getMessage());
        map.put("body",response.getBodyStr());
        return map;
    }

    @Override
    public HashMap queryContract(String contractName, String methodName, Map<String, String> args) {
        Transaction tx = xuperClientConfig.client().queryEVMContract(xuperClientConfig.account(),contractName,methodName,args);
        ContractResponse response = tx.getContractResponse();
        HashMap map = new HashMap<>();
        map.put("status",response.getStatus());
        map.put("message",response.getMessage());
        map.put("body",response.getBodyStr());
        return map;
    }
}
