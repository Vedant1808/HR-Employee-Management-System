import com.thinking.machines.util.*;
class TMLinkedListTestCase
{
public static void main(String gg[])
{
try
{
TMLinkedList list1;
list1=new TMLinkedList();
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
TMLinkedList list2;
list2=new TMLinkedList();
int j=0;
for(int x=1000;x<1080;x++)
{
list2.add(j,x);
j++;
}
//list2.update(4,999999);
//list2.insert(80,11111);
//list2.insert(0,22222);
//list2.insert(43,33333);
System.out.println("Size of list2 is : "+list2.size());
for(int e=0;e<list2.size();e++) System.out.print(list2.get(e)+" ");
TMArrayList list3;
list3=new TMArrayList();
System.out.printf("\n");
list1.appendTo(list2);
list2.copyTo(list1);
list3.appendFrom(list2);
System.out.println("Size of list1 is : "+list1.size());
for(int e=0;e<list1.size();e++) System.out.print(list1.get(e)+" ");
System.out.printf("\n");
System.out.println("Size of list2 is : "+list2.size());
for(int e=0;e<list2.size();e++) System.out.print(list2.get(e)+" ");
System.out.printf("\n");
System.out.println("Size of list3 is : "+list3.size());
for(int e=0;e<list3.size();e++) System.out.print(list3.get(e)+" ");
System.out.printf("\n");
}catch(IndexOutOfBoundsException ioofbe)
{
System.out.println(ioofbe);
}
}
}