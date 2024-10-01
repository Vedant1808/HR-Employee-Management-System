import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
public class EmployeeAadharCardNumberExistsTestCase
{
public static void main(String gg[])
{
try
{
System.out.println("Aadhar card number : "+gg[0]+" exists : "+new EmployeeDAO().aadharCardNumberExists(gg[0]));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}