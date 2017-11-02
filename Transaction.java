import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.util.JSON;
import java.io.IOException;
import java.util.*;
import java.util.Iterator;
import org.bson.Document;
import java.lang.Object;
import org.bson.types.ObjectId;

public class Transaction
{
    int firmId;
    int invoiceNumber;
    Date billingDate;
    float totalAmt;
    float totalDiscount;
    float sgst,cgst,igst;
    ObjectId id;
    String transactionId;
    public Transaction(int firmId,int invoiceNumber,Date billingDate,float totalAmt,float totalDiscount,
        float sgst,float cgst,float igst)
    {
        this.firmId = firmId;
        this.invoiceNumber = invoiceNumber;
        this.billingDate = billingDate;
        this.totalAmt = totalAmt;
        this.totalDiscount = totalDiscount;
        this.sgst = sgst;
        this.cgst = cgst;
        this.igst = igst;
    }
    public Transaction(Utility obj)
    {
        firmId = Integer.parseInt(obj.getAttribute("firmId"));
        invoiceNumber = Integer.parseInt(obj.getAttribute("invoiceNumber"));
        String temp = obj.getAttribute("billingDate");
        try
        {
            SimpleDateFormat ft = new SimpleDateFormat ("EEE MMM dd HH:mm:ss zzz yyyy");
            billingDate = ft.parse(temp);
        }
        catch(Exception e){}
        totalAmt = Float.valueOf(obj.getAttribute("totalAmt"));
        totalDiscount = Float.valueOf(obj.getAttribute("totalDiscount"));
        sgst = Float.valueOf(obj.getAttribute("sgst"));
        cgst = Float.valueOf(obj.getAttribute("cgst"));
        igst = Float.valueOf(obj.getAttribute("igst"));
        transactionId = obj.getAttribute("_id");
    }
    void addNewTransaction()
    {
        DatabaseConnect db = new DatabaseConnect("transaction");
        Document document = new Document("firmId",firmId)
        .append("invoiceNumber",invoiceNumber)
        .append("billingDate",billingDate)
        .append("totalAmt",totalAmt)
        .append("totalDiscount",totalDiscount)
        .append("sgst",sgst)
        .append("cgst",cgst)
        .append("igst",igst);
        db.getCollection().insertOne(document);
        ObjectId id = (ObjectId)document.get("_id");
        // DBObject a = (DBObject)document.get("_id");
        // String reportName = (String) a.get("oid");
        // System.out.println("Report " + reportName);
        transactionId = id.toHexString();
        System.out.println("Transaction working");
        System.out.println(transactionId);
        System.out.println(id.toHexString());
    }
    int getFirmId()
    {
        return firmId;
    }
    int getInvoiceNumber()
    {
        return invoiceNumber;
    }
    Date getBillingDate()
    {
        return billingDate;
    }
    float getTotalAmt()
    {
        return totalAmt;
    }
    float getTotalDiscount()
    {
        return totalDiscount;
    }
    float getSgst()
    {
        return sgst;
    }
    float getCgst()
    {
        return cgst;
    }
    float getIgst()
    {
        return igst;
    }
    String getTransactionId()
    {
        return transactionId;
    }
}