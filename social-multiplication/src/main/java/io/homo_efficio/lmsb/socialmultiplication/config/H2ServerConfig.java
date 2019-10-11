package io.homo_efficio.lmsb.socialmultiplication.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-09
 */
@Configuration
public class H2ServerConfig {

    @Bean
    public Server h2TcpServer() throws SQLException {
        return Server.createTcpServer().start();
    }
}
