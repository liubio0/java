package mysql.liuzm.homework.mysqlproxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class MysqlproxyApplicationTests {

    @Autowired
    @Qualifier("masterJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("slaveJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;

    @Test
    void contextLoads() throws Exception{
        insert();
    }

    public void insert() throws Exception {
        jdbcTemplate1.update("insert into t1(no) values (?)", 1);
        jdbcTemplate2.update("insert into t1(no) values (?)", 2);
    }

}
