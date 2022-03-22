package ltd.chuchen.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @Author chuchen
 * @Date 2022/3/19
 * @Description JWT 工具类
 */
public class JWTUtil {

    //签名
    private static final String SING = "!SR$#^&%@CHUCHEN";

    /**
     *
     * @param map map 要存入 token 的信息
     * @return token
     */
    public static String getToken(Map<String,String> map) {
        JWTCreator.Builder builder = JWT.create();
        //像token中加入传入的用户信息
        map.forEach(builder::withClaim);
        return builder.sign(Algorithm.HMAC256(SING));
    }

    /**
     * 创建自定义返回过期时间的 token
     * @param map 要存入 token 的信息
     * @param day 过期的时间（天）
     * @return token
     */
    public static String getToken(Map<String, String> map, Integer day) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,day); //设置默认过期时间
        JWTCreator.Builder builder = JWT.create();
        //像token中加入传入的用户信息
        map.forEach(builder::withClaim);
        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SING));
    }

    /**
     * 验证 token 的合法性
     * @param token 传入的 token
     */
    public static DecodedJWT getTokenInfo(String token) {
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }
}
