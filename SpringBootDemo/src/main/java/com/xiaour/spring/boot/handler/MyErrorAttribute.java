package com.xiaour.spring.boot.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
@Component
public class MyErrorAttribute extends DefaultErrorAttributes{

	
	@Override
	public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes,
			boolean includeStackTrace) {
           Map<String, Object> map = super.getErrorAttributes(requestAttributes, includeStackTrace);
           map.put("message", "get it");
           //可以获取在异常处理器中自适应的数据
           Map<String,Object> sta = (Map<String, Object>) requestAttributes.getAttribute("ext", 0);
           map.put("ext", sta);
           return map;		
           
	}
	
}
