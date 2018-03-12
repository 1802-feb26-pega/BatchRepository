/* SECTION 2.0 */
SELECT * 
FROM employee;

/* SECTION 2.1 */
SELECT *
FROM employee
WHERE lastname = 'King';

SELECT *
FROM employee
WHERE firstname = 'Andrew' AND reportsto IS NULL;

/* SECTION 2.2 */
SELECT *
FROM album
ORDER BY title DESC;

SELECT firstname
FROM customer
ORDER BY city ASC; /* ascending is default but I put it in anyways for practice */

/* SECTION 2.3 */
INSERT INTO genre VALUES (99, 'Paper');
INSERT INTO genre VALUES (98, 'OldPaper');

INSERT INTO employee VALUES(498, 'Lasty', 'Firsty', 'Duke of Cornwall', NULL, DATE '85-03-01', DATE '10-04-02', '1234 Gloucester Lane', 'Jamestown', 'Virginia', 'United States', '20170', '1234567890', NULL, 'corn@wall.com');
INSERT INTO employee VALUES(497, 'Birghtwood', 'Andersmith', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO customer VALUES(654, 'Forkulus', 'Kniferton', 'MoonCo', '8008 Lunar Cir', 'Orbit', 'Milky', 'Universe', '454545', '7038675309', NULL, 'violentviolet@encourageviolence.edu', NULL);
INSERT INTO customer VALUES(174, 'Twolegs', 'Willy', 'AH', '2731 Texas rd', 'Austin', 'Texas', 'United States', NULL, NULL, NULL, 'email@email.email', NULL);

/* SECTION 2.4 */
UPDATE customer 
SET firstname = 'Robert', 
    lastname = 'Walter'
WHERE firstname = 'Aaron' AND lastname = 'Mitchell';

UPDATE artist
SET name = 'CCR'
WHERE name = 'Creedence Clearwater Revival';

/* SECTION 2.5 */
SELECT * 
FROM invoice
WHERE billingaddress LIKE 'T%'

/* SECTION 2.6 */
SELECT *
FROM invoice
WHERE total BETWEEN 15 AND 50;

SELECT *
FROM employee
WHERE hiredate BETWEEN TO_DATE('2003-6-1 00:00:00','yyyy-mm-dd hh24:mi:ss') AND TO_DATE('2004-3-1 00:00:00', 'yyyy-mm-dd hh24:mi:ss');

/* SECTION 2.7 */
SELECT *
FROM user_constraints
ORDER BY r_constraint_name ASC;

ALTER TABLE invoice
DROP CONSTRAINT fk_invoicecustomerid;

ALTER TABLE invoiceline
DROP CONSTRAINT fk_invoicelineinvoiceid;


ALTER TABLE invoice
ADD CONSTRAINT fk_invoicecustomerid
  FOREIGN KEY (customerid)
  REFERENCES customer (customerid)
  ON DELETE CASCADE;


/*ALTER TABLE invoiceline
ADD CONSTRAINT fk_invoicelineinvoiceid;
  FOREIGN KEY (invoiceid)
  REFERENCES invoice (invoiceid)
  ON DELETE CASCADE;*/

DELETE FROM customer
WHERE firstname = 'Robert' AND lastname = 'Walter';

SELECT *
FROM customer
ORDER BY lastname DESC;

/* SECTION 3.1 */
CREATE OR REPLACE FUNCTION get_current_time
RETURN TIMESTAMP IS l_systimestamp TIMESTAMP;
BEGIN
  SELECT systimestamp  -- predefined variable from oracle
  INTO l_systimestamp
  FROM dual; -- look this up
  RETURN l_systimestamp;
END;
/ -- always put this slash in helps you know where the function ends for DBMS
SELECT get_current_time() FROM dual;

/* SECTION 3.2 */
CREATE OR REPLACE FUNCTION avg_total
  RETURN NUMBER IS avg_val NUMBER(10,2);
BEGIN
  SELECT AVG(total)
  INTO avg_val
  FROM invoice;
  RETURN avg_val;
END;
/
SELECT avg_total() FROM invoice;

CREATE OR REPLACE FUNCTION expensive
  RETURN VARCHAR2 IS trackk VARCHAR2(30);
BEGIN
  SELECT MAX(unitprice)
  INTO trackk
  FROM track;
  RETURN trackk;
END;
/
SELECT expensive() FROM track;

/* SECTION 3.3 */
CREATE OR REPLACE FUNCTION 
/* SECTION 3.4 */

/* SECTION 4.1 */
CREATE OR REPLACE PROCEDURE get_names (
  cursorr OUT SYS_REFCURSOR
)
IS
BEGIN
  OPEN cursorr FOR
  SELECT firstname, lastname
  FROM employee;
END;
/
/* SECTION 4.2 */

/* SECTION 4.3 */

/* SECTION 5.0 */

/* SECTION 6.1 */

/* SECTION 7.1 */
SELECT c.firstname, c.lastname, i.invoiceid
FROM customer c
INNER JOIN invoice i
ON c.customerid = i.customerid;

/* SECTION 7.2 */
SELECT c.customerid, c.firstname, c.lastname, i.invoiceid, i.total
FROM customer c
FULL OUTER JOIN invoice i ON c.customerid = i.customerid;

/* SECTION 7.3 */
SELECT al.title, ar.name
FROM album al
RIGHT JOIN  artist ar ON al.artistid  = ar.artistid;

/* SECTION 7.4 */
SELECT *
FROM artist ar
CROSS JOIN album al
ORDER BY ar.name;

/* SECTION 7.5 */
select *
from employee;
order by reportsto;

SELECT e.firstname, e.lastname, e.title, e2.firstname, e2.lastname, e.title
FROM employee e, employee e2
WHERE e.reportsto <> e2.reportsto;

