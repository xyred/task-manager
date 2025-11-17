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

-- Insert sample boards
INSERT INTO "boards" ("description", "title") VALUES
('Board 1 Description', 'Board 1'),
('Board 2 Description', 'Board 2'),
('Board 3 Description', 'Board 3');

-- Insert sample task lists for each board
INSERT INTO "task_lists" ("position", "title", "board_id") VALUES
(1, 'Task List 1', 1),
(2, 'Task List 2', 1),
(1, 'Task List 1', 2),
(2, 'Task List 2', 2),
(1, 'Task List 1', 3),
(2, 'Task List 2', 3);

-- Insert sample tasks for each task list
INSERT INTO "tasks" ("description", "status", "task_title", "task_list_id") VALUES
('Task 1 Description', 'TODO', 'Task 1', 1),
('Task 2 Description', 'IN_PROGRESS', 'Task 2', 1),
('Task 3 Description', 'DONE', 'Task 3', 2),
('Task 4 Description', 'TODO', 'Task 4', 2),
('Task 5 Description', 'IN_PROGRESS', 'Task 5', 3),
('Task 6 Description', 'DONE', 'Task 6', 3);

-- Assign users to boards
INSERT INTO "board_members" ("board_id", "user_id") VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 3),
(3, 1),
(3, 3);

-- Assign users to tasks
INSERT INTO "task_assignees" ("task_id", "user_id") VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 1),
(5, 2),
(6, 3);
