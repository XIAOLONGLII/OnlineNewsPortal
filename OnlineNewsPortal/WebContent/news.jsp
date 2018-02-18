<%@ include file="includes/header.jsp" %>
<%@ page import= "java.util.*" %>
<%@ page import= "Model.*" %>
<%
	News newsDetails = new News();
	int news_id = Integer.parseInt(request.getParameter ("news_id"));
	HashMap Values =  newsDetails.getNewsDetails(news_id);	
%>
<script>
function validImage() {
	var val = $("#news_image").val();
	var id = $("#news_id").val();
	if(id == '' || val != '')
	{
		if(val == '') {
			alert('Choose the News Image');
			return false;
		}
		if (!val.match(/(?:gif|jpg|png|bmp)$/)) {
			// inputted file path is not an image of one of the above types
			alert("inputted file path is not an image!");
			return false;
		}
	}
	return true;
}
</script>
<div class="wrapper row3">
  <div class="rounded">
    <main class="container clear"> 
      <!-- main body --> 
      <div id="comments" style="width: 70%; float:left; margin-right:30px">
      <h2>News Form</h2>
		<form method="post" action="UploadNews" enctype="multipart/form-data" onsubmit="return validImage()">
          <div class="half_width">
            <label for="email">News Title<span>*</span></label>
            <input type="text" name="news_title" id="news_title" value="<% out.print(Values.get("news_title")); %>" size="22" style="width:300px;" required>
          </div>
          <div class="half_width">
            <label for="email">News Type<span>*</span></label>
            <select style="height: 40px; width:300px" name = "news_type_id" id = "news_type_id" required>
            	<% out.print(newsDetails.getTypeOption((Integer) Values.get("news_type_id"))); %>
            </select>
          </div>
          <div style="clear:both">
            <label for="email">Image<span>*</span></label>
            <input type="file" name="news_image" id="news_image" style="width:300px">
          </div>
          <div style="clear:both">
            <label for="email">Description<span>*</span></label>
			<textarea style="width:300px; height:100px;" name="news_description" required><% out.print(Values.get("news_description")); %></textarea>
          </div>
          <% if(!Values.get("news_image").equals("")) { %>
          <div class="half_width">
			<img src="newsImages/<% out.print(Values.get("news_image")); %>" style="height:100px; width:100px;">
          </div>
          <% } %>
          <div class="block clear"></div>
          <div>
            <input name="submit" type="submit" value="Save News">
            &nbsp;
            <input name="reset" type="reset" value="Reset Form">
          </div>
		  <input type="hidden" name="image_name" value="<% out.print(Values.get("news_image")); %>" />
		  <input type="hidden" name="act" value="Save" />
		  <input type="hidden" name="type_id" value="0" />
		  <input type="hidden" id="news_id" name="news_id" value="<% out.print(Values.get("news_id")); %>"/>
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
