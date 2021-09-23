package mysql.liuzm.homework.mysqlproxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MysqlproxyApplicationTests {

    @Autowired
    @Qualifier("masterJdbcTemplate")
    protected JdbcTemplate writeJdbcTemplate;

    @Autowired
    @Qualifier("slaveJdbcTemplate")
    protected JdbcTemplate readJdbcTemplate;

    @Test
    void contextLoads() {
        insert();
        read();
    }

    public void insert() {
        writeJdbcTemplate.update("insert into t1 values (?)", 6);
    }

    public void read(){
//        List result = readDataSource.query("select * from t1", new RowMapper<Map>() {
//            @Override
//            public Map mapRow(ResultSet rs, int rowNum) throws SQLException{
//                Map row = new HashMap();
//                row.put(rs.getInt("no"), rs.getInt("no"));
//                return row;
//            }});
        List result = readJdbcTemplate.queryForList("select * from t1");
        System.out.println(result);
    }

}
