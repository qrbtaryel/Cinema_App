package org.example.cinema.mapper;

import javax.annotation.processing.Generated;
import org.example.cinema.dto.request.UserRequest;
import org.example.cinema.dto.response.UserResponse;
import org.example.cinema.model.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-05T18:00:40+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.surname( userRequest.getSurname() );
        user.name( userRequest.getName() );
        user.patronymic( userRequest.getPatronymic() );
        user.username( userRequest.getUsername() );
        user.password( userRequest.getPassword() );
        user.birthdate( userRequest.getBirthdate() );

        return user.build();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setName( user.getName() );
        userResponse.setSurname( user.getSurname() );
        userResponse.setPatronymic( user.getPatronymic() );
        userResponse.setUsername( user.getUsername() );
        userResponse.setBalance( user.getBalance() );

        return userResponse;
    }
}
