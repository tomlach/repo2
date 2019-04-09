package pl.lachtom.project.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import pl.lachtom.project.Customer.CustomerDto;

import java.util.Collections;
import java.util.List;

public class LoginUser extends User {

    public LoginUser(CustomerDto customer, List<GrantedAuthority> grantedAuthorityList) {
        super(customer.getEmail(),customer.getPassword(), grantedAuthorityList);
    }
}
