CREATE TABLE `test`.`order` (
  `order_id` BIGINT(11) NOT NULL,
  `amount` DECIMAL(11,2) NOT NULL,
  `user_id` BIGINT(11) NOT NULL,
  `address_id` BIGINT(11) NOT NULL,
  `status` CHAR(1) NOT NULL,
  `create_time` VARCHAR(14) NOT NULL,
  `update_time` VARCHAR(14) NOT NULL,
  PRIMARY KEY (`order_id`),
  UNIQUE INDEX `idorder_UNIQUE` (`order_id` ASC) VISIBLE)
COMMENT = '������';

CREATE TABLE `test`.`order_detail` (
  `order_id` BIGINT(11) NOT NULL,
  `good_id` VARCHAR(45) NOT NULL COMMENT '��Ʒ���',
  `specification_id` BIGINT(11) NOT NULL COMMENT '�����',
  `unit` VARCHAR(16) NOT NULL COMMENT '��λ',
  `price` DECIMAL(11,2) NOT NULL COMMENT '�۸�',
  `count` BIGINT(11) NOT NULL COMMENT '����',
  `status` CHAR(1) NOT NULL COMMENT '״̬',
  `update_time` VARCHAR(14) NOT NULL COMMENT '��¼����ʱ��',
  PRIMARY KEY (`order_id`))
COMMENT = '������ϸ��';

CREATE TABLE `test`.`shiping_address` (
  `address_id` BIGINT(11) NOT NULL,
  `user_id` BIGINT(11) NOT NULL,
  `shiping_name` VARCHAR(45) NOT NULL,
  `shiping_phone` VARCHAR(45) NOT NULL,
  `region_no` VARCHAR(6) NOT NULL COMMENT '�����������',
  `detail_address` VARCHAR(120) NOT NULL COMMENT '��ϸ��ַ',
  PRIMARY KEY (`address_id`))
COMMENT = '�ջ���ַ��';

CREATE TABLE `test`.`user` (
  `user_id` BIGINT(11) NOT NULL,
  `name` VARCHAR(60) NOT NULL,
  `phone` VARCHAR(11) NOT NULL,
  `id_type` VARCHAR(2) NOT NULL,
  `id_number` VARCHAR(30) NOT NULL,
  `status` VARCHAR(2) NOT NULL,
  `register_time` VARCHAR(14) NOT NULL,
  `update_time` VARCHAR(14) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE)
COMMENT = '�û���';

CREATE TABLE `test`.`good` (
  `good_id` BIGINT(11) NOT NULL,
  `status` VARCHAR(2) NOT NULL,
  `unit` VARCHAR(30) NOT NULL,
  `update_time` VARCHAR(14) NOT NULL,
  PRIMARY KEY (`good_id`),
  UNIQUE INDEX `good_id_UNIQUE` (`good_id` ASC) VISIBLE)
COMMENT = '��Ʒ��';

CREATE TABLE `test`.`good_annex` (
  `annex_id` BIGINT(11) NOT NULL,
  `good_id` BIGINT(11) NOT NULL,
  `type` VARCHAR(30) NOT NULL,
  `url` VARCHAR(120) NOT NULL,
  `status` VARCHAR(2) NOT NULL,
  `update_time` VARCHAR(14) NOT NULL,
  PRIMARY KEY (`annex_id`),
  UNIQUE INDEX `annex_id_UNIQUE` (`annex_id` ASC) VISIBLE)
COMMENT = '��Ʒ������';

CREATE TABLE `test`.`good_specification` (
  `specification_id` BIGINT(11) NOT NULL,
  `good_id` BIGINT(11) NOT NULL,
  `specification_group` VARCHAR(120) NOT NULL,
  `url` VARCHAR(120) NOT NULL,
  `price` DECIMAL(11,2) NOT NULL,
  `inventory` BIGINT(11) NOT NULL DEFAULT 0 COMMENT '�������',
  `status` VARCHAR(2) NOT NULL,
  `update_time` VARCHAR(14) NOT NULL,
  PRIMARY KEY (`specification_id`),
  UNIQUE INDEX `specification_id_UNIQUE` (`specification_id` ASC) VISIBLE)
COMMENT = '��Ʒ���';
