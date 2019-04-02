package pl.lachtom.project.Cwiczenia;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class CheckedController {

    @PostMapping("/checked")
    public ModelAndView user (@RequestParam("idChecked") List<String> idUsers){
        System.out.println(idUsers);
        ModelAndView modelAndView = new ModelAndView("index");

        return modelAndView;
    }
}
