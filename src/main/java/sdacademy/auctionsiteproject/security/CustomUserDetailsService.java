package sdacademy.auctionsiteproject.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sdacademy.auctionsiteproject.entity.User;
import sdacademy.auctionsiteproject.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).get();

        if (user != null) {
            return new UserDetailsImpl(user);
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

}
