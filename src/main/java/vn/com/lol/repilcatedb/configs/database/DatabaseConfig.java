package vn.com.lol.repilcatedb.configs.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static vn.com.lol.repilcatedb.constants.DatasourceConstant.READ_DATASOURCE;
import static vn.com.lol.repilcatedb.constants.DatasourceConstant.ROUTING_DATASOURCE;
import static vn.com.lol.repilcatedb.constants.DatasourceConstant.WRITE_DATASOURCE;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "EntityManagerFactory",
        transactionManagerRef = "TransactionManager",basePackages = {"vn.com.lol.repilcatedb"})
@RequiredArgsConstructor
public class DatabaseConfig {

    @Bean(READ_DATASOURCE)
    public DataSource readDataSource() {
        databaseConfig.get
        HikariConfig config = new HikariConfig();
        config.setConnectionTimeout(
                Long.parseLong(env.getProperty("read.datasource.hikari.connection-timeout")));
        config.setJdbcUrl(env.getProperty("read.datasource.url"));
        config.setMaximumPoolSize(
                Integer.parseInt(env.getProperty("read.datasource.hikari.maximum-pool-size")));
        config.setMinimumIdle(Integer.parseInt(env.getProperty("read.datasource.hikari.minimum-idle")));
        config.setUsername(env.getProperty("read.datasource.username"));
        config.setPassword(env.getProperty("read.datasource.password"));
        return new HikariDataSource(config);
    }

    @Bean(WRITE_DATASOURCE)
    @Primary
    public DataSource writeDataSource() {
        HikariConfig config = new HikariConfig ();
        config.setConnectionTimeout(
                Long.parseLong(env.getProperty("write.datasource.hikari.connection-timeout")));
        config.setJdbcUrl(env.getProperty("write.datasource.url"));
        config.setMaximumPoolSize(
                Integer.parseInt(env.getProperty("write.datasource.hikari.maximum-pool-size")));
        config.setMinimumIdle(Integer.parseInt(env.getProperty("write.datasource.hikari.minimum-idle")));
        config.setUsername(env.getProperty("write.datasource.username"));
        config.setPassword(env.getProperty("write.datasource.password"));
        return new HikariDataSource(config);
    }

    @Bean(ROUTING_DATASOURCE)
    public DataSource routingDataSource(
            @Qualifier(WRITE_DATASOURCE) DataSource writeDataSource,
            @Qualifier(READ_DATASOURCE) DataSource readDataSource) {
        ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<Object, Object>();
        dataSourceMap.put(DataSourceType.READ_WRITE, writeDataSource);
        dataSourceMap.put(DataSourceType.READ_ONLY, readDataSource);
        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(writeDataSource);
        return routingDataSource;
    }

    @Bean
    @DependsOn({WRITE_DATASOURCE, READ_DATASOURCE, ROUTING_DATASOURCE})
    public DataSource dataSource(@Qualifier(ROUTING_DATASOURCE) DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }



    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabasePlatform(env.getProperty("spring.jpa.database-platform"));
        adapter.setDatabase(org.springframework.orm.jpa.vendor.Database.MYSQL);
        adapter.setShowSql(Boolean.parseBoolean(env.getProperty("spring.jpa.show-sql")));
        adapter.setGenerateDdl(Boolean.parseBoolean(env.getProperty("spring.jpa.generate-ddl")));
        return adapter;
    }

    @Bean(name = "EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean EntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("dataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean =
                builder.dataSource(dataSource).packages("com.saurav.routing.db").persistenceUnit("dynamicdb")
                        .build();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter());

        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto",
                env.getProperty("spring.jpa.hibernate.ddl-auto"));
        localContainerEntityManagerFactoryBean.setJpaProperties(properties);
        return localContainerEntityManagerFactoryBean;
    }


    @Bean("TransactionManager")
    public PlatformTransactionManager contentTransactionManager(
            @Qualifier("EntityManagerFactory") EntityManagerFactory EntityManagerFactory) {
        return new JpaTransactionManager(EntityManagerFactory);
    }
}
