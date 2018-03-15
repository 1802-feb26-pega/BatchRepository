--Department Head
CREATE TABLE department_head(
    dep_head_id NUMBER(20),
    first_name VARCHAR2(30),
    last_name VARCHAR2(30),
    department VARCHAR2(30) NOT NULL,
    job_title VARCHAR2(30) NOT NULL,
    CONSTRAINT pk_dep_head_id PRIMARY KEY (dep_head_id)
);

CREATE SEQUENCE dep_head_id_seq
MINVALUE 1
NOMAXVALUE
INCREMENT BY 1
START WITH 1;
/

CREATE OR REPLACE TRIGGER dep_head_id_gen
BEFORE INSERT ON department_head
FOR EACH ROW
BEGIN
    SELECT dep_head_id_seq.NEXTVAL
    INTO :new.dep_head_id
    FROM dual;
END;
/

--BenCo
CREATE TABLE BenCo(
    benco_id NUMBER(20),
    first_name VARCHAR2(30),
    last_name VARCHAR2(30),
    CONSTRAINT pk_benco_id PRIMARY KEY (benco_id)
);

CREATE SEQUENCE benco_id_seq
MINVALUE 1
NOMAXVALUE
INCREMENT BY 1
START WITH 1;
/

CREATE OR REPLACE TRIGGER benco_id_gen
BEFORE INSERT ON BenCo
FOR EACH ROW
BEGIN
    SELECT benco_id_seq.NEXTVAL
    INTO :new.benco_id
    FROM dual;
END;
/

--Direct Supervisor
CREATE TABLE direct_supervisor(
    supervisor_id NUMBER(20),
    first_name VARCHAR2(30) NOT NULL,
    last_name VARCHAR2(30) NOT NULL,
    department VARCHAR2(30) NOT NULL,
    dep_head_id NUMBER(20),
    job_title VARCHAR2(30) NOT NULL,
    CONSTRAINT pk_sup_head_id PRIMARY KEY (supervisor_id),
    CONSTRAINT fk_dep_head_id FOREIGN KEY (dep_head_id) 
        REFERENCES department_head(dep_head_id)
);

CREATE SEQUENCE ds_id_seq
MINVALUE 1
NOMAXVALUE
INCREMENT BY 1
START WITH 1;
/

CREATE OR REPLACE TRIGGER ds_id_gen
BEFORE INSERT ON direct_supervisor
FOR EACH ROW
BEGIN
    SELECT ds_id_seq.NEXTVAL
    INTO :new.supervisor_id
    FROM dual;
END;
/

--EMPLOYEE
CREATE TABLE employee (
    employee_id NUMBER(20),
    first_name VARCHAR2(30) NOT NULL,
    last_name VARCHAR2(30) NOT NULL,
    paid_reimbursments NUMBER(8,2) DEFAULT 0,
    supervisor_id NUMBER(20),
    dep_head_id NUMBER(20),
    benco_id NUMBER(20),
    department VARCHAR2(30),
    job_title VARCHAR2(30),
    CONSTRAINT pk_employee_id PRIMARY KEY (employee_id),
    CONSTRAINT fk_dep_head_id2 FOREIGN KEY (dep_head_id) 
        REFERENCES department_head(dep_head_id),
    CONSTRAINT fk_sup_id2 FOREIGN KEY (supervisor_id)
        REFERENCES direct_supervisor(supervisor_id)
);

CREATE SEQUENCE emp_id_seq
MINVALUE 1
NOMAXVALUE
INCREMENT BY 1
START WITH 1;
/

CREATE OR REPLACE TRIGGER emp_id_gen
BEFORE INSERT ON employee
FOR EACH ROW
BEGIN
    SELECT emp_id_seq.NEXTVAL
    INTO :new.employee_id
    FROM dual;
END;
/

--Event type
CREATE TABLE event_type (
    event_id NUMBER(20),
    cover_rate NUMBER(20) NOT NULL,
    event_name VARCHAR2(30) NOT NULL,
    CONSTRAINT pk_event_id PRIMARY KEY (event_id)
);

--Tuition reimbursement form
CREATE TABLE tr_form (
    form_id NUMBER(20),
    employee_id NUMBER(20),
    event_type NUMBER(20) NOT NULL,
    date_time DATE NOT NULL,
    reason CLOB,
    loc VARCHAR(50) NOT NULL,
    money_request NUMBER(8,2) NOT NULL,
    gs_id NUMBER(20),
    missed_work_time NUMBER(20),
    reimbursment_id NUMBER(20),
    CONSTRAINT pk_form_id PRIMARY KEY (form_id),
    CONSTRAINT fk_employee_id3 FOREIGN KEY (employee_id)
        REFERENCES employee(employee_id) on delete cascade
);

CREATE SEQUENCE trf_id_seq
MINVALUE 1
NOMAXVALUE
INCREMENT BY 1
START WITH 1;
/

CREATE OR REPLACE TRIGGER trf_id_gen
BEFORE INSERT ON tr_form
FOR EACH ROW
BEGIN
    SELECT trf_id_seq.NEXTVAL
    INTO :new.form_id
    FROM dual;
END;
/

--TRF attachment
CREATE TABLE trf_attachment (
    attachment_id NUMBER(20),
    form_id NUMBER(20),
    attachment BLOB NOT NULL,
    notes CLOB,
    CONSTRAINT pk_attachment_id PRIMARY KEY (attachment_id),
    CONSTRAINT fk_form_id4 FOREIGN KEY (form_id)
        REFERENCES tr_form(form_id) on delete cascade
);

CREATE SEQUENCE trf_att_id_seq
MINVALUE 1
NOMAXVALUE
INCREMENT BY 1
START WITH 1;
/

CREATE OR REPLACE TRIGGER trf__att_id_gen
BEFORE INSERT ON trf_attachment
FOR EACH ROW
BEGIN
    SELECT trf_att_id_seq.NEXTVAL
    INTO :new.attachment_id
    FROM dual;
END;
/

--Reimbursement
CREATE TABLE reimbursement (
    re_id NUMBER(20),
    form_id NUMBER(20),
    employee_id NUMBER(20),
    ammount_paid NUMBER(8,2) not null,
    status varchar2(20),
    supervisor_id number(20),
    dep_head_id number(20),
    benco_id number(20),
    constraint pk_re_id primary key (re_id),
    constraint fk_form_id5 foreign key (form_id)
        references tr_form(form_id) ON DELETE CASCADE,
    constraint fk_employee_id5 foreign key (employee_id)
        references employee(employee_id),
    constraint fk_supervisor_id5 foreign key (supervisor_id)
        references direct_supervisor(supervisor_id),
    constraint fk_dep_head_id5 foreign key (dep_head_id)
        references department_head(dep_head_id)
);

CREATE SEQUENCE re_id_seq
MINVALUE 1
NOMAXVALUE
INCREMENT BY 1
START WITH 1;
/

CREATE OR REPLACE TRIGGER re_id_gen
BEFORE INSERT ON reimbursement
FOR EACH ROW
BEGIN
    SELECT re_id_seq.NEXTVAL
    INTO :new.re_id
    FROM dual;
END;
/

--Grade Submission
CREATE TABLE grade_submission (
    gs_id NUMBER(20),
    form_id NUMBER(20),
    notes CLOB,
    CONSTRAINT pk_gs_id PRIMARY KEY (gs_id),
    CONSTRAINT fk_form_id6 FOREIGN KEY (form_id)
        REFERENCES tr_form(form_id) on delete cascade
);

CREATE SEQUENCE gs_id_seq
MINVALUE 1
NOMAXVALUE
INCREMENT BY 1
START WITH 1;
/

CREATE OR REPLACE TRIGGER gs_id_gen
BEFORE INSERT ON grade_submission
FOR EACH ROW
BEGIN
    SELECT gs_id_seq.NEXTVAL
    INTO :new.gs_id
    FROM dual;
END;
/

--GRADE SUBMISSION ATTACHMENT
CREATE TABLE gs_attachment (
    attachment_id NUMBER(20),
    gs_id NUMBER(20),
    attach BLOB NOT NULL,
    notes CLOB,
    CONSTRAINT pk_attachment_id9 PRIMARY KEY (attachment_id),
    CONSTRAINT fk_gs_id9 FOREIGN KEY (gs_id)
        REFERENCES grade_submission(gs_id) ON DELETE CASCADE
);

CREATE SEQUENCE gsa_id_seq
MINVALUE 1
NOMAXVALUE
INCREMENT BY 1
START WITH 1;
/

CREATE OR REPLACE TRIGGER gsa_id_gen
BEFORE INSERT ON gs_attachment
FOR EACH ROW
BEGIN
    SELECT gsa_id_seq.NEXTVAL
    INTO :new.attachment_id
    FROM dual;
END;
/