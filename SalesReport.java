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
    SalesReport(int firmId,int type,Date startDate,Date endDate)
    {
        this.firmId = firmId;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    Date getStartOfDay(Date date)
    {
        Utility dateExtractor = Utility();
        int year = Integer.parseInt(dateExtractor.getAttribute("year"));
        int month = Utility.getMonth(dateExtractor.getAttribute("month"));
        int date = Integer.parseInt(dateExtractor.getAttribute("date"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
        return calendar.getTime();
    }
    Date getEndOfDay(Date date)
    {
        Utility dateExtractor = Utility();
        int year = Integer.parseInt(dateExtractor.getAttribute("year"));
        int month = Utility.getMonth(dateExtractor.getAttribute("month"));
        int date = Integer.parseInt(dateExtractor.getAttribute("date"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 23, 59, 59);
        return calendar.getTime();
    }
}