import com.mongodb.BasicDBObject;
import com.mongodb.DB;
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
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
public class SalesReport
{
    int firmId;
    /**
     * values of type
     * 0      weekly
     * 1      monthly
     * 2      yearly
     */
    int type;
    Date startDate;
    Date endDate;
    Vector<Product> product;
    Vector<Double> transactionAmount;
    Vector<String> xAxis = new Vector<String>();
    Vector<String> yAxis = new Vector<String>();
    Vector<Date> startBucket = new Vector<Date>();
    Vector<Date> endBucket = new Vector<Date>();
    SalesReport(int firmId,int type,Date startDate,Date endDate,Vector<Product> product)
    {
        this.firmId = firmId;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.product = product;
    }
    SalesReport(int firmId,int type,Date startDate,Date endDate)
    {
        this.firmId = firmId;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.product = product;
    }
    Date getStartOfDay(Date date)
    {
        Utility dateExtractor = new Utility();
        dateExtractor.dateHandeler(date.toString());
        int year = Integer.parseInt(dateExtractor.getAttribute("year"));
        int month = Utility.getMonth(dateExtractor.getAttribute("month"));
        int currDate = Integer.parseInt(dateExtractor.getAttribute("date"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, currDate, 0, 0, 0);
        return calendar.getTime();
    }
    Date getEndOfDay(Date date)
    {
        Utility dateExtractor = new Utility();
        dateExtractor.dateHandeler(date.toString());
        int year = Integer.parseInt(dateExtractor.getAttribute("year"));
        int month = Utility.getMonth(dateExtractor.getAttribute("month"));
        int currDate = Integer.parseInt(dateExtractor.getAttribute("date"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, currDate, 23, 59, 59);
        return calendar.getTime();
    }
    Date getStartOfMonth(Date date)
    {
        Utility dateExtractor = new Utility();
        dateExtractor.dateHandeler(date.toString());
        int year = Integer.parseInt(dateExtractor.getAttribute("year"));
        int month = Utility.getMonth(dateExtractor.getAttribute("month"));
        int currDate = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, currDate, 0, 0, 0);
        return calendar.getTime();
    }
    Date getEndOfMonth(Date date)
    {
        Utility dateExtractor = new Utility();
        dateExtractor.dateHandeler(date.toString());
        int year = Integer.parseInt(dateExtractor.getAttribute("year"));
        int month = Utility.getMonth(dateExtractor.getAttribute("month"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, Utility.getNumberOfDays(month,year), 23, 59, 59);
        return calendar.getTime();
    }
    Date decrementMonth(Date date)
    {
        Utility dateExtractor = new Utility();
        dateExtractor.dateHandeler(date.toString());
        int year = Integer.parseInt(dateExtractor.getAttribute("year"));
        int month = Utility.getMonth(dateExtractor.getAttribute("month"));
        int currDate = Integer.parseInt(dateExtractor.getAttribute("date"));
        int hour = Integer.parseInt(dateExtractor.getAttribute("hour"));
        int minute = Integer.parseInt(dateExtractor.getAttribute("minute"));
        int second = Integer.parseInt(dateExtractor.getAttribute("second"));
        if(month == 1)
        {
            year--;
            month = 12;
        }
        else
            month--;
        if(currDate > Utility.getNumberOfDays(month, year))
            currDate = Utility.getNumberOfDays(month, year);
        System.out.println("IN month "+month);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, currDate, hour, minute, second);
        System.out.println("IN Calendar"+calendar.getTime());
        return calendar.getTime();
    }
    void createReport()
    {
        System.out.println("start date end date"+startDate + " " + endDate);
        BusinessFirm firm = new BusinessFirm();
        firm.fetchTransaction(firmId);
        Vector<Transaction> record = firm.getTransactionList();
        int numOfRecords = firm.getTransactionNumber();
        Vector<Transaction> recordWithinRange = new Vector<Transaction>();
        System.out.println("record size "+ record.size() + "  "+numOfRecords);
        for (int i = 0;i < record.size() ;i++ )
        {
            System.out.println(record.get(i).getBillingDate());
            if(record.get(i).getBillingDate().after(startDate) && record.get(i).getBillingDate().before(endDate))
                recordWithinRange.add(record.get(i));
        }
        Vector<Double> bucket = new Vector<Double>();
        Date date = new Date();
        date.setTime(endDate.getTime());
        while(date.after(startDate))
        {
            Date a = getStartOfMonth(date);
            Date b = getEndOfMonth(date);
            startBucket.add(a);
            endBucket.add(b);
            date = decrementMonth(date);
            System.out.println("In while LOOP" + date);
        }
        Date a = getStartOfMonth(date);
        Date b = getEndOfMonth(date);
        startBucket.add(a);
        endBucket.add(b);
        if(endBucket.get(0).after(endDate))
            endBucket.set(0,endDate);
        if(startBucket.get(startBucket.size() - 1).before(startDate))
            startBucket.set(startBucket.size() - 1,startDate);
        Collections.reverse(startBucket);
        Collections.reverse(endBucket);
        for (int i = 0;i < startBucket.size() ; i++ )
            bucket.add(0.0);
        System.out.println("Size of "+ recordWithinRange.size());
        System.out.println("Size of "+ startBucket.size());
        System.out.println("start date end date"+startDate + " " + endDate);
        for (int i = 0;i < recordWithinRange.size() ; i++ )
        {
            for(int j = 0;j < startBucket.size();j++)
            {
                System.out.println(startBucket.get(j)+ " " + endBucket.get(j));
                if(recordWithinRange.get(i).getBillingDate().after(startBucket.get(j)) &&
                    recordWithinRange.get(i).getBillingDate().before(endBucket.get(j)))
                {
                System.out.println(recordWithinRange.get(i)+ " " + startBucket.get(j));
                    bucket.set(j,bucket.get(j) + recordWithinRange.get(i).getTotalAmt());
                }
            }
        }
        transactionAmount = bucket;
    }
    Vector<Double> getTransactionAmount()
    {
        return transactionAmount;
    }
    // Vector<String> getXAxis()
    // {
    //     for(int i = 0;i < transactionAmount.size();i++)
    //     {
    //         xAxis.add();
    //     }
    // }
}