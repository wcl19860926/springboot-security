package com.study.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class ResponseUtils {
    private  static Logger logger =  LoggerFactory.getLogger(ResponseUtils.class);

    public static  <T> void writeStringToResponse(HttpServletResponse response, T t) {
        PrintWriter writer = null;
        try {
            response.setContentType("application/json;charset=UTF-8");
            writer = response.getWriter();
            writer.write(t.toString());
        } catch (IOException e) {
            logger.error("向额户端输出息失败", e);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }
}
