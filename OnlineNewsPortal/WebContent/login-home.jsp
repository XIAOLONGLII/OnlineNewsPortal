<%@ include file="includes/header.jsp" %>
<%@ page import= "java.util.*" %>
<div class="wrapper row3">
  <div class="rounded">
    <main class="container clear"> 
      <!-- main body --> 
	    <div style="width: 70%; float:left; margin-right:30px" id="login-home">
		  <h2>Welcome to Online News Portal Admin Panel</h2>
		  
		 <!-- Dashboard For Super Admin Section -->
		 <% if(session.getAttribute("login_level") != null && session.getAttribute("login_level").equals("1")) { %>		
		  <ul>
		  	<li><a href="report-news.jsp">News Report</a></li>
			<li><a href="report-type.jsp">Category Report</a></li>
			<li><a href="report-employee.jsp">System User Report</a></li>
			<li><a href="employee.jsp?employee_id=0">Add System User</a></li>
			<li><a href="news.jsp?news_id=0">Add News</a></li>
			<li><a href="type.jsp?type_id=0">Add Category</a></li>
			<li><a href="change-password.jsp">Change Password</a></li>
			<li><a href="login.jsp?act=logout">Logout</a></li>
		  </ul>
		  <% } %>
		  
		</div>
		<div style="float: left">
			<div><img src="images/save_1.jpg" style="width: 250px"></div><br>
			<div><img src="images/save_2.jpg" style="width: 250px"></div>
		</div>
      <div class="clear"></div>
    </main>
  </div>
</div>
<%@ include file="includes/footer.jsp" %>
