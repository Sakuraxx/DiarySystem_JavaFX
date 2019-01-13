#创建user表
-- create table `user`(
-- `name` char(30) not null collate utf8_bin primary key comment '用户名 主键',
-- `password` char(50) not null collate utf8_bin comment '密码',
-- `secretQuestion` int not null comment '密保问题的下标',
-- `secretAnswer` char(50) not null comment '密保问题答案'
-- )Engine=InnoDB default charset=utf8 collate utf8_bin comment '用户表';

#创建dairy表
create table `dairy`(
	`title` char(30) not null collate utf8_bin primary key comment '标题 主键',
	`feeling` char(20) not null collate utf8_bin comment '心情',
	`weather` int not null comment '天气的下标',
	`date` date not null comment '日期',
	`conetnt` text not null comment '日记内容'
)engine=InnoDB default charset=utf8 collate utf8_bin comment '日记表';

#用户日记表
create table `ud`(
	`name` char(30) not null collate utf8_bin comment '用户名',
	`title` char(30) not null collate utf8_bin comment '标题'
)engine=InnoDB default charset=utf8 collate utf8_bin comment '用户日记表';


alter table `ud` add primary key(`name`,`title`);

alter table `ud` add foreign key(`name`) references `user`(`name`);
alter table `ud` add foreign key(`title`) references `dairy`(`title`);