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

import java.util.Date;
public class Customer
{
    int firmId;
    String gstin;
    String name;
    String email;
    String phoneNumber;
    String address;
    Customer(int firmId,String gstin,String name,String email,String phoneNumber,String address)
    {
        this.firmId = firmId;
        this.gstin = gstin;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    Customer(Utility obj)
    {
        firmId = Integer.parseInt(obj.getAttribute("firmId"));
        gstin = obj.getAttribute("gstin");
        name = obj.getAttribute("name");
        email = obj.getAttribute("email");
        phoneNumber = obj.getAttribute("phoneNumber");
        address = obj.getAttribute("address");
    }
    void add()
    {
        DatabaseConnect db = new DatabaseConnect("Customer");
        Document document = new Document("firmId",firmId)
        .append("gstin",gstin)
        .append("name",name)
        .append("email",email)
        .append("phoneNumber",phoneNumber)
        .append("address",address);
        db.getCollection().insertOne(document);
    }
    int getFirmId()
    {
        return firmId;
    }
    String getGstin()
    {
        return gstin;
    }
    String getName()
    {
        return name;
    }
    String getEmail()
    {
        return email;
    }
    String getPhoneNumber()
    {
        return phoneNumber;
    }
    String getAddress()
    {
        return address;
    }
}