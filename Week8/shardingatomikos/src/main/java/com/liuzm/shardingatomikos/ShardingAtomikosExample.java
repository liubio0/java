package com.liuzm.shardingatomikos;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: liuzm
 * @Date: 2021-09-27 01:28:06
 * @Description: com.liuzm.shardingatomikos
 * @version: 1.1
 */
@ShardingTransactionType(TransactionType.XA)
public class ShardingAtomikosExample {
    /**
     * 生成DataSource，文件路径自行替换
     * @return
     * @throws IOException
     * @throws SQLException
     */
    static private DataSource getShardingDatasource() throws IOException, SQLException {
        String fileName = "D:\\projects\\java\\shardingatomikos\\src\\main\\resources\\sharding-databases-tables.yaml";
        File yamlFile = new File(fileName);
        return YamlShardingSphereDataSourceFactory.createDataSource(yamlFile);
    }


    public static void main(String[] args) throws Exception{
        DataSource dataSource = getShardingDatasource();

//        TransactionTypeHolder.set(TransactionType.XA);

        Connection connection = dataSource.getConnection();
//        connection.setAutoCommit(false);

        String sql = "insert into t_order values (?,?)";

        System.out.println("XA —— insert data");
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            for (int i = 1; i <= 10; i++) {
                statement.setLong(1,i);
                statement.setLong(2,i);
                statement.executeUpdate();
            }

            connection.commit();
        }
        System.out.println("XA —— insert succ!");
    }
}
