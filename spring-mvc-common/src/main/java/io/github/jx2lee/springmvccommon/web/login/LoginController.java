package io.github.jx2lee.springmvccommon.web.login;

import io.github.jx2lee.springmvccommon.domain.login.LoginService;
import io.github.jx2lee.springmvccommon.domain.member.Member;
import io.github.jx2lee.springmvccommon.domain.member.MemberRepository;
import io.github.jx2lee.springmvccommon.web.argumentresolver.Login;
import io.github.jx2lee.springmvccommon.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    // @GetMapping("/login")
    public String homeLoginV1(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
        if (memberId == null) {
            return "/login/home";
        }

        // 로그인
        Member loginMember = memberRepository.findById(memberId);
        if (loginMember == null) {
            return "/login/home";
        }

        model.addAttribute("member", loginMember);
        return "/login/loginHome";
    }

    // @GetMapping("/login")
    public String homeLoginV2(HttpServletRequest request, Model model) {
        // 세션 관리자에 저장된 회원정보를 조회한다.
        Member loginMember = (Member) sessionManager.getSession(request);

        // 로그인
        if (loginMember == null) {
            return "/login/home";
        }

        model.addAttribute("member", loginMember);
        return "/login/loginHome";
    }

    // @GetMapping("/login")
    public String homeLoginV3(HttpServletRequest request, Model model) {

        // 세션이 없다면 로그인 홈 화면을 노출한다.
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "/login/home";
        }

        // 로그인
        Member loginMember = (Member) session.getAttribute(LoginConst.LOGIN_MEMBER);
        if (loginMember == null) {
            return "/login/home";
        }

        model.addAttribute("member", loginMember);
        return "/login/loginHome";
    }

    // @GetMapping("/login")
    public String homeLoginV3Spring(
            @SessionAttribute(name = LoginConst.LOGIN_MEMBER, required = false) Member loginMember,
            Model model) {

        // 세션이 없다면 로그인 홈 화면을 노출한다.
        if (loginMember == null) {
            return "/login/home";
        }

        model.addAttribute("member", loginMember);
        return "/login/loginHome";
    }

    @GetMapping("/login")
    public String homeLoginV3ArgumentResolver(
            @Login Member loginMember,
            Model model) {

        // 세션이 없다면 로그인 홈 화면을 노출한다.
        if (loginMember == null) {
            return "/login/home";
        }

        model.addAttribute("member", loginMember);
        return "/login/loginHome";
    }

    @GetMapping("/login/loginForm")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "/login/loginForm";
    }

    // @PostMapping("/login/loginForm")
    public String loginV1(@Validated @ModelAttribute LoginForm form,
                        BindingResult bindingResult,
                        HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            return "/login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("login? {}", loginMember);

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
            return "login/loginForm";
        }

        // 로그인 성공 처리
        // Cookie 생성
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);
        return "redirect:/login";
    }

    // @PostMapping("/login/loginForm")
    public String loginV2(@Validated @ModelAttribute LoginForm form,
                        BindingResult bindingResult,
                        HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            return "/login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("login? {}", loginMember);

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
            return "login/loginForm";
        }

        // 로그인 성공 처리
        // 세션 관리자를 통해 세션을 생성하고 회원 데이터를 보관한다.
        sessionManager.createSession(loginMember, response);

        return "redirect:/login";
    }

    // @PostMapping("/login/loginForm")
    public String loginV3(@Validated @ModelAttribute LoginForm form,
                        BindingResult bindingResult,
                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "/login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("login? {}", loginMember);

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
            return "login/loginForm";
        }

        // 로그인 성공 처리
        // 세션이 있으면 세션을 반환하고 없다면 신규 세션 생성
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 정보를 보관한다.
        session.setAttribute(LoginConst.LOGIN_MEMBER, loginMember);

        return "redirect:/login";
    }

    @PostMapping("/login/loginForm")
    public String loginV4(@Validated @ModelAttribute LoginForm form,
                        @RequestParam(defaultValue = "/login") String redirectURL,
                        BindingResult bindingResult,
                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "/login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("login? {}", loginMember);

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
            return "login/loginForm";
        }

        // 로그인 성공 처리
        // 세션이 있으면 세션을 반환하고 없다면 신규 세션 생성
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 정보를 보관한다.
        session.setAttribute(LoginConst.LOGIN_MEMBER, loginMember);

        return "redirect:" + redirectURL;
    }

    // @PostMapping("/login/logout")
    public String logoutV1(HttpServletResponse response) {
        expireCookie(response, "memberId");
        return "redirect:/login";
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }


    // @PostMapping("/login/logout")
    public String logoutV2(HttpServletRequest request) {
        sessionManager.expire(request);
        return "redirect:/login";
    }

    @PostMapping("/login/logout")
    public String logoutV3(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
}
