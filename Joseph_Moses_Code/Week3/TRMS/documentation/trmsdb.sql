--Create tables
CREATE TABLE employee(
    empId NUMBER NOT NULL,
    fName VARCHAR2(255),
    lName VARCHAR2(255),
    phone VARCHAR2(20),
    email VARCHAR2(255) NOT NULL UNIQUE,
    password VARCHAR2(255) NOT NULL,
    superId NUMBER,
    deptId NUMBER,
    empLevel NUMBER,
    amountAvailable NUMBER,
    maxAvailable NUMBER,
    pending NUMBER,
    awarded NUMBER,
    CONSTRAINT PK_emp PRIMARY KEY (empId),
    
    CONSTRAINT FK_superId FOREIGN KEY (superId) REFERENCES Employee(empId)
);



CREATE TABLE departments(
    departmentId NUMBER NOT NULL,
    departmentHead NUMBER NOT NULL,
    departmentName VARCHAR2(255),
    CONSTRAINT PK_departmentId PRIMARY KEY (departmentId)
);

CREATE TABLE empLevelLookUp(
    empLevelId NUMBER NOT NULL,
    empLevel VARCHAR2(255),
    CONSTRAINT PK_empLevelId PRIMARY KEY (empLevelId)
);



CREATE TABLE reimbursmentRequest(
    rrId NUMBER NOT NULL,
    empId NUMBER NOT NULL,
    requestDate DATE,
    startDate DATE,
    location VARCHAR2(255),
    description VARCHAR2(255),
    typeOfEventId NUMBER,
    cost NUMBER,
    gradingFormatId NUMBER,
    passingGrade NUMBER,
    justification VARCHAR2(255),
    workTimeMissed NUMBER,
    expectedReimbursment NUMBER,
    priorityId NUMBER,
    statusId NUMBER,
    CONSTRAINT PK_rrId PRIMARY KEY (rrId)
);

CREATE TABLE gradingFormatLookUp(
    formatId NUMBER NOT NULL,
    format VARCHAR2(255),
    defaultPassing NUMBER,
    CONSTRAINT PK_formatId PRIMARY KEY (formatId)
);

CREATE TABLE eventTypeLookUp(
    typeId NUMBER NOT NULL,
    type VARCHAR2(255),
    coverage NUMBER,
    CONSTRAINT PK_typeId PRIMARY KEY (typeId)
);

CREATE TABLE priorityLookUp(
    priorityId NUMBER NOT NULL,
    priority VARCHAR2(255),
    CONSTRAINT PK_priorityId PRIMARY KEY (priorityId)
);

CREATE TABLE statusLookUp(
    statusId NUMBER NOT NULL,
    status VARCHAR2(255),
    CONSTRAINT PK_statusId PRIMARY KEY (statusId)
);

CREATE TABLE approvalLookUp(
    approvalTypeId NUMBER NOT NULL,
    approvalType VARCHAR2(255),
    CONSTRAINT PK_approvalTypeId PRIMARY KEY (approvalTypeId)
);

CREATE TABLE attachments(
    attachmentId NUMBER NOT NULL,
    rrId NUMBER NOT NULL,
    fileType VARCHAR2(255),
    fileName VARCHAR2(255),
    attachedFile BLOB,
    approvalTypeId NUMBER,
    CONSTRAINT PK_attachmentId PRIMARY KEY (attachmentId)
);

CREATE TABLE requestUpdate(
    ruId NUMBER NOT NULL,
    requestorId NUMBER NOT NULL,
    requestedOfId NUMBER NOT NULL,
    description VARCHAR2(255),
    rrId NUMBER NOT NULL,
    CONSTRAINT PK_ruId PRIMARY KEY (ruId)
);

--foreign keys
--employee
ALTER TABLE employee ADD CONSTRAINT fk_deptId FOREIGN KEY (deptID) REFERENCES departments(departmentId);
ALTER TABLE employee ADD CONSTRAINT fk_empLevel FOREIGN KEY (empLevel) REFERENCES empLevelLookUp(empLevelId);

--department
ALTER TABLE departments ADD CONSTRAINT fk_deptHeadId FOREIGN KEY (departmentHead) REFERENCES employee(empId);

--reimbursmentRequest
ALTER TABLE reimbursmentRequest ADD CONSTRAINT fk_empId FOREIGN KEY (empID) REFERENCES employee(empId);
ALTER TABLE reimbursmentRequest ADD CONSTRAINT fk_gradingFormatId FOREIGN KEY (gradingFormatID) REFERENCES gradingFormatLookUp(formatId);
ALTER TABLE reimbursmentRequest ADD CONSTRAINT fk_typeOfEventId FOREIGN KEY (typeOfEventID) REFERENCES eventTypeLookUp(typeId);
ALTER TABLE reimbursmentRequest ADD CONSTRAINT fk_priorityId FOREIGN KEY (priorityID) REFERENCES priorityLookUp(priorityId);
ALTER TABLE reimbursmentRequest ADD CONSTRAINT fk_statusId FOREIGN KEY (statusID) REFERENCES statusLookUp(statusId);

--attachments
ALTER TABLE attachments ADD CONSTRAINT fk_rrId FOREIGN KEY (rrID) REFERENCES reimbursmentRequest(rrId);
ALTER TABLE attachments ADD CONSTRAINT fk_approvalTypeId FOREIGN KEY (approvalTypeID) REFERENCES approvalLookUp(approvalTypeId);

--requestUpdate
ALTER TABLE requestUpdate ADD CONSTRAINT fk_requestorId FOREIGN KEY (requestorID) REFERENCES employee(empId);
ALTER TABLE requestUpdate ADD CONSTRAINT fk_requestedOfId FOREIGN KEY (requestedOfID) REFERENCES employee(empId);
ALTER TABLE requestUpdate ADD CONSTRAINT fk_rrId_requestUpdate FOREIGN KEY (rrID) REFERENCES reimbursmentRequest(rrId);

--sequences for Ids
CREATE SEQUENCE empId_seq
MINVALUE 1
MAXVALUE 1000000
INCREMENT BY 1
START WITH 1;
/

CREATE SEQUENCE departmentId_seq
MINVALUE 1
MAXVALUE 1000000
INCREMENT BY 1
START WITH 1;
/

CREATE SEQUENCE empLevelId_seq
MINVALUE 1
MAXVALUE 1000000
INCREMENT BY 1
START WITH 1;
/

CREATE SEQUENCE rrId_seq
MINVALUE 1
MAXVALUE 1000000
INCREMENT BY 1
START WITH 1;
/

CREATE SEQUENCE formatId_seq
MINVALUE 1
MAXVALUE 1000000
INCREMENT BY 1
START WITH 1;
/

CREATE SEQUENCE eventTypeId_seq
MINVALUE 1
MAXVALUE 1000000
INCREMENT BY 1
START WITH 1;
/

CREATE SEQUENCE priorityId_seq
MINVALUE 1
MAXVALUE 1000000
INCREMENT BY 1
START WITH 1;
/

CREATE SEQUENCE statusId_seq
MINVALUE 1
MAXVALUE 1000000
INCREMENT BY 1
START WITH 1;
/

CREATE SEQUENCE attachmentsId_seq
MINVALUE 1
MAXVALUE 1000000
INCREMENT BY 1
START WITH 1;
/

CREATE SEQUENCE approvalTypeId_seq
MINVALUE 1
MAXVALUE 1000000
INCREMENT BY 1
START WITH 1;
/

CREATE SEQUENCE erId_seq
MINVALUE 1
MAXVALUE 1000000
INCREMENT BY 1
START WITH 1;
/

CREATE SEQUENCE ruId_seq
MINVALUE 1
MAXVALUE 1000000
INCREMENT BY 1
START WITH 1;
/

--Triggers for Ids
CREATE OR REPLACE TRIGGER emp_trigger
BEFORE INSERT ON employee
FOR EACH ROW
BEGIN
    SELECT empId_seq.NEXTVAL
    INTO :new.empId
    FROM dual;
END;
/

CREATE OR REPLACE TRIGGER departments_trigger
BEFORE INSERT ON departments
FOR EACH ROW
BEGIN
    SELECT departmentId_seq.NEXTVAL
    INTO :new.departmentId
    FROM dual;
END;
/

CREATE OR REPLACE TRIGGER empLevel_trigger
BEFORE INSERT ON empLevelLookUp
FOR EACH ROW
BEGIN
    SELECT empLevelId_seq.NEXTVAL
    INTO :new.empLevelId
    FROM dual;
END;
/

CREATE OR REPLACE TRIGGER reimbursmentRequest_trigger
BEFORE INSERT ON reimbursmentRequest
FOR EACH ROW
BEGIN
    SELECT rrId_seq.NEXTVAL
    INTO :new.rrId
    FROM dual;
END;
/

CREATE OR REPLACE TRIGGER gradingFormat_trigger
BEFORE INSERT ON gradingFormatLookUp
FOR EACH ROW
BEGIN
    SELECT formatId_seq.NEXTVAL
    INTO :new.formatId
    FROM dual;
END;
/

CREATE OR REPLACE TRIGGER eventType_trigger
BEFORE INSERT ON eventTypeLookUp
FOR EACH ROW
BEGIN
    SELECT eventTypeId_seq.NEXTVAL
    INTO :new.typeId
    FROM dual;
END;
/

CREATE OR REPLACE TRIGGER priorityLookUp_trigger
BEFORE INSERT ON priorityLookUp
FOR EACH ROW
BEGIN
    SELECT priorityId_seq.NEXTVAL
    INTO :new.priorityId
    FROM dual;
END;
/

CREATE OR REPLACE TRIGGER statusLookUp_trigger
BEFORE INSERT ON statusLookUp
FOR EACH ROW
BEGIN
    SELECT statusId_seq.NEXTVAL
    INTO :new.statusId
    FROM dual;
END;
/

CREATE OR REPLACE TRIGGER approvalLookUp_trigger
BEFORE INSERT ON approvalLookUp
FOR EACH ROW
BEGIN
    SELECT approvalTypeId_seq.NEXTVAL
    INTO :new.approvalTypeId
    FROM dual;
END;
/

CREATE OR REPLACE TRIGGER attachments_trigger
BEFORE INSERT ON attachments
FOR EACH ROW
BEGIN
    SELECT attachmentsId_seq.NEXTVAL
    INTO :new.attachmentId
    FROM dual;
END;
/

CREATE OR REPLACE TRIGGER employeeReimbursmentId_trigger
BEFORE INSERT ON employeeReimbursment
FOR EACH ROW
BEGIN
    SELECT erId_seq.NEXTVAL
    INTO :new.erId
    FROM dual;
END;
/

CREATE OR REPLACE TRIGGER requestUpdateId_trigger
BEFORE INSERT ON requestUpdate
FOR EACH ROW
BEGIN
    SELECT ruId_seq.NEXTVAL
    INTO :new.ruId
    FROM dual;
END;
/

--populate data
INSERT INTO ApprovalLookUp (approvalType) VALUES ('Direct Supervisor');
INSERT INTO ApprovalLookUp (approvalType) VALUES ('Department Head');
INSERT INTO ApprovalLookUp (approvalType) VALUES ('Benefits Coordinator');
INSERT INTO ApprovalLookUp (approvalType) VALUES ('Denied');
INSERT INTO ApprovalLookUp (approvalType) VALUES ('Excess Approved');

INSERT INTO empLevelLookUp (empLevel) VALUES ('Standard');
INSERT INTO empLevelLookUp (empLevel) VALUES ('Supervisor');
INSERT INTO empLevelLookUp (empLevel) VALUES ('Department Head');
INSERT INTO empLevelLookUp (empLevel) VALUES ('Benefits Coordinator');

INSERT INTO eventTypeLookUp (type, coverage) VALUES ('University Course', 80);
INSERT INTO eventTypeLookUp (type, coverage) VALUES ('Seminar', 60);
INSERT INTO eventTypeLookUp (type, coverage) VALUES ('Certification Prep Class', 75);
INSERT INTO eventTypeLookUp (type, coverage) VALUES ('Certification', 100);
INSERT INTO eventTypeLookUp (type, coverage) VALUES ('Technical Training', 90);
INSERT INTO eventTypeLookUp (type, coverage) VALUES ('Other', 30);

INSERT INTO gradingFormatLookUp (format, defaultPassing) VALUES ('Standard', 70);
INSERT INTO gradingFormatLookUp (format, defaultPassing) VALUES ('Prsentation', 70);

INSERT INTO PriorityLookUp (priority) VALUES ('urgent');
INSERT INTO PriorityLookUp (priority) VALUES ('standard');

INSERT INTO StatusLookUp (status) VALUES ('Pending Direct Super');
INSERT INTO StatusLookUp (status) VALUES ('Pending Department Head');
INSERT INTO StatusLookUp (status) VALUES ('Pending Benefits Co');
INSERT INTO StatusLookUp (status) VALUES ('Approved');
INSERT INTO StatusLookUp (status) VALUES ('Denied');

ALTER TABLE Employee ADD CHECK (amountAvailable <= 1000);

INSERT INTO Employee (fName, lName, phone, email, password) VALUES ('Joseph', 'Moses', '(218)-205-8559', 'joseph.k.moses6@gmail.com', 'password');
INSERT INTO Employee (fName, lName, phone, email, password) VALUES ('Brittney', 'Riebel', '(777)-205-8777', 'brittney.r@gmail.com', 'password');

INSERT INTO Departments (departmentHead, departmentName) VALUES (1, 'IT');
INSERT INTO Departments (departmentHead, departmentName) VALUES (2, 'R and D');

UPDATE Employee SET deptId = 1, empLevel = 3, amountAvailable = 1000, maxAvailable = 1000, pending = 0, awarded = 0 WHERE empId = 11; 
UPDATE Employee SET deptId = 2, empLevel = 3, amountAvailable = 1000, maxAvailable = 1000, pending = 0, awarded = 0 WHERE empId = 12; 


INSERT INTO Employee (fName, lName, phone, email, password, superId, deptId, empLevel, amountAvailable, maxAvailable, pending, awarded) VALUES ('Andrew', 'Hanson', '(218)-205-6123', 'andrew.h@gmail.com', 'password', 11, 1, 4, 1000, 1000, 0, 0);
INSERT INTO Employee (fName, lName, phone, email, password, superId, deptId, empLevel, amountAvailable, maxAvailable, pending, awarded) VALUES ('Anthony', 'Hanson', '(218)-205-7864', 'anthony.h@gmail.com', 'password', 11, 1, 2, 1000, 1000, 0, 0);
INSERT INTO Employee (fName, lName, phone, email, password, superId, deptId, empLevel, amountAvailable, maxAvailable, pending, awarded) VALUES ('Mark', 'Brandao', '(218)-205-4891', 'mark.b@gmail.com', 'password', 12, 2, 4, 1000, 1000, 0, 0);
INSERT INTO Employee (fName, lName, phone, email, password, superId, deptId, empLevel, amountAvailable, maxAvailable, pending, awarded) VALUES ('Sean', 'Beseler', '(218)-205-5555', 'sean.b@gmail.com', 'password', 12, 2, 2, 1000, 1000, 0, 0);
INSERT INTO Employee (fName, lName, phone, email, password, superId, deptId, empLevel, amountAvailable, maxAvailable, pending, awarded) VALUES ('Peter', 'Chase', '(218)-205-4444', 'peter.c@gmail.com', 'password', 26, 1, 1, 1000, 1000, 0, 0);
INSERT INTO Employee (fName, lName, phone, email, password, superId, deptId, empLevel, amountAvailable, maxAvailable, pending, awarded) VALUES ('Tom', 'Hemming', '(218)-205-5555', 'tom.h@gmail.com', 'password', 28, 2, 1, 1000, 1000, 0, 0);

CREATE OR REPLACE PROCEDURE get_all_pending_requests(i_empId IN NUMBER, cursorParam OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN cursorParam FOR
    SELECT *
    FROM ReimbursementRequest
    WHERE empId = i_empId AND statusId < 4
    ORDER BY statusId;
END;
/ 

commit;