package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.config.ApplicationProperties;
import com.summerHack.diningTogether.dto.AuthorizeOutput;
import com.summerHack.diningTogether.dto.LoginInput;
import com.summerHack.diningTogether.dto.RegisterInput;
import com.summerHack.diningTogether.dto.UserCodeDTO;
import com.summerHack.diningTogether.exceptions.DuplicatePhoneNumber;
import com.summerHack.diningTogether.exceptions.UserAlreadyExistException;
import com.summerHack.diningTogether.exceptions.UserCodeNotFoundException;
import com.summerHack.diningTogether.exceptions.UserNotFoundException;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.model.UserDetails;
import com.summerHack.diningTogether.repository.UserCodeRepository;
import com.summerHack.diningTogether.repository.UserRepository;
import com.summerHack.diningTogether.service.UserService;
import com.summerHack.diningTogether.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Tag(name = "Authentication")
@RestController
@RequestMapping(path = "/api/v1/auth")
@Validated
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final ApplicationProperties properties;
    private UserRepository userRepository;
    private UserCodeRepository userCodeRepository;
    @PostMapping("login")
    @ApiResponse(description = "Login successful", responseCode = "200")
    @ApiResponse(description = "Username or password incorrect", responseCode = "401")
    public AuthorizeOutput login(@RequestBody @Valid LoginInput request) {
        Authentication authenticate = authenticationManager
            .authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()
                )
            );

        UserDetails user = (UserDetails) authenticate.getPrincipal();
        return buildAuthorizeOutput(user);
    }

    @PostMapping("/register")
    @ApiResponse(description = "User Created", responseCode = "201")
    @ApiResponse(description = "Failed, username or email already exist", responseCode = "409")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody @Valid RegisterInput input,
                                    HttpServletRequest request)
            throws UserAlreadyExistException, MessagingException, UserCodeNotFoundException, UserNotFoundException, DuplicatePhoneNumber {

        final User user = userService.registerUser(input, getSiteURL(request));
        return user;
    }
    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    private AuthorizeOutput buildAuthorizeOutput(UserDetails user) {
        final AuthorizeOutput output = new AuthorizeOutput();
        output.setUsername(user.getUsername());
        output.setUserId(user.getId());
        output.setToken(jwtTokenUtil.generateToken(user));
        output.setExpiresIn(properties.getAccessTokenValiditySeconds());
        return output;
    }

    @GetMapping("/verify")
    @ApiResponse(description = "Verify results given", responseCode = "201")
    @ApiResponse(description = "User or usercode not exist", responseCode = "404")
    @ResponseStatus(HttpStatus.CREATED)
    public String verifyUser(@Param("code") String code, Model model)
            throws UserCodeNotFoundException, UserNotFoundException {

        UserCodeDTO userCode = userCodeRepository
                .findByCode(code);


        User user = userRepository
                .findById(userCode.getId())
                .orElseThrow(UserNotFoundException::new);

        Boolean verified = userService.verify(code);
        String pageTitle = verified? "Verification Succeed!": "Verification Failed!";
        model.addAttribute("pageTitle", pageTitle);
        if(verified) buildAuthorizeOutput(UserDetails.of(user));
        return "registration/" + (verified? "verify_success": "verify_failed");
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Username or password incorrect")
    public void handleBadCredentialException(BadCredentialsException e) {
    }
}
