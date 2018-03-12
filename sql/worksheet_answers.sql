--2.1

SELECT * 
FROM employee;

SELECT *
FROM employee
WHERE lastname = 'King';

SELECT *
FROM employee
WHERE 
(
  firstname = 'Andrew'
  AND
  reportsto IS NULL
);
------------------
--2.2

SELECT *
FROM album
ORDER BY title;

SELECT city, firstname
FROM customer
ORDER BY city;
--------------------
--2.3

INSERT INTO Genre (genreid, name)
values (26, 'Djent');

INSERT INTO Genre (genreid, name)
values (27, 'Slam Metal');
--------------------
--2.4
UPDATE customer
SET firstname = 'Robert',
    lastname = 'Walter'
WHERE firstname = 'Aaron'
      AND
      lastname = 'Mitchell';

UPDATE artist
SET name = 'CCR'
WHERE name = 'Creedence Clearwater Revival';
--------------------
--2.5
SELECT *
FROM invoice
WHERE billingaddress LIKE 'T%';
--------------------
--2.6
SELECT *
FROM invoice
WHERE total BETWEEN 15 AND 20;

SELECT *
FROM employee
WHERE hiredate BETWEEN DATE '2003-06-01' AND DATE '2004-03-01';

-------------------
--2.7

--"Remove the foreign key constraints"

DELETE FROM customer
WHERE 
(
  firstname = 'Robert'
  AND
  lastname = 'Walter'
);
-------------------
--3.2
create or replace FUNCTION avg_invoice_price
RETURN NUMBER
IS l_avg_price NUMBER;
BEGIN
  SELECT AVG(total)
  INTO l_avg_price
  FROM invoice;
  RETURN l_avg_price;
END;
/
-------------------
--3.3
-------------------
--3.4
CREATE OR REPLACE FUNCTION get_emp_names()
RETURN SYS_REFCURSOR
IS l_cursorParam SYS_REFCURSOR
BEGIN
  OPEN l_cursorParam FOR
  SELECT firstname, lastname FROM employee;
END;
/
-------------------
--4.1
CREATE OR REPLACE PROCEDURE get_emp_names(cursorParam OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN cursorParam FOR
  SELECT firstname, lastname
  FROM employee;
END;
-------------------
--4.2

CREATE OR REPLACE PROCEDURE update_emp(
                              id IN NUMBER
                              fn IN VARCHAR2,
                              ln IN VARCHAR2,
                              e IN VARCHAR2,
                              pn IN VARCHAR2
                            )
IS
BEGIN
  UPDATE employees
  SET firstname = fn,
      lastname = ln,
      email = e,
      phonenumber = pn
  WHERE employee_id = id;
END;
-------------------
--4.3

CREATE OR REPLACE PROCEDURE get_namecomp(cursorParam OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN cursorParam FOR
  SELECT name, company
  FROM employee;
END;

-------------------
--5.0
START TRANSACTION;
  ALTER TABLE invoice
  DROP CONSTRAINT FK_INVOICECUSTOMERID; 

  ALTER TABLE invoiceline
  DROP CONSTRAINT FK_INVOICELINEINVOICEID;

  DELETE FROM invoice
  WHERE invoiceid = 2;
COMMIT;

CREATE OR REPLACE PROCEDURE trs_customer_ins
IS
BEGIN
  START TRANSACTION;
  INSERT INTO customer
  VALUES (666, 
          'Plamp', 
          'Goit', 
          'Delectamentum Coffee Roasters',
          '6017 Plamping Goit Drive',
          'Hell',
          'MI',
          'United States',
          '66666',
          '7089996017',
          '7088886017',
          'goit@goit.com',
          '3');
  COMMIT;
END

-------------------
--6.1
CREATE OR REPLACE TRIGGER artist_after_ins 
BEFORE INSERT ON artists
FOR EACH ROW
BEGIN
  SELECT artist_seq.NEXTVAL
  INTO   :new.artist_id
  FROM   dual;
END;

CREATE OR REPLACE TRIGGER album_after_ins 
AFTER DELETE ON artists
FOR EACH ROW
BEGIN
  SELECT album_seq.NEXTVAL
  FROM   dual;
END;

CREATE OR REPLACE TRIGGER cst_after_ins 
AFTER UPDATE ON customers
FOR EACH ROW
BEGIN
  SELECT cst_seq.NEXTVAL
  FROM   dual;
END;

-------------------
--7.1
SELECT customer.customerid, invoice.invoiceid
FROM customer
INNER JOIN invoice ON customer.customerid = invoice.customerid;

SELECT customer.customerid, customer.firstname, customer.lastname, invoice.invoiceid, invoice.total
FROM customer
LEFT OUTER JOIN invoice ON customer.customerid = invoice.customerid
ORDER BY customer.customerid;

SELECT customer.customerid, customer.firstname, customer.lastname, invoice.invoiceid, invoice.total
FROM customer
FULL OUTER JOIN invoice ON customer.customerid = invoice.customerid
ORDER BY customer.customerid;

SELECT artist.name, album.title
FROM artist
RIGHT JOIN album ON artist.artistid = album.artistid;

SELECT * 
FROM album           --or 'FROM album, artist' without ORDER BY
CROSS JOIN artist
ORDER BY artist.name;

SELECT *
FROM employee A, employee B
WHERE A.reportsto = B.reportsto;

SELECT *
FROM employee A
JOIN employee B
WHERE A.reportsto = B.reportsto;

--------------
--PL/SQL

CREATE OR REPLACE FUNCTION get_current_time 
RETURN TIMESTAMP 
IS l_systimestamp TIMESTAMP;
BEGIN
  SELECT systimestamp
  INTO l_systimestamp
  FROM dual;
  RETURN l_systimestamp;
END
/
SELECT get_current_time() FROM dual;
-------------
CREATE OR REPLACE PROCEDURE get_all_artists(cursorParam OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN cursorParam FOR
  SELECT * FROM artist;
END;
/
