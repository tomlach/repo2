package pl.lachtom.project.aCwiczenia;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public ModelAndView welcome (@RequestParam String name, @RequestParam String lastName){
        ModelAndView modelAndView = new ModelAndView("welcome");
        modelAndView.addObject("user", new User (name, lastName));

        return modelAndView;
    }
}
