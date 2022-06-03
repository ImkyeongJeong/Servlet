package co.edu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToDoListDAO extends DAO{
	//전체조회
	public List<ToDoList> TodoList(){
		connect();
		List<ToDoList> list = new ArrayList<>();
		String sql = "select * from todolist";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ToDoList todo = new ToDoList();
				todo.setTodoId(rs.getInt("todo_id"));
				todo.setContent(rs.getString("content"));
				list.add(todo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	//입력
	public void insertTodo(ToDoList todo) {
		connect();
		String sql = "insert into todolist values(todo_seq.nextval, ?, default)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, todo.getContent());
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	//수정
	
	//삭제
}
