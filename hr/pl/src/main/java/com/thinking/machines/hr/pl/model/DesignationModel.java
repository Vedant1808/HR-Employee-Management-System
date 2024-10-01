package com.thinking.machines.hr.pl.model;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
import javax.swing.table.*;
import java.io.*;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.io.image.*;
import com.itextpdf.kernel.font.*;
import com.itextpdf.io.font.constants.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.*;
import com.itextpdf.layout.borders.*;
/*
First we create Model for Designation  and at the end we will create an interface that represent that model

c:\javaProject\hr\pl>notepad build.gradle
apply plugin: 'java'
dependencies {
implementation files('../dl/dist/hr-dl-1.0.jar','../bl/build/libs/bl.jar','../common/dist/hr-common.jar')

DS at pl : We need data is ordered on bases of name ,so we take LinkedList(also TreeSet)(later change),
call getDesignations of bl , it return a set of DesignationInterface type(traverse on the bases of code) , 
we simple copy the designations of set into LinkedList(because we already get clone from bl so we simply copy
 it) and then sort the LinkedList on the bases of title name
}
*/
public class DesignationModel extends AbstractTableModel
{
private java.util.List<DesignationInterface> designations;
private DesignationManagerInterface designationManager;
private String columnTitle[];
public DesignationModel()
{
populateDataStructures();
}
private void populateDataStructures()
{
this.columnTitle=new String[2];
this.columnTitle[0]="S.No.";
this.columnTitle[1]="Designation";
try
{
designationManager=DesignationManager.getDesignationManager();
}catch(BLException blException)
{
// ??????? what to do(take decision later)
}
Set<DesignationInterface> blDesignations=designationManager.getDesignations();
this.designations=new LinkedList<>();
for(DesignationInterface designation:blDesignations)
{
this.designations.add(designation);
}
/*
pass the collection that we need to sort and also give one comparator.
That comparator containe one method compare that get 2 DesignationInterace type object address,
*/
Collections.sort(this.designations,new Comparator<DesignationInterface>(){
public int compare(DesignationInterface left,DesignationInterface right)
{
return left.getTitle().toUpperCase().compareTo(right.getTitle().toUpperCase());
}
});
}
public int getRowCount()
{
return this.designations.size();
}
public int getColumnCount()
{
return this.columnTitle.length;
}
public String getColumnName(int columnIndex)
{
return this.columnTitle[columnIndex];
}
public Class getColumnClass(int columnIndex)
{
if(columnIndex==0) return Integer.class;  // Same:Class.forName("java.lang.Integer")
return String.class;
}
public boolean isCellEditable(int rowIndex,int columnIndex)
{
return false;
}
public Object getValueAt(int rowIndex,int columnIndex)
{
if(columnIndex==0) return rowIndex+1;
return this.designations.get(rowIndex).getTitle();
}
// Application specific methods
public void add(DesignationInterface designation) throws BLException
{
/*
When someone click on Add , a textbox comes where we write designation and add button becomes save button.
Button like edit,delete and Pdf is disabled ,only cancel is enabled. 
When click on save button , add of Model got called , from that addDesignation of BL got called.
Add give BL to add and if exception raised by BL and it pass that exception to PL,
then add designation to linked list and again sort the designations

Very Very Important : Initially when we give Address of model to JTable , it fetch information about table
and create table and data we provide is with the help of list , but later new record is added , model add in
DS , but JTable do not know the new record(JTable is not updated).So JTable internally in between call the 
methods to make changes in JTable.But problem is that we do not know after how much time it call these methods
to update the table ,we need immediate effect on table,so we call fireTableDataChanged() method of
AbstractTableModel (internally we give model address to JTable , JTable internally called method of model
and share JTable address to model , so  fireTableDataChanged() update the JTable)

*/
designationManager.addDesignation(designation);
this.designations.add(designation);
Collections.sort(this.designations,new Comparator<DesignationInterface>(){
public int compare(DesignationInterface left,DesignationInterface right)
{
return left.getTitle().toUpperCase().compareTo(right.getTitle().toUpperCase());
}
});
fireTableDataChanged();
}
public int indexOfDesignation(DesignationInterface designation) throws BLException
{
/*
After adding designation , how the user know that designation is added.
So we highlight the row of table and but it is possible the that row niche ho , so we have to do something 
that vo row scroll ho kar uper aa jaye and comes in visibility area(responsibility of view)
It simply determine that designation at which index to highlight the row
*/
Iterator<DesignationInterface> iterator=this.designations.iterator();
DesignationInterface d;
int index=0;
while(iterator.hasNext())
{
d=iterator.next();
if(d.equals(designation))
{
return index;
}
index++;
}
// if not found
BLException blException=new BLException();
blException.setGenericException("Invalid designation : "+designation.getTitle());
throw blException;
}
public int indexOfTitle(String title,boolean partialLeftSearch) throws BLException
{
/*
There is also search box in ui and in each key pressed in search box ,Model method is called(if type a and 
we want all designation with highlight that start with a).If partialLeftSearch is false means show designation
only if full designation match.
*/
Iterator<DesignationInterface> iterator;
iterator=this.designations.iterator();
DesignationInterface d;
int index=0;
while(iterator.hasNext())
{
d=iterator.next();
if(partialLeftSearch)
{
if(d.getTitle().toUpperCase().startsWith(title.toUpperCase()))
{
return index;
}
}
else
{
if(d.getTitle().equalsIgnoreCase(title))
{
return index;
}
}
index++;
}
BLException blException=new BLException();
blException.setGenericException("Invalid title : "+title);
throw blException;
}
public void update(DesignationInterface designation) throws BLException
{
designationManager.updateDesignation(designation);
/*
first we need to remove the designation from list by finding its index,but it is new designation and we need
to remove the old designation
But indexOfDesignation compare on bases of equals and equals compare on the bases of code
*/
this.designations.remove(indexOfDesignation(designation));
this.designations.add(designation);
Collections.sort(this.designations,new Comparator<DesignationInterface>(){
public int compare(DesignationInterface left,DesignationInterface right)
{
return left.getTitle().toUpperCase().compareTo(right.getTitle().toUpperCase());
}
});
fireTableDataChanged();
}
public void remove(int code) throws BLException
{
designationManager.removeDesignation(code);
Iterator<DesignationInterface> iterator=this.designations.iterator();
DesignationInterface d;
int index=0;
while(iterator.hasNext())
{
if(iterator.next().getCode()==code) break;
index++;
}
if(index==this.designations.size())
{
BLException blException=new BLException();
blException.setGenericException("Invalid designation code : "+code);
throw blException;
}
this.designations.remove(index);
fireTableDataChanged();
}
public DesignationInterface getDesignationAt(int index) throws BLException
{
if(index<0 || index>=this.designations.size())
{
BLException blException=new BLException();
blException.setGenericException("Invalid index : "+index);
throw blException;
}
return this.designations.get(index);
}
public void exportToPDF(File file) throws BLException
{
try
{
if(file.exists()) file.delete();
PdfWriter pdfWriter=new PdfWriter(file);
PdfDocument pdfDocument=new PdfDocument(pdfWriter);
Document doc=new Document(pdfDocument);
Image logo=new Image(ImageDataFactory.create(this.getClass().getResource("/icons/logo_icon.png")));
logo.scaleAbsolute(30,30);
Paragraph logoPara=new Paragraph();
logoPara.add(logo);
Paragraph companyNamePara=new Paragraph();
companyNamePara.add("ABCD Corporation");
PdfFont companyNameFont=PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
companyNamePara.setFont(companyNameFont);
companyNamePara.setFontSize(18);
Paragraph reportTitlePara=new Paragraph("List of Designations");
PdfFont reportTitleFont=PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
reportTitlePara.setFont(reportTitleFont);
reportTitlePara.setFontSize(15);
PdfFont pageNumberFont=PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
PdfFont columnTitleFont=PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
PdfFont dataFont=PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
Paragraph columnTitle1=new Paragraph("S.No.");
columnTitle1.setFont(columnTitleFont);
columnTitle1.setFontSize(14);
Paragraph columnTitle2=new Paragraph("Designations");
columnTitle2.setFont(columnTitleFont);
columnTitle2.setFontSize(14);
Paragraph pageNumberParagraph;
Paragraph dataParagraph;
float topTableColumnWidths[]={1,5};
float dataTableColumnWidths[]={1,5};
int sno,x,pageSize;
pageSize=8;
boolean newPage=true;
Table pageNumberTable;
Table topTable;
Table dataTable=null;
Cell cell;
int numberOfPages=this.designations.size()/pageSize;
if((this.designations.size()%pageSize)!=0) numberOfPages++;
int pageNumber=0;
DesignationInterface designation;
sno=0;
x=0;
while(x<this.designations.size())
{
if(newPage==true)
{
// create new page header
pageNumber++;
topTable=new Table(UnitValue.createPercentArray(topTableColumnWidths));
cell=new Cell();
cell.setBorder(Border.NO_BORDER);
cell.add(logoPara);
topTable.addCell(cell);
cell=new Cell();
cell.setBorder(Border.NO_BORDER);
cell.add(companyNamePara);
cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
topTable.addCell(cell);
doc.add(topTable);
pageNumberParagraph=new Paragraph("Page : "+pageNumber+"/"+numberOfPages);
pageNumberParagraph.setFont(pageNumberFont);
pageNumberParagraph.setFontSize(13);
pageNumberTable=new Table(1);
pageNumberTable.setWidth(UnitValue.createPercentValue(100));  // occupy full width of page
cell=new Cell();
cell.setBorder(Border.NO_BORDER);
cell.add(pageNumberParagraph);
cell.setTextAlignment(TextAlignment.RIGHT);
pageNumberTable.addCell(cell);
doc.add(pageNumberTable);
dataTable=new Table(UnitValue.createPercentArray(dataTableColumnWidths));
dataTable.setWidth(UnitValue.createPercentValue(100));
cell=new Cell(1,2);     // occupy 2 cell
cell.add(reportTitlePara);
cell.setTextAlignment(TextAlignment.CENTER);
dataTable.addHeaderCell(cell);
dataTable.addHeaderCell(columnTitle1);
dataTable.addHeaderCell(columnTitle2);
newPage=false;
}
designation=this.designations.get(x);
// add row to table
sno++;
cell=new Cell();
dataParagraph=new Paragraph(String.valueOf(sno));
dataParagraph.setFont(dataFont);
dataParagraph.setFontSize(14);
cell.add(dataParagraph);
cell.setTextAlignment(TextAlignment.RIGHT);
dataTable.addCell(cell);
cell=new Cell();
dataParagraph=new Paragraph(designation.getTitle());
dataParagraph.setFont(dataFont);
dataParagraph.setFontSize(14);
cell.add(dataParagraph);
dataTable.addCell(cell);
x++;
if(sno%pageSize==0 || x==this.designations.size())
{
// create footer
doc.add(dataTable);
doc.add(new Paragraph("Software by : Thinking Machiens"));
if(x<this.designations.size())
{
// add new page to document
doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
newPage=true;
}
}
}
doc.close();
}catch(Exception exception)
{
BLException blException;
blException=new BLException();
blException.setGenericException(exception.getMessage());
throw blException;
}
}
}