CREATE TABLE follower (
                        id BIGINT PRIMARY KEY,
                        user_id VARCHAR(100) NOT NULL,
                        subscription_date TIMESTAMP NOT NULL
);

CREATE TABLE comment (
                       id BIGINT PRIMARY KEY,
                       content VARCHAR(1000) NOT NULL,
                       event_id BIGINT NOT NULL,
                       follower_id BIGINT NOT NULL,
                       CONSTRAINT fk_comment_event FOREIGN KEY (event_id) REFERENCES sql_event(id) ON DELETE CASCADE,
                       CONSTRAINT fk_comment_follower FOREIGN KEY (follower_id) REFERENCES follower(id) ON DELETE CASCADE
);

ALTER TABLE sql_event ADD COLUMN description VARCHAR(1000);
ALTER TABLE request_event ADD COLUMN description VARCHAR(1000);
ALTER TABLE exception_event ADD COLUMN description VARCHAR(1000);
