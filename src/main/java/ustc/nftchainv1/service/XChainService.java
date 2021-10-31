package ustc.nftchainv1.service;


import java.util.HashMap;
import java.util.Map;

public interface XChainService {
    /**
     * 读取合约编译后的abi和bin文件，进行部署
     * @param contractName
     */
    String  deployContract(String contractName, Map<String,String> initArgs);


    HashMap invokeContract(String contractName, String methodName, Map<String,String> args);

    HashMap queryContract(String contractName, String methodName, Map<String,String> args);

}
