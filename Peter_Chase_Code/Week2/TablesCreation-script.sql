CREATE TABLE department
(
  id	   		        NUMBER(5) CONSTRAINT pk_department PRIMARY KEY,
  name	   		        VARCHAR2(50),
  monthly_budget 	    NUMBER(8,2),
  last_employee_id 	    NUMBER(5)
);

CREATE TABLE employee
(
  id        		    NUMBER(5) CONSTRAINT pk_employee PRIMARY KEY,
  name      		    VARCHAR2(50),
  birthdate 		    DATE,
  phone     		    VARCHAR2(20),
  monthly_salary        NUMBER(7,2) NOT NULL,
  department_id 	    NUMBER(3) CONSTRAINT fk_employee_department REFERENCES department,
  hire_date 		    DATE,
  job_id    		    VARCHAR2(20),
  email     		    VARCHAR2(50)
);

CREATE TABLE products
(
  product_id            NUMBER(5) CONSTRAINT pk_products PRIMARY KEY,
  product_name          VARCHAR2(50) NOT NULL,
  price                 NUMBER(7,2) NOT NULL,
  expiration_date       DATE NOT NULL
);


INSERT INTO department VALUES (1, 'ACCOUNTING', 20000, 8);
INSERT INTO department VALUES (2, 'MARKETING', 15000, 9);
INSERT INTO department VALUES (3, 'INFORMATION TECHNOLOGY', 30000, 10);
INSERT INTO department VALUES (4, 'HUMAN RESOURCES', 25000, 13);
INSERT INTO department VALUES (5, 'REGULATORY AFFAIRS', 5000, null);
INSERT INTO department VALUES (6, 'CUSTOMER SERVICE', 2000, null);



INSERT INTO employee VALUES (1, 'JOHN SMITH', date '1995-01-01', '1.123.456.7890', 4000.00, 1, date '2015-03-28', 'AC_ACCOUNT', 'JSMITH');
INSERT INTO employee VALUES (2, 'JAMES BOSH', date '1992-02-15','1.234.567.8901', 3500.00, 2, date '2014-07-01', 'MK_REP', 'JBOSH');
INSERT INTO employee VALUES (3, 'LUISA JACKSON', date '1970-03-08', '1.345.678.9012', 4500.00, 3, date '2013-08-29', 'IT_PROG', 'LJACKSON');
INSERT INTO employee VALUES (4, 'STUART GARCIA', date '1965-04-12', '1.456.789.0123', 2000.00, 4, date '2010-02-15', 'HR_REP', 'SGARCIA');
INSERT INTO employee VALUES (5, 'JUSTIN BLACK', date '1990-05-16', '1.567.890.1234', 2550.00, 1, date '2015-05-02', 'AC_ACCOUNT', 'JBLACK');
INSERT INTO employee VALUES (6, 'ANGIE CROOD', date '1998-06-22', '1.678.901.2345', 1500.00, 1, date '2015-07-01', 'AC_ACCOUNT', 'ACROOD');
INSERT INTO employee VALUES (7, 'CHARLES DEAN', date '1973-06-08', '1.789.012.3456', 2250.00, 3, date '2002-03-01', 'IT_PROG', 'CDEAN');
INSERT INTO employee VALUES (8, 'EDDIE FARREL', date '1980-07-28', '1.890.123.4567', 3000.00, 1, date '2009-04-20', 'AC_ACCOUNT', 'EFARREL');
INSERT INTO employee VALUES (9, 'GEORGE HAYES', date '1982-08-03', NULL, 2500.00, 2, date '2012-09-22', 'MK_REP', 'GHAYES');
INSERT INTO employee VALUES (10, 'IGOR KEYS OSBOURNE', date '1987-09-11','144.898.7564', 6000.00, 3, date '2014-11-14', 'IT_PROG', 'IKEYS');
INSERT INTO employee VALUES (11, 'LUKE MINT', date '1985-10-19', '1.123.456.7890', 5000.00, 4, date '2011-01-08', 'HR_REP', 'LMINT');
INSERT INTO employee VALUES (12, 'NIGEL OAKS', date '1997-11-05', '52.987.654.3210', 4750.00, 4, date '2014-10-01', 'HR_REP', 'NOAKS');
INSERT INTO employee VALUES (13, 'LUKE GREEN JR', date '1995-02-05', NULL, 4750.00, 4, date '2015-09-01', 'HR_REP', 'LGREEN');
 


INSERT INTO products VALUES (1, 'Aspirin', 5.00, DATE '2020-12-31');
INSERT INTO products VALUES (2, 'Penicillin', 10.00, DATE '2019-04-30');
INSERT INTO products VALUES (3, 'Insulin', 25.00, DATE '2018-05-31');
INSERT INTO products VALUES (4, 'Acetaminophen', 5.00, DATE '2019-01-31');
INSERT INTO products VALUES (5, 'Amoxicillin', 8.00, DATE '2018-07-31');

COMMIT; 
