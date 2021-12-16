package io.github.jx2lee.springmvccommon.web.filter;

import io.github.jx2lee.springmvccommon.web.login.LoginConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whiteList = {"/", "/login", "/login/loginForm", "/login/logout", "/members/add", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("인증 체크 필터 시작: {}", requestURI);
            if (isLoginCheckPath(requestURI)) {
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(LoginConst.LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청: {}", requestURI);
                    httpResponse.sendRedirect("/login/loginForm?redirectURL=" + requestURI);
                    return;
                }
            }
            chain.doFilter(request, response);

        } catch (Exception e) {
            throw e;

        } finally {
            log.info("인증 체크 필터 종료: {}", requestURI);
        }
    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
}
