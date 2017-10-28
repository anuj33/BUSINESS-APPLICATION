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

public class Product
{
    int firmId;
    String name;
    String hsnCode;
    float taxSlab;
    float costPrice;
    float sellingPrice;
    float amount;
    Product(int firmId,String name,String hsnCode,float taxSlab,float costPrice,float sellingPrice,float amount)
    {
        this.firmId = firmId;
        this.name = name;
        this.hsnCode = hsnCode;
        this.taxSlab = taxSlab;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.amount = amount;
    }
    Product(Utility obj)
    {
        firmId = Integer.parseInt(obj.getAttribute("firmId"));
        name = obj.getAttribute("name");
        hsnCode = obj.getAttribute("hsnCode");
        taxSlab = Float.valueOf(obj.getAttribute("taxSlab"));
        costPrice = Float.valueOf(obj.getAttribute("costPrice"));
        sellingPrice = Float.valueOf(obj.getAttribute("sellingPrice"));
        amount = Float.valueOf(obj.getAttribute("amount"));
    }
    double getTax()
    {
        return (sellingPrice*taxSlab)/100.0;
    }
    int getFirmId()
    {
        return firmId;
    }
    String getHsnCode()
    {
        return hsnCode;
    }
    String getName()
    {
        return name;
    }
    float getTaxSlab()
    {
        return  taxSlab;
    }
    float getCostPrice()
    {
        return costPrice;
    }
    float getSellingPrice()
    {
        return sellingPrice;
    }
    float getAmount()
    {
        return amount;
    }
}