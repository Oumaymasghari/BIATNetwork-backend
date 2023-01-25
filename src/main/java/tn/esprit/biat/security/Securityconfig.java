package tn.esprit.biat.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tn.esprit.biat.service.IpersonneService;

@Configuration

public class Securityconfig extends WebSecurityConfigurerAdapter {

   @Autowired
   IpersonneService personneService ;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //		ci-dessous une manière simple de stocker les utilisateurs en mémoire.
        //		connu sous le nom de inMemoryAuthentication
        //		mais c'est pas pratique.
//		auth.inMemoryAuthentication().withUser("Mahdi").password("Mahdi123").roles("USER");
//		auth.inMemoryAuthentication().withUser("Achref").password("Achref123").roles("USER");
//		auth.inMemoryAuthentication().withUser("Boucetta").password("Boucetta123").roles("ADMIN","USER");

        auth.userDetailsService(personneService).passwordEncoder(passwordEncoder());
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //		Puisque on va utiliser Spring security avec le jwt token càd authentification stateless,
        //		nous devons désactiver le mécanisme de protection contre les attaques CSRF
        http.authorizeRequests().antMatchers("/registration").permitAll();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();

        http.authorizeRequests().antMatchers("admin/sayHello").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("user/sayHello").hasRole("USER");
        http.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll();
        http.authorizeRequests().anyRequest().authenticated();

//        http.httpBasic().and().csrf().disable();

        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
        http.addFilterBefore(new JWTAutorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }


    //	 Au début, les mots de passe étaient stockés en texte clair.
    //	 Puis ils ont été stockés après un hachage à sens unique tel que SHA-256.
    //	 Par la suite, on se rend compte que les hachages cryptographiques(comme SHA-256) ne sont plus sûrs.
    //	 ils ont donc migré vers d'autres algorithmes comme BCrypt.(pourtant il ralentit le calcul, et donc les performances de l'application)
    @Bean
    public PasswordEncoder passwordEncoder() {
        //		le PasswordEncoder par défaut est maintenant BCryptPasswordEncoder
//				PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
        //		Mais on aura toujours ce soucis est-ce que le chiffrement des mots de passe est toujours réalisé selon les recommandations actuelles ou pas ?.
        //		Pour cela, spring Security introduit DelegatingPasswordEncoder qui résout ce problème.
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return passwordEncoder;
    }


    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v3
            "/v3/api-docs/**",
            "/v2/api-docs", "/swagger-resources", "/swagger-resources/**",
            "/swagger-ui/**",
            // welcome test rest endpoints
            "/access/**","/login"

    };


}
