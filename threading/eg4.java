class cmn
{
private String something;
synchronized public void keep(String something)
{
this.something=something;
System.out.printf("I have kept %s, now I am tired and I am going to sleep\n",this.something);
try
{
Thread.sleep(1000);
}catch(InterruptedException ie)
{
System.out.println(ie);
}
System.out.printf("Now I am awake, I am picking what I kept %s\n",this.something);
}
}
class Worker extends Thread
{
private String something;
private cmn somePlace;
Worker(cmn somePlace,String something)
{
this.somePlace=somePlace;
this.something=something;
start();
}
public void run()
{
this.somePlace.keep(this.something);
}
}
class eg4psp
{
public static void main(String gg[])
{
cmn commonPlace=new cmn();
Worker w1=new Worker(commonPlace,"Gold");
Worker w2=new Worker(commonPlace,"Silver");
Worker w3=new Worker(commonPlace,"Diamond");
}
}