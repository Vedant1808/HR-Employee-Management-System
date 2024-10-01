import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.managers.*;
import java.util.*;
public class EmployeeManagerEmployeeIdExistsTestCase
{
public static void main(String gg[])
{
try
{
String employeeId=gg[0];
EmployeeManagerInterface employeeManager=EmployeeManager.getEmployeeManager();
System.out.println("Employee id ("+employeeId+") exists : "+employeeManager.employeeIdExists(employeeId));
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