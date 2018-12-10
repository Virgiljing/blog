package com.aisat.hkgott.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by zhenglian on 2016/10/12.
 */
@RestController
public class LanguageController {

    @RequestMapping("/changeSessionLanauage")
    public String changeSessionLanauage(HttpServletRequest request, HttpServletResponse response,
                                        String lang){
        System.out.println(lang);
        try {
            if (null != lang) {
                LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
                if (localeResolver == null) {
                    throw new IllegalStateException(
                            "No LocaleResolver found: not in a DispatcherServlet req?");
                }
                localeResolver.setLocale(request, response, StringUtils.parseLocaleString(lang));
            }
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

}
