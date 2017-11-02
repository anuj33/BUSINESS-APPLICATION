import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;
import java.io.IOException;
import java.lang.Object;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.Vector;
import org.bson.Document;
import org.bson.types.ObjectId;

public class Reminder {
    int noOfRecords;
    int firmId;
    Vector <CreateReminder> vec;
    public Reminder(int firmId){
        noOfRecords=0;
        vec = new Vector<CreateReminder>();
        this.firmId = firmId;
    }

    public String notifyUser()
    {
        Date date = new Date();
        // Retrieve from database
        String db ="reminder";
        DatabaseConnect ob = new DatabaseConnect(db);
        Iterator it = ob.getIterator();
        while (it.hasNext())
        {
            Utility obj = new Utility();
            obj.breakDatabaseString(it.next().toString());
            int currFirmId = Integer.parseInt(obj.getAttribute("firmId"));
            String subject = (obj.getAttribute("subject"));
            String temp = (obj.getAttribute("reminderDate"));
            Date reminderDate = new Date();
            try
            {
                SimpleDateFormat ft = new SimpleDateFormat ("EEE MMM dd HH:mm:ss zzz yyyy");
                reminderDate = ft.parse(temp);
            }
            catch (Exception e){}
            String email = (obj.getAttribute("email"));
            String message = (obj.getAttribute("message"));
            vec.add(new CreateReminder(currFirmId,subject,message,email,reminderDate));
            noOfRecords++;
        }
        System.out.println("Entry Date is " + date);
        for(int i=0;i<vec.size();i++)
        {
            System.out.println("Email Date is " + vec.get(i).getReminderDate());
            if(firmId == vec.get(i).getFirmId())
                System.out.println("Hurray ");
            if(date.after(vec.get(i).getReminderDate()))
                System.out.println("Hurray1 ");
            if(date.after(vec.get(i).getReminderDate()) && firmId == vec.get(i).getFirmId())
            {
                System.out.println("Inside the Loop");
                Mailer.send(vec.get(i).getEmail(),vec.get(i).getSub(),vec.get(i).getMessage());
                // BasicDBObject query = new BasicDBObject();
                // query.put("number", new BasicDBObject("firmId", firmId));
                // ob.getCollection().remove(query);
                ob.getCollection().deleteOne(new Document().append("firmId", firmId).append("subject",vec.get(i).getSub())
                    .append("message",vec.get(i).getMessage()).append("reminderDate",vec.get(i).getReminderDate()));
                return  vec.get(i).getSub()+"$"+vec.get(i).getMessage();
            }
        }
        return null;
    }

}