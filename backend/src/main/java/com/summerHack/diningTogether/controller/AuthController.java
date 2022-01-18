package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.config.JwtConstants;
import com.summerHack.diningTogether.dto.AuthorizeOutput;
import com.summerHack.diningTogether.dto.LoginInput;
import com.summerHack.diningTogether.dto.RegisterInput;
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
    private final JwtConstants jwtConstants;

    @PostMapping("login")
    public ResponseEntity<AuthorizeOutput> login(@RequestBody LoginInput request) {
        try {
            Authentication authenticate = authenticationManager
                .authenticate(
                    new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                    )
                );

            UserDetails user = (UserDetails) authenticate.getPrincipal();
            final AuthorizeOutput output = buildAuthorizeOutput(user);

            return ResponseEntity.ok()
                .header(
                    HttpHeaders.AUTHORIZATION,
                    output.getToken()
                )
                .body(output);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    @ApiResponse(description = "User Created", responseCode = "201")
    @ApiResponse(description = "Failed, username or email already exist", responseCode = "409")
    public ResponseEntity<AuthorizeOutput> register(@RequestBody RegisterInput input) {
        try {
            final User user = userService.registerUser(input);
            final AuthorizeOutput output = buildAuthorizeOutput(UserDetails.of(user));
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(output);

        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    private AuthorizeOutput buildAuthorizeOutput(UserDetails user) {
        final AuthorizeOutput output = new AuthorizeOutput();
        output.setUsername(user.getUsername());
        output.setToken(jwtTokenUtil.generateToken(user));
        output.setExpiresIn(jwtConstants.getAccessTokenValiditySeconds());
        return output;
    }
}
