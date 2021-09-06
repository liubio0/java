package jdbc.liuzm.homework;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

/**
 * @Author: liuzm
 * @Date: 2021-09-06 22:06:40
 * @Description: jdbc.liuzm.homework
 * @version: 1.1
 */
public class OperateDatabase {
    public boolean loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("找不到jdbc驱动程序类，加载失败！");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Connection getConnFromHikari() throws Exception{
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("123456");
        hikariConfig.setMaximumPoolSize(20);

        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        return hikariDataSource.getConnection();
    }

    public static void main(String[] args) throws Exception{
        OperateDatabase operateDatabase = new OperateDatabase();


        //连接数据库
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "123456";
        try {
//            if (operateDatabase.loadDriver()) {
//                System.out.println("load jdbc driver success!");
//            }
//            Connection connection = DriverManager.getConnection(url, username, password);
            Connection connection = operateDatabase.getConnFromHikari();
            Statement stmt = connection.createStatement();
            String sql_drop = "drop table person";
            String sql_create = "create table person(name varchar(30),age int)";
            String sql_insert = "insert into person values (\"张三\",\"10\"),(\"李四\",\"20\"),(\"王五\",\"30\")";
            String sql_update = "update person set name=\"张三u\" where name=\"张三\"";
            String sql_delete = "delete from person where name=\"李四\"";

            stmt.executeUpdate(sql_drop);
            stmt.executeUpdate(sql_create);
            stmt.executeUpdate(sql_insert);
            stmt.executeUpdate(sql_update);
            stmt.executeUpdate(sql_delete);
            stmt.executeUpdate(sql_insert);

            ResultSet resultSet = stmt.executeQuery("select * from person");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt(2);
                System.out.println(name + " is " + age + " years old.");
            }
            resultSet.close();
            stmt.close();

            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement("insert into person values (?,?)");
            pstmt.setString(1, "赵六");
            pstmt.setInt(2, 40);
            pstmt.executeUpdate();

            pstmt.addBatch(sql_insert);
            pstmt.addBatch(sql_update);
            pstmt.addBatch(sql_delete);
            pstmt.executeBatch();
            connection.commit();//提交事务
            pstmt.close();
            connection.close();

        } catch (SQLException se){
            System.out.println("数据库连接失败");
            se.printStackTrace();
        }
    }
}
