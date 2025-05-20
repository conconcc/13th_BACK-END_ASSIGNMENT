package com.conconcc.likelionweek4.Service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.conconcc.likelionweek4.Dto.UserCreationRequestDto;
import com.conconcc.likelionweek4.Dto.UserRegisterRequestDto;
import com.conconcc.likelionweek4.Dto.UserRegisterResponseDto;
import com.conconcc.likelionweek4.Dto.UserResponseDto;
import com.conconcc.likelionweek4.Entity.UserEntity;
import com.conconcc.likelionweek4.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto createUser(UserCreationRequestDto userDto) {
        UserEntity newUser = new UserEntity(userDto.getUsername());
        UserEntity savedUser = userRepository.save(newUser);

        return new UserResponseDto(savedUser.getId(), savedUser.getUsername());
    }

    public UserRegisterResponseDto signUpUser(UserRegisterRequestDto userDto) {

        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("존재하는 id입니다.");
        }
        if(!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        UserEntity userEntity = new UserEntity(userDto.getUsername(), encodedPassword);
        UserEntity savedUser= userRepository.save(userEntity);
        return new UserRegisterResponseDto(savedUser.getId(), savedUser.getUsername());
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers(){
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(user->new UserResponseDto(user.getId(), user.getUsername()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUserByIdAndPassword(Long userId, String password) {
        UserEntity user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("사용자 찾지 못함: "+userId));
        if(!user.getPassword().equals(password)){
            throw new RuntimeException("비밀번호가 맞지 않음 : ");
        }
        return new UserResponseDto(user.getId(), user.getUsername());
    }
    public void deleteUser(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("사용자 찾지 못함: "+userId));
        userRepository.deleteById(userId);
    }
    @Transactional(readOnly = true)
    public boolean authenticateUser(Long userId, String password) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        System.out.println(password);
        System.out.println(user.getPassword());
        System.out.println(passwordEncoder.encode(user.getPassword()));
        return passwordEncoder.matches(password, user.getPassword());

    }

        public UserResponseDto getUserById(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("사용자 찾지 못함: "+userId));
        return new UserResponseDto(user.getId(), user.getUsername());
    }
}
