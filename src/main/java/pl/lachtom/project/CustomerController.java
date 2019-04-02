package pl.lachtom.project;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CustomerController {

    @GetMapping("/customers")
    public ModelAndView registerPage() {
        return new ModelAndView("Register-Form")
                .addObject("customer", new CustomerDto());
    }

    @PostMapping("/customers")
    public ModelAndView register(@ModelAttribute CustomerDto customer) {

        RestTemplate template = new RestTemplate();
        ResponseEntity<CustomerDto> response = template.postForEntity("http://localhost:8080/customers", customer, CustomerDto.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return new ModelAndView("register-succes");
        } else {
            return new ModelAndView("Register-Form")
                    .addObject("customer", customer);
        }
    }

}
