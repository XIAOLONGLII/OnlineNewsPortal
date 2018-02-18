<%@ include file="includes/header.jsp" %>
<%@ page import= "java.util.*" %>
<%@ page import= "Model.*" %>

<%
	Customer customerDetails = new Customer();
	ArrayList allCustomer = customerDetails.getAllCustomer();
%>
<div class="wrapper row3">
  <div class="rounded">
    <main class="container clear"> 
      <!-- main body --> 
      <div class="scrollable">
        <h2>Customer Report</h2>
		<% if(request.getParameter("msg") != null) { %>
		<div class="msg"><%=request.getParameter("msg") %></div>
		<% } %>
        <table>
          <thead>
            <tr>
              <th>Customer Code</th>
              <th>Name</th>
              <th>Email</th>
              <th>Mobile</th>
              <th>City</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
			<% for(int i=0;i<allCustomer.size();i++) 
			{ 
				HashMap CustomerDetails = new HashMap();
				CustomerDetails = (HashMap)allCustomer.get(i);
			%>
				<tr>
				  <td style="text-align:center;"><% out.print(CustomerDetails.get("customer_id")); %></td>
				  <td><% out.print(CustomerDetails.get("customer_name")); %></td>
				  <td><% out.print(CustomerDetails.get("customer_email")); %></td>
				  <td><% out.print(CustomerDetails.get("customer_mobile")); %></td>
				  <td><% out.print(CustomerDetails.get("customer_city")); %></td>
				  <td>
				  <a href="customer.jsp?customer_id=<% out.print(CustomerDetails.get("customer_id")); %>">Edit</a> | 
				  <a href="report-order.jsp?customer_id=<% out.print(CustomerDetails.get("customer_id")); %>">Orders</a>
				  </td>
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
