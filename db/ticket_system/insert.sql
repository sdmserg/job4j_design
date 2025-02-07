INSERT INTO roles(role_name) VALUES ('System Administrator');
INSERT INTO roles(role_name) VALUES ('Software Developer');
INSERT INTO roles(role_name) VALUES ('DevOps Engineer');
INSERT INTO roles(role_name) VALUES ('Database Administrator');
INSERT INTO roles(role_name) VALUES ('QA Engineer');
INSERT INTO roles(role_name) VALUES ('Technical Support');
INSERT INTO roles(role_name) VALUES ('Network Engineer');

INSERT INTO users(user_name, role_id) VALUES ('Иван Смирнов', 1);
INSERT INTO users(user_name, role_id) VALUES ('Алексей Петров', 2);
INSERT INTO users(user_name, role_id) VALUES ('Мария Иванова', 3);
INSERT INTO users(user_name, role_id) VALUES ('Дмитрий Сидоров', 4);
INSERT INTO users(user_name, role_id) VALUES ('Екатерина Орлова', 5);
INSERT INTO users(user_name, role_id) VALUES ('Сергей Кузнецов', 6);
INSERT INTO users(user_name, role_id) VALUES ('Ольга Васильева', 7);

INSERT INTO rules(privilege) values ('Manage Servers');
INSERT INTO rules(privilege) values ('Deploy Applications');
INSERT INTO rules(privilege) values ('Write Code');
INSERT INTO rules(privilege) values ('Review Code');
INSERT INTO rules(privilege) values ('Manage Databases');
INSERT INTO rules(privilege) values ('Monitor Network');
INSERT INTO rules(privilege) values ('Test Applications');
INSERT INTO rules(privilege) values ('Provide Support');

INSERT INTO roles_rules(role_id, rule_id) VALUES (1, 1);
INSERT INTO roles_rules(role_id, rule_id) VALUES (1, 6);
INSERT INTO roles_rules(role_id, rule_id) VALUES (2, 3);
INSERT INTO roles_rules(role_id, rule_id) VALUES (2, 4);
INSERT INTO roles_rules(role_id, rule_id) VALUES (3, 1);
INSERT INTO roles_rules(role_id, rule_id) VALUES (3, 2);
INSERT INTO roles_rules(role_id, rule_id) VALUES (3, 6);
INSERT INTO roles_rules(role_id, rule_id) VALUES (4, 5);
INSERT INTO roles_rules(role_id, rule_id) VALUES (5, 6);
INSERT INTO roles_rules(role_id, rule_id) VALUES (6, 7);
INSERT INTO roles_rules(role_id, rule_id) VALUES (7, 8);

INSERT INTO states (state) VALUES ('New');
INSERT INTO states (state) VALUES ('In Progress');
INSERT INTO states (state) VALUES ('Pending Review');
INSERT INTO states (state) VALUES ('Resolved');
INSERT INTO states (state) VALUES ('Closed');

INSERT INTO categories (category) VALUES ('Software Issue');
INSERT INTO categories (category) VALUES ('Hardware Issue');
INSERT INTO categories (category) VALUES ('Network Issue');
INSERT INTO categories (category) VALUES ('Access Request');
INSERT INTO categories (category) VALUES ('Bug Report');

INSERT INTO items (item_name, description, user_id, category_id, state_id) VALUES ('Ошибка в приложении', 'Приложение вылетает при запуске', 2, 1, 1);
INSERT INTO items (item_name, description, user_id, category_id, state_id) VALUES ('Не работает принтер', 'Принтер не печатает, ошибка сети', 7, 2, 2);
INSERT INTO items (item_name, description, user_id, category_id, state_id) VALUES ('Проблемы с VPN', 'Не удаётся подключиться к VPN', 5, 3, 3);
INSERT INTO items (item_name, description, user_id, category_id, state_id) VALUES ('Доступ к серверу', 'Требуется доступ к базе данных', 4, 4, 1);
INSERT INTO items (item_name, description, user_id, category_id, state_id) VALUES ('Критическая ошибка в системе', 'Система не отвечает на запросы', 1, 5, 2);
INSERT INTO items (item_name, description, user_id, category_id, state_id) VALUES ('Проблема с Wi-Fi', 'Подключение прерывается каждые 5 минут', 3, 3, 4);
INSERT INTO items (item_name, description, user_id, category_id, state_id) VALUES ('Запрос на доступ к репозиторию', 'Нужен доступ к Git-репозиторию', 6, 4, 5);

INSERT INTO comments (comment, item_id) VALUES ('Ошибка воспроизводится на Windows 10.', 1);
INSERT INTO comments (comment, item_id) VALUES ('Принтер не печатает даже после замены картриджа.', 2);
INSERT INTO comments (comment, item_id) VALUES ('Пробовал подключиться с другого устройства — та же проблема.', 3);
INSERT INTO comments (comment, item_id) VALUES ('Доступ предоставлен, проверьте.', 4);
INSERT INTO comments (comment, item_id) VALUES ('Ошибка устранена, система работает стабильно.', 5);
INSERT INTO comments (comment, item_id) VALUES ('Wi-Fi подключается, но пинг высокий.', 6);
INSERT INTO comments (comment, item_id) VALUES ('Доступ к репозиторию предоставлен.', 7);

INSERT INTO attachs (attach_path, item_id) VALUES ('/attachments/error_screenshot.png', 1);
INSERT INTO attachs (attach_path, item_id) VALUES ('/attachments/printer_error_log.txt', 2);
INSERT INTO attachs (attach_path, item_id) VALUES ('/attachments/vpn_config.json', 3);
INSERT INTO attachs (attach_path, item_id) VALUES ('/attachments/db_access_request.pdf', 4);
INSERT INTO attachs (attach_path, item_id) VALUES ('/attachments/system_crash_report.log', 5);
INSERT INTO attachs (attach_path, item_id) VALUES ('/attachments/wifi_ping_results.txt', 6);
INSERT INTO attachs (attach_path, item_id) VALUES ('/attachments/git_access_granted.png', 7);