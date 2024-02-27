package stu.common.common;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.owasp.esapi.ESAPI;
import org.springframework.web.filter.OncePerRequestFilter;

@WebFilter("/*")
public class XSSFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestMethod = request.getMethod();

        if (requestMethod.equalsIgnoreCase("POST") || requestMethod.equalsIgnoreCase("PUT")) {
            // POST 또는 PUT 요청의 경우에만 XSS 필터링을 수행합니다.
            String filteredRequestBody = filterRequestBody(request);
            XSSRequestWrapper wrappedRequest = new XSSRequestWrapper(request, filteredRequestBody);
            filterChain.doFilter(wrappedRequest, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private String filterRequestBody(HttpServletRequest request) {
        try {
            // 요청 본문을 ESAPI를 사용하여 안전한 캐노니컬화 수행
            String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            String canonicalizedInput = ESAPI.encoder().canonicalize(requestBody);

            // Jsoup을 사용하여 안전한 HTML을 유지하면서 XSS 필터링 수행
            String filteredInput = Jsoup.clean(canonicalizedInput, Safelist.none());

            return filteredInput;
            
        } catch (IOException ioe) {
            // 예외 처리를 수행하거나 로깅할 수 있습니다.
            ioe.printStackTrace();
            return null;
        }
    }
}
