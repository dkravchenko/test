CREATE DATABASE `test` /*!40100 DEFAULT CHARACTER SET utf-8 */;

USE `test`;

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `pass` varchar(45) NOT NULL,
  `FN` varchar(45) NOT NULL,
  `LN` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf-8;

CREATE TABLE `meetings` (
  `id_meeting` int(11) NOT NULL,
  `meeting_title` varchar(300) NOT NULL,
  `meeting_description` text,
  `meeting_date` datetime NOT NULL,
  `id_user_iniciator` int(11) NOT NULL,
  PRIMARY KEY (`id_meeting`),
  KEY `fk_meetings_users1` (`id_user_iniciator`),
  CONSTRAINT `fk_meetings_users1` FOREIGN KEY (`id_user_iniciator`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf-8;

CREATE TABLE `users_has_meetings` (
  `users_id_user` int(11) NOT NULL,
  `meetings_id_meeting` int(11) NOT NULL,
  PRIMARY KEY (`users_id_user`,`meetings_id_meeting`),
  KEY `fk_users_has_meetings_meetings1` (`meetings_id_meeting`),
  KEY `fk_users_has_meetings_users1` (`users_id_user`),
  CONSTRAINT `fk_users_has_meetings_meetings1` FOREIGN KEY (`meetings_id_meeting`) REFERENCES `meetings` (`id_meeting`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_users_has_meetings_users1` FOREIGN KEY (`users_id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf-8;

CREATE TABLE `tasks` (
  `id_task` int(11) NOT NULL AUTO_INCREMENT,
  `task_title` varchar(300) NOT NULL,
  `task_text` text NOT NULL,
  `task_path` text NOT NULL,
  PRIMARY KEY (`id_task`)
) ENGINE=InnoDB DEFAULT CHARSET=utf-8;

CREATE TABLE `tasks_has_users` (
  `tasks_id_task` int(11) NOT NULL,
  `users_id_user` int(11) NOT NULL,
  PRIMARY KEY (`tasks_id_task`,`users_id_user`),
  KEY `fk_tasks_has_users_users1` (`users_id_user`),
  KEY `fk_tasks_has_users_tasks` (`tasks_id_task`),
  CONSTRAINT `fk_tasks_has_users_tasks` FOREIGN KEY (`tasks_id_task`) REFERENCES `tasks` (`id_task`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_tasks_has_users_users1` FOREIGN KEY (`users_id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf-8;







