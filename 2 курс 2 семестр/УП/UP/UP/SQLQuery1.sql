create table player(
	id INT IDENTITY(1,1) PRIMARY KEY(id)NOT NULL,
	name varchar(20) NOT NULL UNIQUE,
	weapon_id INT,
	potion_id INT,
	FOREIGN KEY (weapon_id) REFERENCES weapon(id) on delete cascade,
	FOREIGN KEY (potion_id) REFERENCES potion(id) on delete cascade
)
create table weapon(
	id INT IDENTITY(1, 1) PRIMARY KEY(id)NOT NULL,
	weapon_damage INT NOT NULL,
	price INT NOT NULL,
	name varchar(20) NOT NULL
)
create table potion(
	id INT IDENTITY(1, 1) PRIMARY KEY(id)NOT NULL,
	potion_type varchar(20) NOT NULL,
	potion_damage INT NOT NULL,
	potion_price INT NOT NULL
)
INSERT INTO potion
VALUES
	('Паучий яд', 30, 100),
	('Зелье лечения', -100, 200)

INSERT INTO weapon
VALUES
	(10, 150,'Меч Захара'),
	(3, 0, 'Деревянный меч')
INSERT INTO player
VALUES
	('Захар', 1, 1),
	('Артем', 2, 1)
DROP TABLE weapon
DROP TABLE potion
DROP TABLE player
Create database UP