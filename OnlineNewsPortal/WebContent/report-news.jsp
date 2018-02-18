<%@ include file="includes/header.jsp" %>
<%@ page import= "java.util.*" %>
<%@ page import= "Model.*" %>

<%
	News newsDetails = new News();
	ArrayList allNews = newsDetails.getAllNews(0);
%>
<div class="wrapper row3">
  <div class="rounded">
    <main class="container clear"> 
      <!-- main body --> 
      <div class="scrollable">
        <h2>News Report</h2>
		<% if(request.getParameter("msg") != null) { %>
		<div class="msg"><%=request.getParameter("msg") %></div>
		<% } %>
        <table>
          <thead>
            <tr>
              <th>News Code</th>
              <th>Image</th>
              <th>Title</th>
              <th>Type</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
			<% for(int i=0;i<allNews.size();i++) 
			{ 
				HashMap NewsDetails = new HashMap();
				NewsDetails = (HashMap)allNews.get(i);
			%>
				<tr>
				  <td style="text-align:center;"><% out.print(NewsDetails.get("news_id")); %></td>
				  <td>
					<a href="news-details.jsp?news_id=<% out.print(NewsDetails.get("news_id")); %>">
						<img src="newsImages/<% out.print(NewsDetails.get("news_image")); %>" style="height:80px; width:80px;">
					</a>
				  </td>
				  <td><% out.print(NewsDetails.get("news_title")); %></td>
				  <td><% out.print(NewsDetails.get("news_type")); %></td>
				  <td> <a href="news.jsp?news_id=<% out.print(NewsDetails.get("news_id")); %>">Edit</a> </td>
				</tr>
			<%
			}
			%>
			</tbody>
        </table>
        </div>
    </main>
  </div>
</div>
<%@ include file="includes/footer.jsp" %>
