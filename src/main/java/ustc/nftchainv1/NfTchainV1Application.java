package ustc.nftchainv1;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@ServletComponentScan    //使filter生效
@Slf4j
@EnableAsync
@Data
public class NfTchainV1Application {

    public static void main(String[] args) {
        SpringApplication.run(NfTchainV1Application.class, args);
    }



}
