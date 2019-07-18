package ru.relex.hotelteam.db.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Author: Yakimov Date: 18.07.2019 Time: 11:17
 */
@Configuration
@Profile("dev")
@EnableTransactionManagement
@PropertySource("db-dev.properties")
@MapperScan("ru.relex.hotelteam.db.mapper")
public class DbDevConfiguration {

}
