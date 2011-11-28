CREATE  TABLE users (users_login VARCHAR(45), users_pass VARCHAR(40), users_FN VARCHAR(45), users_LN VARCHAR(45),users_role VARCHAR(45), PRIMARY KEY(users_login));
CREATE  TABLE tasks (tasks_id_task INT(11) IDENTITY, tasks_title VARCHAR(300),tasks_text TEXT,tasks_path TEXT,tasks_status VARCHAR(45),tasks_end_time DATETIME, tasks_closed INT(1),tasks_users_login VARCHAR(45),PRIMARY KEY (tasks_id_task));
CREATE  TABLE meetings (meetings_id_meeting INT(11) IDENTITY ,meetings_title VARCHAR(300),meetings_description TEXT,meetings_date DATETIME,meetings_users_login VARCHAR(45),PRIMARY KEY (meetings_id_meeting));
CREATE  TABLE users_has_meetings (users_has_meetings_login VARCHAR(45),users_has_meetings_id_meeting INT(11),PRIMARY KEY (users_has_meetings_login, users_has_meetings_id_meeting));

