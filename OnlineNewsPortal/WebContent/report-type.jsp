<%@ include file="includes/header.jsp" %>
<%@ page import= "java.util.*" %>
<%@ page import= "Model.*" %>

<%
	Type typeDetailsNew = new Type();
	ArrayList allTypeNew = typeDetailsNew.getAllType(0);
%>
<div class="wrapper row3">
  <div class="rounded">
    <main class="container clear"> 
      <!-- main body --> 
      <div class="scrollable">
        <h2>Type Report</h2>
		<% if(request.getParameter("msg") != null) { %>
		<div class="msg"><%=request.getParameter("msg") %></div>
		<% } %>
        <table>
          <thead>
            <tr>
              <th>Typd ID</th>
              <th>Name</th>
              <th>In Menu</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
			<% for(int i=0;i<allTypeNew.size();i++) 
			{ 
				HashMap TypeDetails = new HashMap();
				String checked = "No";
				TypeDetails = (HashMap)allTypeNew.get(i);
				if(TypeDetails.get("type_show").equals("1")) {
					checked = "Yes";
				}
			%>
				<tr>
				  <td style="text-align:center;"><% out.print(TypeDetails.get("type_id")); %></td>
				  <td><% out.print(TypeDetails.get("type_name")); %></td>
				  <td><% out.print(checked); %></td>
				  <td> <a href="type.jsp?type_id=<% out.print(TypeDetails.get("type_id")); %>">Edit</a> </td>
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
