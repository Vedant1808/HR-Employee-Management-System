package com.thinking.machines.util;
import java.lang.reflect.*;
import java.io.*;
public class SetterGetterGenerator
{
public static void main(String gg[])
{
if(gg.length!=1 && gg.length!=2)
{
System.out.println("Usage : java -classpath path_to_jar_file;. com.thinking.machines.util.SetterGetterGenerator class_name constructor=true/false");
return;
}
if(gg.length==2)
{
if(gg[1].equalsIgnoreCase("constructor=true")==false && gg[1].equalsIgnoreCase("constructor=false")==false)
{
System.out.println("Usage : java -classpath path_to_jar_file;. com.thinking.machines.util.SetterGetterGenerator class_name constructor=true/false");
return;
}
}
String className=gg[0];
try
{
Class c=Class.forName(className);
Field fields[]=c.getDeclaredFields();
Field field;
TMList<String> list=new TMArrayList<String>();
String fieldName;
String tmp;
String line;
Class fieldType;
if(gg.length==1 || (gg.length==2 && gg[1].equalsIgnoreCase("constructor=true")))
{
line="public "+c.getSimpleName()+"()";
list.add(line);
list.add("{");
for(int e=0;e<fields.length;e++)
{
field=fields[e];
fieldName=field.getName();
fieldType=field.getType();
line="this."+fieldName+"="+getDefaultValue(fieldType)+";";
list.add(line);
}
list.add("}");
}
String setterName;
String getterName;
for(int e=0;e<fields.length;e++)
{
field=fields[e];
fieldName=field.getName();
fieldType=field.getType();
if(fieldName.charAt(0)>=97 && fieldName.charAt(0)<=122)
{
tmp=fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
}
else
{
tmp=fieldName;
}
setterName="set"+tmp;
getterName="get"+tmp;
line="public void "+setterName+"("+fieldType.getName()+" "+fieldName+")";
list.add(line);
list.add("{");
line="this."+fieldName+"="+fieldName+";";
list.add(line);
list.add("}");
line="public "+fieldType.getName()+" "+getterName+"()";
list.add(line);
list.add("{");
line="return this."+fieldName+";";
list.add(line);
list.add("}");
}
File file=new File("tmp.tmp");
if(file.exists()) file.delete();
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
TMIterator<String> iter=list.iterator();
while(iter.hasNext())
{
line=iter.next();
randomAccessFile.writeBytes(line+"\r\n");
}
randomAccessFile.close();
System.out.println("setter getter for : "+className+" is generated in file name as tmp.tmp");
}catch(ClassNotFoundException classNotFoundException)
{
System.out.println("unable to load class,classpath missing");
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
}
private static String getDefaultValue(Class c)
{
String type=c.getName();
if(type.equals("java.lang.Long") || type.equals("long")) return "0";
if(type.equals("java.lang.Integer")|| type.equals("int")) return "0";
if(type.equals("java.lang.Short")|| type.equals("short")) return "0";
if(type.equals("java.lang.Byte")|| type.equals("byte")) return "0";
if(type.equals("java.lang.Double")|| type.equals("double")) return "0.0";
if(type.equals("java.lang.Float")|| type.equals("float")) return "0.0f";
if(type.equals("java.lang.Character")|| type.equals("char")) return "' '";
if(type.equals("java.lang.Boolean")|| type.equals("boolean")) return "false";
if(type.equals("java.lang.String")) return "\"\"";
return "null";
}
}