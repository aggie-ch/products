package io.github.aggie.users;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersConfiguration {

    @Bean
    public UserService userService(UsersRepository usersRepository, UserMapper userMapper) {
        return new UserService(usersRepository, userMapper);
    }
}
