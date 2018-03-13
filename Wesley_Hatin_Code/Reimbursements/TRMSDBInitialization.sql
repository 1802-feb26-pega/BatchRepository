CREATE TABLE employees( 
    employee_id number NOT NULL,
    first_name varchar2(50) NULL,
    last_name varchar2(50) NULL,
    password varchar2(50) NOT NULL,
    reimburse_remain number NOT NULL,
    reimburse_pend number NULL,
    CONSTRAINT employee_pk PRIMARY KEY (employee_id)
);

CREATE TABLE reimbursements(
    reimburse_id number NOT NULL,
    employee_id number NOT NULL,
    event_date DATE NOT NULL,
    event_time TIMESTAMP NOT NULL,
    location varchar2(50) NOT NULL,
    cost binary_double NOT NULL,
    proj_reimburse binary_double NOT NULL,
    description varchar2(140) NOT NULL,
    grading_format varchar2(50) NOT NULL,
    event_type varchar2(50) NOT NULL,
    event_attach varchar2(100) NULL,
    approval_attach varchar2(100) NULL,
    work_missed number NULL,
    
    CONSTRAINT reimburse_pk PRIMARY KEY (reimburse_id),
    CONSTRAINT employee_fk FOREIGN KEY (employee_id)
    REFERENCES employees(employee_id),
    CONSTRAINT event_type_fk FOREIGN KEY (event_type)
    REFERENCES event_types(event_type)
);

CREATE TABLE grading_format(
    format varchar(50) NOT NULL,
    presentation number NOT NULL,
    grade number NOT NULL,
    passing_grade number NOT NULL,
    approval_email varchar2(50) NOT NULL,
    
    CONSTRAINT format_pk PRIMARY KEY (reimburse_id)
)

CREATE TABLE event_types(
    event_type varchar2(50) NOT NULL,
    percentage binary_double NOT NULL,
    
    CONSTRAINT event_type_pk PRIMARY KEY (event_type)
);