import com.thinking.machines.util.*;
class ForEachTestCase
{
public static void main(String gg[])
{
TMList list1=new TMArrayList();
list1.add(10);
list1.add(20);
list1.add(30);
list1.add(40);
System.out.println("Iterating in array list : ");
list1.forEach((p)->{System.out.println(p);});

TMList<Double> list2=new TMLinkedList();
list2.add(42.42);
list2.add(24.999);
list2.add(86.001);
list2.add(412.223);
System.out.println("Iterating in linked list : ");
list2.forEach((p)->{System.out.println(p);});

System.out.println("Iterating in array list : ");
TMArrayList<String> i=new TMArrayList<>();
i.add("Babita ji");
i.add("Akansha Monga");
i.add("Shruti Hassan");
i.add("Disha Patni");
i.forEach((m)->{System.out.println(m);});

System.out.println("Iterating in linked list : ");
TMLinkedList<Character> j=new TMLinkedList<>();
j.add('i');
j.add(new Character('a'));
j.add('P');
j.forEach((p)->{System.out.println(p);});
}
}