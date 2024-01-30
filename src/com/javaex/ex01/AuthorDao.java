package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	//필드

	//생성자
	// 기본 생성자 사용

	//메소드 gs

	//메소드 일반
	public int authorInsert(String name, String desc) {
		
		int count=0; 
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");

			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL
			String query="";
			query += "insert into author ";
			query += "values(null, ?, ?)";

			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, desc);

			//실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록 되었습니다.");


		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {            
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return count;
	}

	public int authorDelete(int num) {
		
		int count = -1;
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");

			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL
			String query="delete from author"
					+ " where author_id=?";

			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);

			//실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 삭제 되었습니다.");


		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {            
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		} 
		return count;
	}

	public List<AuthorVo> authorList() {
		//리스트 준비
		List<AuthorVo> authorList=new ArrayList<AuthorVo>();
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");

			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL
			String query="select author_id, author_name,"
					+ " author_desc from author";
			//바인딩
			pstmt = conn.prepareStatement(query);

			//실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while(rs.next()) {
				int no=rs.getInt("author_id");
				String name=rs.getString("author_name");
				String desc=rs.getString("author_desc");
				
				AuthorVo authorVo=new AuthorVo(no, name, desc);
				authorList.add(authorVo);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {            
				if (rs != null) {
					rs.close();
				}  

				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		System.out.println(authorList.toString());
		return authorList;
	}
}