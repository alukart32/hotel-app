package ru.relex.hotelteam.db.configuration;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@Profile("prod")
@EnableTransactionManagement
@PropertySource("db.properties")
@MapperScan("ru.relex.hotelteam.db.mapper")
public class DbConfiguration {
}
