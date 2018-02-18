<%@ page import= "Model.*" %>
<%@ page import= "java.util.*" %>

<%
	Type type = new Type();
	String emp_id = "0";
	if((request.getParameter("act")).equals("Save"))
	{
		HashMap results = new HashMap();
		
		results.put("type_name",request.getParameter("type_name"));
		results.put("type_description",request.getParameter("type_description"));
		results.put("type_show",request.getParameter("type_show"));
		
		if((request.getParameter("type_id")).equals(""))
		{
			out.println(type.saveType(results));
			response.sendRedirect("../report-type.jsp?msg=Type Saved Successfully");
		}
		else
		{
			results.put("type_id",request.getParameter("type_id"));
			out.println(type.updateType(results));
			response.sendRedirect("../report-type.jsp?msg=Type Updated Successfully");
		}
	}
%>
