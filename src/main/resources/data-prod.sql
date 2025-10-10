-- Clear join tables first
DELETE FROM task_assignees;
DELETE FROM board_members;

-- Then clear child tables
DELETE FROM tasks;
DELETE FROM task_lists;

-- Then clear parent tables
DELETE FROM boards;
DELETE FROM users;

-- Users
INSERT INTO users (id, username, email, password) VALUES
  (1, 'alice', 'alice@example.com', 'password1'),
  (2, 'bob', 'bob@example.com', 'password2');

-- Boards
INSERT INTO boards (id, title, description) VALUES
  (1, 'Project Alpha', 'Alpha board'),
  (2, 'Project Beta', 'Beta board');

-- Board Members (join table)
INSERT INTO board_members (board_id, user_id) VALUES
  (1, 1),
  (1, 2),
  (2, 2);

-- Task Lists
INSERT INTO task_lists (id, title, position, board_id) VALUES
  (1, 'To Do', 0, 1),
  (2, 'In Progress', 1, 1);

-- Tasks
INSERT INTO tasks (id, task_title, description, status, task_list_id) VALUES
  (1, 'First Task', 'This is a sample task', 'TODO', 1),
  (2, 'Second Task', 'Another example', 'IN_PROGRESS', 2);

-- Task Assignees (join table)
INSERT INTO task_assignees (task_id, user_id) VALUES
  (1, 1),
  (2, 2);
  