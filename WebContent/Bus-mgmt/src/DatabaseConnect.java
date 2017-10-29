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
import java.util.*;
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
        mongo = new MongoClient( "localhost" , 27017 );
        System.out.println("Connected to the database successfully");
        // Accessing the database
        MongoDatabase database = mongo.getDatabase("BusinessFirm");

        // Retrieving a collection
        collection = database.getCollection(collName);
        System.out.println("Collection sampleCollection selected successfully");

        // Getting the iterable object
        iterDoc = collection.find();

        // Getting the iterator
        it = iterDoc.iterator();
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