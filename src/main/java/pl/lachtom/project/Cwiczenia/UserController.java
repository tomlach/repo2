package pl.lachtom.project.Cwiczenia;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private List<User> users = new ArrayList<>();

    @GetMapping()
    public ModelAndView userPage(){
        return new ModelAndView("welcome4").addObject("user", new User());
    }

    @PostMapping(value = "/save")
    public ModelAndView user (@ModelAttribute User user){
        users.add(user);

        ModelAndView modelAndView = new ModelAndView("welcome");
        modelAndView.addObject("user", user);

        return modelAndView;
    }


}
