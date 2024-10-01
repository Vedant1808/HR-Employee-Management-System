import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
public class EmployeePANNumberExistsTestCase
{
public static void main(String gg[])
{
try
{
System.out.println("PAN number : "+gg[0]+" exists : "+new EmployeeDAO().panNumberExists(gg[0]));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}