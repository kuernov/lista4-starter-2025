-- Najpierw inserty followerów
INSERT INTO follower (id, user_id, subscription_date) VALUES (2001, 'Adam', PARSEDATETIME('2018-02-04 10:00:00', 'yyyy-MM-dd HH:mm:ss'));
INSERT INTO follower (id, user_id, subscription_date) VALUES (2002, 'Adam1', PARSEDATETIME('2018-02-05 12:00:00', 'yyyy-MM-dd HH:mm:ss'));
INSERT INTO follower (id, user_id, subscription_date) VALUES (2003, 'Jan', PARSEDATETIME('2018-02-06 14:00:00', 'yyyy-MM-dd HH:mm:ss'));
INSERT INTO follower (id, user_id, subscription_date) VALUES (2004, 'Jan', PARSEDATETIME('2018-02-07 08:30:00', 'yyyy-MM-dd HH:mm:ss'));

-- Następnie inserty komentarzy, teraz z follower_id
INSERT INTO comment (id, content, event_id, follower_id) VALUES (1001, 'Komentarz do zdarzenia 21', 21, 2001);
INSERT INTO comment (id, content, event_id, follower_id) VALUES (1002, 'Komentarz do zdarzenia 22', 22, 2002);
INSERT INTO comment (id, content, event_id, follower_id) VALUES (1003, 'Komentarz do zdarzenia 23', 23, 2003);
INSERT INTO comment (id, content, event_id, follower_id) VALUES (1004, 'Komentarz do zdarzenia 24', 24, 2004);


UPDATE request_event SET description = 'Initial PUT request by Adam' WHERE id = 1;
UPDATE request_event SET description = 'Second PUT request by Adam' WHERE id = 2;
UPDATE request_event SET description = 'Jan testing PUT at noon' WHERE id = 3;
UPDATE request_event SET description = 'GET request - late night by Jan' WHERE id = 4;
UPDATE request_event SET description = 'Repeated GET on same thread' WHERE id = 5;
UPDATE request_event SET description = 'Heavy request needing no analysis' WHERE id = 6;
UPDATE request_event SET description = 'Very quick GET - 10ms' WHERE id = 7;
UPDATE request_event SET description = 'Tiny GET request by Adam' WHERE id = 8;
UPDATE request_event SET description = 'Paweł inspecting server via GET' WHERE id = 9;
UPDATE request_event SET description = 'Long PUT request (6.5s)' WHERE id = 10;
UPDATE request_event SET description = 'Another PUT request' WHERE id = 11;
UPDATE request_event SET description = 'POST data to server' WHERE id = 12;
UPDATE request_event SET description = 'Simple POST by Paweł' WHERE id = 13;
UPDATE request_event SET description = 'POST by Adam, same time as DELETE' WHERE id = 14;
UPDATE request_event SET description = 'DELETE request - maybe cleanup?' WHERE id = 15;
UPDATE request_event SET description = 'Archived PUT from 2017' WHERE id = 16;
UPDATE request_event SET description = 'Another old PUT request' WHERE id = 17;
UPDATE request_event SET description = 'Old POST event' WHERE id = 18;
UPDATE request_event SET description = 'POST by Paweł to secondary server' WHERE id = 19;
UPDATE request_event SET description = 'Last recorded POST by Adam' WHERE id = 20;

UPDATE sql_event SET description = 'Basic SELECT 1 query by Adam' WHERE id = 21;
UPDATE sql_event SET description = 'Same query by Adam later that day' WHERE id = 22;
UPDATE sql_event SET description = 'Jan performs late SELECT' WHERE id = 23;
UPDATE sql_event SET description = 'Nighttime SELECT by Jan' WHERE id = 24;
UPDATE sql_event SET description = 'Repetition of SELECT, low cost' WHERE id = 25;
UPDATE sql_event SET description = 'Heavy SQL query, 4.2s' WHERE id = 26;
UPDATE sql_event SET description = 'Short query by Jan' WHERE id = 27;
UPDATE sql_event SET description = 'Quick query by Adam on new thread' WHERE id = 28;
UPDATE sql_event SET description = 'Routine query by Paweł' WHERE id = 29;
UPDATE sql_event SET description = 'Long-running SELECT from Paweł' WHERE id = 30;
UPDATE sql_event SET description = 'Slightly older query' WHERE id = 31;
UPDATE sql_event SET description = 'Standard SELECT for testing' WHERE id = 32;
UPDATE sql_event SET description = 'Paweł loads data on thread 8' WHERE id = 33;
UPDATE sql_event SET description = 'Adam runs the same SELECT' WHERE id = 34;
UPDATE sql_event SET description = 'Third identical query by Adam' WHERE id = 35;
UPDATE sql_event SET description = 'Oldest query in logs by Paweł' WHERE id = 36;
UPDATE sql_event SET description = 'Legacy SELECT call' WHERE id = 37;
UPDATE sql_event SET description = 'Very early test by Paweł' WHERE id = 38;
UPDATE sql_event SET description = 'Testing with date from 2017' WHERE id = 39;
UPDATE sql_event SET description = 'Analysis-required SELECT by Adam' WHERE id = 40;

