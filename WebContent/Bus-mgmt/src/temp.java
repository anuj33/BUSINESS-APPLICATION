import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
// import static com.mongodb.client.model.Filters.*;
// import com.mongodb.client.model.Filters;
// import static com.mongodb.client.model.Updates.*;
import com.mongodb.BasicDBObject;
// import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
// import com.mongodb.client.FindIterable;
// import com.mongodb.client.MongoCollection;
// import com.mongodb.client.MongoDatabase;
import com.mongodb.DBCursor;
// import java.util.Iterator;
// import com.mongodb.MongoCredential;
import java.util.Iterator;
import java.util.*;
// import org.bson.Document;
// class RetrievingAllDocuments {

//    public static void main( String args[] ) {

//       // Creating a Mongo client
//       MongoClient mongo = new MongoClient( "localhost" , 27017 );

//       // Creating Credentials
//       MongoCredential credential;
//       credential = MongoCredential.createCredential("limo_756", "Dimension/*-+123",
//          "password".toCharArray());
//       System.out.println("Connected to the database successfully");

//       // Accessing the database
//       MongoDatabase database = mongo.getDatabase("myDb");

//       // Retrieving a collection
//       MongoCollection<Document> collection = database.getCollection("myCollection");
//       System.out.println("Collection sampleCollection selected successfully");

//       int i = 1;


//       BasicDBObject query = new BasicDBObject();
//       BasicDBObject field = new BasicDBObject();
//       field.put("lane", 1);
//       ArrayList<String> result = new ArrayList<String>();
//       DBCursor cursor = collection.find(query,field).iterator();
//       while (cursor.hasNext()) {
//           BasicDBObject obj = (BasicDBObject) cursor.next();
//           result.add(obj.getString("lane"));
//       }
//       // BasicDBObject query = new BasicDBObject();
//       // query.put("lane", 1);
//       // DBCursor cursor = collection.find(query);
//       // ArrayList<String> arr = new ArrayList<String>();
//       // String str;
//       // while (cursor.hasNext()) {
//       //     str=cursor.curr().get("lane").toString();
//       //     arr.add(str);
//       // }
//    }
// }
//     import org.bson.Document;

//     import com.mongodb.BasicDBObject;
//     import com.mongodb.MongoClient;
//     import com.mongodb.ServerAddress;
//     import com.mongodb.client.MongoCollection;
    // import com.mongodb.client.MongoCursor;
//     import com.mongodb.client.MongoDatabase;
// class a
// {

//    public static void main(String[] args)
//    {
//     MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));

//     MongoDatabase db = mongoClient.getDatabase("myDb");

//     MongoCollection<Document> collection = db.getCollection("myCollection");

//     BasicDBObject searchQuery = new BasicDBObject();
//     searchQuery.put("lane","talwandi");

//     MongoCursor<Document> cursor = collection.find(searchQuery).iterator();
//     try {
//         while (cursor.hasNext()) {
//             System.out.println(cursor.next().toJson());
//         }
//     } finally {
//         cursor.close();
//     }

//    }
// }
import java.io.*;
import java.util.Scanner;
import java.lang.*;

class a {
      int generateId()
      {
          String filePath = "FirmId.txt";
          File file;
          Scanner inputFile;
          try
          {
              file = new File(filePath);
              inputFile = new Scanner(file);
              String line = inputFile.nextLine();
              line=line.trim();
              int result = Integer.parseInt(line) + 1;
              inputFile.close();
              BufferedWriter bw = null;
              FileWriter fw = null;
              try
              {
                  fw = new FileWriter(filePath);
                  bw = new BufferedWriter(fw);
                  bw.write(Integer.toString(result));
                  try
                  {
                      if (bw != null)
                          bw.close();
                      if (fw != null)
                          fw.close();
                  }
                  catch (IOException ex)
                  {
                      ex.printStackTrace();
                  }
              }
              catch(IOException e)
              {
                  e.printStackTrace();
              }
              return result;
          }
          catch(FileNotFoundException e)
          {
              System.out.println("File not found"+filePath);
          }
          return -1;
      }

   public static void main( String args[] ) {

      // Creating a Mongo client
      // MongoClient mongo = new MongoClient( "localhost" , 27017 );

      // Creating Credentials
      // MongoCredential credential;
      // credential = MongoCredential.createCredential("limo_756", "Dimension/*-+123",
      //    "password".toCharArray());
      // System.out.println("Connected to the database successfully");

      // Accessing the database
      // MongoDatabase database = mongo.getDatabase("myDb");

      // Retrieving a collection
      // MongoCollection<Document> collection = database.getCollection("myCollection");
      // System.out.println("Collection sampleCollection selected successfully");

      // Getting the iterable object
      // FindIterable<Document> iterDoc = collection.find();
      // int i = 1;

      // Getting the iterator
      // Iterator it = iterDoc.iterator();
      // a d = new a();
      // while (it.hasNext()) {
      //    Utility obj = new Utility();
      //    obj.breakDatabaseString(it.next().toString());
      //    System.out.println(obj.getAttribut("_id"));
      //    // System.out.println(it.next());
      // i++;
      // }
      // Document document = new Document("street", "4845431")
      // .append("lane","varanasi" )
      // .append("city", "delhi")
      // .append("state", "up");
      // collection.insertOne(document);
      // System.out.println("Entered Succesfully");

      // BasicDBObject newDocument = new BasicDBObject();
      // newDocument.put("city", "bara");
      // BasicDBObject searchQuery = new BasicDBObject().append("street", "3215");
      // collection.update(searchQuery, newDocument);
      // System.out.println("Uodated Succesfully");
      // BusinessFirm obj = new BusinessFirm();
      // obj.fetchCustomer();
      // int n = obj.getCustomerNumber();
      // ArrayList<Customer> s = obj.getCustomerList();
      // Customer arr[] = new Customer[10];
      // if(n>0)
      // {
      // arr = s.toArray(arr);
      // System.out.println();
      // System.out.println();
      // System.out.println();
      // System.out.println();
      // for (int i=0; i<n;i++ ) {
      //    System.out.println(arr[i].getName());
      // }
      // }
      // Customer p = new Customer(22500,"dfwe5521","aksh","aksh@gmail.com","25875441","7847-L Gandhi Nagar");
      // obj.addNewCustomer(p);
      a obj = new a();
      System.out.println(obj.generateId());
   }
}