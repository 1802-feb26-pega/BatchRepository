--3.0	SQL Functions 6
--In this section you will be using the Oracle system functions, as well as your own functions, to perform various actions against the database

--3.1 System Defined Functions
--Task – Create a function that returns the current time.
CREATE OR REPLACE FUNCTION get_current_time
RETURN TIMESTAMP IS l_systimestamp TIMESTAMP;

BEGIN
    SELECT systimestamp
    INTO l_systimestamp
    FROM dual;
    RETURN l_systimestamp;
END;
/
SELECT get_current_time() FROM dual;

--Task – create a function that returns the length of a mediatype from the mediatype table
CREATE OR REPLACE FUNCTION get_mediatype_length (l_mediatypeId IN NUMBER)
RETURN NUMBER IS l_mediatype_length NUMBER;

BEGIN
    SELECT LENGTH(name)
    INTO l_mediatype_length
    FROM mediatype mt
    WHERE mt.MEDIATYPEID = l_mediatypeId;
    RETURN l_mediatype_length;
END;
/

SELECT get_mediatype_length(1) FROM dual;

--3.2 System Defined Aggregate Functions
--Task – Create a function that returns the average total of all invoices
CREATE OR REPLACE FUNCTION get_avg_invoice_total
RETURN NUMBER IS l_avg_invoice_total NUMBER(5,2);
BEGIN
    SELECT AVG(total)
    INTO l_avg_invoice_total
    FROM invoice;
    RETURN l_avg_invoice_total;
END;
/

SELECT get_avg_invoice_total() FROM dual;

--Task – Create a function that returns the most expensive track
CREATE OR REPLACE FUNCTION most_expensive_track
RETURN sys_refcursor as l_track sys_refcursor;

BEGIN
    OPEN l_track FOR
        SELECT t.trackId, t.name, t.unitPrice
        FROM track t
        WHERE unitPrice = ( SELECT MAX(unitPrice)
                            FROM track);
    RETURN l_track;                                    
END;
/

SELECT most_expensive_track() FROM dual;

--3.3 User Defined Scalar Functions
--Task – Create a function that returns the average price of invoiceline items in the invoiceline table
CREATE OR REPLACE FUNCTION get_average_price_invoiceLine
RETURN NUMBER AS l_average_price NUMBER(5, 2);

BEGIN
    SELECT AVG(unitPrice)
    INTO l_average_price
    FROM invoiceLine;
    RETURN l_average_price;
END;
/

SELECT get_average_price_invoiceLine() FROM dual;

--3.4 User Defined Table Valued Functions
--Task – Create a function that returns all employees who are born after 1968.
CREATE OR REPLACE FUNCTION get_employees_born_after_1968
RETURN sys_refcursor as l_employees sys_refcursor;

BEGIN
    OPEN l_employees FOR
        SELECT e.employeeId, e.firstName, e.lastName, e.birthDate
        FROM employee e
        WHERE birthDate > DATE '1968-12-31';
    RETURN l_employees;                                    
END;
/

SELECT get_employees_born_after_1968 FROM dual;



