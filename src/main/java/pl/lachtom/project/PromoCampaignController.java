package pl.lachtom.project;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PromoCampaignController {

    @GetMapping("/campaigns")
    public ModelAndView registerPage(){
        return new ModelAndView("Register-Campaign")
                .addObject("promo", new PromoDto());
    }
    @PostMapping("/campaigns")
    public ModelAndView register(@ModelAttribute PromoDto promo){

        RestTemplate template = new RestTemplate();
        ResponseEntity<PromoDto> response = template.postForEntity("http://localhost:8080/campaigns", promo, PromoDto.class);
        if (response.getStatusCode().is2xxSuccessful()){
            return  new ModelAndView("register-succes");
        } else {
            return  new ModelAndView("Register-Campaign")
                    .addObject("promo", promo);
        }

    }
}
