package IT1_2215.fix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/aboutus")
    public String aboutus() {
        return "about";
    }

    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }
    @RequestMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";  // Название вашего HTML-шаблона
    }
}
