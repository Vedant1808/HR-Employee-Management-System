import java.util.*;
import java.math.*;
class Batch
{}
class Student
{
private int rollNumber;
private String name;
private String city;
private boolean isAdult;
private float marks;
private String fName;
private String MName;
private char gender;
private double salary;
private short floor;
private String address;
private Batch batch;
private Date date;
private BigDecimal feePaid;
private abcd hiii;
public Student()
{
this.rollNumber=0;
this.name="";
this.city="";
this.isAdult=false;
this.marks=0.0f;
this.fName="";
this.MName="";
this.gender=' ';
this.salary=0.0;
this.floor=0;
this.address="";
this.batch=null;
this.date=null;
this.feePaid=null;
this.hiii=null;
}
public void setRollNumber(int rollNumber)
{
this.rollNumber=rollNumber;
}
public int getRollNumber()
{
return this.rollNumber;
}
public void setName(java.lang.String name)
{
this.name=name;
}
public java.lang.String getName()
{
return this.name;
}
public void setCity(java.lang.String city)
{
this.city=city;
}
public java.lang.String getCity()
{
return this.city;
}
public void setIsAdult(boolean isAdult)
{
this.isAdult=isAdult;
}
public boolean getIsAdult()
{
return this.isAdult;
}
public void setMarks(float marks)
{
this.marks=marks;
}
public float getMarks()
{
return this.marks;
}
public void setFName(java.lang.String fName)
{
this.fName=fName;
}
public java.lang.String getFName()
{
return this.fName;
}
public void setMName(java.lang.String MName)
{
this.MName=MName;
}
public java.lang.String getMName()
{
return this.MName;
}
public void setGender(char gender)
{
this.gender=gender;
}
public char getGender()
{
return this.gender;
}
public void setSalary(double salary)
{
this.salary=salary;
}
public double getSalary()
{
return this.salary;
}
public void setFloor(short floor)
{
this.floor=floor;
}
public short getFloor()
{
return this.floor;
}
public void setAddress(java.lang.String address)
{
this.address=address;
}
public java.lang.String getAddress()
{
return this.address;
}
public void setBatch(Batch batch)
{
this.batch=batch;
}
public Batch getBatch()
{
return this.batch;
}
public void setDate(java.util.Date date)
{
this.date=date;
}
public java.util.Date getDate()
{
return this.date;
}
public void setFeePaid(java.math.BigDecimal feePaid)
{
this.feePaid=feePaid;
}
public java.math.BigDecimal getFeePaid()
{
return this.feePaid;
}
public void setHiii(abcd hiii)
{
this.hiii=hiii;
}
public abcd getHiii()
{
return this.hiii;
}
}