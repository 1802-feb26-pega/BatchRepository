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
            IF acc = account_name THEN
                result := 1;
            END IF;
        END LOOP;
    END;
/

