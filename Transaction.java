import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Transaction
{
    int firmId;
    String invoiceNumber;
    Date billingDate;
    float totalAmt;
    float totalDiscount;
    float sgst,cgst,igst;
    ObjectId id;
    Transaction(int firmId,String invoiceNumber,Date billingDate,float totalAmt,float totalDiscount,
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
    Transaction(Utility obj)
    {
        firmId = Integer.parseInt(obj.getAttribute("firmId"));
        invoiceNumber = obj.getAttribute("invoiceNumber");
        String temp = obj.getAttribute("billingDate");
        try
        {
            SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
            billingDate = ft.parse(temp);
        }
        catch(Exception e){}
        totalAmt = Float.valueOf(obj.getAttribute("totalAmt"));
        totalDiscount = Float.valueOf(obj.getAttribute("totalDiscount"));
        sgst = Float.valueOf(obj.getAttribute("sgst"));
        cgst = Float.valueOf(obj.getAttribute("cgst"));
        igst = Float.valueOf(obj.getAttribute("igst"));
    }
    void addNewTransaction()
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
        ObjectId id = (ObjectId)document.get( "_id" );
    }
    int getFirmId()
    {
        return firmId;
    }
    String getInvoiceNumber()
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
    ObjectId getTransactionId()
    {
        return id;
    }
}