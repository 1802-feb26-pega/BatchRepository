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
    empReimbursmentId NUMBER,
    CONSTRAINT PK_emp PRIMARY KEY (empId),
    CONSTRAINT FK_superId FOREIGN KEY (superId) REFERENCES Employee(empId)
);

CREATE TABLE employeeReimbursment(
    erId NUMBER NOT NULL,
    amountAvailable NUMBER,
    maxAvailable NUMBER,
    pending NUMBER,
    awarded NUMBER,
    CONSTRAINT PK_erId PRIMARY KEY (erId)
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
    passingGrade VARCHAR2(1),
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
ALTER TABLE employee ADD CONSTRAINT fk_empReimbursment FOREIGN KEY (empReimbursmentId) REFERENCES employeeReimbursment(erId);

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
INSERT INTO Employee (fName, lName, phone, email, password) VALUES ('Joseph', 'Moses', '(218)-205-8559', 'joseph.k.moses6@gmail.com', 'password');

INSERT INTO Departments (departmentHead, departmentName) VALUES (1, 'R&D');

