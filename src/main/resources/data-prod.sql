-- Purge existing data while maintaining referential integrity
TRUNCATE TABLE "board_members" RESTART IDENTITY CASCADE;
TRUNCATE TABLE "task_assignees" RESTART IDENTITY CASCADE;
TRUNCATE TABLE "tasks" RESTART IDENTITY CASCADE;
TRUNCATE TABLE "task_lists" RESTART IDENTITY CASCADE;
TRUNCATE TABLE "boards" RESTART IDENTITY CASCADE;
TRUNCATE TABLE "users" RESTART IDENTITY CASCADE;

-- Insert sample users
INSERT INTO "users" ("email", "password", "username") VALUES
('user1@example.com', 'password1', 'user1'),
('user2@example.com', 'password2', 'user2'),
('user3@example.com', 'password3', 'user3');

-- Insert two sample boards
INSERT INTO "boards" ("description", "title") VALUES
('Board A Description', 'Board A'),
('Board B Description', 'Board B');

-- Insert two unique task lists for each board
INSERT INTO "task_lists" ("position", "title", "board_id") VALUES
(1, 'Board A - Task List 1', 1),
(2, 'Board A - Task List 2', 1),
(1, 'Board B - Task List 1', 2),
(2, 'Board B - Task List 2', 2);

-- Insert three tasks with unique statuses for each task list
INSERT INTO "tasks" ("description", "status", "task_title", "task_list_id") VALUES
-- Tasks for Board A - Task List 1
('Task 1 Description', 'TODO', 'Task 1', 1),
('Task 2 Description', 'IN_PROGRESS', 'Task 2', 1),
('Task 3 Description', 'DONE', 'Task 3', 1),

-- Tasks for Board A - Task List 2
('Task 4 Description', 'TODO', 'Task 4', 2),
('Task 5 Description', 'IN_PROGRESS', 'Task 5', 2),
('Task 6 Description', 'DONE', 'Task 6', 2),

-- Tasks for Board B - Task List 1
('Task 7 Description', 'TODO', 'Task 7', 3),
('Task 8 Description', 'IN_PROGRESS', 'Task 8', 3),
('Task 9 Description', 'DONE', 'Task 9', 3),

-- Tasks for Board B - Task List 2
('Task 10 Description', 'TODO', 'Task 10', 4),
('Task 11 Description', 'IN_PROGRESS', 'Task 11', 4),
('Task 12 Description', 'DONE', 'Task 12', 4);

-- Assign users to boards
INSERT INTO "board_members" ("board_id", "user_id") VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 3);

-- Assign users to tasks
INSERT INTO "task_assignees" ("task_id", "user_id") VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 1),
(5, 2),
(6, 3),
(7, 1),
(8, 2),
(9, 3),
(10, 1),
(11, 2),
(12, 3);