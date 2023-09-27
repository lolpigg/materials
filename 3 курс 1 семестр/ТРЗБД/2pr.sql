SELECT rolname FROM pg_roles;
CREATE USER user1 WITH PASSWORD '1';
CREATE USER user2 WITH PASSWORD '2';
CREATE USER user3 WITH PASSWORD '3';
CREATE USER user4 WITH PASSWORD '4';

GRANT INSERT, SELECT, UPDATE, DELETE ON personal_info_of_workers TO user1;
GRANT INSERT, SELECT, UPDATE, DELETE ON official_position TO user1;
GRANT INSERT, SELECT, UPDATE, DELETE ON workers TO user1;
GRANT USAGE ON SEQUENCE personal_info_of_workers_id_seq TO user1;
GRANT USAGE ON SEQUENCE official_position_id_seq TO user1;
GRANT USAGE ON SEQUENCE workers_id_seq TO user1;

GRANT INSERT, SELECT, UPDATE, DELETE ON company TO user2;
GRANT INSERT, SELECT, UPDATE, DELETE ON workers TO user2;
GRANT SELECT ON official_position TO user2;
GRANT USAGE ON SEQUENCE company_id_seq TO user2;
GRANT USAGE ON SEQUENCE workers_id_seq TO user2;

GRANT CREATE ON DATABASE idk TO user3;
GRANT CREATE ON SCHEMA public To user3;
GRANT ALL PRIVILEGES ON SCHEMA "public" TO user3;
GRANT ALL PRIVILEGES ON DATABASE idk TO user3;
--Тут должно быть разрешение на изменение таблиц, но ALTER не работает

GRANT REFERENCES ON TABLE company TO user4;
GRANT REFERENCES ON TABLE workers TO user4;
GRANT REFERENCES ON TABLE personal_info_of_workers TO user4;
GRANT REFERENCES ON TABLE official_position TO user4;
GRANT REFERENCES ON TABLE daughter_companies TO user4;
GRANT CREATE ON SCHEMA public TO user4;
GRANT DELETE ON TABLE company TO user4;
GRANT DELETE ON TABLE workers TO user4;
GRANT DELETE ON TABLE personal_info_of_workers TO user4;
GRANT DELETE ON TABLE official_position TO user4;
GRANT DELETE ON TABLE daughter_companies TO user4;
ALTER TABLE daughter_companies OWNER TO user4;
GRANT REFERENCES ON TABLE actioneers TO user4;

CREATE ROLE role1;
CREATE ROLE role2;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO role1;
GRANT INSERT ON ALL TABLES IN SCHEMA public TO role2;
GRANT role1 TO user3;
GRANT role1 TO user4;
GRANT role2 TO user3;
CREATE USER user5 WITH PASSWORD '5';
CREATE USER user6 WITH PASSWORD '6';
GRANT role1 TO user5;
GRANT role2 TO user6;