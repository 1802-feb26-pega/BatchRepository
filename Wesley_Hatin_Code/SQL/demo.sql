CREATE OR REPLACE FUNCTION get_current_time
RETURN TIMESTAMP IS l_systimestamp TIMESTAMP;

BEGIN
    SELECT systimestamp
    INTO l_systimestamp
    FROM dual;
    RETURN l_systimestamp;
END;
/

CREATE OR REPLACE FUNCTION do_something
RETURN TIMESTAMP IS n_systimestamp TIMESTAMP;

BEGIN
    SELECT systimestamp
    INTO n_systimestamp
    FROM dual;
    RETURN n_systimestamp+get_current_time();
END;
/

SELECT do_something FROM dual;