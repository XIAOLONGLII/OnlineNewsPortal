package Model;

import java.util.*;
import java.sql.*;
import com.*;
import java.io.*;

public class News extends Connect
{
    /////Function for connect to the MySQL Server Database////////////
	public News()
    {
		Connect.connect_mysql();
    }
	//////////Save User Details /////
	public String saveNews(HashMap newsData)
	{
		String SQL = "INSERT INTO `news` (`news_title`, `news_type_id`, `news_image`, `news_description`) VALUES (?, ?, ?, ?);";
		int record=0; 
		String error = "";
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1,(String) newsData.get("news_title"));
			pstmt.setString(2,(String) newsData.get("news_type_id"));
			pstmt.setString(3,(String) newsData.get("news_image"));
			pstmt.setString(4,(String) newsData.get("news_description"));
			
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
    public HashMap getNewsDetails(int news_id)
	{
        HashMap results = new HashMap();
        int count=0;
		try
		{
			String SQL = "SELECT * FROM `news`,`type` WHERE news_type_id = type_id AND news_id = "+news_id ;
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{
				results.put("news_title",rs.getString("news_title"));
				results.put("news_type_id",Integer.parseInt(rs.getString("news_type_id")));
				results.put("news_description",rs.getString("news_description"));
				results.put("news_id",rs.getString("news_id"));
				results.put("news_image",rs.getString("news_image"));
				results.put("news_type",rs.getString("type_name"));
				
				count++;
            }
			if(count==0)
			{
				results.put("news_title","");
				results.put("news_type_id",0);
				results.put("news_description","");
				results.put("news_image","");
				results.put("news_id","");	
			}
         }
		 catch(Exception e)
		 {
            System.out.println("Error is: "+ e);
       	 }
        return results;
    }
    /// Update the News ////
	public String updateNews(HashMap newsData)
	{
		String SQL = "UPDATE `news` SET `news_title` = ?, `news_type_id` = ?, `news_image` = ?, `news_description` = ? WHERE `news_id` = ?;";
		String error = "";
		
		int record=0;	
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1,(String) newsData.get("news_title"));
			pstmt.setString(2,(String) newsData.get("news_type_id"));
			pstmt.setString(3,(String) newsData.get("news_image"));
			pstmt.setString(4,(String) newsData.get("news_description"));
			pstmt.setString(5,(String) newsData.get("news_id"));
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
    public ArrayList getAllNews(int typeID)
	{
		int count=0;
		String SQL = "";
		if(typeID != 0)
			SQL = "SELECT * FROM `news`,`type` WHERE news_type_id = type_id AND type_id = "+typeID;
		else 
			SQL = "SELECT * FROM `news`, `type` WHERE news_type_id = type_id";
        ArrayList resultArray = new ArrayList();
        try
		{
			statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{		
				HashMap results = new HashMap();
				results.put("news_title",rs.getString("news_title"));
				results.put("news_type_id",Integer.parseInt(rs.getString("news_type_id")));
				results.put("news_description",rs.getString("news_description"));
				results.put("news_id",rs.getString("news_id"));
				results.put("news_image",rs.getString("news_image"));
				results.put("news_type",rs.getString("type_name"));
				
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
    /////Function for Getting the List////////////
	public String getTypeOption(Integer SelID)
    {
		int selectedID = SelID.intValue();
    	return Connect.getOptionList("type","type_id","type_name","type_id,type_name",selectedID,"1");
    }
}
