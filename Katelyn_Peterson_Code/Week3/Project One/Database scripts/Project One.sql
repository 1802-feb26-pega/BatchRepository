-- attachment trigger
CREATE OR REPLACE TRIGGER attach_trigger 
BEFORE INSERT ON attachments
FOR EACH ROW

BEGIN
    SELECT attachment_seq.NEXTVAL
    INTO :new.attachid
    FROM dual;
END;
/

-- department trigger
CREATE OR REPLACE TRIGGER department_trigger 
BEFORE INSERT ON department
FOR EACH ROW

BEGIN
    SELECT department_seq.NEXTVAL
    INTO :new.departmentid
    FROM dual;
END;
/

-- employee trigger
CREATE OR REPLACE TRIGGER employee_trigger 
BEFORE INSERT ON employee
FOR EACH ROW

BEGIN
    SELECT employee_seq.NEXTVAL
    INTO :new.employeeid
    FROM dual;
END;
/

-- form trigger
CREATE OR REPLACE TRIGGER form_trigger 
BEFORE INSERT ON form
FOR EACH ROW

BEGIN
    SELECT form_seq.NEXTVAL
    INTO :new.formid
    FROM dual;
END;
/
