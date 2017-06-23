DROP DATABASE IF EXISTS test1;
create database test1;
use test1;
drop table if exists student;
create table student(
	stuId varchar(30) primary key,
    stuName varchar(30)not null,
    stuSex varchar(30), check (stuSex in('男','女')),
    stuAge int check(stuAge>1),
    stuFrom varchar(30),
    stuDept varchar(40)
);
insert into student values('1140299232','王文杰','男','21','广东','计算机');
insert into student values('1140299233','王魁曙','男','21','建德','通信');
insert into student values('1140299234','梁凯杰','男','21','嵊州','计算机');
insert into student values('1140299235','黄崇煌','男','21','温州','计算机');
insert into student values('1140299236','缪沈聪','男','21','嘉兴','数字媒体');
select *from student;


drop table if exists grate;
create table grate(
	stuId varchar(30) primary key,
    stuName varchar(30)not null,
    stuSex varchar(30), check (stuSex in('男','女')),
    stuDept varchar(40),
    stugrate int check(stugrate>1)
);