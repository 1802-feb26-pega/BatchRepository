-- Attachment trigger
CREATE OR REPLACE TRIGGER attach_trigger 
BEFORE INSERT ON attachments
FOR EACH ROW

BEGIN
    SELECT attachment_seq.NEXTVAL
    INTO :new.attachid
    FROM dual;
END;
/

-- Department trigger
CREATE OR REPLACE TRIGGER department_trigger 
BEFORE INSERT ON department
FOR EACH ROW

BEGIN
    SELECT department_seq.NEXTVAL
    INTO :new.departmentid
    FROM dual;
END;
/

-- Employee trigger
CREATE OR REPLACE TRIGGER employee_trigger 
BEFORE INSERT ON employee
FOR EACH ROW

BEGIN
    SELECT employee_seq.NEXTVAL
    INTO :new.employeeid
    FROM dual;
END;
/

-- Form trigger
CREATE OR REPLACE TRIGGER form_trigger 
BEFORE INSERT ON forms
FOR EACH ROW

BEGIN
    SELECT form_seq.NEXTVAL
    INTO :new.formid
    FROM dual;
END;
/

-- Add Form
CREATE OR REPLACE PROCEDURE add_form(
employeeid IN forms.employeeid%TYPE,
e_location IN forms.e_location%TYPE,
e_cost IN forms.e_cost%TYPE,
event_type IN forms.event_type%TYPE,
grade_type IN forms.grade_type%TYPE)
IS
BEGIN
    -- Insert New Form
    INSERT INTO forms (employeeid, e_location, e_cost, event_type, grade_type)
    VALUES(employeeid, e_location, e_cost, event_type, grade_type);
END;
/
