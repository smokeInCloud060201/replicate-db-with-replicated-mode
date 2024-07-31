package vn.com.smoke.masterservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class MasterServiceApplication implements CommandLineRunner {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(MasterServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
    }
}
