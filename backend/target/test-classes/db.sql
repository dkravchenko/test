CREATE TABLE roles (roles_role VARCHAR(50) NOT NULL PRIMARY KEY, roles_value VARCHAR(50) NOT NULL);
CREATE TABLE users (users_login  VARCHAR(50) NOT NULL PRIMARY KEY,users_pass VARCHAR(40) NOT NULL, users_FN VARCHAR(50) NOT NULL, users_LN VARCHAR(50) NOT NULL, users_role VARCHAR(50) NOT NULL, CONSTRAINT fk FOREIGN KEY (users_role) REFERENCES roles (roles_role) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE reports (reports_id_reports INT PRIMARY KEY IDENTITY, reports_date DATETIME NOT NULL, reports_working_hours INT NOT NULL, reports_done_hours INT NOT NULL, reports_users_login VARCHAR(50) NOT NULL, CONSTRAINT fk_1 FOREIGN KEY (reports_users_login) REFERENCES users (users_login) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE tasks (tasks_id_task INT PRIMARY KEY IDENTITY,tasks_title VARCHAR(300) NOT NULL,tasks_text VARCHAR(10000) NOT NULL,tasks_hours_todo INT NOT NULL,tasks_status VARCHAR(45) NOT NULL,tasks_end_time DATETIME DEFAULT NULL,tasks_users_login VARCHAR(50) NOT NULL,CONSTRAINT fk_2 FOREIGN KEY (tasks_users_login) REFERENCES users (users_login) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE reports_has_tasks (treports_id_reports INT NOT NULL,rtasks_id_task INT NOT NULL,PRIMARY KEY (treports_id_reports,rtasks_id_task),CONSTRAINT fk_reports_has_tasks_reports1 FOREIGN KEY (treports_id_reports) REFERENCES reports (reports_id_reports) ON DELETE CASCADE ON UPDATE CASCADE,CONSTRAINT fk_reports_has_tasks_tasks1 FOREIGN KEY (rtasks_id_task) REFERENCES tasks (tasks_id_task) ON DELETE CASCADE ON UPDATE CASCADE);