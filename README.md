# EmployeService

Email ID : javaeecub@gmail.com
Skype ID : thanooj.kalathuru

Employe Service


create table employee_department.department (id int primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), name varchar(30) not null, location varchar(30) not null);

insert into employee_department.department (name, location) values ('ram','ayodhya');

insert into employee_department.department (name, location) values ('seeta','midhila');

insert into employee_department.department (name, location) values ('lakshman','ayodhya');

insert into employee_department.department (name, location) values ('hanuma','kiskindha');

create table employee_department.employee (id int primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), name varchar(30) not null, location varchar(30) not null, did int not null);

insert into employee_department.employee (name, location ,did) values ('ram','ayodhya',1);

insert into employee_department.employee (name, location,did) values ('seeta','midhila',2);

insert into employee_department.employee (name, location,did) values ('lakshman','ayodhya',3);

insert into employee_department.employee (name, location,did) values ('hanuma','kiskindha',4);

update employee_department.employee set name='sriram' where did=1;

update employee_department.employee set name='seethamma' where did=2;

update employee_department.employee set name='lakshmana' where did=3;


http://localhost:8888/DepartmentService/rest/department/getDepartments

http://localhost:9999/EmployeeService/rest/employee/getEmployees

http://localhost:9999/EmployeeService/rest/employee/getDepartmentDetailsByEmployeeID/1

http://localhost:8888/DepartmentService/rest/department/getDepartmentByID/1





https://sourceforge.net/projects/squirrel-sql/files/1-stable/3.7.1-plainzip/squirrelsql-3.7.1-standard.zip/download
http://localhost:8888/EmployeeService/rest/department/getEmployeeByID/1
