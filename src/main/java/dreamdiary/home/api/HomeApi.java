package dreamdiary.home.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("home")
@RestController
public class HomeApi {

    @GetMapping
    public String home() {
        return "home";
    }
}
