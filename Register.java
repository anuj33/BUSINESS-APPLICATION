import java.io.*;
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

import java.util.Scanner;
import java.lang.*;
import java.util.*;
public class Register
{
    String username;
    String password;
    String firmName;
    int firmId;
    int billType;
    String gstin;
    String address;
    String bankDetails;
    String phoneNumber;
    String emailId;
    boolean remindUserEmpPay;
    boolean remindUserOrderDelivery;
    boolean feedBackService;
    boolean trackingService;
    Vector<Customer> buyers;
    Preferences defaultSettings;
    Inventory stock;

    Register(String username,String password,String firmName,String gstin,String address,String bankDetails,
        String phoneNumber,String emailId,int billType,boolean remindUserEmpPay,boolean remindUserOrderDelivery,
        boolean feedBackService,boolean trackingService)
    {
        this.username = username;
        this.password = password;
        this.firmName = firmName;
        this.gstin = gstin;
        this.address = address;
        this.bankDetails = bankDetails;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.billType = billType;
        this.remindUserEmpPay = remindUserEmpPay;
        this.remindUserOrderDelivery = remindUserOrderDelivery;
        this.feedBackService = feedBackService;
        this.trackingService = trackingService;
        stock = new Inventory();
        buyers = new Vector<Customer>();
    }
    boolean addBuyer(String gstin,String name,String email,String phoneNumber,String address)
    {
        Customer obj = new Customer(firmId,gstin,name,email,phoneNumber,address);
        obj.add();
        return true;
    }
    boolean addProduct(String name,String hsnCode,float taxSlab,float costPrice,
        float sellingPrice,float amount)
    {
        Product obj = new Product(firmId,name,hsnCode,taxSlab,costPrice,sellingPrice,amount);
        stock.addNewProduct(obj);
        return true;
    }
    /**
     * TO BE COMPLETED
     *
     *
     *
     *
     */
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

    int addFirm()
    {
        Authenticator obj = new Authenticator();
        System.out.println("\n\nIn Register \n\n\n");
        if(obj.userAlreadyExist(username)!=-1)
            return 1;
        DatabaseConnect db = new DatabaseConnect("Login");
        DatabaseConnect dbId = new DatabaseConnect("fId");
        Iterator it = db.getIterator();
        while (it.hasNext())
        {
            Utility obj = new Utility();
            obj.breakDatabaseString(it.next().toString());
            firmId = obj.getAttribute("firmId");
        }
        dbId.deleteOne(new Document("firmId",firmId));
        Document doc = new Document("firmId",++firmId);
        dbId.getCollection().insertOne(doc);
        Document document = new Document("firmId",firmId)
        .append("firmName",firmName)
        .append("username",username)
        .append("password",password);
        db.getCollection().insertOne(document);
        db = new DatabaseConnect("Firm");
        document = new Document("firmId",firmId)
        .append("username",username)
        .append("password",password)
        .append("gstin",gstin)
        .append("address",address)
        .append("bankDetails",bankDetails)
        .append("phoneNumber",phoneNumber)
        .append("emailId",emailId);
        db.getCollection().insertOne(document);
        defaultSettings = new Preferences(firmId,billType,remindUserEmpPay,remindUserOrderDelivery,
        feedBackService,trackingService);
        defaultSettings.addPreferences();
        return 0;
    }
}