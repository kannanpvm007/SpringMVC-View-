package com.ast.maven_sample.Utils;

import javax.servlet.http.HttpServletRequest;

public class URLUtils {

    public static boolean isAjaxRequest(HttpServletRequest pRequest) {
        return "XMLHttpRequest".equals(pRequest.getHeader("X-Requested-With"));
    }

}
