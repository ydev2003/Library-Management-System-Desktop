create database LMS;

use LMS;

set sql_safe_updates = 0;

create table Books(
BId varchar(20) primary key,
BName varchar(100),
AName varchar(100),
BPrice double
)
insert into books (BId, BName, AName, BPrice) values 
("CSE225", "Developing Android Apps", "Rahul Kumar", 200.0),
("CSE357", "Combinatorial Studies", "Gaurav Sharma", 700.0),
("CSE406", "Advanced Java Programming", "Ravi Kant Sahu", 1500.0)

select * from books;
delete from books where BId = 'cse406';

delete from books where BId = 'int22';
alter table books add availability varchar(10);
update books set availability = 'Yes';
ALTER TABLE books ALTER COLUMN availability SET DEFAULT 'Yes';

ALTER TABLE books CHANGE available Available varchar(50);



create table student(
 SId INT AUTO_INCREMENT PRIMARY KEY,
 SName varchar(50),
 FName varchar(50),
 SAge varchar(5),
 SMobile varchar(20)
 );
 insert into student (SName, FName, SAge, SMobile) values ("Devesh Yadav", "Rakesh Kumar Yadav", 20, "8707378201");
 
 select * from student;
 delete from student where SId > 3;
 alter table student add BTaken int;
ALTER TABLE student alter column BTaken SET DEFAULT 0;
UPDATE student SET BTaken = COALESCE(BTaken, 0);

update student set BTaken  = BTaken - 1 where SId = 3;

create table bookissued (
BId varchar(20) primary key,
SId INT
);
select * from bookissued;
delete from bookissued where BId  = 'cse406';

update books set Available  = 'Yes' where BId = 'cse406';