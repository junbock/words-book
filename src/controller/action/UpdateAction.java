package controller.action;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.Dictionary;
import model.entity.DictionaryDAO;
public class UpdateAction implements Action{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String kor = request.getParameter("kor");
			String en = request.getParameter("eng");
			
			if (kor == null || kor.trim().length() == 0 ||
					en == null || en.trim().length() == 0){
				throw new Exception("입력값이 충분하지 않습니다.");
			}
			
			boolean result = DictionaryDAO.update(Dictionary.builder().kor(kor).en(en).build());
			
			if (result) {
				response.sendRedirect("index.html");
			} else {
				throw new Exception("수정 에러");
			}
		} catch(Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}