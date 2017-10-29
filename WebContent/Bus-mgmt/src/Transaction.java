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
}