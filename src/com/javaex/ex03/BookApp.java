package com.javaex.ex03;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {

//		BookVo bookVo=new BookVo("어쩌구책", "출판사", "2022-02-02", 5);
//		bookDao.bookInsert(bookVo);
		
		BookDao bookDao=new BookDao();
//		bookDao.bookInsert(18, "책제목", "출판사", "2011-11-11", 3);
		
//		bookDao.bookDelete(18);
		
//		BookVo bookVo=new BookVo("수정된책", "출판사", "2022-02-02", 4, 14);
//		bookDao.bookUpdate(bookVo);
		
//		bookDao.bookSelect();
//		bookDao.bookSelectAll();
		
		bookDao.bookSelectOne(2);
	}

}
