/*
C:\JavaProjects\hr\pl>copy testcases\*.data(data file where we execute the application)
Execute :- C:\JavaProjects\hr\pl>java -classpath ..\common\hr-common.jar;..\dl\dist\hr-dl-1.0.jar;..\bl\build\libs\bl.jar;build\libs\pl.jar;. com.thinking.machines.hr.pl.Main
*/
package com.thinking.machines.hr.pl.ui;
import com.thinking.machines.hr.pl.model.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.io.*;
public class DesignationUI extends JFrame implements ListSelectionListener
{
private JLabel titleLabel;
private JLabel searchLabel;
private JTextField searchTextField;
private JButton clearSearchTextFieldButton;
private JLabel searchErrorLabel;
private JTable designationTable;
private JScrollPane scrollPane;
private DesignationModel designationModel;
private Container container;
private DesignationPanel designationPanel;
private enum MODE{VIEW,ADD,EDIT,DELETE,EXPORT_TO_PDF};
private MODE mode;
private ImageIcon logoIcon;
private ImageIcon addIcon;
private ImageIcon editIcon;
private ImageIcon deleteIcon;
private ImageIcon cancelIcon;
private ImageIcon pdfIcon;
private ImageIcon saveIcon;
private ImageIcon clearIcon;
public DesignationUI()
{
initComponents();  // perform object creation in it
setAppearance();  // font setting,adding component in position
addListeners(); // event handling
setViewMode();
designationPanel.setViewMode();
}
private void initComponents()
{
logoIcon=new ImageIcon(this.getClass().getResource("/icons/logo_icon.png"));
//logoIcon=new ImageIcon("c:"+File.separator+"javaProjects"+File.separator+"hr"+File.separator+"icons"+File.separator+"logo_icon.png");
setIconImage(logoIcon.getImage());
addIcon=new ImageIcon(this.getClass().getResource("/icons/add_icon.png"));
editIcon=new ImageIcon(this.getClass().getResource("/icons/edit_icon.png"));
deleteIcon=new ImageIcon(this.getClass().getResource("/icons/delete_icon.png"));
cancelIcon=new ImageIcon(this.getClass().getResource("/icons/cancel_icon.png"));
pdfIcon=new ImageIcon(this.getClass().getResource("/icons/pdf_icon.png"));
saveIcon=new ImageIcon(this.getClass().getResource("/icons/save_icon.png"));
clearIcon=new ImageIcon(this.getClass().getResource("/icons/clear_icon.png"));
designationModel=new DesignationModel();
titleLabel=new JLabel("Designations");
searchLabel=new JLabel("Search");
searchTextField=new JTextField();
clearSearchTextFieldButton=new JButton("X");
searchErrorLabel=new JLabel("");
designationTable=new JTable(designationModel);
scrollPane=new JScrollPane(designationTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
container=getContentPane();
designationPanel=new DesignationPanel();
}
private void setAppearance()
{
Font titleFont=new Font("Verdana",Font.BOLD,18); 
Font captionFont=new Font("Verdana",Font.BOLD,16);
Font dataFont=new Font("Verdana",Font.PLAIN,16); 
Font searchErrorFont=new Font("Verdana",Font.BOLD,12);
Font columnHeaderFont=new Font("Verdana",Font.BOLD,16);

titleLabel.setFont(titleFont);
searchLabel.setFont(captionFont);
searchTextField.setFont(dataFont);
searchErrorLabel.setFont(searchErrorFont);
searchErrorLabel.setForeground(Color.red);
designationTable.setFont(dataFont);
designationTable.setRowHeight(30);
designationTable.getColumnModel().getColumn(0).setPreferredWidth(20);
designationTable.getColumnModel().getColumn(1).setPreferredWidth(400);
designationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
designationTable.setRowSelectionAllowed(true);

JTableHeader header=designationTable.getTableHeader();
header.setFont(columnHeaderFont);
header.setReorderingAllowed(false);
header.setResizingAllowed(false);                  // size of column do not change

int lm=0;   // left margin
int tm=0;   // top margin
titleLabel.setBounds(lm+10,tm+10,200,40);  //(left margin,right margin,width,height)
searchLabel.setBounds(lm+10,tm+10+40+10,100,30);
searchTextField.setBounds(lm+10+100+5,tm+10+40+10,400,30);
clearSearchTextFieldButton.setBounds(lm+10+100+10+400+10,tm+10+40+10,30,30);
searchErrorLabel.setBounds(lm+10+100+400+10-75,tm+10+10+20,100,20);
scrollPane.setBounds(lm+10,tm+10+40+10+30+10,565,300);
designationPanel.setBounds(lm+10,tm+10+40+10+30+10+300+10,565,200);

container.setLayout(null);   // by default border layout,we do not want any layout
container.add(titleLabel);
container.add(searchLabel);
container.add(searchTextField);
container.add(clearSearchTextFieldButton);
container.add(searchErrorLabel);
container.add(scrollPane);
container.add(designationPanel);

Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
int width=600;
int height=660;
setSize(width,height);
setLocation((d.width/2)-(width/2),(d.height/2)-(height/2));
setDefaultCloseOperation(EXIT_ON_CLOSE);
}
private void addListeners()
{
/*
add Listener to text field
if we write designation,remove or changed(even a single letter) ,we need to search designation,
so in every case we simply call seachDesignation() method , it search from Model. If indexOfTitle raised 
exception means we type wrong designation otherwise we selected the row from rowIndex to rowIndex
*/
searchTextField.getDocument().addDocumentListener(new DocumentListener(){
public void insertUpdate(DocumentEvent de)
{
searchDesignation();
}
public void changedUpdate(DocumentEvent de)
{
searchDesignation();
}
public void removeUpdate(DocumentEvent de)
{
searchDesignation();
}
});
// add Listener to clear button,when click text field become empty and text field get focused
clearSearchTextFieldButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
searchTextField.setText("");
searchTextField.requestFocus();
}
});
designationTable.getSelectionModel().addListSelectionListener(this);
}
public void valueChanged(ListSelectionEvent ev)
{
int selectedRowIndex=designationTable.getSelectedRow();
try
{
DesignationInterface designation=designationModel.getDesignationAt(selectedRowIndex);
designationPanel.setDesignation(designation);
}catch(BLException blException)
{
designationPanel.clearDesignation();
}
}
private void searchDesignation()
{
searchErrorLabel.setText("");
String title=searchTextField.getText().trim();
if(title.length()==0) return;
int rowIndex;
try
{
rowIndex=designationModel.indexOfTitle(title,true);
}catch(BLException blException)
{
searchErrorLabel.setText("Not found");
return;
}
designationTable.setRowSelectionInterval(rowIndex,rowIndex); 
Rectangle rectangle=designationTable.getCellRect(rowIndex,0,true); // selecting the rectangle with rowIndex and columnIndex;
designationTable.scrollRectToVisible(rectangle);  // put rectangle portion into visiblity area
}
/*
There are different stages;
When user only view ,it is in VIEW mode.
when user click on add button , it go to ADD mode.
When add mode,user either click on save button or cancel button.
when user click save button , again it comes in view mode
So we created enumerated data type MODE and take one pointer of it.
Both outer and inner class contain setViewMode method
*/
private void setViewMode()
{
this.mode=MODE.VIEW;
if(designationModel.getRowCount()==0)
{
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);
}
else
{
searchTextField.setEnabled(true);
clearSearchTextFieldButton.setEnabled(true);
designationTable.setEnabled(true);
}
}
private void setAddMode()
{
this.mode=MODE.ADD;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);
}
private void setEditMode()
{
this.mode=MODE.EDIT;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);
}
private void setDeleteMode()
{
this.mode=MODE.DELETE;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);
}
private void setExportToPDFMode()
{
this.mode=MODE.EXPORT_TO_PDF;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);
}
// inner class starts
class DesignationPanel extends JPanel 
{
private JLabel titleCaptionLabel;
private JLabel titleLabel;   // when click on any designation ,data is shown in Label
private JTextField titleTextField;           
private JButton clearTitleTextFieldButton;
private JButton addButton;
private JButton editButton;
private JButton cancelButton;
private JButton deleteButton;
private JButton exportToPDFButton;
private JPanel buttonsPanel;
private DesignationInterface designation;
DesignationPanel()
{
setBorder(BorderFactory.createLineBorder(new Color(165,165,165)));
initComponents();
setAppearance();
addListeners();
}
private void initComponents()
{
designation=null;
titleCaptionLabel=new JLabel("Designation");
titleLabel=new JLabel("");
titleTextField=new JTextField();
clearTitleTextFieldButton=new JButton(clearIcon);
clearTitleTextFieldButton.setHorizontalTextPosition(SwingConstants.CENTER);
clearTitleTextFieldButton.setVerticalTextPosition(SwingConstants.CENTER);
buttonsPanel=new JPanel();
addButton=new JButton(addIcon);
addButton.setHorizontalTextPosition(SwingConstants.CENTER);
addButton.setVerticalTextPosition(SwingConstants.CENTER);
editButton=new JButton(editIcon);
editButton.setHorizontalTextPosition(SwingConstants.CENTER);
editButton.setVerticalTextPosition(SwingConstants.CENTER);
cancelButton=new JButton(cancelIcon);
cancelButton.setHorizontalTextPosition(SwingConstants.CENTER);
cancelButton.setVerticalTextPosition(SwingConstants.CENTER);
deleteButton=new JButton(deleteIcon);
deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
deleteButton.setVerticalTextPosition(SwingConstants.CENTER);
exportToPDFButton=new JButton(pdfIcon);
exportToPDFButton.setHorizontalTextPosition(SwingConstants.CENTER);
exportToPDFButton.setVerticalTextPosition(SwingConstants.CENTER);
}
private void setAppearance()
{
Font captionFont=new Font("Verdana",Font.BOLD,16);
Font dataFont=new Font("Verdana",Font.PLAIN,16);
titleCaptionLabel.setFont(captionFont);
titleLabel.setFont(dataFont);
titleTextField.setFont(dataFont);
setLayout(null);
int lm,tm;
lm=0;
tm=0;
titleCaptionLabel.setBounds(lm+10,tm+20,110,30);
titleLabel.setBounds(lm+10+110+5,tm+20,400,30);
titleTextField.setBounds(lm+10+110+5,tm+20,350,30);
clearTitleTextFieldButton.setBounds(lm+10+110+5+350+5,tm+20,30,30);

buttonsPanel.setBounds(50,tm+20+30+30,465,75);
buttonsPanel.setBorder(BorderFactory.createLineBorder(new Color(165,165,165)));
addButton.setBounds(70,12,50,50);
editButton.setBounds(70+50+20,12,50,50);
cancelButton.setBounds(70+50+20+50+20,12,50,50);
deleteButton.setBounds(70+50+20+50+20+50+20,12,50,50);
exportToPDFButton.setBounds(70+50+20+50+20+50+20+50+20,12,50,50);

buttonsPanel.setLayout(null);
buttonsPanel.add(addButton);
buttonsPanel.add(editButton);
buttonsPanel.add(cancelButton);
buttonsPanel.add(deleteButton);
buttonsPanel.add(exportToPDFButton);
add(titleCaptionLabel);
add(titleTextField);
add(titleLabel);
add(clearTitleTextFieldButton);
add(buttonsPanel);
}
private void addListeners()
{
this.addButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
if(mode==MODE.VIEW)
{
setAddMode();
}
else
{
// logic to save and get back to view mode
if(addDesignation())
{
setViewMode();
}
}
}
});
/*
when click on edit , first we need to check that designation ,is sellected or not ,if not htan we give message
dialog.If yes,than selected designation appear in textbox,than update designation wrap in object and give it to
model to update
*/
this.editButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
if(mode==MODE.VIEW)
{
setEditMode();
}
else
{
if(updateDesignation())
{
setViewMode();
}
}
}
});
this.deleteButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
setDeleteMode();
}
});
this.cancelButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
setViewMode();
}
});
this.exportToPDFButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
JFileChooser jfc=new JFileChooser();
jfc.setCurrentDirectory(new File("."));
int selectedOption=jfc.showSaveDialog(DesignationUI.this);
if(selectedOption==JFileChooser.APPROVE_OPTION)
{
try
{
File selectedFile=jfc.getSelectedFile();
String pdfFile=selectedFile.getAbsolutePath();
if(pdfFile.endsWith(".")) pdfFile+="pdf";
else if(pdfFile.endsWith(".pdf")==false) pdfFile+=".pdf";
File file=new File(pdfFile);
File parent=new File(file.getParent());
if(parent.exists()==false || parent.isDirectory()==false)
{
JOptionPane.showMessageDialog(DesignationUI.this,"Incorrect path : "+file.getAbsolutePath());
return;
}
designationModel.exportToPDF(file);
JOptionPane.showMessageDialog(DesignationUI.this,"Data exported to : "+file.getAbsolutePath());
}catch(BLException blException)
{
if(blException.hasGenericException())
{
JOptionPane.showMessageDialog(DesignationUI.this,blException.getGenericException());
}
}
catch(Exception e)
{
System.out.println(e.getMessage());
}
}

}
});
this.clearTitleTextFieldButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
titleTextField.setText("");
titleTextField.requestFocus();
}
});
}
private boolean addDesignation()
{
String title=titleTextField.getText().trim();
if(title.length()==0)
{
// if user give length of designation as zero then we show message box and do not give into view mode and return false
JOptionPane.showMessageDialog(this,"Designation required");
titleTextField.requestFocus();
return false;
}
DesignationInterface d=new Designation();
d.setTitle(title);
try
{
designationModel.add(d);
int rowIndex=0;
try
{
rowIndex=designationModel.indexOfDesignation(d);
}catch(BLException blException)
{
// do nothing
}
designationTable.setRowSelectionInterval(rowIndex,rowIndex); 
Rectangle rectangle=designationTable.getCellRect(rowIndex,0,true); // selecting the rectangle with rowIndex and columnIndex;
designationTable.scrollRectToVisible(rectangle);  // put rectangle portion into visiblity area
return true;
}catch(BLException blException)
{
if(blException.hasGenericException())
{
JOptionPane.showMessageDialog(this,blException.getGenericException());
}
else
{
if(blException.hasException("title"))
{
JOptionPane.showMessageDialog(this,blException.getException("title"));
}
}
titleTextField.requestFocus();
return false;
}
}
private boolean updateDesignation()
{
String title=titleTextField.getText().trim();
if(title.length()==0)
{
JOptionPane.showMessageDialog(this,"Designation required");
titleTextField.requestFocus();
return false;
}
DesignationInterface d=new Designation();
d.setCode(this.designation.getCode());
d.setTitle(title);
try
{
designationModel.update(d);
int rowIndex=0;
try
{
rowIndex=designationModel.indexOfDesignation(d);
}catch(BLException blException)
{
// do nothing
}
designationTable.setRowSelectionInterval(rowIndex,rowIndex); 
Rectangle rectangle=designationTable.getCellRect(rowIndex,0,true); // selecting the rectangle with rowIndex and columnIndex;
designationTable.scrollRectToVisible(rectangle);  // put rectangle portion into visiblity area
return true;
}catch(BLException blException)
{
if(blException.hasGenericException())
{
JOptionPane.showMessageDialog(this,blException.getGenericException());
}
else
{
if(blException.hasException("title"))
{
JOptionPane.showMessageDialog(this,blException.getException("title"));
}
}
titleTextField.requestFocus();
return false;
}
}
private void removeDesignation()
{
try
{
String title=this.designation.getTitle();
int selectedOption=JOptionPane.showConfirmDialog(this,"Delete "+title+" ?","Confirmation",JOptionPane.YES_NO_OPTION);
if(selectedOption==JOptionPane.NO_OPTION) return;
designationModel.remove(this.designation.getCode());
JOptionPane.showMessageDialog(this,title+" deleted");
this.clearDesignation();
}catch(BLException blException)
{
if(blException.hasGenericException())
{
JOptionPane.showMessageDialog(this,blException.getGenericException());
}
else
{
if(blException.hasException("title"))
{
JOptionPane.showMessageDialog(this,blException.getException("title"));
}
}
}
}
public void setDesignation(DesignationInterface designation)
{
this.designation=designation;
titleLabel.setText(designation.getTitle());
}
public void clearDesignation()
{
this.designation=null;
titleLabel.setText("");
}
void setViewMode()
{
DesignationUI.this.setViewMode();
this.addButton.setIcon(addIcon);
this.editButton.setIcon(editIcon);
this.titleLabel.setVisible(true);
this.titleTextField.setVisible(false);
this.addButton.setEnabled(true);
this.cancelButton.setEnabled(false);
this.clearTitleTextFieldButton.setVisible(false);
if(designationModel.getRowCount()>0)
{
this.editButton.setEnabled(true);
this.deleteButton.setEnabled(true);
this.exportToPDFButton.setEnabled(true);
}
else
{
this.editButton.setEnabled(false);
this.deleteButton.setEnabled(false);
this.exportToPDFButton.setEnabled(false);
}
}
void setAddMode()
{
DesignationUI.this.setAddMode();
this.titleLabel.setVisible(false);
this.titleTextField.setText("");
this.titleTextField.setVisible(true);
this.clearTitleTextFieldButton.setVisible(true);
this.addButton.setIcon(saveIcon);
this.editButton.setEnabled(false);
this.deleteButton.setEnabled(false);
this.cancelButton.setEnabled(true);
this.exportToPDFButton.setEnabled(false);
}
void setEditMode()
{
//when user click on some row , designation against that row in set in designtion
if(designationTable.getSelectedRow()<0 || designationTable.getSelectedRow()>=designationModel.getRowCount())
{
// if user not select any designation
JOptionPane.showMessageDialog(this,"Select designation to edit");
return;
}
DesignationUI.this.setEditMode();
this.titleLabel.setVisible(false);
this.titleTextField.setText(this.designation.getTitle());
this.titleTextField.setVisible(true);
this.clearTitleTextFieldButton.setVisible(true);
this.addButton.setEnabled(false);
this.editButton.setIcon(saveIcon);
this.deleteButton.setEnabled(false);
this.cancelButton.setEnabled(true);
this.exportToPDFButton.setEnabled(false);
}
void setDeleteMode()
{
if(designationTable.getSelectedRow()<0 || designationTable.getSelectedRow()>=designationModel.getRowCount())
{
// if user not select any designation
JOptionPane.showMessageDialog(this,"Select designation to delete");
return;
}
DesignationUI.this.setDeleteMode();
this.addButton.setEnabled(false);
this.editButton.setEnabled(false);
this.deleteButton.setEnabled(false);
this.cancelButton.setEnabled(false);
this.exportToPDFButton.setEnabled(false);
removeDesignation();
DesignationUI.this.setViewMode();
this.setViewMode();
}
void setExportToPDFMode()
{
DesignationUI.this.setExportToPDFMode();
this.addButton.setEnabled(false);
this.editButton.setEnabled(false);
this.deleteButton.setEnabled(false);
this.cancelButton.setEnabled(false);
this.exportToPDFButton.setEnabled(false);
}
}// inner class ends
}