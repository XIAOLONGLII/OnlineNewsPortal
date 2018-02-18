package Model;

import java.util.*;
import java.sql.*;
import com.*;
import java.io.*;

public class Comments extends Connect
{
    /////Function for connect to the MySQL Server Database////////////
	public Comments()
    {
		Connect.connect_mysql();
    }
	//////////Save User Details /////
	public String saveComments(HashMap ticketData)
	{
		String SQL = "INSERT INTO `comments` (`comment_news_id`, `comment_name`, `comment_email`, `comment_description`) VALUES (?, ?, ?, ?);";
		int record=0; 
		String error = "";
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1,(String) ticketData.get("comment_news_id"));
			pstmt.setString(2,(String) ticketData.get("comment_name"));
			pstmt.setString(3,(String) ticketData.get("comment_email"));
			pstmt.setString(4,(String) ticketData.get("comment_description"));
			
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
    public ArrayList getAllComments(String news_id)
	{
		String SQL = "SELECT * FROM `comments` WHERE comment_news_id = "+news_id+" ORDER BY comment_id ASC";
		int count=0;
        ArrayList resultArray = new ArrayList();
        try
		{
			statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{		
				HashMap results = new HashMap();
				results.put("comment_description",rs.getString("comment_description"));
				results.put("comment_name",rs.getString("comment_name"));
				results.put("comment_email",rs.getString("comment_email"));
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
