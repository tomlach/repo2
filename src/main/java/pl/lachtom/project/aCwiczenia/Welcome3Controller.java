package pl.lachtom.project.aCwiczenia;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@RestController
public class Welcome3Controller {

    @GetMapping ("/checked")
    public ModelAndView user (){
        ModelAndView modelAndView = new ModelAndView("checked");
        modelAndView.addObject("users", Arrays.asList(new User ("Tom", "Lach", "1"),
                new User ("ktoś", "taki", "2"),
                new User("Ktosiek", "Cosiek", "3")));

        return modelAndView;
    }

}
