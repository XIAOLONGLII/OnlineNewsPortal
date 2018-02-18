package Model;

import java.util.*;
import java.sql.*;
import com.*;
import java.io.*;

public class Login extends Connect
{
    /////Function for connect to the MySQL Server Database////////////
	public Login()
    {
		Connect.connect_mysql();
    }
	//////////////////Function for Update the airport////////////////////////
	public boolean changePassword(String old_password,String new_password, int login_id)
	{		
		String SQL;
		int count = 0;
		try
		{
		    SQL = "SELECT * FROM login WHERE login_password = '"+old_password+"' AND login_id = "+login_id ;
			statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())	count++;    
			if(count==1)
			{
				SQL = "UPDATE login SET login_password=? WHERE login_id=?";
				int record=0;
				pstmt = connection.prepareStatement(SQL);
				pstmt.setString(1,new_password);
				pstmt.setInt(2,login_id);
				record = pstmt.executeUpdate();
				pstmt.close();
				connection.close();
			}
		}
		catch(Exception e)
		{
			System.out.println(" Error : "+ e.toString());
		}
		if(count==0)
			return false;
		return true;
	}
	//////////////////Function for geting the Single Airport Details//////////	
    public boolean checkLogin(String login_user,String login_password)
	{
        int count=0;
		try
		{
            String SQL = "SELECT * FROM login WHERE login_user = '"+login_user+"' AND login_password = '"+login_password+"'" ;
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())	count++;    
         }
		 catch(Exception e)
		 {
            System.out.println("Error is: "+ e);
       	 }
		if(count==0)
			return false;
        return true;
    }
	//////////////////Function for getting Login Details//////////	
    public HashMap getLoginDetails(String login_user,String login_password)
	{
        HashMap resultsArray = new HashMap();
        int count=0;
		try
		{
            String SQL =  "SELECT * FROM login WHERE login_user = '"+login_user+"' AND login_password = '"+login_password+"'" ;
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{
				resultsArray.put("login_id",rs.getString("login_id"));
				resultsArray.put("login_emp_id",rs.getString("login_emp_id"));
				resultsArray.put("login_user",rs.getString("login_user"));
				resultsArray.put("login_level",rs.getString("login_level"));
				count++;
            }
			if(count==0)
			{
				resultsArray.put("login_id","");
				resultsArray.put("login_emp_id","");
				resultsArray.put("login_user","");
				resultsArray.put("login_level","");
			}
         }
		 catch(Exception e)
		 {
            System.out.println("Error is: "+ e);
       	 }
        return resultsArray;
    }	
    //////////////////Function for getting Login Details//////////	
    public int checkUsernameExits(String login_user, int type)
	{
        HashMap resultsArray = new HashMap();
        int exits=0;
		try
		{
			String SQL = "";
			if(type == 1) {
				SQL =  "SELECT * FROM login WHERE login_user = '"+login_user+"'" ;
			}
			if(type == 2) {
				SQL =  "SELECT * FROM login WHERE login_email = '"+login_user+"'" ;
			}
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{
				exits++;
            }
         }
		 catch(Exception e)
		 {
            System.out.println("Error is: "+ e);
       	 }
        return exits;
    }	
}
