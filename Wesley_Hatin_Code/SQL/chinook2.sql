CREATE OR REPLACE PROCEDURE get_all_artists(cursorParam OUT SYS_REFCURSOR)
IS 
BEGIN
    OPEN cursorParam FOR
    SELECT * FROM artist;
END;
/

DECLARE
  l_cursor  SYS_REFCURSOR;

BEGIN
    get_all_artists(cursorparam=>l_cursor/*REF CURSOR*/);
END;
/

Variable rc refcursor;
execute get_all_artists(:rc)
print rc;