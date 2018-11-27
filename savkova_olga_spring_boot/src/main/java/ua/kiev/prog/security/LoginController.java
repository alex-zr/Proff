package ua.kiev.prog.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/admin")
    public String adminPage() {
        return "admin";
    }
}
