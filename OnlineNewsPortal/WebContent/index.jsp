<%@ include file="includes/header.jsp" %>
<%@ include file="includes/slider.jsp" %>
<%@ page import= "java.util.*" %>
<%@ page import= "Model.*" %>

<%
	News newsDetails = new News();
	int typeID = 0;
	if (request.getParameter("type_id") != null){
		typeID = Integer.parseInt(request.getParameter("type_id"));
	}
	ArrayList allNews = newsDetails.getAllNews(typeID);
	
	Type typeDetailsNew = new Type();
	ArrayList allTypeNew = typeDetailsNew.getAllType(0);
%>
<div class="wrapper row3">
  <div class="rounded">
    <main class="container clear"> 
      <!-- main body --> 
      <!-- ################################################################################################ -->
      <div class="group btmspace-30"> 
        <!-- Left Column -->
        <div class="one_quarter first"> 
          <!-- ################################################################################################ -->
		  <h2 style="color:#000000; font-weight:bold">News Category</h2>
          <ul class="nospace">
		  <% for(int i=0;i<allType.size();i++) 
			{ 
				HashMap TypeDetailsNew = new HashMap();
				TypeDetailsNew = (HashMap)allTypeNew.get(i);
			%>
				<li class="btmspace-15">
				<a href="index.jsp?type_id=<% out.print(TypeDetailsNew.get("type_id")); %>"><em class="heading"><% out.print(TypeDetailsNew.get("type_name")); %></em></a>
				</li>
		   <%
			}
		   %>
          </ul>
          <!-- ################################################################################################ --> 
        </div>
        <!-- / Left Column --> 
        <!-- Middle Column -->
        <div class="one_half" style="width:70%"> 
          <!-- ################################################################################################ -->
          <h2>ONLINE NEWS PORTAL</h2>
          <ul class="nospace listing">
			<% for(int i=0;i<allNews.size();i++) 
			{ 
				HashMap NewsDetails = new HashMap();
				NewsDetails = (HashMap)allNews.get(i);
				String desc = NewsDetails.get("news_description").toString();
			%>	
            <li class="clear">
              <div class="imgl borderedbox">
			  <a href="news-details.jsp?news_id=<% out.print(NewsDetails.get("news_id")); %>">
				<img src="newsImages/<% out.print(NewsDetails.get("news_image")); %>" style="height:120px; width:120px">
			  </a>
			  </div>
              <p class="nospace btmspace-15"><a href="news-details.jsp?news_id=<% out.print(NewsDetails.get("news_id")); %>" style="font-size:15px; font-weight:bold;"><% out.print(NewsDetails.get("news_title")); %></a></p>
			  <% out.print(desc.substring(0,400)); %>
            </li>
            <%
			}
			%>
          </ul>
          <!-- ################################################################################################ --> 
        </div>
        <!-- / Middle Column --> 
      </div>
      
      <!-- ################################################################################################ --> 
      <!-- ################################################################################################ -->
      
      <!-- ################################################################################################ --> 
      <!-- / main body -->
      <div class="clear"></div>
    </main>
  </div>
</div>

<%@ include file="includes/footer.jsp" %>
