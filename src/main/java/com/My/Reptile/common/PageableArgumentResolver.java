package com.My.Reptile.common;

import com.My.Reptile.utils.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 根据Request参数自动binding Pageable
 */
public class PageableArgumentResolver implements HandlerMethodArgumentResolver {

    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final String DEFAULT_ORDER_FIELD = "id";
    public static final String DEFAULT_ORDER_DIRECTION = "desc";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Pageable.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        String pageCurrent = webRequest.getParameter("pageCurrent");
        int page = 0;
        if (StringUtils.isNotEmpty(pageCurrent)) {
            page = StringUtils.toInteger(pageCurrent) - 1;
        }

        String pageSize = webRequest.getParameter("pageSize");
        int size = DEFAULT_PAGE_SIZE;
        if (StringUtils.isNotEmpty(pageSize)) {
            size = StringUtils.toInteger(pageSize);
        }

        String orderField = webRequest.getParameter("orderField");
        String field = DEFAULT_ORDER_FIELD;
        if (StringUtils.isNoneEmpty(orderField)) {
            field = orderField;
        }

        String orderDirection = webRequest.getParameter("orderDirection");
        String direction = DEFAULT_ORDER_DIRECTION;
        if (StringUtils.isNoneEmpty(orderDirection)) {
            direction = orderDirection;
        }

        Sort sort = new Sort(direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, field);

        Pageable request = new PageRequest(page, size, sort);
        return request;
    }
}

