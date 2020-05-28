package hcc.pete.smartivr.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import hcc.pete.smartivr.annotation.PassToken;
import hcc.pete.smartivr.annotation.UserLoginToken;
import hcc.pete.smartivr.pojo.User;
import hcc.pete.smartivr.response.ErrorCodeAndMsg;
import hcc.pete.smartivr.response.MyException;
import hcc.pete.smartivr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Pete Chen
 * @date 2020/5/25
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        // 获取cookie中的token令牌
        String token = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod)object;

        Method method = handlerMethod.getMethod();
        // 检查是否有passToken注解，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        // 检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null || "".equals(token)) {
                    throw new MyException(ErrorCodeAndMsg.NO_TOKEN);
                }
                // 获取token中的user id
                String userName;
                try {
                    userName = JWT.decode(token).getClaim("username").asString();
                } catch (JWTDecodeException j) {
                    throw new MyException(ErrorCodeAndMsg.INVALID_TOKEN);
                }

                User user = userService.findByUsername(userName);
                if (user == null) {
                    throw new MyException(ErrorCodeAndMsg.NON_EXISTENT_USER);
                }
                // 验证token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).withIssuer("文总").build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    e.printStackTrace();
                    throw new MyException(ErrorCodeAndMsg.INVALID_TOKEN);
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
