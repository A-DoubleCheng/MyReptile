package com.My.Reptile.common;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.Iterator;
import java.util.Map;

public class SearchParamsArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(SearchParams.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        ServletRequest request = webRequest.getNativeRequest(ServletRequest.class);

        // 过滤search_开头的参数
        Map<String, Object> parameters = WebUtils.getParametersStartingWith(request, "search_");

        SearchParams searchParams = new SearchParams();
        searchParams.setData(parameters);

        Iterator entries = parameters.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = entry.getKey().toString().replace(".", "_");
            String value = entry.getValue().toString();

            mavContainer.addAttribute(key, value);
        }

        return searchParams;
    }
}
