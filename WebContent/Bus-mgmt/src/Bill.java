import java.util.*;
public class Bill
{
    Hashtable<Integer,String> s = new Hashtable<Integer,String>();
    String getNum(int num)
    {
        if(num == 0)
            return null;
        if(s.containsKey(num))
            return s.get(num);
        // System.out.println("in the  sfeoinio "+ num + "   " + ((num/10)%10)*10 + "   " + num%10);
        String result="";
        if(s.containsKey(((num/10)%10)*10))
        {
            // System.out.println("yes " + result);
            result = result + s.get(((num/10)%10)*10);
        }
        if(s.containsKey(num%10))
        {
            // System.out.println("second yes " + result);
            result = result + " " + s.get(num%10);
        }
        return result;
    }
    public String numToWord(int num,String str)
    {
        s.put(1,"one");
        s.put(2,"two");
        s.put(3,"three");
        s.put(4,"four");
        s.put(5,"five");
        s.put(6,"six");
        s.put(7,"seven");
        s.put(8,"eight");
        s.put(9,"nine");
        s.put(10, "ten");
        s.put(11, "eleven");
        s.put(12, "tweleve");
        s.put(13, "thirteen");
        s.put(14, "fourteen");
        s.put(15, "fifteen");
        s.put(16, "sixteen");
        s.put(17, "seventeen");
        s.put(18, "eighteen");
        s.put(19, "nineteen");
        s.put(20, "tweenty");
        s.put(30, "thirty");
        s.put(40, "fourty");
        s.put(50, "fifty");
        s.put(60, "sixty");
        s.put(70, "seventy");
        s.put(80, "eighty");
        s.put(90, "ninety");
        String temp = "zero";
        if(num == 0)
            return temp;
        String units[] = {"","hundred","thousand","lack","crore","billion"};
        int []holder = new int[6];
        holder[0] = (str.charAt(1)-'0')*10 + str.charAt(0)-'0';
        holder[1] = str.charAt(2)-'0';
        holder[2] = (str.charAt(4)-'0')*10 + str.charAt(3)-'0';
        holder[3] = (str.charAt(6)-'0')*10 + str.charAt(5)-'0';
        holder[4] = (str.charAt(8)-'0')*10 + str.charAt(7)-'0';
        holder[5] = (str.charAt(10)-'0')*10 + str.charAt(9)-'0';
        String numberInWords="";
        for (int i = 5; i >= 0 ; --i)
        {
            temp = getNum(holder[i]);
            // System.out.println(temp);
            if( temp != null )
                numberInWords += temp + " " + units[i] + " ";
        }
        return numberInWords + " rupees";
    }
}