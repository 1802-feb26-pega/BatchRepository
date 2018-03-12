--5.1 Transactions
--Task – Create a transaction that, given a invoiceId, will delete that invoice (There may be constraints that rely on this, 
    --find out how to resolve them).
CREATE OR REPLACE PROCEDURE delete_invoice(invoice_Id IN number)
AS
BEGIN   
    EXECUTE IMMEDIATE 'ALTER TABLE invoiceline DROP CONSTRAINT FK_INVOICELINEINVOICEID';
    
    EXECUTE IMMEDIATE 'ALTER TABLE invoice ADD CONSTRAINT FK_INVOICELINEINVOICEID_CASCADE
       FOREIGN KEY (invoicelineid) REFERENCES invoice(invoiceid) ON DELETE CASCADE';
       
    DELETE FROM invoice WHERE invoiceid = invoice_id;
    COMMIT;
END;
/

--Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
CREATE OR REPLACE PROCEDURE insert_Customer
IS
BEGIN
    INSERT INTO customer VALUES (70, 'Bob', 'Smith', 'ACME', '100 Anvil St.', 'Loony', 'Toons', 'T.V. Land', 
        '10101', '123-456-7890', 'idk', 'asdf@lt.com', 5);
    COMMIT;
END;
/
