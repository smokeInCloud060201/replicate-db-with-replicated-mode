package vn.com.lol.repilcatedb.configs.datasource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DatasourceProperties {
    private String host;
    private int port;
    private String username;
    private String password;
}
