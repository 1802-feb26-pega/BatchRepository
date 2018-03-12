/* SEQUENCE */
CREATE SEQUENCE artist_seq
 START WITH     276
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;


/* TRIGGER */
CREATE OR REPLACE TRIGGER artist_trigger
BEFORE INSERT ON artist
FOR EACH ROW

BEGIN
    SELECT artist_seq.NEXTVAL
    INTO :new.artistid
    FROM dual;
END;
/

DROP TRIGGER artist_trigger;

INSERT INTO artist VALUES (0, 'This is an Artist');