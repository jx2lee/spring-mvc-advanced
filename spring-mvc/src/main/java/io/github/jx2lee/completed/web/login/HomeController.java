package io.github.jx2lee.completed.web.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/login")
public class HomeController {

    @GetMapping()
    public String home() {
        return "/login/home";
    }
}
