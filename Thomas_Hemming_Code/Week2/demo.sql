CREATE OR REPLACE PROCEDURE insertTest(
  testid IN NUMBER,
  testname IN VARCHAR2,
  testvalue IN NUMBER)
IS
BEGIN
  INSERT INTO test VALUES(testid, testname, testvalue);
END;
/