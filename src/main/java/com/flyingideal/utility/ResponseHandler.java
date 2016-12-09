package com.flyingideal.utility;

import com.google.common.base.Preconditions;
import org.apache.http.ConnectionClosedException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/11/29.
 */
public class ResponseHandler {

    public static void responseWithSuccessJson(HttpServletResponse response) {

    }

    private static void configureResponse(final ResponseTypes type, final HttpServletResponse response, final Object responseData) {
        Preconditions.checkNotNull(response, "The provided response is invalid");
        Preconditions.checkNotNull(responseData, "The provided data is invalid");

        try {
            switch (type) {
                case JSON_OBJECT:
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(responseData.toString());
                    break;
                case JSON_ARRAY:
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(responseData.toString());
                    break;
                case PLAINTEXT:
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(responseData.toString());
                    break;
                case HTML:
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(responseData.toString());
                    break;
                default:
                    throw new RuntimeException("Unsupported response type encountered");
            }

            if (response.getWriter().checkError()) {
                throw new RuntimeException();
            }
        } catch (IOException e) {

        }
    }
}
