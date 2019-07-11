package ru.relex.hotelteam.db;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("db.properties")
@MapperScan("ru.relex.hotelteam.db.mapper")
public class DbConfiguration {
}
