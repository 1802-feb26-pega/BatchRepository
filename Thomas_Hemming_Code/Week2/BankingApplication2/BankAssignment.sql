DROP TABLE accounts;
DROP TABLE users;

CREATE TABLE users (
    userid NUMBER,
    firstname VARCHAR2(100),
    lastname VARCHAR2(100),
    email VARCHAR2(100) UNIQUE,
    password VARCHAR2(200),
    CONSTRAINT user_pk PRIMARY KEY(userid)
);

CREATE TABLE accounts (
    accountid NUMBER,
    userid NUMBER,
    balance NUMBER(16,2),
    CONSTRAINT account_pk PRIMARY KEY(accountid),
    CONSTRAINT fk_user
        FOREIGN KEY (userid)
        REFERENCES users(userid)
);

DROP SEQUENCE userid_seq;

/* SEQUENCE */
CREATE SEQUENCE userid_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;


/* TRIGGER */
CREATE OR REPLACE TRIGGER userid_trigger
BEFORE INSERT ON users
FOR EACH ROW

BEGIN
    SELECT userid_seq.NEXTVAL
    INTO :new.userid
    FROM dual;
END;
/

DROP SEQUENCE accountid_seq;

/* SEQUENCE */
CREATE SEQUENCE accountid_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;


/* TRIGGER */
CREATE OR REPLACE TRIGGER accountid_trigger
BEFORE INSERT ON accounts
FOR EACH ROW

BEGIN
    SELECT accountid_seq.NEXTVAL
    INTO :new.accountid
    FROM dual;
END;
/

/* Total balance of accounts */
SELECT SUM(balance)
FROM accounts
WHERE userid = 1;

DROP PROCEDURE get_accounts_total;

CREATE OR REPLACE PROCEDURE get_accounts_total(
	   l_userid IN NUMBER,
	   l_accounts_total OUT NUMBER)
IS
BEGIN
    SELECT SUM(balance)
    INTO l_accounts_total
    FROM accounts a
    WHERE a.userid = l_userid;
END;
/




/* INSERT INTO users VALUES (0, 'Thomas', 'Hemming', 'hemming.thomas@gmail.com', 'password');
INSERT INTO users VALUES (0, 'Guy', 'Person', 'guy.person@gmail.com', 'password');

INSERT INTO accounts VALUES(0, 1, 700.00);
INSERT INTO accounts VALUES(0, 2, 500.00);
INSERT INTO accounts VALUES(0, 1, 300.00); */

/* DELETE FROM users
WHERE userid=1;

TRUNCATE TABLE accounts;
TRUNCATE TABLE users; */

