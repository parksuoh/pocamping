package com.camping.camping.applications;


import com.camping.camping.domains.Cart;
import com.camping.camping.domains.User;
import com.camping.camping.exceptions.NameAlreadyExist;
import com.camping.camping.repositories.CartRepository;
import com.camping.camping.repositories.UserRepository;
import com.camping.camping.security.AccessTokenGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.camping.camping.domains.vo.Role.ROLE_USER;

@Service
@Transactional
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenGenerator accessTokenGenerator;
    private final CartRepository cartRepository;

    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder, AccessTokenGenerator accessTokenGenerator, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.accessTokenGenerator = accessTokenGenerator;
        this.cartRepository = cartRepository;
    }


    public String register(String name, String password) {

        if(userRepository.existsByName(name)) {
            throw new NameAlreadyExist(name);
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(name, encodedPassword, ROLE_USER);
        userRepository.save(user);

        Cart newCart = new Cart(user);
        cartRepository.save(newCart);

        String accessToken = accessTokenGenerator.generate(user.name(), user.role());

        return accessToken;

    }
}
