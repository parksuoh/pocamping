package com.camping.camping.applications;


import com.camping.camping.domains.User;
import com.camping.camping.dtos.LoginResponseDto;
import com.camping.camping.exceptions.NameAlreadyExist;
import com.camping.camping.exceptions.NameNotExist;
import com.camping.camping.exceptions.PasswordNotMatch;
import com.camping.camping.repositories.UserRepository;
import com.camping.camping.security.AccessTokenGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenGenerator accessTokenGenerator;

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder, AccessTokenGenerator accessTokenGenerator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.accessTokenGenerator = accessTokenGenerator;
    }

    public LoginResponseDto login(String name, String password){

        User user = userRepository
                .findByName(name)
                .orElseThrow(NameNotExist::new);


        boolean isPassMatch = passwordEncoder.matches(password, user.password());

        if(!isPassMatch){
            throw new PasswordNotMatch();
        }

        String accessToken = accessTokenGenerator.generate(user.name(), user.role());



        return new LoginResponseDto(user.name(), user.role().toString(), accessToken);
    }

}
