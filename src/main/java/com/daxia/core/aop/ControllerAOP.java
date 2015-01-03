package com.daxia.core.aop;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.daxia.core.common.Log;
import com.daxia.core.common.Module;
import com.daxia.core.config.AppProperties;
import com.daxia.core.dto.SystemLogDTO;
import com.daxia.core.exception.ObjectNotFoundException;
import com.daxia.core.exception.ValidationException;
import com.daxia.core.model.User;
import com.daxia.core.service.SystemLogService;
import com.daxia.core.util.JsonUtils;
import com.daxia.core.util.MD5Util;
import com.daxia.core.util.SpringSecurityUtils;
import com.daxia.wy.dto.api.BaseAPIOutput;
import com.daxia.wy.dto.api.MobileApiOutput;
import com.google.common.collect.Lists;

/**
 * 实现aop的一个例子，@Aspect和@Component要同时有。 可惜的是，似乎没法拦截controller层。
 * 
 * @date 2013-6-19
 * 
 */
@Aspect
@Component
public class ControllerAOP {
	private static Logger logger = Logger.getLogger(ControllerAOP.class);
	
	public static final String EDP = "execution(* com.daxia.*.*.controller..*.*(..))";
	private String clientSecret = "123498sdfasdf89ujalkdsf098asdf2332a";

	public ControllerAOP() {
		System.out.println("Create ControllerAOP...");
	}

	@Pointcut(EDP)
	public void pointcut() {
	}

	// spring中Before通知
	@Before("pointcut()")
	public void logBefore() {
	}

	// spring中After通知
	@After("pointcut()")
	public void logAfter() {
	}

	// spring中Around通知
	@Around("pointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
	    long start = System.currentTimeMillis();
		try {
            return process(joinPoint);
        } catch (Exception e) {
            throw e;
        } finally {
            if (logger.isInfoEnabled()) {
                RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
                if (requestAttributes instanceof ServletRequestAttributes) {
                   HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
                   String url = request.getServletPath();
                   logger.info("took " + (System.currentTimeMillis() - start) + "ms to process [" + url + "?" + getParameters(request) + "]");
                }
            }
        }
	}

    private Object process(ProceedingJoinPoint joinPoint) throws InstantiationException, IllegalAccessException,
            ClassNotFoundException, Throwable {
        if (logger.isDebugEnabled()) {
	        logger.debug("logAround...");
        }
		SystemLogDTO sl = new SystemLogDTO();
		
		Object[] args = joinPoint.getArgs();
		Object obj = null;
		
		String packageName = joinPoint.getTarget().getClass().getPackage().getName();
		if (logger.isDebugEnabled()) {
            logger.debug("packageName = " + packageName);
        }
		boolean isFromMobile = packageName.equals(AppProperties.get("basePackage") + ".web.controller.m");
		if (isFromMobile) {
			// 手机端的
			if (logger.isDebugEnabled()) {
                logger.debug("手机端的请求");
            }
			
			// check login
			 RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
			 if (requestAttributes instanceof ServletRequestAttributes) {
				HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
				String url = request.getServletPath();
				if (SpringSecurityUtils.getCurrentUser() == null) {
					// no login
					if (url.startsWith("/m/user/register") || url.startsWith("/m/user/login")) {
						// do nothing
					} else {
						BaseAPIOutput dto = new BaseAPIOutput();
						dto.setState("-2");
						dto.setError("请先登陆");
						// return JsonUtils.toJson(dto);
					}
				}
			}
		}
		
		try {
		    if (isFromMobile) {
		        // checkSign(getRequest());
		    }
		    long start = System.currentTimeMillis();
			obj = joinPoint.proceed(args);
			System.out.println("took " + (System.currentTimeMillis() - start) + "ms to process " + joinPoint.getTarget().getClass().getName() + ", " + joinPoint.getSignature().getName());
			sl.setIsSuccess(true);
		} catch (Throwable e) {
			obj = processException(joinPoint, sl, packageName, e);
		} finally {
		    if (isFromMobile) {
		        String callback = getRequest().getParameter("callback");
		        if (obj != null && StringUtils.isNotBlank(callback)) {
		            obj = callback + "(" + obj.toString() + ");";
		        }
		    }
			boolean needLog = ifNeedLog(joinPoint, sl);
			if (needLog) {
				setUserInfo(sl);
				setRequestInfo(sl);
				// Date date;
				sl.setDate(new Date());
				systemLogService.create(sl);
			}
		}
		return obj;
    }

	@SuppressWarnings("unchecked")
    private void checkSign(HttpServletRequest request) {
	    if (request.getServletPath().equals("/m/image/upload")) {
	        return;
	    }
	    List<String> paramKeys = Lists.newArrayList();
	    paramKeys.addAll(request.getParameterMap().keySet());
	    Collections.sort(paramKeys);
	    String str = "";
	    for (String key : paramKeys) {
	        if (!"sign".equals(key)) {
	            str += key + "=" + request.getParameter(key);
	        }
        }
	    str += clientSecret;
	    String clientSign = request.getParameter("sign");
	    String calculatedSign = MD5Util.getMD5Encoding(str);
	    logger.error("str = " + str + ", clientSign = " + clientSign + ", serverSign = " + calculatedSign);
	    if (!calculatedSign.equalsIgnoreCase(clientSign)) {
	        throw new ValidationException("非法请求");
	    }
    }

    private Object processException(ProceedingJoinPoint joinPoint, SystemLogDTO sl, String packageName, Throwable e)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, Throwable {
	    logger.error(e, e);
	    sl.setIsSuccess(false);
	    sl.setErrorMessage(e.getMessage());
	    
	    if (logger.isDebugEnabled()) {
	        logger.debug("packageName = " + packageName);
	    }
	    if (packageName.equals(AppProperties.get("basePackage") + ".web.controller.m")) {
	    	// 手机端的
	    	if (logger.isDebugEnabled()) {
	            logger.debug("手机端的请求");
	        }
	    	
	    	// check login
	    	 RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
	    	 if (requestAttributes instanceof ServletRequestAttributes) {
	    		HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
	    		String url = request.getServletPath();
	    		if (SpringSecurityUtils.getCurrentUser() == null) {
	    			// no login
	    			if (url.startsWith("/m/user/register") || url.startsWith("/m/user/login")) {
	    				// do nothing
	    			} else {
	    				BaseAPIOutput dto = new BaseAPIOutput();
	    				dto.setState("-2");
	    				dto.setError("请先登陆");
	    				// return JsonUtils.toJson(dto);
	    			}
	    		}
	    	}
	    	
	    	if (e instanceof ObjectNotFoundException) {
	    		// 返回空的就行了
	    		String controllerName = joinPoint.getTarget().getClass().getSimpleName();
	    		String mobileModule = controllerName.substring(1).replace("Controller", "");
	    		if (logger.isDebugEnabled()) {
	                logger.debug("mobileModule = " + mobileModule);
	            }
	    		String apiDTOClassName = AppProperties.get("basePackage") + ".dto.api.info." + mobileModule + "InfoAPIDTO";
	    		MobileApiOutput output = new MobileApiOutput();
	    		output.setData(Class.forName(apiDTOClassName).newInstance());
	    		return JsonUtils.toJson(output);
	    	} else if ( e instanceof ValidationException) {
	    	    return JsonUtils.toJson(BaseAPIOutput.error(e.getMessage()));
	    	} else {
	    		// 其它的异常统一返回{state:"-1"}
	    		return JsonUtils.toJson(BaseAPIOutput.error(e.getMessage()));
	    	}
	    }
	    throw e;
    }

	private void setRequestInfo(SystemLogDTO sl) {
	    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {
			HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
			String url = request.getServletPath();
			// String url;
			sl.setUrl(url);
			sl.setIp(request.getRemoteHost());
			@SuppressWarnings("unchecked")
            Map<String, String> map = request.getParameterMap();
			// String params;
			if (MapUtils.isNotEmpty(map)) {
				StringBuilder params = new StringBuilder();
				for (Object key : map.keySet()) {
					// params.append(key.toString()).append("=").append(map.get(key).toString()).append("\n");
					Object value = map.get(key);
					if (value instanceof String[]) {
						String[] arr = (String[]) value;
						StringBuilder valueString = new StringBuilder();
						for (String a : arr) {
							valueString.append(a).append(",");
						}
						params.append(key.toString()).append("=").append(valueString).append("\n");
					} else {
						params.append(key.toString()).append("=").append(value.toString()).append("\n");
					}
				}
				sl.setParams(params.toString());
			}
		}
    }

	private String getParameters(HttpServletRequest request) {
	    Map<String, String> map = request.getParameterMap();
        // String params;
        if (MapUtils.isNotEmpty(map)) {
            StringBuilder params = new StringBuilder();
            for (Object key : map.keySet()) {
                // params.append(key.toString()).append("=").append(map.get(key).toString()).append("\n");
                Object value = map.get(key);
                if (value instanceof String[]) {
                    String[] arr = (String[]) value;
                    StringBuilder valueString = new StringBuilder();
                    for (String a : arr) {
                        valueString.append(a).append(",");
                    }
                    params.append(key.toString()).append("=").append(valueString).append("\n");
                } else {
                    params.append(key.toString()).append("=").append(value.toString()).append("\n");
                }
            }
            return params.toString();
        }
        return null;
	}
	
	private boolean ifNeedLog(ProceedingJoinPoint joinPoint, SystemLogDTO sl) {
	    boolean needLog = false;
	    // String module;
		try {
			Class<? extends Object> targetClass = joinPoint.getTarget().getClass();
			if (targetClass.isAnnotationPresent(Module.class)) {
				String module = ((Module)targetClass.getAnnotation(Module.class)).name().getRemark();
				sl.setModule(module);
			}
			
			// String operation;
	        Method[] methods = joinPoint.getTarget().getClass().getDeclaredMethods();
	        for (Method m : methods) {
	            if (m.getName().equals(joinPoint.getSignature().getName())) {
	            	if (m.isAnnotationPresent(Log.class)) {
	            		sl.setOperation(((Log)m.getAnnotation(Log.class)).operation());
	            		needLog = true;
	            	}
	            }
            }
        } catch (SecurityException e1) {
        	
        };
	    return needLog;
    }

	private void setUserInfo(SystemLogDTO sl) {
	    // User user;
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		if (au != null && au.getPrincipal() != null && au.getPrincipal() instanceof User) {
			User user = (User) au.getPrincipal();
			// System.out.println(user.getUsername());
			sl.setUser(user);
		}
    }
	
	private HttpServletRequest getRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {
			HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
			return request;
		} else {
			return null;
		}
	}
	
	@Autowired
	private SystemLogService systemLogService;
}
