package context;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repositories.AuthRepository;
import repositories.ProductRepository;
import repositories.UsersRepository;
import services.ProductService;
import services.UserService;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class JavaConfig {

    private static final String DATABASE_NAME = "gamestore";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "r1a2z3i4l5";

//    @Value("${repository.product}")
//    private String productRepositoryName;
//
//    @Value("${repository.auth}")
//    private String authRepositoryName;
//
//    @Value("${repository.users}")
//    private String userRepositoryName;
//
//    @Value("${service.product}")
//    private String productServiceName;
//
//    @Value("${service.user}")
//    private String userServiceName;

    @Bean
    @SneakyThrows
    public DriverManagerDataSource dataSource(){
        Class.forName("org.postgresql.Driver");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/" + DATABASE_NAME);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthRepository authRepository(){
        return new AuthRepository(dataSource());
    }
    @Bean
    public ProductRepository productRepository(){
        return new ProductRepository(dataSource());
    }
    @Bean
    public UsersRepository usersRepository(){
        return new UsersRepository(dataSource());
    }
    @Bean
    public ProductService productService(){
        return new ProductService(productRepository());
    }
    @Bean
    public UserService userService(){
        return new UserService(usersRepository(), authRepository(), passwordEncoder());
    }

}