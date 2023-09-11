CREATE TABLE company(
	id serial PRIMARY KEY, 
	name VARCHAR(20) NOT NULL,
	price INT NOT NULL
);
CREATE TABLE personal_info_of_workers(
	id serial PRIMARY KEY,
	worker_name VARCHAR(10) NOT NULL,
	surname VARCHAR (10) NULL,
	patronymic VARCHAR(10) NULL,
	sex VARCHAR(1) NOT NULL,
	series_and_number_of_the_passport INT NOT NULL,
	age INT NOT NULL
);
CREATE TABLE official_position(
	id serial PRIMARY KEY,
	job_title VARCHAR(20) NOT NULL,
	wage INT NOT NULL,
	schedule VARCHAR(30) NOT NULL
);
CREATE TABLE workers(
	id serial PRIMARY KEY,
	company_id INT,
	personal_info_of_workers_id INT,
	official_position_id INT,
	status varchar(20),
	work_expirience INT,
	FOREIGN KEY (company_id)
		REFERENCES company(id),
	FOREIGN KEY (personal_info_of_workers_id)
		REFERENCES personal_info_of_workers(id),
	FOREIGN KEY (official_position_id)
		REFERENCES official_position(id)
);
CREATE TABLE daughter_companies(
	id serial PRIMARY KEY,
	company_id INT,
	name VARCHAR(20) NOT NULL,
	price INT NOT NULL,
	proportion INT NOT NULL,
	FOREIGN KEY (company_id)
		REFERENCES company(id)
);
INSERT INTO company(
	name,
	price
)
VALUES
	('Аэрофлот', 1400000),
	('ООО МПТ', 9999999),
	('ЧВК Буканов', 10);
INSERT INTO personal_info_of_workers(
	worker_name,
	surname,
	patronymic,
	sex,
	series_and_number_of_the_passport,
	age
)
VALUES
	('Артём', NULL, NULL, 'M', 321321, 44),
	('Ростислав', 'Игошев', 'Вадимович', 'M', 321321, 44),
	('Василий', 'Макаров', 'Негрович', 'M', 321321, 44),
	('Михаил', 'Замятин', 'Абобович', 'M', 321321, 44),
	('Юлианна', '"Воркута"', NULL, 'Ж', 321321, 44),
	('Арина', 'Клюева', 'Олеговна', 'Ж', 321321, 44);
INSERT INTO official_position(
	job_title,
	wage,
	schedule
)
VALUES 
	('Менеджер', 70000,  '2-2, 10:00 - 22:00'),
	('Программист', 120000,  '5-2, 08:00 - 16:00'),
	('Тимлид', 300000,  '5-2, 08:00 - 18:00'),
	('Уборщик', 45000,  '7-0, 06:00, 17:00'),
	('Администратор', 100000, '7-0, 10:00 - 18:00'),
	('Директор', 700000,  '5-2, 10:00 - 20:00');
INSERT INTO workers(
	company_id,
	personal_info_of_workers_id,
	official_position_id,
	status,
	work_expirience
)
VALUES
	(1, 1, 1,'Активен',3),
	(2, 2, 2,'Отпуск',5),
	(2, 3, 3,'Активен',5),
	(1, 4, 4,'Активен',10),
	(3, 5, 5,'Командировка',7),
	(3, 6, 6,'Активен',2);
INSERT INTO daughter_companies(
	name,
	price,
	proportion
)
VALUES
	('Проигрыш', 313213, 15),
	('Кириешка', 0231231, 7),
	('Не важно где!', 666666, 6);
SELECT * FROM company;
SELECT * FROM daughter_companies;
SELECT * FROM official_position;
SELECT * FROM personal_info_of_workers;
SELECT * FROM workers;