--CREATE TABLES
CREATE TABLE Users(
    userId NUMBER NOT NULL,
    firstName VARCHAR2(255) NOT NULL,
    lastName VARCHAR2(255) NOT NULL,
    email VARCHAR2(255) NOT NULL,
    password VARCHAR2(255) NOT NULL,
    CONSTRAINT PK_User PRIMARY KEY (userId)
);

ALTER TABLE Users 
ADD CONSTRAINT UNIQUE_Emails 
UNIQUE (Email);

CREATE TABLE Accounts(
    accountId NUMBER NOT NULL,
    userId NUMBER NOT NULL,
    balance NUMBER DEFAULT 0.00,
    CONSTRAINT PK_Account PRIMARY KEY (accountId),
    CONSTRAINT FK_UserId FOREIGN KEY (userId) REFERENCES Users(userId) 
);

--Sequences for auto increment keys
CREATE SEQUENCE userId_seq
MINVALUE 1
MAXVALUE 1000000
INCREMENT BY 1
START WITH 1;
/

CREATE SEQUENCE accountId_seq
MINVALUE 1
MAXVALUE 1000000
INCREMENT BY 1
START WITH 1;
/

--Triggers for auto incrementing keys
CREATE OR REPLACE TRIGGER user_trigger
BEFORE INSERT ON users
FOR EACH ROW
BEGIN
    SELECT userId_seq.NEXTVAL
    INTO :new.userId
    FROM dual;
END;
/

CREATE OR REPLACE TRIGGER account_trigger
BEFORE INSERT ON Accounts
FOR EACH ROW
BEGIN
    SELECT accountId_seq.NEXTVAL
    INTO :new.accountId
    FROM dual;
END;
/

--Populate with data
INSERT INTO users (firstName, lastName, email, password) VALUES ('Joseph', 'Moses', 'joseph.k.moses6@gmail.com', 'password'); 
INSERT INTO users (firstName, lastName, email, password) VALUES ('Peter', 'Chase', 'peter.chase@gmail.com', 'password');
INSERT INTO users (firstName, lastName, email, password) VALUES ('Tom', 'Hemming', 'tom.hemming@gmail.com', 'password');

INSERT INTO accounts (userId, balance) VALUES (1, 100.00);
INSERT INTO accounts (userId, balance) VALUES (1, 1000.00);
INSERT INTO accounts (userId, balance) VALUES (2, 100.00);
INSERT INTO accounts (userId, balance) VALUES (3, 100.00);
INSERT INTO accounts (userId, balance) VALUES (3, DEFAULT);

--Stored functions
CREATE OR REPLACE PROCEDURE total_of_accounts(p_userId IN NUMBER, o_total OUT NUMBER) 
IS
BEGIN
    SELECT SUM(balance) INTO o_total
    FROM Accounts
    WHERE userId = p_userId;
END;
/

select * from users where email = 'hi';
