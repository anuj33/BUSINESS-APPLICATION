import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
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
import java.util.*;
import java.util.Iterator;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import org.bson.Document;
public class DatabaseConnect
{
    MongoClient mongo;
    MongoDatabase database;
    MongoCollection<Document> collection;
    FindIterable<Document> iterDoc;
    Iterator it;
    DatabaseConnect(String collName)
    {
        // Creating a Mongo client
        // String textUri = "mongodb://"+username+":"+password+"@"+host+":"+port;
        String textUri = "mongodb://pareekakash:qwerty@ds161304.mlab.com:61304/businessfirm";
        //MongoClientURI uri = new MongoClientURI(textUri);
        //mongo = new MongoClient(uri);
         mongo = new MongoClient( "localhost" , 27017 );
        System.out.println("Connected to the database successfully");
        // Accessing the database
        //MongoDatabase database = mongo.getDatabase("businessfirm");
        MongoDatabase database = mongo.getDatabase("BusinessFirm");
        // Retrieving a collection
        collection = database.getCollection(collName);
        System.out.println("Collection sampleCollection selected successfully");

        // Getting the iterable object
        iterDoc = collection.find();
        System.out.print("HUVDGsvfgsqvq\n\n");
        // Getting the iterator
        it = iterDoc.iterator();
        System.out.print("SYSTEM IS CONNECTED NOW AAAAAAAAAAA\n");
    }
    public MongoClient getClient()
    {
        return mongo;
    }
    public MongoDatabase getDatabase()
    {
        return database;
    }
    public MongoCollection<Document> getCollection()
    {
        return collection;
    }
    public Iterator getIterator()
    {
        return it;
    }
}