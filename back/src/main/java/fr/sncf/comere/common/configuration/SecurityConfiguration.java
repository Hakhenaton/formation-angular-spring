package fr.sncf.comere.common.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
/** Sert à activer les annotations de type @PreAuthorize  */
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final CustomUserDetailsService customUserDetailsService;
    
    /**
     * Ici, on customise la chaîne de filtres de Spring Security.
     * Pour mieux comprendre ce qu'est la chaîne de filtres, il faut lire la
     * documentation de Spring Security dans la partie portant sur l'architecture
     * du module.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        return http
            /**
             * Indique à Spring Security qu'il faut systématiquement créer une session
             * si aucun cookie n'est passé par l'utilisateur.
             */
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
            /**
             * Ici on désactive un mécanisme de protection de Spring Security contre les 
             * attaques dites "CSRF".
             * C'est temporaire, évidemment.
             */
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(this.corsConfigurationSource()))
            /**
             * On désactive le système par défaut de login de Spring Security (via un formulaire HTML)
             */
            .formLogin(formLogin -> formLogin.disable())
            /**
             * On active le filtre d'authentification avec HTTP Basic.
             */
            .httpBasic()
            .and()
            /**
             * On fournit notre implémentation personnalisé d'un UserDetailsService
             * pour que Spring Security puisse s'en servir via la méthode loadByUsername
             */
            .userDetailsService(this.customUserDetailsService)
            /**
             * Ici, on fait du contrôle d'accès très général.
             * C'est notre première couche de contrôle, elle s'applique vis à vis des chemins HTTP
             * et des méthodes utilisées.
             * Par défaut, on interdit tout.
             */
            .authorizeHttpRequests()
            .requestMatchers("/auth")
            .permitAll()
            .requestMatchers(HttpMethod.POST, "/api/users")
            .permitAll()
            .anyRequest()
            .denyAll()
            .and()
            .build();
    }

    /**
     * Spring a besoin d'un PasswordEncoder pour réaliser 
     * l'authentification via un mot de passe (c'est le cas pour HTTP Basic).
     * On utilisera aussi ce bean pour hasher nos mot de passe dans la partie
     * inscription.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        final var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowCredentials(true);
        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
