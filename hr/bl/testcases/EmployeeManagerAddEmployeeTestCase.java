import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.enums.*;
import java.text.*;
import java.util.*;
import java.math.*;
public class EmployeeManagerAddEmployeeTestCase
{
public static void main(String gg[])
{
String name="Rani";
DesignationInterface designation=new Designation();
designation.setCode(6);
Date dateOfBirth=new Date();
char gender='F';
boolean isIndian=false;
BigDecimal basicSalary=new BigDecimal("2222");
String panNumber="PAN2222";
String aadharCardNumber="UID2222";
try
{
EmployeeInterface employee=new Employee();
employee.setName(name);
employee.setDesignation(designation);
employee.setDateOfBirth(dateOfBirth);
if(gender=='M') employee.setGender(GENDER.MALE);
if(gender=='F') employee.setGender(GENDER.FEMALE);
employee.setIsIndian(isIndian);
employee.setBasicSalary(basicSalary);
employee.setPANNumber(panNumber);
employee.setAadharCardNumber(aadharCardNumber);
EmployeeManagerInterface employeeManager=EmployeeManager.getEmployeeManager();
employeeManager.addEmployee(employee);
System.out.println("Emloyee added with employee Id : "+employee.getEmployeeId());
}catch(BLException blException)
{
if(blException.hasGenericException()) System.out.println(blException.getGenericException());
List<String> properties=blException.getProperties();
properties.forEach((property)->{
System.out.println(blException.getException(property));
});
}
}
}