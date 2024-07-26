package vn.com.lol.repilcatedb.configs.datasource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DatabaseConfig {
    private DatasourceProperties master;
    private List<DatasourceProperties> slaves;
}
