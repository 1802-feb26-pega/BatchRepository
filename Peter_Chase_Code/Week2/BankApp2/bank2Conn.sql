---------- CREATION ----------
------------ USER ------------
DROP TABLE baccount;
DROP TABLE buser;
CREATE TABLE buser(
    user_id NUMBER(10) PRIMARY KEY,
    user_name VARCHAR2(32) NOT NULL UNIQUE,
    user_email VARCHAR2(64) NOT NULL UNIQUE,
    user_password VARCHAR2(64) NOT NULL
);

DROP SEQUENCE user_id_seq;
CREATE SEQUENCE user_id_seq
    MINVALUE 1
    MAXVALUE 9999999999
    INCREMENT BY 1
    START WITH 1;
/

CREATE OR REPLACE TRIGGER user_id_trigger
    BEFORE INSERT ON buser
    FOR EACH ROW
    
    BEGIN
        SELECT user_id_seq.NEXTVAL
        INTO:new.user_id
        FROM dual;
    END;
/
------------------------------
--------- ACCOUNT ------------
CREATE TABLE baccount(
   account_id NUMBER(10) PRIMARY KEY,
   buser_id NUMBER(10),
   CONSTRAINT buser_id
        FOREIGN KEY(buser_id) REFERENCES buser(user_id)
        ON DELETE CASCADE,
   account_name VARCHAR(32) NOT NULL,
   account_pin VARCHAR2(4) NOT NULL,
   account_balance NUMBER(16, 2) NOT NULl
);

DROP SEQUENCE account_id_seq;
CREATE SEQUENCE account_id_seq
    MINVALUE 1
    MAXVALUE 9999999999
    INCREMENT BY 1
    START WITH 1;
/

CREATE OR REPLACE TRIGGER account_id_trigger
    BEFORE INSERT ON baccount
    FOR EACH ROW
    
    BEGIN
        SELECT account_id_seq.NEXTVAL
        INTO:new.account_id
        FROM dual;
    END;
/
------------------------------
------------------------------
--------- MOCK DATA ----------
------------ USER ------------
INSERT INTO buser (user_name, user_email, user_password) VALUES (
    'dummy',
    'dummy@mail.com',
    'password123'
);

INSERT INTO buser (user_name, user_email, user_password) VALUES (
    'memes',
    'memes@gmail.com',
    'password123'
);
commit;
------------------------------
--------- ACCOUNT ------------
INSERT INTO baccount (buser_id, account_name, account_pin, account_balance) VALUES (
    1, 'checking', '1111', 123.45);
    
INSERT INTO baccount (buser_id, account_name, account_pin, account_balance) VALUES (
    2, 'checking', '1111', 420.00);
    
INSERT INTO baccount (buser_id, account_name, account_pin, account_balance) VALUES (
    2, 'saving', '1111', 200.00);
    
INSERT INTO baccount (buser_id, account_name, account_pin, account_balance) VALUES (
    2, 'spare change', '1111', 3.50);

commit;
------------------------------
------------------------------

--------- SELECTION ----------
SELECT * FROM buser;
SELECT * FROM baccount;
SELECT * FROM baccount WHERE buser_id = 2;
SELECT * FROM baccount WHERE baccount_id = 2;
SELECT * FROM buser INNER JOIN baccount ON buser.buser_id = baccount.buser_id;
------------------------------


UPDATE baccount SET buser_id = 2, baccount_name = 'checking', baccount_pin = '1111', baccount_balance = 400.00 WHERE baccount_id = 2;