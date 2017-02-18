package cn.luoxx.shiro.interceptor;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.luoxx.shiro.config.ShiroCasConfig;
import cn.luoxx.shiro.entity.User;

/**
 * 用户session拦截
 */
public class UserSessionInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(UserSessionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession(false);

		logger.info("收到请求URL: >>> {}", request.getRequestURL());

		AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();

		if (principal == null) {
			User user = (User) session.getAttribute("user");
			if (user == null) {
				// 未登录
				response.sendRedirect(ShiroCasConfig.loginUrl);
				return false;
			}
			return true;
		}

		Map<String, Object> attributes = principal.getAttributes();

		logger.info("收到cas服务器登录的反馈信息:\n");
		Set<String> keys = attributes.keySet();
		for (String attr : keys) {
			logger.info(attributes.get(attr).toString() + "\n");
		}

		Object userId = attributes.get("id");
		if (userId != null) {
			User User = new User();
			User.setId(Integer.parseInt(String.valueOf(userId)));
			User.setUsername((String) attributes.get("username"));
			session.setAttribute("user", User);
			session.setAttribute("userId", userId);
		}

		return true;
	}

}
