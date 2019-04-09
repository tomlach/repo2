package pl.lachtom.project.Security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.lachtom.project.Customer.CustomerDto;
import pl.lachtom.project.Customer.Role;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoginUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CustomerDto> response = restTemplate.getForEntity("http://localhost:8080/customers/" +email, CustomerDto.class);
        if (response.getBody()==null){
            throw new UsernameNotFoundException("email " + email + " doesnt exist.");
        }

        return new LoginUser(response.getBody(), getGrantedAuthority(response.getBody().getRoles()));
    }

    public List<GrantedAuthority> getGrantedAuthority(Set<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

    }



}
