---------- CREATION ----------
DROP TABLE account;
CREATE TABLE account(
    act_id NUMBER(10) PRIMARY KEY,
    act_name VARCHAR2(32) NOT NULL UNIQUE,
    act_email VARCHAR2(64) NOT NULL UNIQUE,
    act_password VARCHAR2(64) NOT NULL,
    act_balance NUMBER(13, 2) NOT NULL
);

DROP SEQUENCE act_id_seq;
CREATE SEQUENCE act_id_seq
    MINVALUE 1
    MAXVALUE 9999999999
    INCREMENT BY 1
    START WITH 1;
/

CREATE OR REPLACE TRIGGER act_id_trigger
    BEFORE INSERT ON account
    FOR EACH ROW
    
    BEGIN
        SELECT act_id_seq.NEXTVAL
        INTO:new.act_id
        FROM dual;
    END;
/
------------------------------

--------- MOCK DATA ----------
INSERT INTO account (act_name, act_email, act_password, act_balance) VALUES (
    'Dummy Account',
    'dummy@mail.com',
    'password123',
    543.32
);

INSERT INTO account (act_name, act_email, act_password, act_balance) VALUES (
    'memes',
    'memes@gmail.com',
    'password123',
    3.50
);
commit;
------------------------------

--------- SELECTION ----------
SELECT * FROM account;
------------------------------
