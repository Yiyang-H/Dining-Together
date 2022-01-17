package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.dto.LoginInput;
import com.summerHack.diningTogether.dto.RegisterInput;
import com.summerHack.diningTogether.dto.UserDTO;
import com.summerHack.diningTogether.exceptions.UserAlreadyExistException;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.model.UserDetails;
import com.summerHack.diningTogether.service.UserService;
import com.summerHack.diningTogether.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication")
@RestController
@RequestMapping(path = "/api/v1/auth")
@Validated
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final ModelMapper mapper;
    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginInput request) {
        try {
            Authentication authenticate = authenticationManager
                .authenticate(
                    new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                    )
                );

            UserDetails user = (UserDetails) authenticate.getPrincipal();

            return ResponseEntity.ok()
                .header(
                    HttpHeaders.AUTHORIZATION,
                    jwtTokenUtil.generateToken(user)
                )
                .body(mapper.map(user, UserDTO.class));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    @ApiResponse(description = "Succeed", responseCode = "201")
    @ApiResponse(description = "Failed, username or email already exist", responseCode = "409")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterInput input) {
        try {
            final User user = userService.registerUser(input);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.map(user, UserDTO.class));

        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
