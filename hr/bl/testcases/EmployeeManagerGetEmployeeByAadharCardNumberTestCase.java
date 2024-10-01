import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.enums.*;
import java.text.*;
import java.util.*;
import java.math.*;
public class  EmployeeManagerGetEmployeeByAadharCardNumberTestCase
{
public static void main(String gg[])
{
String aadharCardNumber=gg[0];
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
try
{
EmployeeManagerInterface employeeManager=EmployeeManager.getEmployeeManager();
EmployeeInterface employee=employeeManager.getEmployeeByAadharCardNumber(aadharCardNumber);
System.out.println("Employee id : "+employee.getEmployeeId());
System.out.println("Name : "+employee.getName());
DesignationInterface designation=employee.getDesignation();
System.out.println("Designation code : "+designation.getCode());
Date dateOfBirth=employee.getDateOfBirth();
System.out.println("Date of birth : "+sdf.format(dateOfBirth));
System.out.println("Gender : "+employee.getGender());
System.out.println("Is indian : "+employee.getIsIndian());
System.out.println("Basic salary : "+employee.getBasicSalary().toPlainString());
System.out.println("PAN number : "+employee.getPANNumber());
System.out.println("Aadhar card number : "+employee.getAadharCardNumber());
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