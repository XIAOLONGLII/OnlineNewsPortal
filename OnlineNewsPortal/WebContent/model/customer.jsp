<%@ page import= "Model.*" %>
<%@ page import= "java.util.*" %>

<%
	Customer customer = new Customer();
	String emp_id = "0";
	if(request.getParameter("act").equals("login_status"))
	{
		if(session.getAttribute("customer_id") == null) {
			session.setAttribute("from_shipping",1);
			session.setAttribute("total_amount",request.getParameter("total_amount"));
			response.sendRedirect("../customer-login.jsp?msg=Login to continue !!!.");
		} else {
			session.setAttribute("total_amount",request.getParameter("total_amount"));
			response.sendRedirect("../checkout.jsp");
		}
	}
	
	if((request.getParameter("act")).equals("Save"))
	{
		HashMap results = new HashMap();
		
		results.put("customer_name",request.getParameter("customer_name"));
		results.put("customer_mobile",request.getParameter("customer_mobile"));
		results.put("customer_email",request.getParameter("customer_email"));
		results.put("customer_password",request.getParameter("customer_password"));
		results.put("customer_address",request.getParameter("customer_address"));
		results.put("customer_city",request.getParameter("customer_city"));
		results.put("customer_state",request.getParameter("customer_state"));
		results.put("customer_pincode",request.getParameter("customer_pincode"));	
		results.put("customer_id",request.getParameter("customer_id"));			
		
						
		if((request.getParameter("customer_id")).equals(""))
		{
			out.println(customer.saveCustomer(results));
			response.sendRedirect("../report-customer.jsp?msg=Customer Saved Successfully");
		}
		else
		{
			results.put("customer_id",request.getParameter("customer_id"));
			out.println(customer.updateCustomer(results));
			
			if(request.getParameter("customer_id").equals(session.getAttribute("customer_id")))
			{
				response.sendRedirect("../customer.jsp?customer_id="+session.getAttribute("customer_id")+"&msg=Account Updated Successfully");
			}
			else
			{
				response.sendRedirect("../report-customer.jsp?msg=Customer Updated Successfully");
			}
		}			
	}
	if((request.getParameter("act")).equals("chk_login"))
	{
		if(customer.checkLogin(request.getParameter("login_user"),request.getParameter("login_password")))
		{
			HashMap Values =  customer.getLoginDetails(request.getParameter("login_user"),request.getParameter("login_password"));
			
			session.setAttribute("customer_id",Values.get("customer_id"));
			session.setAttribute("login_id",Values.get("customer_id"));
			session.setAttribute("customer_name",Values.get("customer_name"));
			session.setAttribute("login_level","4");
			session.setAttribute("login_name",Values.get("customer_name"));
			if(session.getAttribute("from_shipping") == null) {
				response.sendRedirect("../login-home.jsp?msg=You are login successfully.");
				
			} else {
				session.setAttribute("from_shipping",null);
				response.sendRedirect("../checkout.jsp");
			}
		}
		else
		{
			response.sendRedirect("../customer-login.jsp?msg=Invalid User/Password. Please try again.........");			
		}
	}
	if((request.getParameter("act")).equals("logout"))
	{
		session.setAttribute("login_id",null);
		session.setAttribute("login_level",null);
		session.setAttribute("customer_id",null);
		session.setAttribute("customer_name",null);
		response.sendRedirect("../customer-login.jsp?msg=You are logout successfully......");			
	}
%>
