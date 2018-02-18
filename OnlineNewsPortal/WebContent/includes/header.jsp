<%@ page import= "java.util.*" %>
<%@ page import= "Model.*" %>

<%
	Type typeDetails = new Type();
	ArrayList allType = typeDetails.getAllType(0);
%>
<html>
<head>
<title>Online News Portal</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/jquery.validate.js"></script>
<style>
.ui-datepicker {
	font-family: "Trebuchet MS", "Helvetica", "Arial",  "Verdana", "sans-serif";
	font-size: 12px;
}
.ui-datepicker select.ui-datepicker-month, .ui-datepicker select.ui-datepicker-year {
	float:left;
}
</style>
<script>
jQuery('document').ready(function() {
	jQuery("#Form").validate();
});
</script>
</head>
<body id="top">
<div class="wrapper row0">
  <div id="topbar" class="clear"> 
    <nav>
      <ul>
		<% if(session.getAttribute("login_id")!=null) { %>
		<li>Welcome <% out.print(session.getAttribute("login_name")); %></li>
        <li><a href="model/login.jsp?act=logout">Logout</a></li>
		<% } else { %>
		<li><a href="index.jsp">Home</a></li>
        <li><a href="about-us.jsp">About Us</a></li>
        <li><a href="contact-us.jsp">Contact Us</a></li>
		<li><a href="login.jsp?title=Adminstrator">Admin Login</a></li>
		<% } %>
      </ul>
    </nav>
  </div>
</div>
<div class="wrapper row1">
  <header id="header" class="clear"> 
    <div id="logo" class="fl_left">
       <div style="float:left; margin-top:-11px;"><h1 style="font-size:36px;"><a href="index.jsp">Online News Portal</a></h1>
      <p style="color:#ffffff">All about the News - Online News Portal</p>
      </div>
    </div>
    <div class="fl_right">
      <form class="clear" method="post" action="#">
        <fieldset>
          <legend>Search:</legend>
          <input type="text" value="" placeholder="Search Here">
          <button class="fa fa-search" type="submit" title="Search"><em>Search</em></button>
        </fieldset>
      </form>
    </div>
  </header>
</div>
<div class="wrapper row2">
  <div class="rounded">
    <nav id="mainav" class="clear"> 
      <ul class="clear">
        <li class="active"><a href="index.jsp">Home</a></li>
        <!-- Menu System for Super Admin Login -->
        <% if(session.getAttribute("login_level") != null && session.getAttribute("login_level").equals("1")) { %>
		<li><a href="report-news.jsp?type_id=0">News Report</a></li>
		<li><a href="news.jsp?news_id=0">Add News</a></li>
		<li><a href="report-type.jsp">Category Report</a></li>
		<li><a href="type.jsp?type_id=0">Add Category</a></li>
        <li><a href="change-password.jsp">Change Password</a></li>
        <li><a href="model/login.jsp?act=logout">Logout</a></li>
		<% } %>
		<!-- End of the Super Admin Menu Section -->
		
		
		<% 	if(session.getAttribute("login_level") == null) {
			for(int i=0;i<allType.size();i++) 
			{ 
				HashMap TypeDetails = new HashMap();
				TypeDetails = (HashMap)allType.get(i);
		%>
			<li><a href="index.jsp?type_id=<% out.print(TypeDetails.get("type_id")); %>"><% out.print(TypeDetails.get("type_name")); %></a></li>

		<% } 
		}
		%>
      </ul>
    </nav>
  </div>
</div>
