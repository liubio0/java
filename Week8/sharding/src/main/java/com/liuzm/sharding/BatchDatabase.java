package com.liuzm.sharding;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

/**
 * @Author: liuzm
 * @Date: 2021-09-26 23:13:57
 * @Description: com.liuzm.sharding
 * @version: 1.1
 */
public class BatchDatabase {

    HikariConfig hikariConfig = new HikariConfig();
    HikariDataSource hikariDataSource = null;

    public BatchDatabase() {
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:13306/sharding_db");
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        hikariConfig.setMaximumPoolSize(20);
        hikariDataSource = new HikariDataSource(hikariConfig);
    }


    public Connection getConnFromHikari() throws Exception {
        return hikariDataSource.getConnection();
    }

    public int asyncBatchInsert(CountDownLatch latch, int k) {
        try {
            Connection connection = getConnFromHikari();
            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement("insert into t_order values (?,?,1000000.00,1000001,'1',?,?)");
            long beginTime = System.currentTimeMillis();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            LocalDateTime day = LocalDateTime.now();
            String currentDatetime = day.format(formatter);

            //完整订单

            for (int j = 0; j < 10; j++) {
                StringBuilder str_insert = new StringBuilder("insert into t_order (order_id, user_id, amount, address_id, status, create_time, update_time) values ");
                for (int i = 0; i < 6250 - 1; i++) {
                    str_insert.append("("+ (k*62500+j*6250+i) + "," + (k*62500+j*6250+i) + ",1000000.00,1000001,'1',\"" + currentDatetime + "\",\"" + currentDatetime + "\"),");
                }
                str_insert.append("("+ (k*62500+j*6250+6249) + "," + (k*62500+j*6250+6249) + ",1000000.00,1000001,'1',\"" + currentDatetime + "\",\"" + currentDatetime + "\")");
                pstmt.addBatch(str_insert.toString());
            }


            pstmt.executeBatch();
            connection.commit();
            System.out.println("pstmt.executeBatch()花费时间：" + (System.currentTimeMillis() - beginTime) + " ms");
            pstmt.close();
            connection.close();
            latch.countDown();
        } catch (SQLException se) {
            System.out.println("数据库连接失败");
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("数据库操作异常");
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        BatchDatabase operateDatabase = new BatchDatabase();

        try {

            //批量插入
//            long beginTime = System.currentTimeMillis();
//
//            /*StringBuilder循环拼接 + 线程池*/
//            //每个线程插入6.25万条，一共需要16个子线程。
//            CountDownLatch latch = new CountDownLatch(16);
//            List<CompletableFuture> list = new ArrayList<>(16);
//            for (int i = 0; i < 16; i++) {
//                int finalI = i;
//                CompletableFuture<Void> future = CompletableFuture.runAsync(new FutureTask<>(() -> operateDatabase.asyncBatchInsert(latch, finalI)));
//                list.add(future);
//            }
//            latch.await();
//            for (CompletableFuture future : list) {
//                future.get();
//            }
//
//            System.out.println("线程池花费时间：" + (System.currentTimeMillis() - beginTime) + " ms");

            Connection connection = operateDatabase.getConnFromHikari();
            Statement stmt = connection.createStatement();
            String sql_update = "update t_order set amount=2.2 where order_id=999999";
            String sql_delete = "delete from t_order where order_id=1";
            String sql_query = "select * from t_order where order_id=999999";

            stmt.executeUpdate(sql_update);
            stmt.executeUpdate(sql_delete);

            ResultSet resultSet =  stmt.executeQuery(sql_query);
            while (resultSet.next()) {
                BigDecimal amount = resultSet.getBigDecimal("amount");
                int order_id = resultSet.getInt(1);
                System.out.println(order_id + "'amount is " + amount.doubleValue());
            }
            resultSet.close();
            stmt.close();

        } catch (Exception e) {
            System.out.println("主线程异常");
            e.printStackTrace();
        }
    }
}
