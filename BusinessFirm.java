import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.BasicDBObject;
import com.mongodb.ServerAddress;
import com.mongodb.DBCursor;
import java.util.Iterator;
import java.util.*;

public class BusinessFirm
{
    Vector<Transaction> record;
    Vector<Customer> buyer;
    int numberOfRecords;
    int numberOfBuyer;
    BusinessFirm()
    {
        record = new Vector<Transaction>();
        buyer = new Vector<Customer>();
        numberOfRecords = 0;
        numberOfBuyer = 0;
    }
    void fetchTransaction()
    {
        DatabaseConnect db = new DatabaseConnect("transaction");
        Iterator it = db.getIterator();
        while (it.hasNext())
        {
            Utility obj = new Utility();
            obj.breakDatabaseString(it.next().toString());
            Transaction curr = new Transaction(obj);
            record.add(curr);
            numberOfRecords++;
        }
    }
    void addNewTransaction(Transaction newRecord)
    {
        DatabaseConnect db = new DatabaseConnect("transaction");
        Document document = new Document("firmId",newRecord.getFirmId())
        .append("invoiceNumber",newRecord.getInvoiceNumber())
        .append("billingDate",newRecord.getBillingDate())
        .append("totalAmt",newRecord.getTotalAmt())
        .append("totalDiscount",newRecord.getTotalDiscount())
        .append("sgst",newRecord.getSgst())
        .append("cgst",newRecord.getCgst())
        .append("igst",newRecord.getIgst());
        db.getCollection().insertOne(document);
    }
    void fetchCustomer()
    {
        DatabaseConnect db = new DatabaseConnect("Customer");
        Iterator it = db.getIterator();
        while (it.hasNext())
        {
            Utility obj = new Utility();
            obj.breakDatabaseString(it.next().toString());
            Customer curr = new Customer(obj);
            buyer.add(curr);
            numberOfBuyer++;
        }
    }
    int getCustomerNumber()
    {
        return numberOfBuyer;
    }
    Vector<Customer> getCustomerList()
    {
        return buyer;
    }
}