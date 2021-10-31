package ustc.nftchainv1.config;

import io.ipfs.api.IPFS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class IPFSConfig {
    @Value("${IPFS.host}")
    private String host;
    @Value("${IPFS.port}")
    private int port;

    @Bean
    public IPFS IPFS(){
        return new IPFS(host,port);
    }
}
