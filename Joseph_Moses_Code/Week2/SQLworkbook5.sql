--5.0 Transactions 2
--In this section you will be working with transactions. Transactions are usually nested within a stored procedure.

--Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely 
--on this, find out how to resolve them).
CREATE OR REPLACE PROCEDURE delete_invoice(p_invoiceId IN NUMBER)
IS
BEGIN
    DELETE FROM invoice
    WHERE invoiceId = p_invoiceId;
    COMMIT;
END;
/

EXECUTE delete_invoice(5);
SELECT invoiceId
FROM invoice;

--Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
CREATE OR REPLACE PROCEDURE add_customer(p_customerId IN NUMBER,
                                        p_firstName IN VARCHAR2,
                                        p_lastName IN VARCHAR2,
                                        p_email IN VARCHAR2)
IS
BEGIN
    INSERT INTO customer(customerId, firstName, lastName, email)
                VALUES (p_customerId, p_firstName, p_lastName, p_email);
    COMMIT;
END;
/

CALL add_customer(63, 'Joseph', 'Moses', 'joseph.k.moses6@gmail.com');
