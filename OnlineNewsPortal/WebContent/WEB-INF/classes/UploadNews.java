import java.io.File;
import java.io.IOException;
import Model.*;
import java.util.*;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
 
@WebServlet("/UploadNews")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
public class UploadNews extends HttpServlet {
    private static final String SAVE_DIR = "newsImages";
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//// Logic for Upload the File ////
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + SAVE_DIR;
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
		long unixTime = System.currentTimeMillis() / 1000L;
	
		Part part;
        part = request.getPart("news_image");
        String fileName = extractFileName(part);
        if(!fileName.equals("")) {
			fileName = unixTime+"_"+extractFileName(part);
			part.write(savePath + File.separator + fileName);
		} else {
			fileName = request.getParameter("image_name");
		}
		//// Upload File Complete///
		
		/////Save the News Details /////
		News newsObj = new News();
		String emp_id = "0";
		if((request.getParameter("act")).equals("Save"))
		{
			HashMap results = new HashMap();

			results.put("news_title",request.getParameter("news_title"));
			results.put("news_type_id",request.getParameter("news_type_id"));
			results.put("news_description",request.getParameter("news_description"));
			results.put("news_id",request.getParameter("news_id"));
			results.put("news_image",fileName);
					
			if((request.getParameter("news_id")).equals(""))
			{
				newsObj.saveNews(results);
				request.setAttribute("message", "News Saved Successfully !!!!");
				getServletContext().getRequestDispatcher("/report-news.jsp").forward(request, response);
			}
			else
			{
				results.put("news_id",request.getParameter("news_id"));
				newsObj.updateNews(results);
				request.setAttribute("message", "News Updated Successfully !!!!");
				getServletContext().getRequestDispatcher("/report-news.jsp").forward(request, response);
			}
		}
    }
    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}
