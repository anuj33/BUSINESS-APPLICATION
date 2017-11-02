import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
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

public class CreateReminder {
    private int firmId;
    private String sub;
    private String message;
    private String email;
    private Date reminderDate;
    public CreateReminder(int firmID,String sub,String message,String email,Date reminderDate)
    {
        this.firmId = firmID;
        this.sub = sub;
        this.message = message;
        this.email = email;
        this.reminderDate = reminderDate;
    }
    public void addNewReminder(){
        DatabaseConnect db = new DatabaseConnect("reminder");
        Document document = new Document("firmId",firmId)
                .append("subject",sub)
                .append("message",message)
                .append("reminderDate",reminderDate)
                .append("email",email);
        db.getCollection().insertOne(document);
    }
    int getFirmId(){return firmId;}
    String getSub(){return sub;}
    String getMessage(){return message;}
    String getEmail(){return email;}
    Date getReminderDate(){return reminderDate;}
}