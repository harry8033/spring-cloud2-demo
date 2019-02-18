package com.pf.spring.base;

import com.pf.core.util.Common;
import com.pf.spring.exception.LessParamsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(BaseController.class);

	public Map<String, Object> getRequestParams() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}
		Enumeration<String> names = request.getParameterNames();
		Map<String, Object> params = new HashMap<>();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			params.put(name, request.getParameter(name));
		}
		return params;
	}
	
	
	public Map<String, Object> getMultipartRequestParams(MultipartHttpServletRequest multipartRequest) {
		try {
			multipartRequest.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}
		Map<String, Object> params = new HashMap<>();
		Enumeration<String> names = multipartRequest.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			params.put(name, multipartRequest.getParameter(name));
		}
		return params;
	}

	public Map<String, String> getRequestParamsStr() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		Map<String, String> params = new HashMap<>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			params.put(name, request.getParameter(name));
		}
		return params;
	}

	/**
	 * 获取页面传递的某一个数组值,
	 * 
	 * @return Class<T>
	 * @throws Exception
	 */
	public String[] getParaValues(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String str = request.getParameter(key);
		if (str != null){
			return str.split(",");
		}
		return new String[] {};
	}

	/**
	 * 获取页面传递的某一个数组值,
	 * 
	 * @return Class<T>
	 * @throws Exception
	 */
	public String getParaValue(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request.getParameter(key);
	}

	/**
	 * 获取页面传递的某一个数组值,
	 * 
	 * @return Class<T>
	 * @throws Exception
	 */
	public String[] getParaValues2(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String[] str = request.getParameterValues(key);
		return str;
	}

	/**
	 * 校验是否缺少关键参数
	 * 
	 * @param params
	 *            参数map对象
	 * @param keys
	 *            需要校验的key
	 * @return
	 */
	public boolean validateParams(Map<String, ?> params, String[] keys) throws LessParamsException {
		for (String key : keys) {
			if (!params.containsKey(key)) {
				throw new LessParamsException(key);
			}
		}
		return true;
	}
	
	/**
	 * 校验是否缺少关键参数
	 * 
	 * @param params
	 *            参数map对象
	 * @param keys
	 *            需要校验的key
	 * @return
	 */
	public boolean validateEmpty(Map<String, ?> params, String...keys) throws LessParamsException {
		for (String key : keys) {
			if (Common.isEmpty(String.valueOf(params.get(key)))) {
				throw new LessParamsException(key);
			}
		}
		return true;
	}
	
	public Map<String, String> getHeaders() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}
		Map<String, String> params = new HashMap<>(6);
		params.put("os", request.getHeader("platform-type"));
		params.put("platformid", request.getHeader("platformid"));
		params.put("channel", request.getHeader("channel"));
		params.put("deviceid", request.getHeader("deviceid"));
		params.put("ver", request.getHeader("client-version"));
		params.put("package", Common.isEmpty(request.getHeader("package"))?"wemeet":request.getHeader("package"));
		return params;
	}
}
