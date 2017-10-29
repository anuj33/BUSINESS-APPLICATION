import java.util.*;
public class Authenticator
{
    Vector <String> username;
    Vector <String> password;
    Vector <Integer> firmId;
    int numberOfUsers;
    Authenticator()
    {
        numberOfUsers = 0;
        DatabaseConnect db = new DatabaseConnect("Login");
        Iterator it = db.getIterator();
        while (it.hasNext())
        {
            Utility obj = new Utility();
            obj.breakDatabaseString(it.next().toString());
            username.add(obj.getAttribute("username"));
            password.add(obj.getAttribute("password"));
            firmId.add(Integer.parseInt((obj.getAttribute("firmId"))));
            numberOfUsers++;
        }
    }
    int checkCredentials(String usr,String pass)
    {
        int len = username.size();
        for (int i=0;i<len ;i++)
        {
            if(username.elementAt(i) == usr && password.elementAt(i) == pass)
                return firmId.elementAt(i);
        }
        return -1;
    }
    int userAlreadyExist(String usr)
    {
        int len = username.size();
        for (int i=0;i<len ;i++)
        {
            if(username.elementAt(i) == usr)
                return firmId.elementAt(i);
        }
        return -1;
    }
}