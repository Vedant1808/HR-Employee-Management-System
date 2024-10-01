import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
public class EmployeeGetCountByDesignationCodeTestCase
{
public static void main(String gg[])
{
int designationCode=Integer.parseInt(gg[0]);
try
{
System.out.println("Number of employee with designation code : "+designationCode+" is : "+new EmployeeDAO().getCountByDesignationCode(designationCode));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}