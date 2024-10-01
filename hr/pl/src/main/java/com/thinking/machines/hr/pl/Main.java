//application starting point is Main.java,means application start from class Main(in hr\pl>notepad Main.java)
//in constructor we cannot set visiblity,Benefit:we create so many object and setVisible whichever we want
package com.thinking.machines.hr.pl;
import com.thinking.machines.hr.pl.ui.*;
class Main
{
public static void main(String args[])
{
DesignationUI designationUI=new DesignationUI();
designationUI.setVisible(true);   
}
}