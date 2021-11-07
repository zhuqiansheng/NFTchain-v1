package ustc.nftchainv1.common.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ustc.nftchainv1.exception.MyException;

import java.util.Date;

/**
 * 创建token，验证token，从token中获取userId
 * jwt java 官方文档
 * https://github.com/auth0/java-jwt
 */

@Component
@Slf4j
public class JwtUtil {
    //秘钥
    @Value("${jwt.secret}")
    private String secret;

    //过期时间(天)
    @Value("${jwt.expire}")
    private int expire;

    /**
     * @param userId ：由userId，秘钥，过期时间 生成token
     * @return token字符串
     */
    public String createToken(int userId) {
        //设过期时间为当前时间往后偏移5天
        Date expireDate = DateUtil.offset(new Date(), DateField.DAY_OF_YEAR, 5);
        Algorithm algorithm = Algorithm.HMAC256("secret");   //传入秘钥创建加密算法对象
        String token = JWT.create()
                .withClaim("userId", userId)           //绑定userId,之后可以通过token获取userId
                .withExpiresAt(expireDate)                   //过期时间
                .sign(algorithm);                            //加密算法
        return token;
    }

    /**
     * @param token 解码token，反向获取之前传入的用户id
     * @return userId
     */
    public int getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);             //解码
            return jwt.getClaim("userId").asInt();       //由绑定的userId属性名反向获取数据，转成int型
        } catch (Exception e) {
            throw new MyException("令牌无效");
        }


    }

    /**
     * 验证令牌内容正确性，是否过期
     *
     * @param token
     */
    public void verifierToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256("secret");      //与加密算法对象相同
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);                //验证内容和有效期
    }
}
