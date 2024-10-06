import com.thinking.machines.util.*;
class TMArrayListTestCase
{
public static void main(String gg[])
{
try
{
TMArrayList list1;
list1=new TMArrayList();
for(int x=1;x<=100;x++) list1.add(x);
System.out.println("Size of list1 is : "+list1.size());
for(int e=0;e<list1.size();e++) System.out.print(list1.get(e)+" ");
System.out.printf("\n");
list1.add(0,34343);
list1.add(101,439);
list1.add(12,999);
System.out.println("Size of list1 is : "+list1.size());
for(int e=0;e<list1.size();e++) System.out.print(list1.get(e)+" ");
System.out.printf("\n");
list1.remove(0);
list1.remove(101);
list1.remove(43);
System.out.println("Size of list1 is : "+list1.size());
for(int e=0;e<list1.size();e++) System.out.print(list1.get(e)+" ");
System.out.printf("\n");
//list1.removeAll();
//System.out.println("Size of list1 is : "+list1.size());
TMArrayList list2;
list2=new TMArrayList();
int j=0;
for(int x=1000;x<1080;x++)
{
list2.add(j,x);
j++;
}
list2.update(4,999999);
list2.appendFrom(list1);
System.out.println("Size of list2 is : "+list2.size());
for(int e=0;e<list2.size();e++) System.out.print(list2.get(e)+" ");
System.out.printf("\n");
list2.appendTo(list1);
System.out.println("Size of list1 is : "+list1.size());
for(int e=0;e<list1.size();e++) System.out.print(list1.get(e)+" ");
System.out.printf("\n");
}catch(IndexOutOfBoundsException ioofbe)
{
System.out.println(ioofbe);
}
}
}