CREATE TABLE employees( 
    employee_id number NOT NULL,
    first_name varchar2(50) NULL,
    last_name varchar2(50) NULL,
    email varchar2(50)NULL,
    password varchar2(50) NOT NULL,
    reimburse_remain number NOT NULL,
    reimburse_pending number NULL,
    date_of_birth DATE NOT NULL,
    job_level number NOT NULL,
    department number NOT NULL,
    address varchar2(50) NOT NULL,
    city varchar2(50) NOT NULL,
    state varchar2(50) NOT NULL,
    
    CONSTRAINT employee_pk PRIMARY KEY (employee_id),
    CONSTRAINT department_fk FOREIGN KEY (department)
        REFERENCES departments(dept_id)
    
);

CREATE TABLE reimbursements(
    reimb_id number NOT NULL,
    employee_id number NOT NULL,
    event_date DATE NOT NULL,
    event_time TIMESTAMP NOT NULL,
    city varchar2(50) NOT NULL,
    state varchar2(50) NOT NULL,
    cost binary_double NOT NULL,
    proj_reimburse binary_double NOT NULL,
    description varchar2(140) NOT NULL,
    grading_format varchar2(50) NOT NULL,
    event_type varchar2(50) NOT NULL,
    attach_id number NULL,
    approval_id number NOT NULL,
    work_missed number NULL,
    
    CONSTRAINT reimburse_pk PRIMARY KEY (reimb_id),
    CONSTRAINT employee_fk FOREIGN KEY (employee_id)
    REFERENCES employees(employee_id),
    CONSTRAINT event_type_fk FOREIGN KEY (event_type)
    REFERENCES event_types(event_type),
    CONSTRAINT attach_fk FOREIGN KEY (attach_id)
    REFERENCES attachments(attach_id),
    CONSTRAINT approval_fk FOREIGN KEY (approval_id)
    REFERENCES approval(approval_id)
);

CREATE TABLE grading_format(
    format varchar(50) NOT NULL,
    presentation number NULL,
    grade number NULL,
    passing_grade number NOT NULL,
    approval_email varchar2(50) NOT NULL,
    
    CONSTRAINT format_pk PRIMARY KEY (reimb_id)
);

CREATE TABLE event_types(
    event_type varchar2(50) NOT NULL,
    percentage binary_double NOT NULL,
    
    CONSTRAINT event_type_pk PRIMARY KEY (event_type)
);

CREATE TABLE approval(
    approval_id number NOT NULL,
    ds_approval number NOT NULL,
    ds_reasoning varchar(200) NULL,
    ds_email BLOB NULL,
    dh_approval number NOT NULL,
    dh_email BLOB NULL,
    bc_approval number NOT NULL,
    bc_reasoning varchar(200) NULL,
    
    CONSTRAINT approval_pk PRIMARY KEY (approval_id)
);

CREATE TABLE attachments(
    attach_id number,
    attachment BLOB,
    
    CONSTRAINT attach_pk PRIMARY KEY (attach_id)
);

CREATE TABLE departments(
    dept_id number NOT NULL,
    dept_name varchar2(50) NOT NULL,
    dept_head number NOT NULL,
    
    CONSTRAINT dept_pk PRIMARY KEY (dept_id),
    CONSTRAINT head_fk FOREIGN KEY (dept_head) REFERENCES employees(employee_id)
);