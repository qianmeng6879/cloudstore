package top.mxzero.cloudstore.aspect;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.mxzero.common.dto.MemberDTO;
import top.mxzero.common.response.RestResponse;
import top.mxzero.common.utils.UserUtil;
import top.mxzero.jwt.service.ITokenService;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户认证
 *
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@Aspect
@Component
public class AuthenticationCheckAspect {
    @Autowired
    private ITokenService tokenService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationCheckAspect.class);

    /**
     * 拦截带有top.mxzero.jwt.annotation.JWTAuthentication注解的类
     * 检测请求头时候带有身份认证参数
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("@annotation(top.mxzero.jwt.annotation.JWTAuthentication)")
    public Object handler(ProceedingJoinPoint point) throws Throwable {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String authorization = request.getHeader("Authorization");
        String token;

        if (authorization == null || authorization.length() < 10) {
            token = request.getParameter("access_token");
            if (!StringUtils.hasLength(token)) {
                return RestResponse.fail("Unauthenticated").code(401);
            }
        } else {
            token = authorization.substring(7);
        }

        if (!tokenService.verifyToken(token)) {
            return RestResponse.fail("Unauthenticated").code(401);
        }

        try {
            Jws<Claims> claimsJws = tokenService.parseToken(token);
            MemberDTO memberDTO = JSONObject.parseObject(claimsJws.getBody().getSubject(), MemberDTO.class);
            UserUtil.set(memberDTO);

            return point.proceed();
        } catch (Throwable throwable) {
            LOGGER.error("error:{}", throwable.getMessage());
            throw throwable;
        } finally {
            UserUtil.remove();
        }

    }
}
