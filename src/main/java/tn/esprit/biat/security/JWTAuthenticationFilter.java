package tn.esprit.biat.security;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tn.esprit.biat.Entity.Personne;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        Personne appUser = new Personne();

        try {
            appUser = new ObjectMapper().readValue(request.getInputStream(), Personne.class);
        } catch (IOException e) {

            e.printStackTrace();
        }

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(appUser.getEmail(),appUser.getPassword()));


    }



    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        User springUser = (User) authResult.getPrincipal();

        List<String> roles = new ArrayList<>();
        authResult.getAuthorities().forEach(a->{roles.add(a.getAuthority());});


        String jwtToken= JWT.create()
                .withIssuer(request.getRequestURI())
                .withSubject(springUser.getUsername())
                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                .withExpiresAt(new Date(System.currentTimeMillis()+ SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SecurityConstants.SECRET));

        System.out.println(" Token  "+jwtToken );

        response.addHeader("authorization" , jwtToken);


    }




}
