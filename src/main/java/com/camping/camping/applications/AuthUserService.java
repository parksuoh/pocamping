package com.camping.camping.applications;

import com.camping.camping.domains.User;
import com.camping.camping.dtos.AuthInfoDto;
import com.camping.camping.dtos.AuthUserDto;
import com.camping.camping.exceptions.NameNotExist;
import com.camping.camping.exceptions.TokenNotAvailable;
import com.camping.camping.repositories.UserRepository;
import com.camping.camping.security.AccessTokenGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthUserService {

    private final AccessTokenGenerator accessTokenGenerator;
    private final UserRepository userRepository;


    public AuthUserService(AccessTokenGenerator accessTokenGenerator, UserRepository userRepository) {
        this.accessTokenGenerator = accessTokenGenerator;
        this.userRepository = userRepository;
    }


    public AuthUserDto auth(String token) {

        boolean isTokenOk = accessTokenGenerator.verify(token);

        if (!isTokenOk) {
            throw new TokenNotAvailable();
        }

        AuthInfoDto tokenInfo = accessTokenGenerator.getTokenInfo(token);

        User user = userRepository
                .findByName(tokenInfo.name())
                .orElseThrow(NameNotExist::new);

        String accessToken = accessTokenGenerator.generate(user.name(), user.role());

        return new AuthUserDto(user.name(), user.password(), accessToken);
    }
}
