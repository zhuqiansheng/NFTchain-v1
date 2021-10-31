package ustc.nftchainv1.config;

import com.baidu.xuper.api.Account;
import com.baidu.xuper.api.XuperClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class XuperClientConfig {

    @Value("${XuperClient.port}")
    private int port;
    @Value("${XuperClient.host}")
    private String host;
    @Value("${XuperClient.keysPath}")
    private String keysPath;

    @Bean
    public XuperClient client() {
        return new XuperClient(host+":"+port);
    }

    @Bean
    public Account account(){
        return  Account.create(keysPath);      //读keys文件获取账户
    }
}
