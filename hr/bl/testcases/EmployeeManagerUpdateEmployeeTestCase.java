import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.enums.*;
import java.text.*;
import java.util.*;
import java.math.*;
public class EmployeeManagerUpdateEmployeeTestCase
{
public static void main(String gg[])
{
String employeeId="A10000001";
String name="Ankit";
DesignationInterface designation=new Designation();
designation.setCode(4);
Date dateOfBirth=new Date();
char gender='M';
boolean isIndian=true;
BigDecimal basicSalary=new BigDecimal("1212121");
String panNumber="PAN1234";
String aadharCardNumber="UID1234";
try
{
EmployeeInterface employee=new Employee();
employee.setEmployeeId(employeeId);
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
employeeManager.updateEmployee(employee);
System.out.println("Emloyee updated");
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