package ua.kiev.prog;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MyController1 {

    static Map<String, String> map = new HashMap<String, String>();

    static {
        map.put("user1", "password1");
        map.put("user2", "password2");
    }

    @RequestMapping("/old")
    public String onIndex() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String onLogin(Model model,
                          @RequestParam String login,
                          @RequestParam String password,
                          HttpServletRequest request) {
        String pass = map.get(login);

        model.addAttribute("login", login);
        if (password.equals(pass)) {
            model.addAttribute("message", "Success");
        } else {
            model.addAttribute("message", "Failure");
        }

        return "result";
    }
}
