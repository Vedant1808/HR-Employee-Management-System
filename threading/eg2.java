class aaa extends Thread
{
aaa()
{
start();
}
public void run()
{
for(int i=200;i<250;i++)
{
System.out.print(i+" ");
}
}
}
class eg2psp
{
public static void main(String gg[])
{
aaa a=new aaa();
for(int i=5001;i<5300;i++)
{
System.out.print(i+" ");
}
}
}