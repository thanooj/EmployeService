create table employee_department.employee (id int primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), name varchar(30) not null, location varchar(30) not null, did int not null);
insert into employee_department.employee (name, location) values ('ram','ayodhya',1);
insert into employee_department.employee (name, location) values ('seeta','midhila',2);
insert into employee_department.employee (name, location) values ('lakshman','ayodhya',3);
insert into employee_department.employee (name, location) values ('hanuma','kiskindha',4);