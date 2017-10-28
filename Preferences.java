import java.util.*;
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

public class Preferences
{
    int firmId;
    int billType;
    boolean remindUserEmpPay;
    boolean remindUserOrderDelivery;
    boolean feedBackService;
    boolean trackingService;
    Preferences(int firmId)
    {
        this.firmId = firmId;
        DatabaseConnect db = new DatabaseConnect("Preferences");
        Iterator it = db.getIterator();
        while (it.hasNext())
        {
            Utility obj = new Utility();
            obj.breakDatabaseString(it.next().toString());
            if(firmId == Integer.parseInt(obj.getAttribute("firmId")));
            {
                billType = Integer.parseInt(obj.getAttribute("billType"));
                remindUserEmpPay = Boolean.valueOf(obj.getAttribute("remindUserEmpPay"));
                remindUserOrderDelivery = Boolean.valueOf(obj.getAttribute("remindUserOrderDelivery"));
                feedBackService = Boolean.valueOf(obj.getAttribute("feedBackService"));
                trackingService = Boolean.valueOf(obj.getAttribute("trackingService"));
            }
        }
    }
    Preferences(int firmId,int billType,boolean remindUserEmpPay,boolean remindUserOrderDelivery,
        boolean feedBackService,boolean trackingService)
    {
        this.firmId = firmId;
        this.billType = billType;
        this.remindUserEmpPay = remindUserEmpPay;
        this.remindUserOrderDelivery = remindUserOrderDelivery;
        this.feedBackService = feedBackService;
        this.trackingService = trackingService;
    }
    void addPreferences()
    {
        DatabaseConnect db = new DatabaseConnect("Preferences");
        Document document = new Document("firmId",firmId)
        .append("billType",billType)
        .append("remindUserEmpPay",remindUserEmpPay)
        .append("remindUserOrderDelivery",remindUserOrderDelivery)
        .append("feedBackService",feedBackService)
        .append("trackingService",trackingService);
        db.getCollection().insertOne(document);
    }
    int getBillType()
    {
        return billType;
    }
    boolean isRemindUserAboutEmpPay()
    {
        return remindUserEmpPay;
    }
    boolean isRemindUserAboutOrderDelivery()
    {
        return remindUserOrderDelivery;
    }
    boolean  isFeedBackServiceEnabled()
    {
        return feedBackService;
    }
    boolean isTrackingServiceEnabled()
    {
        return trackingService;
    }
}