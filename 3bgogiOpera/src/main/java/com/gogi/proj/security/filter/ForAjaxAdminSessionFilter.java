package com.gogi.proj.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.util.UrlPathHelper;

import com.gogi.proj.security.AdminVO;

public class ForAjaxAdminSessionFilter implements Filter{

	private String ajaxHeader;
	
	private static final Logger logger = LoggerFactory.getLogger(ForAjaxAdminSessionFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void setAjaxHeader(String ajaxHeader) {
		this.ajaxHeader = ajaxHeader;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		if(isAjaxRequest(req)) {
			
			try {
				
				chain.doFilter(req, res);
				
			}catch(AccessDeniedException e) {
				
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				
			}catch(AuthenticationException e) {
				
				res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				
			}
			
		}else {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			try {
				
				AdminVO adminVo = (AdminVO)auth.getPrincipal();
				
				UrlPathHelper urlPathHelper = new UrlPathHelper();
				String pathUrl = urlPathHelper.getOriginatingRequestUri(req);
				
				logger.info(adminVo.getUsername()+" ["+adminVo.getAdminname()+"]"+" 접근 주소={}",pathUrl);
			}catch(ClassCastException e) {
				chain.doFilter(req, res);
			}


			
			chain.doFilter(req, res);
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	private boolean isAjaxRequest(HttpServletRequest request) {
		return request.getHeader(ajaxHeader) != null && request.getHeader(ajaxHeader).equals(Boolean.TRUE.toString());
	}
	


}
