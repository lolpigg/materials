create database UP

create table role(
	id INT IDENTITY (1,1) PRIMARY KEY,
	name VARCHAR(20) not null
)
create table logs(
	id INT IDENTITY (1,1) PRIMARY KEY,
	role_id INT null,
	login VARCHAR(40) not null,
	password VARCHAR(40) NOT NULL,
	FOREIGN KEY (role_id) REFERENCES role(id) on delete cascade
)
create table worker(
	id INT IDENTITY (1,1) primary key,
	logs_id INT null,
	fio varchar(50) not null,
	age INT not null,
	sex varchar(1) not null,
	expirience int not null,
	profession varchar(30) not null
	FOREIGN KEY (logs_id) REFERENCES logs(id) on delete cascade,
)

create table passenger(
	id int identity (1,1) primary key,
	fio varchar(50) not null,
	age int not null,
	sex varchar(1) not null,
	passport_number int null
)
create table benefit(
	id int identity (1,1) primary key,
	min_age int not null,
	max_age int not null,
	size_in_percent int not null
)
create table train_model(
	id int identity (1,1) primary key,
	producer varchar (40) not null,
	mark varchar (40) not null,
	max_speed int not null
)
create table train(
	id int identity (1,1) primary key,
	train_model_id int null,
	have_advertising_color bit not null,
	expirience int not null,
	FOREIGN KEY (train_model_id) REFERENCES train_model(id) on delete cascade
)
create table route(
	id int identity (1,1) primary key,
	start_station varchar(30) not null,
	end_station varchar(30) not null,
	price int not null
)
create table routes_of_trains(
	id int identity (1,1) primary key,
	route_id int null,
	train_id int null,
	FOREIGN KEY (train_id) REFERENCES train(id) on delete cascade,
	FOREIGN KEY (route_id) REFERENCES route(id) on delete cascade
)
create table trip(
	id int identity (1,1) primary key,
	worker_id int null,
	route_id int null,
	train_id int null,
	FOREIGN KEY (route_id) REFERENCES route(id) on delete cascade,
	FOREIGN KEY (worker_id) REFERENCES worker(id) on delete cascade,
	FOREIGN KEY (train_id) REFERENCES train(id) on delete cascade
)
create table list_of_passengeres(
	id int identity (1,1) primary key,
	passenger_id int null,
	trip_id int null,
	FOREIGN KEY (trip_id) REFERENCES trip(id) on delete cascade,
	FOREIGN KEY (passenger_id) REFERENCES passenger(id) on delete cascade,
)
create table ticket(
	id int identity (1,1) primary key,
	passenger_id int null,
	benefit_id int null,
	trip_id int null,
	payment int not null,
	FOREIGN KEY (trip_id) REFERENCES trip(id) on delete cascade,
	FOREIGN KEY (passenger_id) REFERENCES passenger(id) on delete cascade,
	FOREIGN KEY (benefit_id) REFERENCES benefit(id) on delete cascade
)
DROP table logs
DROP table worker
DROP table ticket
DROP database UP