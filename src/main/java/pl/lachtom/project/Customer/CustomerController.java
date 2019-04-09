package pl.lachtom.project.Customer;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@RestController
public class CustomerController {

    private BCryptPasswordEncoder passwordEncoder;

    public CustomerController(BCryptPasswordEncoder passwordEncoder){
        this.passwordEncoder = Objects.requireNonNull(passwordEncoder, "password mus be definied");
    }

    @GetMapping("/customers")
    public ModelAndView registerPage() {
        return new ModelAndView("Register-Form")
                .addObject("customer", new CustomerDto());
    }

    @PostMapping("/customers")
    public ModelAndView register(@ModelAttribute CustomerDto customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));


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
