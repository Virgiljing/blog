package com.aisat.hkgott.filter;

import com.aisat.hkgott.utils.SpringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
import java.util.TimeZone;

/**
 * SystemLanguageFilter
 *
 * @author virgilin
 * @date 2018/12/4
 */
@Component
public class SystemLanguageFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        req.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE,
                SpringUtil.getBean("localeResolver"));
        Locale locale = RequestContextUtils.getLocale(request);

        req.setAttribute("systemLanguage", locale.getLanguage());

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }

}
