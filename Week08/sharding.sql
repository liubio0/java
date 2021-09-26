show tables;
drop table t_order;

CREATE TABLE `t_order` (
  `order_id` BIGINT(11) NOT NULL primary key unique,
  `user_id` BIGINT(11) NOT NULL,
  `amount` DECIMAL(11,2) NOT NULL,
  `address_id` BIGINT(11) NOT NULL,
  `status` CHAR(1) NOT NULL,
  `create_time` VARCHAR(14) NOT NULL,
  `update_time` VARCHAR(14) NOT NULL
  )
COMMENT = '订单表';

select * from t_order;
select count(*) from t_order;
select * from t_order where order_id=999999;
select * from t_order where order_id=1;
