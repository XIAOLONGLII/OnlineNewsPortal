<%@ page import= "Model.*" %>
<%@ page import= "java.util.*" %>

<%
	Comments commentObj = new Comments();
	String emp_id = (String) session.getAttribute("login_emp_id");
	if((request.getParameter("act")).equals("Save"))
	{
		HashMap results = new HashMap();
		results.put("comment_user_id",emp_id);
		results.put("comment_news_id",request.getParameter("news_id"));
		results.put("comment_name",request.getParameter("comment_name"));
		results.put("comment_email",request.getParameter("comment_email"));
		results.put("comment_description",request.getParameter("comment_description"));
		out.println(commentObj.saveComments(results));
		response.sendRedirect("../news-details.jsp?news_id="+request.getParameter("news_id")+"&msg=Comment Saved Successfully");
	}
%>
