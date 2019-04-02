package pl.lachtom.project.Cwiczenia;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Welcome2Controller {

    @GetMapping("/welcome2")
    public ModelAndView welcome (@RequestParam int age){
        ModelAndView modelAndView = new ModelAndView("welcome2");
        modelAndView.addObject("age", age);
        return modelAndView;

    }


}
