package basicauth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class BasicAuthenticationController {

    public BasicAuthenticationController() {
    }

    @GetMapping("/basicauth")
    public AuthenticationBean getAllTodos() {
        return new AuthenticationBean("You are authenticated");
    }

}
