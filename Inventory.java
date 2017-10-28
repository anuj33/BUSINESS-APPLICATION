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

public class Inventory
{
    Vector <Product> goods;
    int numberOfGoods;
    Inventory()
    {
        goods = new Vector<Product>();
        numberOfGoods = 0;
    }
    void fetchProduct()
    {
        DatabaseConnect db = new DatabaseConnect("Product");
        Iterator it = db.getIterator();
        while (it.hasNext())
        {
            Utility obj = new Utility();
            obj.breakDatabaseString(it.next().toString());
            Product curr = new Product(obj);
            goods.add(curr);
            numberOfGoods++;
        }
    }
    void addNewProduct(Product newProduct)
    {
        DatabaseConnect db = new DatabaseConnect("Product");
        Document document = new Document("firmId",newProduct.getFirmId())
        .append("name",newProduct.getName())
        .append("hsnCode",newProduct.getHsnCode())
        .append("taxSlab",newProduct.getTaxSlab())
        .append("costPrice",newProduct.getCostPrice())
        .append("sellingPrice",newProduct.getSellingPrice())
        .append("amount",newProduct.getAmount());
        db.getCollection().insertOne(document);
    }
    int getNumberOfGoods()
    {
        return numberOfGoods;
    }
    Vector<Product> getProductList()
    {
        return goods;
    }
}