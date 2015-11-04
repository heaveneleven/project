package com.njqs.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.njqs.utils.SessionKey;
import com.njqs.utils.UserInfo;

public class LoginInterceptor implements HandlerInterceptor{

	private List<String> excludeUrls;//不需要拦截的资源
	
	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
			String requestUri=request.getRequestURI();
			String contextPath=request.getContextPath();
			String url=requestUri.substring(contextPath.length());
			UserInfo user=(UserInfo)request.getSession().getAttribute(SessionKey.UserInfoKey);
			if(user==null || user.toString().isEmpty()){
				System.out.println("session 丢失 url:"+SessionKey.UserInfoKey);
				request.setAttribute("msg", "您还没有登录或登录已超时，请重新登录！");
				request.getRequestDispatcher("/login/welcome.do").forward(request, response);
				return false;
			}
			return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
