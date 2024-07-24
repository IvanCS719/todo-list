CREATE TABLE `task` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(100),
  `description` varchar(300),
  `dueDate` datetime COMMENT 'Fecha limite para terminar la tarea',
  `status` bool COMMENT 'Tarea terminado true, tarea no terminado false',
  `user_id` int
);

CREATE TABLE `users` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(50),
  `password` varchar(65)
);

ALTER TABLE `task` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);