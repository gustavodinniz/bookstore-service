package com.gustavodinniz.bookstore_service.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Component
public class RequestIdFilter implements Filter {

    private static final String REQUEST_ID_KEY = "requestId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            var requestId = generateRequestId();
            MDC.put(REQUEST_ID_KEY, requestId);

            if (servletResponse instanceof HttpServletResponse httpResponse) {
                httpResponse.setHeader("X-Request-ID", requestId);
            }

            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.remove(REQUEST_ID_KEY);
        }
    }

    private String generateRequestId() {
        try {
            var uuid = UUID.randomUUID().toString();
            var messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(uuid.getBytes(StandardCharsets.UTF_8));

            var hexString = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                var hex = Integer.toHexString(0xff & hashBytes[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating request ID", e);
        }
    }
}
