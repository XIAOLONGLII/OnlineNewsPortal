package Model;

import java.util.*;
import java.sql.*;
import com.*;
import java.io.*;

public class Customer extends Connect
{
    /////Function for connect to the MySQL Server Database////////////
	public Customer()
    {
		Connect.connect_mysql();
    }
	//////////Save User Details /////
	public String saveCustomer(HashMap customerData)
	{
		String SQL = "INSERT INTO `customer` (`customer_name`, `customer_mobile`, `customer_email`, `customer_password`, `customer_address`, `customer_city`, `customer_state`, `customer_pincode`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		int record=0; 
		String error = "";
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1,(String) customerData.get("customer_name"));
			pstmt.setString(2,(String) customerData.get("customer_mobile"));
			pstmt.setString(3,(String) customerData.get("customer_email"));
			pstmt.setString(4,(String) customerData.get("customer_password"));
			pstmt.setString(5,(String) customerData.get("customer_address"));
			pstmt.setString(6,(String) customerData.get("customer_city"));
			pstmt.setString(7,(String) customerData.get("customer_state"));
			pstmt.setString(8,(String) customerData.get("customer_pincode"));
			
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
    public HashMap getCustomerDetails(int customer_id)
	{
        HashMap results = new HashMap();
        int count=0;
		try
		{
			String SQL = "SELECT * FROM `customer` WHERE customer_id = "+customer_id ;
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{
				results.put("customer_name",rs.getString("customer_name"));
				results.put("customer_mobile",rs.getString("customer_mobile"));
				results.put("customer_email",rs.getString("customer_email"));
				results.put("customer_password",rs.getString("customer_password"));
				results.put("customer_address",rs.getString("customer_address"));
				results.put("customer_city",rs.getString("customer_city"));
				results.put("customer_state",Integer.parseInt(rs.getString("customer_state")));
				results.put("customer_pincode",rs.getString("customer_pincode"));	
				results.put("customer_id",rs.getString("customer_id"));					
				count++;
            }
			if(count==0)
			{
				results.put("customer_name","");
				results.put("customer_mobile","");
				results.put("customer_email","");
				results.put("customer_password","");
				results.put("customer_address","");
				results.put("customer_city","");
				results.put("customer_state",0);	
				results.put("customer_pincode","");	
				results.put("customer_id","");				
			}
         }
		 catch(Exception e)
		 {
            System.out.println("Error is: "+ e);
       	 }
        return results;
    }
    /// Update the Customer ////
	public String updateCustomer(HashMap customerData)
	{
		String SQL = "UPDATE `customer` SET `customer_name` = ?, `customer_mobile` = ?, `customer_email` = ?, `customer_password` = ?, `customer_address` = ?, `customer_city` = ?, `customer_state` = ?, `customer_pincode` = ? WHERE `customer_id` = ?;";
		String error = "";
		
		int record=0;	
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1,(String) customerData.get("customer_name"));
			pstmt.setString(2,(String) customerData.get("customer_mobile"));
			pstmt.setString(3,(String) customerData.get("customer_email"));
			pstmt.setString(4,(String) customerData.get("customer_password"));
			pstmt.setString(5,(String) customerData.get("customer_address"));
			pstmt.setString(6,(String) customerData.get("customer_city"));
			pstmt.setString(7,(String) customerData.get("customer_state"));
			pstmt.setString(8,(String) customerData.get("customer_pincode"));
			pstmt.setString(9,(String) customerData.get("customer_id"));
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
    public ArrayList getAllCustomer()
	{
		String SQL = "SELECT * FROM `customer`";
		int count=0;
        ArrayList resultArray = new ArrayList();
        try
		{
			statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{		
				HashMap results = new HashMap();
				results.put("customer_name",rs.getString("customer_name"));
				results.put("customer_mobile",rs.getString("customer_mobile"));
				results.put("customer_email",rs.getString("customer_email"));
				results.put("customer_password",rs.getString("customer_password"));
				results.put("customer_address",rs.getString("customer_address"));
				results.put("customer_city",rs.getString("customer_city"));
				results.put("customer_state",Integer.parseInt(rs.getString("customer_state")));
				results.put("customer_pincode",rs.getString("customer_pincode"));	
				results.put("customer_id",rs.getString("customer_id"));			
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
	public String getStateOption(Integer SelID)
    {
		int selectedID = SelID.intValue();
    	return Connect.getOptionList("state","state_id","state_name","state_id,state_name",selectedID,"1");
    }
    //////////////////Function for getting Login Details//////////	
    public HashMap getLoginDetails(String login_user,String login_password)
	{
        HashMap resultsArray = new HashMap();
        int count=0;
		try
		{
            String SQL =  "SELECT * FROM customer WHERE customer_email = '"+login_user+"' AND customer_password = '"+login_password+"'" ;
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{
				resultsArray.put("customer_id",rs.getString("customer_id"));
				resultsArray.put("customer_name",rs.getString("customer_name"));
				resultsArray.put("login_level",4);
				count++;
            }
			if(count==0)
			{
				resultsArray.put("customer_id","");
				resultsArray.put("customer_name","");
				resultsArray.put("login_level",0);
			}
         }
		 catch(Exception e)
		 {
            System.out.println("Error is: "+ e);
       	 }
        return resultsArray;
    }	
    //////////////////Function for checking the existing username//////////	
    public int checkUsernameExits(String login_user, int type)
	{
        HashMap resultsArray = new HashMap();
        int exits=0;
		try
		{
			String SQL = "";
			if(type == 1) {
				SQL =  "SELECT * FROM customer WHERE customer_email = '"+login_user+"'" ;
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
    //////////////////Function for geting the Single Airport Details//////////	
    public boolean checkLogin(String login_user,String login_password)
	{
        int count=0;
		try
		{
            String SQL = "SELECT * FROM customer WHERE customer_email = '"+login_user+"' AND customer_password = '"+login_password+"'" ;
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
}
