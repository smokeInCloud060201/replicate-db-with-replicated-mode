//package vn.com.lol.repilcatedb.configs;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import vn.com.lol.repilcatedb.configs.datasource.ReplicationRoutingDataSource;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class ReplicationDataSourceConfig {
//
//    @Primary
//    @Bean
//    // @DependsOn required!! thanks to Michel Decima
//    @DependsOn({"writeDataSource", "readDataSource", "routingDataSource"})
//    public DataSource dataSource() {
//        return new LazyConnectionDataSourceProxy(routingDataSource());
//    }
//
//    @Bean
//    public DataSource routingDataSource() {
//        ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();
//
//        Map<Object, Object> dataSourceMap = new HashMap<>();
//        dataSourceMap.put("write", writeDataSource());
//        dataSourceMap.put("read", readDataSource());
//        routingDataSource.setTargetDataSources(dataSourceMap);
//        routingDataSource.setDefaultTargetDataSource(writeDataSource());
//
//        return routingDataSource;
//    }
//
//    @Bean(destroyMethod = "shutdown")
//    public DataSource writeDataSource() {
//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder()
//                .setName("lazyWriteDb")
//                .setType(EmbeddedDatabaseType.H2)
//                .setScriptEncoding("UTF-8")
//                .addScript("classpath:/writedb.sql");
//        return builder.build();
//    }
//
//    @Bean(destroyMethod = "shutdown")
//    public DataSource readDataSource() {
//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder()
//                .setName("lazyReadDb")
//                .setType(EmbeddedDatabaseType.H2)
//                .setScriptEncoding("UTF-8")
//                .addScript("classpath:/readdb.sql");
//        return builder.build();
//    }
//}
