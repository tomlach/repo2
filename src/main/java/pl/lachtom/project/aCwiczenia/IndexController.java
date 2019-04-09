package pl.lachtom.project.aCwiczenia;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {

    @GetMapping("/index")
    public ModelAndView index (){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }


}
