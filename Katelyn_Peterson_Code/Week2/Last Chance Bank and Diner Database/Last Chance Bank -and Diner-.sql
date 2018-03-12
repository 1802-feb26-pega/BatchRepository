-- Users Table
CREATE TABLE Users
(
    user_id NUMBER NOT NULL,
    username VARCHAR2(20) NOT NULL,
    u_password VARCHAR2(20) NOT NULL,
    first_name VARCHAR2(40) NOT NULL,
    last_name VARCHAR2(20) NOT NULL,
    CONSTRAINT PK_Users PRIMARY KEY  (user_id)
);

-- Accounts Table
CREATE TABLE Accounts
(
    account_id NUMBER NOT NULL,
    user_id NUMBER NOT NULL,
    balance NUMBER(10,2) NOT NULL,
    CONSTRAINT PK_Accounts PRIMARY KEY  (account_id)
);

-- Accounts, add Foreign Key
ALTER TABLE Accounts ADD CONSTRAINT FK_UsersId
    FOREIGN KEY (user_id) REFERENCES Users (user_id);
    
-- Sequence/Trigger for Users
CREATE SEQUENCE user_seq
MINVALUE 1
MAXVALUE 999999999999
INCREMENT BY 1
START WITH 1;
/

DROP SEQUENCE user_seq;

CREATE OR REPLACE TRIGGER user_trigger
BEFORE INSERT ON users
FOR EACH ROW

BEGIN
    SELECT user_seq.NEXTVAL
    INTO :new.user_id
    FROM dual;
END;
/

-- Sequence/Trigger for Accounts
CREATE SEQUENCE account_seq
MINVALUE 1
MAXVALUE 999999999999
INCREMENT BY 1
START WITH 1;
/

DROP SEQUENCE account_seq;

CREATE OR REPLACE TRIGGER account_trigger
BEFORE INSERT ON accounts
FOR EACH ROW

BEGIN
    SELECT account_seq.NEXTVAL
    INTO :new.account_id
    FROM dual;
END;
/

-- Remove Account
CREATE OR REPLACE procedure remove_account(
u_id IN accounts.user_id%TYPE)
IS
BEGIN
    DELETE FROM accounts
    WHERE user_id = u_id;
END;
/

-- Add User
CREATE OR REPLACE PROCEDURE add_user(
user_name IN users.username%TYPE,
user_password IN users.u_password%TYPE,
user_first IN users.first_name%TYPE,
user_last IN users.last_name%TYPE)
IS
u_id users.user_id%TYPE;
BEGIN
    -- Insert New User
    INSERT INTO users (username, u_password, first_name, last_name)
    VALUES(user_name, user_password, user_first, user_last);
    
    -- Get user_id for the new user and Create an account for the new user
    u_id := get_user_id(user_name, user_password, user_first, user_last);
    BEGIN
        add_account(u_id);
    END;
END;
/

-- Function to retrieve User Id for add account
CREATE OR REPLACE FUNCTION get_user_id (
user_name IN users.username%TYPE,
user_password IN users.u_password%TYPE,
user_first IN users.first_name%TYPE,
user_last IN users.last_name%TYPE)

RETURN NUMBER IS u_id NUMBER;

BEGIN
    SELECT user_id
    INTO u_id
    FROM users
    WHERE user_name = users.username AND
            user_password = users.u_password AND
            user_first = users.first_name AND
            user_last = users.last_name;
    RETURN u_id;
END;
/

-- Add Account
create or replace PROCEDURE add_account(
u_id IN users.user_id%TYPE)
IS
BEGIN
    INSERT INTO accounts (user_id, balance)
    VALUES(u_id, 0.00);
END;
/