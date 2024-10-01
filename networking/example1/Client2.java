import java.net.*;
import java.io.*;
class Client2
{
public static void main(String gg[])
{
try
{
int rollNumber=Integer.parseInt(gg[0]);
String name=gg[1];
String gender=gg[2];
String request=rollNumber+","+name+","+gender+"#";
Socket socket=new Socket("localhost",5500);
OutputStream os;
OutputStreamWriter osw;
os=socket.getOutputStream();
osw=new OutputStreamWriter(os);
osw.write(request);
osw.flush();
InputStream is;
InputStreamReader isr;
is=socket.getInputStream();
isr=new InputStreamReader(is);
int x;
StringBuffer sb=new StringBuffer();
String response;
while(true)
{
x=isr.read();
if(x==-1) break;
if(x=='#') break;
sb.append((char)x);
}
response=sb.toString();
System.out.println("Response Arrived");
System.out.println(response);
socket.close();
}catch(Exception exception)
{
System.out.println(exception);
}
}
}