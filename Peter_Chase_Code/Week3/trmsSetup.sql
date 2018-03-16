---------------
-- DROPPING --
---------------
drop table employee;
drop table department;
drop table trms_location;
drop table reimbursment;
drop table event_type;
drop table attachment;
drop table grading_format;
drop table approval_type;
drop table approval;
drop table rbmt_type;
---------------
-- CREATION --
---------------
CREATE TABLE trms_location(
    loc_id NUMBER(10) PRIMARY KEY,
    loc_country VARCHAR2(32) NOT NULL,
    loc_city VARCHAR2(32) NOT NULL,
    loc_province VARCHAR(32) NOT NULL,
    loc_postal_code VARCHAR(8) NOT NULL,
    loc_address_1 VARCHAR(32) NOT NULL,
    loc_address_2 VARCHAR(32),
    loc_phone VARCHAR(16)
);
CREATE SEQUENCE loc_id_seq
    MINVALUE 1
    MAXVALUE 9999999999
    INCREMENT BY 1
    START WITH 1;
/
CREATE OR REPLACE TRIGGER loc_id_trigger
    BEFORE INSERT ON trms_location
    FOR EACH ROW
    
    BEGIN
        SELECT loc_id_seq.NEXTVAL
        INTO:new.loc_id
        FROM dual;
    END;
/

CREATE TABLE department(
    dep_id NUMBER(10) PRIMARY KEY,
    dep_name VARCHAR(64)
);
CREATE SEQUENCE dep_iq_seq
    MINVALUE 1
    MAXVALUE 9999999999
    INCREMENT BY 1
    START WITH 1;
/
CREATE OR REPLACE TRIGGER dep_id_trigger
    BEFORE INSERT ON department
    FOR EACH ROW
    
    BEGIN
        SELECT dep_iq_seq.NEXTVAL
        INTO:new.dep_id
        FROM dual;
    END;
/


CREATE TABLE employee(
    emp_id NUMBER(10) PRIMARY KEY,
    supervisor_emp_id NUMBER(10),
    dep_head_emp_id NUMBER(10),
    dep_id NUMBER(10) NOT NULL,
    CONSTRAINT dep_id
        FOREIGN KEY(dep_id) REFERENCES department(dep_id)
        ON DELETE CASCADE,
    loc_id NUMBER(10) NOT NULL,
    CONSTRAINT loc_id
        FOREIGN KEY(loc_id) REFERENCES trms_location(loc_id)
        ON DELETE CASCADE,
    emp_is_dep_head NUMBER(1) NOT NULL,
    emp_email VARCHAR2(64) NOT NULL,
    emp_password VARCHAR2(64) NOT NULL,
    emp_avaliable_rbmt NUMBER(5, 2) NOT NULL,
    emp_phone VARCHAR2(10)
);
CREATE SEQUENCE emp_id_seq
    MINVALUE 1
    MAXVALUE 9999999999
    INCREMENT BY 1
    START WITH 1;
/
CREATE OR REPLACE TRIGGER emp_id_trigger
    BEFORE INSERT ON employee
    FOR EACH ROW
    
    BEGIN
        SELECT emp_id_seq.NEXTVAL
        INTO:new.emp_id
        FROM dual;
    END;
/


CREATE TABLE grading_format(
    grad_id NUMBER(10) PRIMARY KEY,
    grad_name VARCHAR2(32) NOT NULL UNIQUE,
    grad_cutoff VARCHAR2(32)
);
CREATE SEQUENCE grad_id_seq
    MINVALUE 1
    MAXVALUE 9999999999
    INCREMENT BY 1
    START WITH 1;
/
CREATE OR REPLACE TRIGGER grad_id_trigger
    BEFORE INSERT ON grading_format
    FOR EACH ROW
    
    BEGIN
        SELECT grad_id_seq.NEXTVAL
        INTO:new.grad_id
        FROM dual;
    END;
/


CREATE TABLE approval_type(
    aprv_id NUMBER(10) PRIMARY KEY,
    aprv_status VARCHAR2(16) NOT NULL UNIQUE
);
CREATE SEQUENCE aprv_id_seq
    MINVALUE 1
    MAXVALUE 9999999999
    INCREMENT BY 1
    START WITH 1;
/
CREATE OR REPLACE TRIGGER aprv_id_trigger
    BEFORE INSERT ON approval_type
    FOR EACH ROW
    
    BEGIN
        SELECT aprv_id_seq.NEXTVAL
        INTO:new.aprv_id
        FROM dual;
    END;
/


CREATE TABLE event_type(
    evt_id NUMBER(10) PRIMARY KEY,
    evt_name VARCHAR2(32) NOT NULL UNIQUE
);
CREATE SEQUENCE evt_id_seq
    MINVALUE 1
    MAXVALUE 9999999999
    INCREMENT BY 1
    START WITH 1;
/
CREATE OR REPLACE TRIGGER evt_id_trigger
    BEFORE INSERT ON event_type
    FOR EACH ROW
    
    BEGIN
        SELECT evt_id_seq.NEXTVAL
        INTO:new.evt_id
        FROM dual;
    END;
/


CREATE TABLE rbmt_type(
    rtype_id NUMBER(10) PRIMARY KEY,
    rtype_name VARCHAR2(32) NOT NULL UNIQUE,
    rtype_coverage NUMBER(3, 2) NOT NULL
);
CREATE SEQUENCE rtype_id_seq
    MINVALUE 1
    MAXVALUE 9999999999
    INCREMENT BY 1
    START WITH 1;
/
CREATE OR REPLACE TRIGGER rtype_id_trigger
    BEFORE INSERT ON rbmt_type
    FOR EACH ROW
    
    BEGIN
        SELECT rtype_id_seq.NEXTVAL
        INTO:new.rtype_id
        FROM dual;
    END;
/



CREATE TABLE reimbursment(
    rbmt_id NUMBER(10) PRIMARY KEY,
    
    rtype_id NUMBER(10) NOT NULL,
    CONSTRAINT rtype_id
        FOREIGN KEY(rtype_id) REFERENCES rbmt_type(rtype_id)
        ON DELETE CASCADE,
        
    emp_id NUMBER(10) NOT NULL,
    CONSTRAINT emp_id
        FOREIGN KEY(emp_id) REFERENCES employee(emp_id)
        ON DELETE CASCADE,
        
    evt_id NUMBER(10) NOT NULL,
    CONSTRAINT evt_id
        FOREIGN KEY(evt_id) REFERENCES event_type(evt_id)
        ON DELETE CASCADE,
    
    grad_id NUMBER(10) NOT NULL,
    CONSTRAINT grad_id
        FOREIGN KEY(grad_id) REFERENCES grading_format(grad_id)
        ON DELETE CASCADE,
        
    evt_loc_id NUMBER(10) NOT NULL,
    CONSTRAINT evt_loc_id
        FOREIGN KEY(evt_loc_id) REFERENCES trms_location(loc_id)
        ON DELETE CASCADE,
        
    rbmt_event_date DATE NOT NULL,
    rbmt_course_date DATE NOT NULL,
    rbmt_desc VARCHAR2(1024),
    rbmt_cost NUMBER(7, 2) NOT NULL,
    rbmt_award_amount NUMBER(7, 2) NOT NULL,
    rbmt_submission_date DATE NOT NULL
);
CREATE SEQUENCE rbmt_id_seq
    MINVALUE 1
    MAXVALUE 9999999999
    INCREMENT BY 1
    START WITH 1;
/
CREATE OR REPLACE TRIGGER rbmt_id_trigger
    BEFORE INSERT ON reimbursment
    FOR EACH ROW
    
    BEGIN
        SELECT rbmt_id_seq.NEXTVAL
        INTO:new.rbmt_id
        FROM dual;
    END;
/



CREATE TABLE attachment(
    atcmt_id NUMBER(10) PRIMARY KEY,
    rbmt_id NUMBER(10) NOT NULL,
    CONSTRAINT rbmt
        FOREIGN KEY(rbmt_id) REFERENCES reimbursment(rbmt_id)
        ON DELETE CASCADE,
    
    atcmt_data BLOB NOT NULL,
    atcmt_filename VARCHAR2(32) NOT NULL
);
CREATE SEQUENCE atcmt_id_seq
    MINVALUE 1
    MAXVALUE 9999999999
    INCREMENT BY 1
    START WITH 1;
/
CREATE OR REPLACE TRIGGER atcmt_id_trigger
    BEFORE INSERT ON attachment
    FOR EACH ROW
    
    BEGIN
        SELECT atcmt_id_seq.NEXTVAL
        INTO:new.atcmt_id
        FROM dual;
    END;
/



CREATE TABLE approval(
    apvl_id NUMBER(10) PRIMARY KEY,
    
    rbmt_id NUMBER(10) NOT NULL,
    CONSTRAINT rbmt_id
        FOREIGN KEY(rbmt_id) REFERENCES reimbursment(rbmt_id)
        ON DELETE CASCADE,
    
    apvl_emp_id NUMBER(10) NOT NULL,
    CONSTRAINT apvl_emp_id
        FOREIGN KEY(apvl_emp_id) REFERENCES employee(emp_id)
        ON DELETE CASCADE,
        
    aprv_id NUMBER(10) NOT NULL,
        CONSTRAINT aprv_id
        FOREIGN KEY(aprv_id) REFERENCES approval_type(aprv_id)
        ON DELETE CASCADE,
        
    apvl_approved NUMBER(1),
    
    apvl_reason VARCHAR2(32)
);
CREATE SEQUENCE apvl_id_seq
    MINVALUE 1
    MAXVALUE 9999999999
    INCREMENT BY 1
    START WITH 1;
/
CREATE OR REPLACE TRIGGER apvl_id_trigger
    BEFORE INSERT ON approval
    FOR EACH ROW
    
    BEGIN
        SELECT apvl_id_seq.NEXTVAL
        INTO:new.apvl_id
        FROM dual;
    END;
/
---------------
-- INSERTION --
---------------

