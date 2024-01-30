package com.javaex.ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver" ;
	private String url = "jdbc:mysql://localhost:3306/book_db";
	private String id = "book";
	private String pw = "book";
	
	private void getConnection() {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {
			System.out.println("error:" + e);

		}

	}
	
	private void close() {
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
	
	public int bookInsert(BookVo bookVo) {

		int count = -1; 

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// -SQL문 준비
			String query="insert into book "
					+ "values(null, ?, ?, ?, ?);";

			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getDate());
			pstmt.setInt(4,  bookVo.getuId());

			// -실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록 되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		this.close();
		return count;
	}
	
	public int bookInsert(int bookId, String title, String pubs, String pub_date, int authorId) {

		int count = -1; 

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// -SQL문 준비
			String query="insert into book "
					+ "values(?, ?, ?, ?, ?);";

			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);
			pstmt.setString(2, title);
			pstmt.setString(3, pubs);
			pstmt.setString(4, pub_date);
			pstmt.setInt(5,  authorId);

			// -실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록 되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		this.close();
		return count;
	}
	
	public int bookDelete(int bookId) {

		int count = -1; 

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// -SQL문 준비
			String query="delete from book "
					+ "where book_id=?";

			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);


			// -실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 삭제 되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		this.close();
		return count;
	}
	
	public int bookUpdate(BookVo bookVo) {

		int count = -1; 

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// -SQL문 준비
			String query="update book "
					+ "set title=?, pubs=?, pub_date=?, author_id=? "
					+ "where book_id=?";

			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getDate());
			pstmt.setInt(4, bookVo.getuId());
			pstmt.setInt(5, bookVo.getId());


			// -실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 수정 되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		this.close();
		return count;
	}
	
	public void bookSelect() {
		List<BookVo> bookList=new ArrayList<BookVo>();
		int count = -1; 

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// -SQL문 준비
			String query="select book_id, title, pubs, pub_date, author_id "
					+ "from book;";

			//바인딩
			pstmt = conn.prepareStatement(query);

			//실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while(rs.next()) {
				int id=rs.getInt("book_id");
				String title=rs.getString("title");
				String pubs=rs.getString("pubs");
				String date=rs.getString("pub_date");
				int author=rs.getInt("author_id");
				// 묶기
				BookVo v01=new BookVo(id, title, pubs, date, author);

				//리스트에 추가
				bookList.add(v01);
			}
			for (int i=0; i<bookList.size(); i++) {
				int id=bookList.get(i).getId();
				String title=bookList.get(i).getTitle();
				String pubs=bookList.get(i).getPubs();
				String date=bookList.get(i).getDate();
				int author=bookList.get(i).getAuthor();		
				System.out.println(id+", "+title+", "+pubs+", "+date+", "+author);
			}


		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		this.close();
	}
	
	public void bookSelectAll() {
		List<BookVo> bookList=new ArrayList<BookVo>();
		int count = -1; 

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// -SQL문 준비
			String query="select b.book_id,"
					+ " b.title,"
					+ " b.pubs,"
					+ " date_format(b.pub_date, '%Y년 %m월 %d일') pub_date,"
					+ " b.author_id,"
					+ " a.author_id,"
					+ " a.author_name,"
					+ " a.author_desc"
					+ " from book b"
					+ " left outer join author a on b.author_id=a.author_id"
					+ " order by b.title desc";

			//바인딩
			pstmt = conn.prepareStatement(query);

			//실행
			rs = pstmt.executeQuery();

			//검색결과에서 데이터 꺼내기
			while(rs.next()) {
				int id=rs.getInt("book_id");
				String title=rs.getString("title");
				String pubs=rs.getString("pubs");
				String date=rs.getString("pub_date");
				int author=rs.getInt("author_id");
				int uId=rs.getInt("author_id");
				String name=rs.getString("author_name");
				String desc=rs.getString("author_desc");
				
				// 묶기
				BookVo v01=new BookVo(id, title, pubs, date, author, uId, name, desc);

				//리스트에 추가
				bookList.add(v01);
			}

			// 4.결과처리
			for (int i=0; i<bookList.size(); i++) {
				int id=bookList.get(i).getId();
				String title=bookList.get(i).getTitle();
				String pubs=bookList.get(i).getPubs();
				String date=bookList.get(i).getDate();
				int author=bookList.get(i).getAuthor();
				int uId=bookList.get(i).getuId();
				String name=bookList.get(i).getName();;
				String desc=bookList.get(i).getDesc();;
				
				System.out.println(id+", "+title+", "+pubs+", "+date+", "+author+", "+uId+", "+name+", "+desc);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		this.close();
	}
	
	public void bookSelectOne(int bookId) {
		List<BookVo> bookList=new ArrayList<BookVo>();
		int count = -1; 

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// -SQL문 준비
			String query="select b.book_id,"
					+ " b.title,"
					+ " b.pubs,"
					+ " date_format(b.pub_date, '%Y년 %m월 %d일') pub_date,"
					+ " b.author_id,"
					+ " a.author_id,"
					+ " a.author_name,"
					+ " a.author_desc"
					+ " from book b"
					+ " left outer join author a on b.author_id=a.author_id"
					+ " where b.book_id=?";

			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, 2);

			//실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while(rs.next()) {
				int id=rs.getInt("book_id");
				String title=rs.getString("title");
				String pubs=rs.getString("pubs");
				String date=rs.getString("pub_date");
				int author=rs.getInt("author_id");
				int uId=rs.getInt("author_id");
				String name=rs.getString("author_name");
				String desc=rs.getString("author_desc");
				System.out.println(id+" "+title+" "+pubs +" "+ date+" "+author+" "+uId+" "+ name+" "+desc);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		this.close();
	}
}
