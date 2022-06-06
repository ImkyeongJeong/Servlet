package co.edu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToDoListDAO extends DAO{
	//전체조회
	public List<ToDoList> TodoList(){
		connect();
		List<ToDoList> list = new ArrayList<>();
		String sql = "select * from todolist order by 1";
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
	public void insertTodo(String todo) {
		connect();
		String sql = "insert into todolist values(todo_seq.nextval, ?, default)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, todo);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	//수정
	public void updateTodo(ToDoList vo) {
		connect();
		String sql = "";
		if(vo.getStatus() == 0) {
			sql = "update todolist set status = 1 where todo_id = ?";
		} else if(vo.getStatus() == 1) {
			sql = "update todolist set status = 0 where todo_id = ?";
		}
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getTodoId());
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	//삭제
	public void deleteTodo(int num) {
		connect();
		String sql = "delete from todolist where todo_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, num);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
}
