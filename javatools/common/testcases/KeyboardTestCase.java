import com.thinking.machines.util.*;
class KeyboardTestCase
{
public static void main(String gg[])
{
Keyboard k=new Keyboard();
String a;
System.out.print("Enter string : ");
a=k.getString();
String b;
b=k.getString("Enter another string : ");
char c;
c=k.getCharacter("Enter gender(M/F) : ");
long d;
d=k.getLong("Enter a long type value : ");
int e;
e=k.getInt("Enter a int type value : ");
short f;
f=k.getShort("Enter a short type value : ");
byte g;
g=k.getByte("Enter a byte type value : ");
float h;
h=k.getFloat("Enter a float type value : ");
Double i;
i=k.getDouble("Enter a double type value : ");
boolean j;
j=k.getBoolean("Enter a boolean type value : ");
System.out.println(a);
System.out.println(b);
System.out.println(c);
System.out.println(d);
System.out.println(e);
System.out.println(f);
System.out.println(g);
System.out.println(h);
System.out.println(i);
System.out.println(j);
}
}