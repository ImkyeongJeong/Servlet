package co.edu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleServlet extends HttpServlet{
	//IOC: 인스턴스 생성부터 소멸까지의 인스턴스 생명주기 관리를 개발자가 아닌 컨테이너가 대신 해줍니다
	
	//최초 호출일 때만 실행, 두 번재부터는 실행되지 않음
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 호출..");
	}
	
	//HttpServletRequest 요청처리 객체 > 요청정보를 req에 담고 있음
	//HttpServletResponse 응답처리 객체 > 응답정보를 resp로 담아서 보냄
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청정보의 타입 정의
		req.setCharacterEncoding("utf-8");
		//응답정보의 컨텐트 타입 정의
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		
		System.out.println("service 호출..");
		
		//GET방식이 들어왔을 때 처리 (주소 표시줄에 입력하는 것) / POST방식이 들어왔을 때 처리(Form태그)
		if(req.getMethod().equals("GET")) {			
			System.out.println("GET 요청입니다.");
		} else if (req.getMethod().equals("POST")) {
			System.out.println("POST 요청입니다.");
		}
		
		//요청 정보를 읽을 때 getParameter
		String name = req.getParameter("name");	// name=?&age=?라는 값이 넘어와야 getParameter를 통해 값을 얻을 수 있다
		String age = req.getParameter("age");
		
		//응답 정보 getWriter가 반환해주는 타입이 printWriter
		PrintWriter out = resp.getWriter();
		out.print("<h3>요청 파라미터: " + name + "</h3>");
		out.print("<h3>요청 파라미터: " + age + "</h3>");
		out.close();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//service외 아래 방법으로 처리
	//get방식 처리
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("GET 요청입니다.");
//	}
//	
//	//post방식 처리
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("POST 요청입니다.");
//	}

	//Server가 Stop될 때 실행
	@Override
	public void destroy() {
		System.out.println("destroy 호출..");
	}
}
