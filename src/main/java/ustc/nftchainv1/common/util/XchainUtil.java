package ustc.nftchainv1.common.util;

import com.baidu.xuper.api.Account;
import com.baidu.xuper.api.XuperClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;


@Data
@Component
@Slf4j
public class XchainUtil {
    public static XuperClient client;
    public static Account account;

    @Value("${XuperClient.host}")
    private String host;
    @Value("${XuperClient.port}")
    private String port;
    @Value("${XuperClient.contractAccount}")
    private String contractAccount;


    public static void start() throws IOException {
//        client = new XuperClient(host+":"+port);        //连接结点
//        account = Account.create("/Users/sens/Documents/key/xuperChain/keys");      //读keys文件获取账户
//        account.setContractAccount(contractAccount);
//        log.info("account Address: " + account.getAddress());
        client = new XuperClient("127.0.0.1:37101");        //连接结点
        account = Account.create("/Users/sens/Documents/key/xuperChain/keys");      //读keys文件获取账户
        account.setContractAccount("XC1000000000000000@xuper");
        log.info("account Address: " + account.getAddress());
    }




    public static String contractResToJson(String cr) {
//        为什么这么写不行？？？？
//        cr.replace("[", "").replace("]","");
//        cr.replace("{","").replace("}","");
//        String newCr="{"+cr+"}";
//        return newCr;
        String[] split = cr.split(",");
        System.out.println(Arrays.toString(split));
        StringBuffer stringBuffer = new StringBuffer();
        for (String s : split) {
            s=s.replace("{","").replace("}","").replace("[","{").replace("]","}");
            stringBuffer.append(s);
            stringBuffer.append(",");
        }
        String substring = stringBuffer.substring(0, stringBuffer.length() - 1);
        return substring;
    }
}
