--CREATE SEQUENCE artist_seq
--MINVALUE 1
--MAXVALUE 999999999
--INCREMENT BY 1
--START WITH 276;
--/

--CREATE OR REPLACE TRIGGER artist_trgr
--BEFORE INSERT ON artist
--FOR EACH ROW
--BEGIN
--    SELECT artist_seq.NEXTVAL
--    INTO :new.artistid
--    FROM dual;
--END;
--/

INSERT INTO artist(name) VALUES ('Filly In The Box');
SELECT * FROM artist ORDER BY artistid DESC;
commit;