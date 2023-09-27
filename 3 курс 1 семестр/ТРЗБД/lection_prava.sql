CREATE TABLE ACTIONEERS(
	id SERIAL PRIMARY KEY,
	name VARCHAR(30),
	share float
);
ALTER TABLE ACTIONEERS ADD surname VARCHAR(30)

SELECT * FROM personal_info_of_workers;
INSERT INTO personal_info_of_workers(age,patronymic,series_and_number_of_the_passport,sex,surname,worker_name)
VALUES (12,'idk',132,'m','idk','idk');
SELECT * FROM personal_info_of_workers;
UPDATE personal_info_of_workers SET age = 9 WHERE id = 7
DELETE FROM personal_info_of_workers WHERE id = 7;
SELECT * FROM personal_info_of_workers;

SELECT * FROM workers;
INSERT INTO workers(company_id,official_position_id,personal_info_of_workers_id,status,work_expirience)
VALUES (2,3,3,'Active',12);
UPDATE workers SET status = 'Non-active' WHERE id = 8;
SELECT * FROM workers;
DELETE FROM daughter_companies WHERE id = 2;
SELECT * FROM workers;

ALTER TABLE daughter_companies ADD actioneer_id INT
ALTER TABLE daughter_companies ADD FOREIGN KEY (actioneer_id) REFERENCES actioneers (id)