/*
PL/SQL = Procedural Langauge extension of SQL

    Stored functions
        - Definition: used to compute some value
        - must have a return value
        - allowed parameters: INPUT
        - error handling is not possible
        - DML & DDL statements are not supported
        - cannot fire triggers
        - can be used in SELECT, WHERE, and HAVING clauses
        - cannot call stored procedures, but can (maybe) call other functions
        - cannot use the TIMESTAMP datatype
        - cannot use print commands
        
    Stored Procedures
        - Definition: used to prefor a specific task
        - may or may not return a value
        - allowed parameters: INPUT, OUTPUT, INPUT/OUTPUT
        - error handling is possible using try/catch block(s)
        - DML & DDL statements are supported
        - can fire triggers
        - cannot be used in SELECT, WHERE, and HAVING clauses
        - can call stored functions
        - can use ALL datatypes
        -can use print commands
    
    
    Triggers
        - Definition: stored program in PL/SQL which is automatically
                      executed upon the occurrence of some event
                      (such as an INSERT into a table).
*/ 

--Create a function that returns the current time
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
