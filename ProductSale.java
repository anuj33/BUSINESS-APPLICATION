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
import java.lang.Object;
import org.bson.types.ObjectId;

public class ProductSale
{
    String id;
    String name;
    float rate;
    float qty;
    float taxSlab;
    float totalTax;
    float discRate;
    float totalDiscount;
    float totalPrice;
    ProductSale(String id,String name,float rate,float qty,float taxSlab,float totalTax,float discRate,
        float totalDiscount,float totalPrice)
    {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.qty = qty;
        this.taxSlab = taxSlab;
        this.totalTax = totalTax;
        this.discRate = discRate;
        this.totalDiscount = totalDiscount;
        this.totalPrice = totalPrice;
    }
    ProductSale(Utility obj)
    {
        id = obj.getAttribute("transactionId");
        name = obj.getAttribute("name");
        rate = Float.valueOf(obj.getAttribute("rate"));
        qty = Float.valueOf(obj.getAttribute("qty"));
        taxSlab = Float.valueOf(obj.getAttribute("taxSlab"));
        totalTax = Float.valueOf(obj.getAttribute("totalTax"));
        discRate = Float.valueOf(obj.getAttribute("discRate"));
        totalDiscount = Float.valueOf(obj.getAttribute("totalDiscount"));
        totalPrice = Float.valueOf(obj.getAttribute("totalPrice"));
    }
    void addNewProductSale()
    {
        DatabaseConnect db = new DatabaseConnect("ProductSale");
        Document document = new Document("transactionId",id)
        .append("name",name)
        .append("rate",rate)
        .append("qty",qty)
        .append("taxSlab",taxSlab)
        .append("totalTax",totalTax)
        .append("discRate",discRate)
        .append("totalDiscount",totalDiscount)
        .append("totalPrice",totalPrice);
        db.getCollection().insertOne(document);
    }
    String getTransactionId()
    {
        return id;
    }
    String getName()
    {
        return name;
    }
    float getRate()
    {
        return rate;
    }
    float getQuantity()
    {
        return qty;
    }
    float getTaxSlab()
    {
        return taxSlab;
    }
    float getTotalTax()
    {
        return totalTax;
    }
    float getDiscountRate()
    {
        return discRate;
    }
    float getTotalDiscount()
    {
        return totalDiscount;
    }
    float getTotalPrice()
    {
        return totalPrice;
    }
}