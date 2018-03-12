CREATE TABLE users( 
    username varchar2(50) NOT NULL,
    firstname varchar2(50) NULL,
    lastname varchar2(50) NULL,
    password varchar2(50) NOT NULL,
    CONSTRAINT username_pk PRIMARY KEY (username)
);

CREATE TABLE accounts(
    accountname varchar2(50) NOT NULL,
    username varchar2(50) NOT NULL,
    balance binary_double NOT NULL,
    CONSTRAINT accountname_pk PRIMARY KEY (accountname),
    CONSTRAINT user_fk FOREIGN KEY (username)
    REFERENCES users(username)
);

CREATE OR REPLACE PROCEDURE user_check(user_name IN varchar2, 
    result OUT int)
    IS
        uname varchar2(50) := ' ';
    BEGIN
        result := 0;
        SELECT username
        INTO uname
        FROM users
        WHERE username = user_name;
        result := 1;
        
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
              result := 0;
    END; 
/

CREATE OR REPLACE PROCEDURE password_check(user_name IN varchar2, 
    pass_word IN varchar2, result OUT int)
    IS
        pword varchar2(50) := ' ';
    BEGIN
        result := 0;
        SELECT password
        INTO pword
        FROM users
        WHERE username = user_name;
        
        IF pword = pass_word THEN
            result := 1;
        END IF;
        
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
              result := 0;
    END; 
/

CREATE OR REPLACE PROCEDURE account_check(user_name IN varchar2, 
    account_name IN varchar2, result OUT int)
    IS
        cursor acclist IS
            SELECT accountname
            FROM accounts
            WHERE username = user_name;
        
    BEGIN
    
        result := 0;
        FOR acc IN acclist 
        LOOP
            IF acc.accountname = account_name THEN
                result := 1;
            END IF;
        END LOOP;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
              result := 0;
    END;
/
