package com.javamsdt.filestore.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class FileStoreUtil {

    public static List<String> extractFileName(String filePath) {
        int lastDot = filePath.lastIndexOf(".");
        return List.of(filePath.substring(0, lastDot), filePath.substring(lastDot));
    }

    public static String buildImageUrl(HttpServletRequest request, Long id) {
        return request.getScheme() + "://"
                + request.getServerName() + ":"
                + request.getServerPort()
                + request.getContextPath()
                + request.getRequestURI() + "/"
                + id;
    }
}
