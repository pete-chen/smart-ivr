package hcc.pete.smartivr.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import hcc.pete.smartivr.pojo.User;

import java.util.Date;

/**
 * @author Pete Chen
 * @date 2020/5/25
 */
public class JwtUtil {

    // 有效时间2小时
    private static final long EXPIRE_TIME= 2*60*60*1000;

    public static String getToken(User user) {

        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("文总")
                    .withClaim("username", user.getUsername())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;

    }
}
