import com.thinking.machines.util.*;
class TMIteratorTestCase
{
public static void main(String args[])
{
TMLinkedList list1=new TMLinkedList();
list1.add(10);
list1.add(20);
list1.add(30);
list1.add(40);
TMArrayList list2=new TMArrayList();
list2.add(1000);
list2.add(2000);
list2.add(3000);
System.out.println("Iterating on linked list");
TMIterator iterator1=list1.iterator();
int data;
while(iterator1.hasNext())
{
data=iterator1.next();
System.out.println(data);
}
System.out.println("Iterating on array list");
TMIterator iterator2=list2.iterator();
while(iterator2.hasNext())
{
data=iterator2.next();
System.out.println(data);
}
System.out.println("Iterating on linked list");
//list1.forEach((j)->{System.out.println(j);});
System.out.println("Iterating on array list");
//list2.forEach((j)->{System.out.println(j);});
}
}