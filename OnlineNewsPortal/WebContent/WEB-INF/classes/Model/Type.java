package Model;

import java.util.*;
import java.sql.*;
import com.*;
import java.io.*;

public class Type extends Connect
{
    /////Function for connect to the MySQL Server Database////////////
	public Type()
    {
		Connect.connect_mysql();
    }
	//////////Save User Details /////
	public String saveType(HashMap typeData)
	{
		String SQL = "INSERT INTO type (type_name, type_description, type_show) VALUES (?, ?, ?);";
		int record=0; 
		String error = "";
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1,(String) typeData.get("type_name"));
			pstmt.setString(2,(String) typeData.get("type_description"));
			pstmt.setString(3,(String) typeData.get("type_show"));
			
			record = pstmt.executeUpdate();
			pstmt.close();
			connection.close();
		}
		catch(Exception e)
		{
			StringWriter writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter( writer );
			e.printStackTrace( printWriter );
			printWriter.flush();
			String stackTrace = writer.toString();
			error+="Error : "+stackTrace;
			System.out.println(" Error : "+ e.toString());
		}
		return error;
	}
	//////////////////Function for getting Users Details//////////	
    public HashMap getTypeDetails(int type_id)
	{
        HashMap results = new HashMap();
        int count=0;
		try
		{
            String SQL =  "SELECT * FROM `type` WHERE type_id = "+type_id ;
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{
				results.put("type_name",rs.getString("type_name"));
				results.put("type_description",rs.getString("type_description"));
				results.put("type_show",rs.getString("type_show"));
				results.put("type_id",rs.getString("type_id"));
				count++;
            }
			if(count==0)
			{
				results.put("type_name","");
				results.put("type_description","");
				results.put("type_show","");
				results.put("type_id","");				
			}
         }
		 catch(Exception e)
		 {
            System.out.println("Error is: "+ e);
       	 }
        return results;
    }
    /// Update the Type ////
	public String updateType(HashMap typeData)
	{
		String SQL = "UPDATE type SET type_name = ?, type_description = ?, type_show = ? WHERE type_id = ?;";
		String error = "";
		
		int record=0;	
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1,(String) typeData.get("type_name"));
			pstmt.setString(2,(String) typeData.get("type_description"));
			pstmt.setString(3,(String) typeData.get("type_show"));			
			pstmt.setString(4,(String) typeData.get("type_id"));			
			
			record = pstmt.executeUpdate();
			pstmt.close();
			connection.close();
		}
		catch(Exception e)
		{
			StringWriter writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter( writer );
			e.printStackTrace( printWriter );
			printWriter.flush();
			String stackTrace = writer.toString();
			error+="Error : "+stackTrace;
			System.out.println(" Error : "+ e.toString());
		}
		return error;
	}
	
	////////////////Function for getting all the Airport Details////////////////////  
    public ArrayList getAllType(int type_id)
	{
		String SQL =  "SELECT * FROM `type`" ;
		int count=0;
        ArrayList resultArray = new ArrayList();
        try
		{
			statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{		
				HashMap results = new HashMap();
				results.put("type_name",rs.getString("type_name"));
				results.put("type_description",rs.getString("type_description"));
				results.put("type_show",rs.getString("type_show"));
				results.put("type_id",rs.getString("type_id"));				
				count++;
                resultArray.add(results);
            }
         }
		catch(Exception e)
		{
            System.out.println("Error is: "+ e);
        }
        return resultArray;
    }
	////////////////Function for getting all the Airport Details////////////////////  
    public ArrayList getMenu(int type_id)
	{
		String SQL =  "SELECT * FROM `type` WHERE type_show = '1'" ;
		int count=0;
        ArrayList resultArray = new ArrayList();
        try
		{
			statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{		
				HashMap results = new HashMap();
				results.put("type_name",rs.getString("type_name"));
				results.put("type_description",rs.getString("type_description"));
				results.put("type_show",rs.getString("type_show"));
				results.put("type_id",rs.getString("type_id"));				
				count++;
                resultArray.add(results);
            }
         }
		catch(Exception e)
		{
            System.out.println("Error is: "+ e);
        }
        return resultArray;
    }
}
