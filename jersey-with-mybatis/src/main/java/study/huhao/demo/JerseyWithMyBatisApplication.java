package study.huhao.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JerseyWithMyBatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(JerseyWithMyBatisApplication.class, args);
    }

}
