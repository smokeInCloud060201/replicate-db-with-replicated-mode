package vn.com.lol.repilcatedb.configs.datasource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.config")
@Getter
@Setter
public class AppConfig {
    private DatabaseConfig database;
}
