package com.rarait.education.login.auth;

import com.rarait.education.login.resource.*;
import com.rarait.education.shared.route.BaseRoute;
import com.rarait.education.shared.route.InstituteRoute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping(value = BaseRoute.AUTH)
public class UserAuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Autowired
    public UserAuthController(AuthenticationManager authenticationManager,
                              JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody AuthRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            AuthResponse response = jwtProvider.getAuthToken(authentication);
            return ResponseEntity.ok(response);
        }

        @PostMapping("/signout")
        public ResponseEntity<LogoutResponse> logout(@RequestBody LogoutRequest request) {
            jwtProvider.logout(request.getAuthToken());
            return ResponseEntity.ok(new LogoutResponse("Logout successful"));
        }
//
//        @PostMapping(InstituteRoute.PDF)
//        public ResponseEntity<PdfResponse>printPdf(@RequestBody PdfRequest request){
//           jwtProvider.printPdf(request.getAuthToken());
//            return ResponseEntity.ok(new PdfResponse("Pdf Generated"));
//    }
}
