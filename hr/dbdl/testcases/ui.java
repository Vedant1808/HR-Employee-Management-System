import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import java.util.*;
class DesignationListModel extends AbstractTableModel
{
private Set<DesignationDTOInterface> setDesignation;
private DesignationDAOInterface designationDAO;
private String title[];
private Object data[][];
DesignationListModel()
{
populateDataStructure();
}
public int getRowCount()
{
return data.length;
}
public int getColumnCount()
{
return title.length;
}
public String getColumnName(int columnIndex)
{
return title[columnIndex];
}
public Object getValueAt(int rowIndex,int columnIndex)
{
return data[rowIndex][columnIndex];
}
public boolean isCellEditable(int rowIndex,int columnIndex)
{
return false;
}
public Class getColumnClass(int columnIndex)
{
Class c=null;
try
{
if(columnIndex==0 || columnIndex==2) c=Class.forName("java.lang.Integer");
if(columnIndex==1) c=Class.forName("java.lang.String");
}catch(Exception e)
{
System.out.println(e);
}
return c;
}
private void populateDataStructure()
{
title=new String[3];
title[0]="S.No";
title[1]="Designation";
title[2]="Code";
designationDAO=new DesignationDAO();
try
{
setDesignation=designationDAO.getAll();
}catch(DAOException e)
{
System.out.println(e);
}
data=new Object[setDesignation.size()][3];
int i=0;
int j=0;
int k=1;
for(DesignationDTOInterface designationDTO:setDesignation)
{
data[i][j]=k;
j++;
data[i][j]=designationDTO.getTitle();
j++;
data[i][j]=designationDTO.getCode();
i++;
j=0;
k++;
}
}
}
class ui extends JFrame
{
private JTable table;
private JScrollPane jsp;
private JButton b1;
private Container container;
private DesignationListModel designationListModel;
ui()
{
super("List of Designation");
designationListModel=new DesignationListModel();
table=new JTable(designationListModel);
jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
container=getContentPane();
container.setLayout(new BorderLayout());
container.add(jsp);
setLocation(400,200);
setSize(600,600);
setVisible(true);
setDefaultCloseOperation(EXIT_ON_CLOSE);
}
}
class uipsp
{
public static void main(String gg[])
{
ui a=new ui();
}
}