package pl.lachtom.project.PromoCampaign;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pl.lachtom.project.Customer.CustomerDto;

import java.security.Principal;
import java.util.List;


@RestController
public class PromoCampaignController {
    @Configuration
    public class DateTimeFormatConfiguration extends WebMvcConfigurerAdapter {

        @Override
        public void addFormatters(FormatterRegistry registry) {
            DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
            registrar.setUseIsoFormat(true);
            registrar.registerFormatters(registry);
        }
    }

    @GetMapping("/campaigns")
    public ModelAndView registerPage(){
        return new ModelAndView("Register-Campaign")
                .addObject("promo", new PromoDto());
    }

    @PostMapping("/campaigns")
    public ModelAndView register(@ModelAttribute PromoDto promo, Principal principal){

        RestTemplate template = new RestTemplate();
        String loggedUserEmail = principal.getName();

        ResponseEntity <CustomerDto> loggedCustomerResponse = template.getForEntity("http://localhost:8080/customers/"+loggedUserEmail, CustomerDto.class);

        CustomerDto loggedCustomer = loggedCustomerResponse.getBody();
        promo.setCustomerID(loggedCustomer.getId());
        promo.setBrand(loggedCustomer.getBrand());

        ResponseEntity<PromoDto> response = template.postForEntity("http://localhost:8080/campaigns", promo, PromoDto.class);
        if (response.getStatusCode().is2xxSuccessful()){
            return  new ModelAndView("register-succes");
        } else {
            return  new ModelAndView("Register-Campaign")
                    .addObject("promo", promo);
        }
    }

    @GetMapping("/campaigns/all")
    ModelAndView allCampaign() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List> result = restTemplate.getForEntity("http://localhost:8080/campaigns", List.class);
        return new ModelAndView("showAllCampaigns")
                .addObject("campaigns", result.getBody());
    }

}
