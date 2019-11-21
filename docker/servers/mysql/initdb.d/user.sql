# noinspection SqlResolveForFile

CREATE USER 'mydrive2'@'%' IDENTIFIED BY 'password';

GRANT SELECT, INSERT, UPDATE , DELETE ON MyDrive2.* TO 'mydrive2'@'%';

