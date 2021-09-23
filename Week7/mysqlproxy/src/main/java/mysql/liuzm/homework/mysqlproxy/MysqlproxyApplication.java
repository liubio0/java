package mysql.liuzm.homework.mysqlproxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class MysqlproxyApplication {


    public static void main(String[] args) {
        SpringApplication.run(MysqlproxyApplication.class, args);
        MysqlproxyApplication mysqlproxyApplication = new MysqlproxyApplication();

    }

}
