package stu_sys;
import java.io.*;
public class Main
{
  public static void main(String[] args)
  {
    while(true)
    {
      Menu.paint();
      int ctrlTag = Console.readInt();
      switch(ctrlTag)
      {
        case 1:
        {
          Menu.action1();
          break;
        }

        case 2:
        {
          Menu.action2();
          break;
        }

        case 3:
        {
          Menu.action3();
          break;
        }

        case 4:
        {
          Menu.action4();
          break;
        }

        case 5:
        {
          Menu.action5();
          break;
        }

        case 6:
        {
          Menu.action6();
          break;
        }

        case 0:
        {
          System.exit(0);
          break;
        }
      }
    }
  }
}

class Student
{
  private int number;
  private String name;
  private int english;
  private int math;
  private int computer;
  private int average;
  private int total;

  public Student()
  {
  }

  public Student(int number, String name, int english, int math, int computer)
  {
    this.number = number;
    this.name = name;
    this.english = english;
    this.math = math;
    this.computer = computer;
    total = english + math + computer;
    average = total/3;
  }

  public int getNumber()
  {
    return number;
  }

  public String getName()
  {
    return name;
  }

  public int getEnglish()
  {
    return english;
  }

  public int getMath()
  {
    return math;
  }

  public int getComputer()
  {
    return computer;
  }

  public int getAverage()
  {
    return average;
  }

  public int getTotal()
  {
    return total;
  }
}

class StudentSet
{
  private static final int maxLen = 1000;
  private static int len = 0;
  private static Student[] data = new Student[maxLen];

  public static int getMaxLen()
  {
    return maxLen;
  }

  public static int getLen()
  {
    return len;
  }

  public static Student getData(int index)
  {
    return data[index];
  }

  public static int addData(Student newData)
  {
    if (len < maxLen)
    {
      data[len] = newData;
      len++;
      return 0;
    } else
    {
      return -1;
    }
  }

  public static void sortData()
  {
    for (int i = 0; i < len -1; i++)
    {
      int minIndex = i;
      for (int j = i+1; j < len; j++)
      {
        if (data[minIndex].getAverage() > data[j].getAverage())
        {
          minIndex = j;
        }
      }
      if (minIndex != i)
      {
        Student temp = data[i];
        data[i] = data[minIndex];
        data[minIndex] = temp;
      }
    }
  }

  public static int search(int number)
  {
    for(int i = 0; i < len; i++)
    {
      if (data[i].getNumber() == number)
      {
        return i;
      }
    }
    return -1;
  }

  public static int delete(int number)
  {
    for (int i = 0; i < len; i++)
    {
      if (data[i].getNumber() == number)
      {
        for (int j = i + 1; j < len; j++)
        {
          data[j-1] = data[j];
        }
        len--;
        return 0;
      }
    }
    return -1;
  }

  public static int stat(int min, int max)
  {
    int sum =0;

    for (int i = 0; i < len; i++)
    {
      if ((data[i].getAverage() >= min) && (data[i].getAverage() <= max))
      {
        sum++;
      }
    }
    return sum;
  }
}

class Console
{
  public static int readInt()
  {
    int result = 0;
    try{
      String temp =new BufferedReader(new InputStreamReader(System.in)).readLine();
      result = Integer.parseInt(temp);
    } catch (Exception e)
    {
      System.out.println("Error: "+e);
    }
    return result;
  }

  public static String readSting()
  {
    String result = null;
    try{
      result = new BufferedReader(new InputStreamReader(System.in)).readLine();
    } catch (Exception e)
    {
      System.out.println("Error: "+e);
    }
    return result;
  }
}

class Menu
{
  public static void paint()
  {
    clrscr();
    for (int i = 0; i < 80; i++)
    {
      System.out.print('=');
    }
    System.out.println();

    System.out.println("                             學生成績管理系統                 ");
    System.out.println();
    System.out.println("               1 輸入記錄                       2 輸出所有記錄");
    System.out.println("               3 按平均成績排序並輸出           4 查詢記錄");
    System.out.println("               5 統計各分數段人數               6 刪除記錄");
    System.out.println("               0 退出");

    for (int i = 0; i < 80; i++)
    {
      System.out.print('=');
    }
    System.out.println();
    System.out.print("請輸入命令:");
  }

  public static int action1()
  {
    while(true)
    {
      clrscr();
      System.out.print("待輸入記錄的學號(輸入“-1”退出):");

      int number = Console.readInt();
      if (number != -1)
      {
        System.out.print("  *姓名:");
        String name = Console.readSting();
        System.out.print("  *英語:");
        int english = Console.readInt();
        System.out.print("  *數學:");
        int math = Console.readInt();
        System.out.print("  *計算機:");
        int computer = Console.readInt();
        Student newData = new Student(number, name, english, math, computer);
        if (StudentSet.addData(newData) == -1)
        {
          System.out.println("資料溢位!");
          return -2;
        }
      } else
      {
        return -1;
      }
    }
  }

  public static void action2()
  {
    clrscr();
    if (StudentSet.getLen() == 0)
    {
      System.out.println("沒有記錄!");
    } else
    {
      System.out.println("         數學|英語|計算機|平均|總計");
      for (int i = 0; i < StudentSet.getLen(); i++)
      {
        System.out.println("學號:"+StudentSet.getData(i).getNumber()+"  姓名:"+StudentSet.getData(i).getName());
        System.out.println("      "+StudentSet.getData(i).getMath()+"    "+StudentSet.getData(i).getEnglish()+
          "    "+StudentSet.getData(i).getComputer()+"    "+StudentSet.getData(i).getAverage()+"    "+StudentSet.getData(i).getTotal());
      }
    }
  }

  public static void action3()
  {
    StudentSet.sortData();
    action2();
  }

  public static void action4()
  {
    clrscr();
    System.out.print("請輸入要查詢記錄的學號:");
    int number = Console.readInt();
    int i;
    if ((i = StudentSet.search(number)) != -1)
    {
      clrscr();
      System.out.println("         數學|英語|計算機|平均|總計");
      System.out.println("學號:"+StudentSet.getData(i).getNumber()+"  姓名:"+StudentSet.getData(i).getName());
      System.out.println("      "+StudentSet.getData(i).getMath()+"    "+StudentSet.getData(i).getEnglish()+
        "    "+StudentSet.getData(i).getComputer()+"    "+StudentSet.getData(i).getAverage()+"    "+StudentSet.getData(i).getTotal());
    } else
    {
      System.out.println("沒有找到相應的記錄!");
    }
  }
  public static void action5()
  {
    clrscr();
    System.out.println("90-100: "+StudentSet.stat(90, 100)+"人");
    System.out.println("80-89: "+StudentSet.stat(80, 89)+"人");
    System.out.println("70-79: "+StudentSet.stat(70, 79)+"人");
    System.out.println("60-69: "+StudentSet.stat(60, 69)+"人");
    System.out.println("0-59: "+StudentSet.stat(0, 59)+"人");
  }

  public static void action6()
  {
    while(true)
    {
      clrscr();
      System.out.print("請輸入要刪除記錄的學號(輸入“-1”退出):");
      int number = Console.readInt();
      if (number != -1)
      {
        if(StudentSet.delete(number) == -1)
        {
          System.out.println("沒有找到相應的記錄!");
          return;
        } else
        {
          System.out.println("刪除記錄成功!");
        }
      } else
      {
        return;
      }
    }
  }

  public static void clrscr()
  { 
    System.out.println();
    System.out.println();
  }
}