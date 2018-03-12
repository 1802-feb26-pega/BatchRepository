/* 3.1 System Defined Functions
Task – Create a function that returns the current time.
Task – create a function that returns the length of a mediatype from the mediatype table */
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

CREATE OR REPLACE FUNCTION get_mediatype_length(l_mediatypeid NUMBER)
RETURN NUMBER AS l_mediatype_length NUMBER;

BEGIN
    SELECT LENGTH(name)
    INTO l_mediatype_length
    FROM mediatype mt
    WHERE mt.mediatypeid = l_mediatypeid;
    RETURN l_mediatype_length;
END;
/

SELECT get_mediatype_length(1) FROM dual;

/* 3.2 System Defined Aggregate Functions
Task – Create a function that returns the average total of all invoices
Task – Create a function that returns the most expensive track */

/* 3.3 User Defined Scalar Functions
Task – Create a function that returns the average price of invoiceline items in the invoiceline table */

/* 3.4 User Defined Table Valued Functions
Task – Create a function that returns all employees who are born after 1968.*/
