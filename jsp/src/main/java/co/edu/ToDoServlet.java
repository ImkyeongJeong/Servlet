package co.edu;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/todolist")
public class ToDoServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");
		System.out.println("get 호출!");
		
		//Java에서 Json을 파싱하고, 생성하기 위해 사용 java object -> json , json -> java object로 변환 가능
		Gson gson = new GsonBuilder().create();
		
		ToDoListDAO dao = new ToDoListDAO();
		List<ToDoList> list = dao.TodoList();
		resp.getWriter().print(gson.toJson(list)); //json형태 문자열
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");
		System.out.println("post 호출!");
		
		String cmd = req.getParameter("cmd");
		String todo = req.getParameter("todo");
		
		ToDoListDAO dao = new ToDoListDAO();
		Gson gson = new GsonBuilder().create();
		ToDoList td = new ToDoList();
		
		if(req.getParameter("cmd").equals("insert")) {
			td.setContent(todo);
			dao.insertTodo(td.getContent());
			resp.getWriter().print(gson.toJson(todo));
		} else if(req.getParameter("cmd").equals("delete")) {
	         int todoId = Integer.parseInt(req.getParameter("todoId"));
	         dao.deleteTodo(todoId);
		}
	}
}
