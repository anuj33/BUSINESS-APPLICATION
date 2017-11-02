import java.util.*;
public class Utility
{
    HashMap<String , String> mapping = new HashMap<String , String>();
    public static String removeExcessSpace(String str)
    {
        // System.out.println("remove space "+str);
        if(str.length() == 0 || str.equals(""))
          return null;
        int start = 0 ,end = str.length() - 1;
        while(str.charAt(start)==' ' || str.charAt(start)=='\n')
            start++;
        while(str.charAt(end)==' ' || str.charAt(end)=='\n')
            end--;
        str = str.substring(start,end+1);
        end = str.length();
        String result="";
        boolean take=true;
        for (int i = 0; i < end; ++i)
        {
            if(!(take==false && str.charAt(i) == ' '))
                result += str.charAt(i);
            take = (str.charAt(i) == ' ') ? false : true;
        }
        return result;
    }
    public static String[] breakTheSentence(String sentence,char delimeter)
    {
       char temp[] = new char[400];
       String words[] = new String[100];
       int numberOfWords=0;
       int k=0,i;
       for (i=0;i<sentence.length() ;i++ )
       {
           if(sentence.charAt(i)==delimeter)
           {
               words[numberOfWords++] = new String(temp,0,k);
               k=0;
           }
           else
           {
               temp[k++]=sentence.charAt(i);
           }
       }
       words[numberOfWords++]=new String(temp,0,k);
       String wordLen[]=new String[numberOfWords];
       for (i=0;i<numberOfWords ;i++ )
       {
            wordLen[i]=words[i];
       }
       return wordLen;
    }

    public void breakDatabaseString(String str)
    {
        int start = str.lastIndexOf('{',str.length());
        int end = str.indexOf('}',0);
        // System.out.println(start + "  " + end);
        str = str.substring(start+1,end);
        // System.out.println(str);
        String pairs[] = breakTheSentence(str,',');
        for (int i=0;i < pairs.length ;i++)
        {
            // System.out.println("pairs i "+pairs[i]);
            String keyValue[] = breakTheSentence(pairs[i],'=');
            mapping.put(removeExcessSpace(keyValue[0]),removeExcessSpace(keyValue[1]));
        }
    }
    public void dateHandeler(String date)
    {
        String pairs[] = breakTheSentence(date,' ');
        String time[] = breakTheSentence(removeExcessSpace(pairs[3]),':');
        mapping.put("day",pairs[0]);
        mapping.put("month",pairs[1]);
        mapping.put("date",pairs[2]);
        mapping.put("hour",time[0]);
        mapping.put("minute",time[1]);
        mapping.put("second",time[2]);
        mapping.put("timezone",pairs[4]);
        mapping.put("year",pairs[5]);
    }
    public String getAttribute(String key)
    {
        if(mapping.containsKey(key))
            return mapping.get(key);
        return null;
    }
    public static int getMonth(String month)
    {
      HashMap<String,Integer> monthList = new HashMap<String,Integer>();
      monthList.put("jan",0);
      monthList.put("feb",1);
      monthList.put("mar",2);
      monthList.put("apr",3);
      monthList.put("may",4);
      monthList.put("jun",5);
      monthList.put("jul",6);
      monthList.put("aug",7);
      monthList.put("sep",8);
      monthList.put("oct",9);
      monthList.put("nov",10);
      monthList.put("dec",11);
      month = removeExcessSpace(month);
      month = month.toLowerCase();
      if(monthList.containsKey(month))
        return monthList.get(month);
      return 0;
    }
    public static int getDay(String day)
    {
      HashMap<String,Integer> dayList = new HashMap<String,Integer>();
      dayList.put("mon",1);
      dayList.put("tue",2);
      dayList.put("wed",3);
      dayList.put("thu",4);
      dayList.put("fri",5);
      dayList.put("sat",6);
      dayList.put("sun",7);
      day = removeExcessSpace(day);
      day = day.toLowerCase();
      if(dayList.containsKey(day))
        return dayList.get(day);
      return 0;
    }
    public static int getNumberOfDays(int month,int year)
    {
      if(year%4 == 0 && month == 2)
        return 29;
      HashMap<Integer,Integer> dayNum = new HashMap<Integer,Integer>();
      dayNum.put(0,31);
      dayNum.put(1,28);
      dayNum.put(2,31);
      dayNum.put(3,30);
      dayNum.put(4,31);
      dayNum.put(5,30);
      dayNum.put(6,31);
      dayNum.put(7,31);
      dayNum.put(8,30);
      dayNum.put(9,31);
      dayNum.put(10,30);
      dayNum.put(11,31);
      if(dayNum.containsKey(month))
        return dayNum.get(month);
      return 0;
    }
    public static String getMonthInString(int month)
    {
      HashMap<Integer,String> monthList = new HashMap<Integer,String>();
      monthList.put(0,"jan");
      monthList.put(1,"feb");
      monthList.put(2,"mar");
      monthList.put(3,"apr");
      monthList.put(4,"may");
      monthList.put(5,"jun");
      monthList.put(6,"jul");
      monthList.put(7,"aug");
      monthList.put(8,"sep");
      monthList.put(9,"oct");
      monthList.put(10,"nov");
      monthList.put(11,"dec");
      if(monthList.containsKey(month))
        return monthList.get(month);
      return null;
    }
    public static double getVectorMax(Vector<Double> vec)
    {
        double maxVector = vec.get(0);
        for (int i = 1;i < vec.size() ;i++ )
        {
            if(maxVector < vec.get(i))
                maxVector = vec.get(i);
        }
        return maxVector;
    }
}