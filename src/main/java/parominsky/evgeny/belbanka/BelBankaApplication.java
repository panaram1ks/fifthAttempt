package parominsky.evgeny.belbanka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import parominsky.evgeny.belbanka.dto.UserDTO;
import parominsky.evgeny.belbanka.entity.User;
import parominsky.evgeny.belbanka.service.UserService;

import javax.annotation.PostConstruct;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@EnableJpaRepositories("parominsky.evgeny.belbanka.repository")
@EnableTransactionManagement
public class BelBankaApplication {

    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public BelBankaApplication(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        String name = "admin";
        String password = "admin";
        if (userService.getUser(name) == null) {
            UserDTO userDTO = new UserDTO(
                    name,
                    passwordEncoder.encode(password)
            );
            User user = userService.createAdmin(userDTO);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(BelBankaApplication.class, args);
    }

}
