package com.marin.usersms.service;

import com.marin.usersms.entity.UserEntity;
import com.marin.usersms.repository.UsersRepository;
import com.marin.usersms.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    UsersRepository usersRepository;
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDto createUser(UserDto userDto) {

        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

        usersRepository.save(userEntity);

        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);

        return returnValue;
    }

}
