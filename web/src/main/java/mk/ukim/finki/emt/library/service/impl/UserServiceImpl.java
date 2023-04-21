package mk.ukim.finki.emt.library.service.impl;

import mk.ukim.finki.emt.library.model.User;
import mk.ukim.finki.emt.library.model.enumerations.Role;
import mk.ukim.finki.emt.library.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.emt.library.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.emt.library.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.emt.library.repository.UserRepository;
import mk.ukim.finki.emt.library.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role userRole) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username,passwordEncoder.encode(password),name,surname,userRole);
        return userRepository.save(user);
    }

    @PostConstruct
    public void init() {
        this.register("librarian", "lib", "lib", "Librarian", "Librarian", Role.ROLE_LIBRARIAN);
        this.register("user", "user", "user", "User", "User", Role.ROLE_USER);
    }
}

