/*
c:\javaProjects\hr\pl\testcases>notepad DesignationModelTestCase.java
Compile : C:\JavaProjects\hr\pl\testcases>javac -classpath ..\..\dl\dist\hr-dl-1.0.jar;..\..\common\dist\hr-common.jar;..\..\bl\build\libs\bl.jar;..\build\libs\pl.jar;. *.java

Execute : C:\JavaProjects\hr\pl\testcases>java -classpath ..\..\dl\dist\hr-dl-1.0.jar;..\..\common\dist\hr-common.jar;..\..\bl\build\libs\bl.jar;..\build\libs\pl.jar;. DesignationModelTestCase

Add Designation at Data Layer from testcases of Presentation Layer:(add required classpath and testcases of dl)
C:\JavaProjects\hr\pl\testcases>java -classpath ..\..\dl\dist\hr-dl-1.0.jar;..\..\common\dist\hr-common.jar;..\..\dl\testcases;. DesignationAddTestCase Officer

Now the file must be in testcases folder of pl so , but it is in 
testcases of bl
***** jha ham file ko execute kar rahe hai vaha file hona chaiye
C:\JavaProjects\hr\pl\testcases>copy ..\..\bl\testcases\*.data
*/
import com.thinking.machines.hr.pl.model.*;
import com.thinking.machines.hr.bl.exceptions.*;
import javax.swing.*;
import java.awt.*;
public class DesignationModelTestCase extends JFrame
{
private DesignationModel designationModel;
private JTable tb;
private JScrollPane jsp;
private Container container;
public DesignationModelTestCase()
{
designationModel=new DesignationModel();
/*
DesignationModel constructor->populateDataStructures()->getDesignationManager()->constructuor()->populateData
Structure of bl->take data from dl and build DS -> getDesignations (take data from DS)
*/
tb=new JTable(designationModel);
jsp=new JScrollPane(tb,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
container=getContentPane();
container.setLayout(new BorderLayout());
container.add(jsp);
setLocation(300,200);
setSize(400,600);
setVisible(true);
setDefaultCloseOperation(EXIT_ON_CLOSE);
}
public static void main(String gg[])
{
DesignationModelTestCase designationModelTestCase=new DesignationModelTestCase();
}
}