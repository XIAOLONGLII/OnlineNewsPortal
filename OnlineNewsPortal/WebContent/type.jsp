<%@ include file="includes/header.jsp" %>
<%@ page import= "java.util.*" %>
<%@ page import= "Model.*" %>
<%
	Type typeDetailsNew = new Type();
	String checked = "";
	int type_id = Integer.parseInt(request.getParameter ("type_id"));
	HashMap Values =  typeDetailsNew.getTypeDetails(type_id);	
	if(Values.get("type_show").equals("1")) {
		checked = "checked";
	}
%>
<div class="wrapper row3">
  <div class="rounded">
    <% if(request.getParameter("msg") != null) { %>
	<div class="msg"><%=request.getParameter("msg") %></div>
	<% } %>
    <main class="container clear"> 
      <!-- main body --> 
      <div id="comments" style="width: 70%; float:left; margin-right:30px">
		<h4>Add News Category</h4>
		<form method="post" action="model/type.jsp">
          <div>
            <label for="email">Category Name<span>*</span></label>
            <input type="text" name="type_name" id="type_name" value="<% out.print(Values.get("type_name")); %>" size="22" style="width:300px;" required>
          </div>
          <div>
          <div style="clear:both">Show in menu</div> 
		  <div style="float:left; clear:both"><input type="checkbox" name="type_show" id="type_show" value="1" size="22" style="margin:10px" <% out.print(checked); %>></div>
          </div>
          <div style="clear:both"></div>
          <div>
            <label for="email">Description<span>*</span></label>
            <textarea style="width:300px; height:100px;" name="type_description" required><% out.print(Values.get("type_description")); %></textarea>
          </div>
          <div>
            <input name="submit" type="submit" value="Save Type">
            &nbsp;
            <input name="reset" type="reset" value="Reset Form">
          </div>
		  <input type="hidden" name="image_name" value="<% out.print(Values.get("type_image")); %>" />
		  <input type="hidden" name="act" value="Save" />
		  <input type="hidden" id="type_id" name="type_id" value="<% out.print(Values.get("type_id")); %>"/>
        </form>
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
