#Databases
CREATE DATABASE sfg_dev;
CREATE DATABASE sfg_prod;

#Create users
CREATE USER 'sfg_dev_user'@'localhost' IDENTIFIED BY 'guru';
CREATE USER 'sfg_prod_user'@'localhost' IDENTIFIED BY 'guru';
CREATE USER 'sfg_dev_user'@'%' IDENTIFIED BY 'guru';
CREATE USER 'sfg_prod_user'@'%' IDENTIFIED BY 'guru';

#Grants
GRANT SELECT, INSERT, DELETE, UPDATE ON sfd_dev.* TO 'sfg_dev_user'@'localhost';
GRANT SELECT, INSERT, DELETE, UPDATE ON sfd_prod.* TO 'sfg_prod_user'@'localhost';
GRANT SELECT, INSERT, DELETE, UPDATE ON sfd_dev.* TO 'sfg_dev_user'@'%';
GRANT SELECT, INSERT, DELETE, UPDATE ON sfd_prod.* TO 'sfg_prod_user'@'%';
