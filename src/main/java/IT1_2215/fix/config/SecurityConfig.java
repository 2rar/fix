package IT1_2215.fix.config;

import IT1_2215.fix.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/register", "/api/register", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Только для админов
                        .requestMatchers("/dev/**").hasRole("DEV")   // Только для разработчиков
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN", "DEV") // Доступ для пользователей
                        .requestMatchers("/").hasAnyRole("USER", "ADMIN", "DEV") // Главная страница
                        .requestMatchers("/smartphones").hasAnyRole("ADMIN", "DEV") // Страница смартфонов
                        .requestMatchers("/smartphones/new").hasAnyRole("ADMIN", "DEV") // Добавление нового смартфона
                        .requestMatchers("/smartphones/{id}/edit").hasAnyRole("ADMIN", "DEV") // Редактирование смартфона
                        .requestMatchers("/users").hasRole("ADMIN") // Страница пользователей доступна только администраторам
                        .anyRequest().authenticated()  // Все остальные запросы требуют аутентификации
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/access-denied")  // Обрабатываем ошибку доступа 403
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Страница логина
                        .defaultSuccessUrl("/", true) // Перенаправление после успешного входа
                        .permitAll()  // Разрешаем доступ ко всем
                )
                .logout(logout -> logout
                        .permitAll()  // Разрешаем выход
                )
                .csrf(csrf -> csrf.disable()); // Отключение CSRF для упрощения работы (лучше включить для продакшн)

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Шифрование пароля с использованием BCrypt
    }
}
