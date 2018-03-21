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
commit;
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
DROP SEQUENCE loc_id_seq;
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
    dep_name VARCHAR(64) NOT NULL
);
DROP SEQUENCE dep_id_seq;
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
    emp_avaliable_rbmt NUMBER(7, 2) NOT NULL
);
DROP SEQUENCE emp_id_seq;
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
DROP SEQUENCE grad_id_seq;
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
DROP SEQUENCE aprv_id_seq;
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
DROP SEQUENCE evt_id_seq;
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
DROP SEQUENCE rtype_id_seq;
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
DROP SEQUENCE rbmt_id_seq;
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
DROP SEQUENCE atcmt_id_seq;
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
DROP SEQUENCE apvl_id_seq;
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


CREATE TABLE employee_rbmt (
    emp_id NUMBER(10),
    CONSTRAINT bridge_emp_id FOREIGN KEY(emp_id) REFERENCES employee(emp_id) ON DELETE CASCADE,
    rbmt_id NUMBER(10),
    CONSTRAINT bridge_rbmt_id FOREIGN KEY(rbmt_id) REFERENCES reimbursment(rbmt_id) ON DELETE CASCADE,
    PRIMARY KEY(emp_id, rbmt_id)
);


commit;
---------------
-- INSERTION --
---------------
INSERT INTO department(dep_name) VALUES ('management');
INSERT INTO department(dep_name) VALUES ('human resources');
INSERT INTO department(dep_name) VALUES ('sales');
INSERT INTO department(dep_name) VALUES ('marketing');
INSERT INTO department(dep_name) VALUES ('maintenance');
INSERT INTO department(dep_name) VALUES ('benefits');

delete from employee;
INSERT INTO employee (supervisor_emp_id, dep_head_emp_id, dep_id, loc_id, emp_is_dep_head, emp_email, emp_password, emp_avaliable_rbmt)
VALUES (NULL, NULL, 61, 1, 1, 'management@trms.com', 'admin', 1000.00);

INSERT INTO employee (supervisor_emp_id, dep_head_emp_id, dep_id, loc_id, emp_is_dep_head, emp_email, emp_password, emp_avaliable_rbmt)
VALUES (NULL, NULL, 62, 1, 1, 'humanResources@trms.com', 'admin', 1000.00);

INSERT INTO employee (supervisor_emp_id, dep_head_emp_id, dep_id, loc_id, emp_is_dep_head, emp_email, emp_password, emp_avaliable_rbmt)
VALUES (NULL, NULL, 63, 1, 1, 'salest@trms.com', 'admin', 1000.00);

INSERT INTO employee (supervisor_emp_id, dep_head_emp_id, dep_id, loc_id, emp_is_dep_head, emp_email, emp_password, emp_avaliable_rbmt)
VALUES (NULL, NULL, 64, 1, 1, 'marketing@trms.com', 'admin', 1000.00);

INSERT INTO employee (supervisor_emp_id, dep_head_emp_id, dep_id, loc_id, emp_is_dep_head, emp_email, emp_password, emp_avaliable_rbmt)
VALUES (NULL, NULL, 65, 1, 1, 'maintenance@trms.com', 'admin', 1000.00);

INSERT INTO employee (supervisor_emp_id, dep_head_emp_id, dep_id, loc_id, emp_is_dep_head, emp_email, emp_password, emp_avaliable_rbmt)
VALUES (NULL, NULL, 66, 1, 1, 'benco@trms.com', 'admin', 1000.00);
commit;


INSERT INTO trms_location (loc_country, loc_city, loc_province, loc_postal_code, loc_address_1, loc_address_2, loc_phone) VALUES (
    'United States',
    'New York',
    'NY',
    '11357',
    '151-60 20th rd',
    '',
    '4136525514'
);


INSERT INTO trms_location (loc_country, loc_city, loc_province, loc_postal_code, loc_address_1, loc_address_2, loc_phone) VALUES (
    'United States',
    'Manchester',
    'VT',
    '05255',
    '230 Kent Hill rd',
    '',
    '8029382743'
);

INSERT INTO trms_location (loc_country, loc_city, loc_province, loc_postal_code, loc_address_1, loc_address_2, loc_phone) VALUES (
    'United States',
    'Memphis',
    'TN',
    '38152',
    '3720 Alumni Ave',
    '',
    '9016782000'
);

ALTER TABLE rbmt_type MODIFY rtype_coverage NUMBER(3, 2);
ALTER TABLE reimbursment MODIFY emp_id NUMBER(10) NULL;
ALTER TABLE reimbursment ADD CONSTRAINT emp_id FOREIGN KEY(emp_id) REFERENCES employee(emp_id) ON DELETE CASCADE;
ALTER TABLE reimbursment MODIFY rbmt_award_amount NULL;

INSERT INTO rbmt_type (rtype_name, rtype_coverage) VALUES (
    'University Course', 0.80
);

INSERT INTO rbmt_type (rtype_name, rtype_coverage) VALUES (
    'Seminar', 0.60
);

INSERT INTO rbmt_type (rtype_name, rtype_coverage) VALUES (
    'Certification Preparation Class', 0.75
);

INSERT INTO rbmt_type (rtype_name, rtype_coverage) VALUES (
    'Certification', 1.00
);

INSERT INTO rbmt_type (rtype_name, rtype_coverage) VALUES (
    'Technical Training', 0.90
);

INSERT INTO rbmt_type (rtype_name, rtype_coverage) VALUES (
    'Other', 0.30
);

INSERT INTO grading_format(grad_name, grad_cutoff) VALUES (
    'Letter', 'C'
);

INSERT INTO grading_format(grad_name, grad_cutoff) VALUES (
    'Number', '75'
);

INSERT INTO grading_format(grad_name, grad_cutoff) VALUES (
    'Pass/Fail', 'P'
);

DELETE FROM reimbursment;
INSERT INTO reimbursment (rtype_id, grad_id, evt_loc_id, rbmt_event_date,
rbmt_course_date, rbmt_desc, rbmt_cost, rbmt_submission_date) VALUES
(
    1, 1, 5, DATE '2018-04-03', DATE '2018-08-29', 'University Robotics Course', 800.00, DATE '2018-03-03'
);

INSERT INTO reimbursment (rtype_id, grad_id, evt_loc_id, rbmt_event_date,
rbmt_course_date, rbmt_desc, rbmt_cost, rbmt_submission_date) VALUES
(
    4, 3, 5, DATE '2018-04-03', DATE '2018-08-29', 'OCA Java Certification', 250.00, DATE '2018-03-03'
);

INSERT INTO reimbursment (rtype_id, grad_id, evt_loc_id, rbmt_event_date,
rbmt_course_date, rbmt_desc, rbmt_cost, rbmt_submission_date) VALUES
(
    5, 2, 5, DATE '2018-04-03', DATE '2018-08-29', 'Training in Data Science with Hadoop', 100.00, DATE '2018-03-03'
);

commit;


INSERT INTO approval_type (aprv_status) VALUES ('Pending');
INSERT INTO approval_type (aprv_status) VALUES ('Denied');
INSERT INTO approval_type (aprv_status) VALUES ('Accepted');
INSERT INTO approval_type (aprv_status) VALUES ('Exceeded');
commit;

INSERT INTO employee (supervisor_emp_id, dep_head_emp_id, dep_id, loc_id, emp_is_dep_head, emp_email, emp_password, emp_avaliable_rbmt)
VALUES (NULL, NULL, 66, 1, 1, 'benco@trms.com', 'admin', 1000.00);

INSERT INTO employee (supervisor_emp_id, dep_head_emp_id, dep_id, loc_id, emp_is_dep_head, emp_email, emp_password, emp_avaliable_rbmt)
VALUES (NULL, NULL, 61, 1, 1, 'theBoss@mail.com', 'password123', 1000.00);


INSERT INTO employee (supervisor_emp_id, dep_head_emp_id, dep_id, loc_id, emp_is_dep_head, emp_email, emp_password, emp_avaliable_rbmt)
VALUES (NULL, NULL, 62, 2, 1, 'theOtherBoss@mail.com', 'password123', 1000.00);

INSERT INTO employee (supervisor_emp_id, dep_head_emp_id, dep_id, loc_id, emp_is_dep_head, emp_email, emp_password, emp_avaliable_rbmt)
VALUES (1, 1, 62, 2, 0, 'theSlave@mail.com', 'password123', 1000.00);



ALTER TABLE rbmt_type MODIFY rtype_coverage NUMBER(5, 2);
ALTER TABLE approval MODIFY apvl_approved NUMBER(10, 0);
ALTER TABLE approval RENAME COLUMN apvl_approved TO apvl_approved_id;
ALTER TABLE approval MODIFY apvl_reason VARCHAR2(128) NULL;
ALTER TABLE reimbursment DROP COLUMN emp_id;


commit;
---------------
-- SELECTION --
---------------
SELECT * FROM department;
SELECT * FROM trms_location;
SELECT * FROM employee;
SELECT * FROM rbmt_type;
SELECT * FROM reimbursment;
SELECT * FROM grading_format;
SELECT * FROM employee_rbmt;
SELECT * FROM approval;
SELECT * FROM approval_type;

SELECT apvl_id, apvl_approved_id FROM approval INNER JOIN employee ON approval.apvl_approved_id = employee.emp_id WHERE apvl_approved_id = 4;
SELECT * FROM reimbursment INNER JOIN approval ON reimbursment.rbmt_id = approval.rbmt_id;

DELETE FROM approval;
DELETE FROM employee_rbmt;
commit;