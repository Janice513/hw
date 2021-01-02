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

    System.out.println("                             �ǥͦ��Z�޲z�t��                 ");
    System.out.println();
    System.out.println("               1 ��J�O��                       2 ��X�Ҧ��O��");
    System.out.println("               3 ���������Z�ƧǨÿ�X           4 �d�߰O��");
    System.out.println("               5 �έp�U���Ƭq�H��               6 �R���O��");
    System.out.println("               0 �h�X");

    for (int i = 0; i < 80; i++)
    {
      System.out.print('=');
    }
    System.out.println();
    System.out.print("�п�J�R�O:");
  }

  public static int action1()
  {
    while(true)
    {
      clrscr();
      System.out.print("�ݿ�J�O�����Ǹ�(��J��-1���h�X):");

      int number = Console.readInt();
      if (number != -1)
      {
        System.out.print("  *�m�W:");
        String name = Console.readSting();
        System.out.print("  *�^�y:");
        int english = Console.readInt();
        System.out.print("  *�ƾ�:");
        int math = Console.readInt();
        System.out.print("  *�p���:");
        int computer = Console.readInt();
        Student newData = new Student(number, name, english, math, computer);
        if (StudentSet.addData(newData) == -1)
        {
          System.out.println("��Ʒ���!");
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
      System.out.println("�S���O��!");
    } else
    {
      System.out.println("         �ƾ�|�^�y|�p���|����|�`�p");
      for (int i = 0; i < StudentSet.getLen(); i++)
      {
        System.out.println("�Ǹ�:"+StudentSet.getData(i).getNumber()+"  �m�W:"+StudentSet.getData(i).getName());
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
    System.out.print("�п�J�n�d�߰O�����Ǹ�:");
    int number = Console.readInt();
    int i;
    if ((i = StudentSet.search(number)) != -1)
    {
      clrscr();
      System.out.println("         �ƾ�|�^�y|�p���|����|�`�p");
      System.out.println("�Ǹ�:"+StudentSet.getData(i).getNumber()+"  �m�W:"+StudentSet.getData(i).getName());
      System.out.println("      "+StudentSet.getData(i).getMath()+"    "+StudentSet.getData(i).getEnglish()+
        "    "+StudentSet.getData(i).getComputer()+"    "+StudentSet.getData(i).getAverage()+"    "+StudentSet.getData(i).getTotal());
    } else
    {
      System.out.println("�S�����������O��!");
    }
  }
  public static void action5()
  {
    clrscr();
    System.out.println("90-100: "+StudentSet.stat(90, 100)+"�H");
    System.out.println("80-89: "+StudentSet.stat(80, 89)+"�H");
    System.out.println("70-79: "+StudentSet.stat(70, 79)+"�H");
    System.out.println("60-69: "+StudentSet.stat(60, 69)+"�H");
    System.out.println("0-59: "+StudentSet.stat(0, 59)+"�H");
  }

  public static void action6()
  {
    while(true)
    {
      clrscr();
      System.out.print("�п�J�n�R���O�����Ǹ�(��J��-1���h�X):");
      int number = Console.readInt();
      if (number != -1)
      {
        if(StudentSet.delete(number) == -1)
        {
          System.out.println("�S�����������O��!");
          return;
        } else
        {
          System.out.println("�R���O�����\!");
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