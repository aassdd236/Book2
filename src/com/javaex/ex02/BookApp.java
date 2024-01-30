package com.javaex.ex02;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		//authorDao.authorInsert("서장훈", "농구선수");
		//authorDao.authorInsert("안정환", "축구선수");
		
		AuthorVo authorVo=new AuthorVo("황일영", "학원강사");
		authorDao.authorInsert(authorVo);
		
		authorDao.authorDelete(26);
		authorDao.authorDelete(27);
		authorDao.authorDelete(28);

		List<AuthorVo> authorList= authorDao.authorList();
		/*
		for(AuthorVo authorVo : authorList) {
			System.out.println(authorVo.getAuthorId() + ","
		                     + authorVo.getAuthorName() + ", "
					         + authorVo.getAuthorDesc());
		}
		*/
		
		
		/*
		AuthorDao authorDao=new AuthorDao();
		
		int cnt=authorDao.authorInsert("이효리", "제주도");
		System.out.println(cnt+" success");
		
		List<AuthorVo> authorList=authorDao.authorList();
		
		for (AuthorVo authorVo : authorList) {
			int id=authorVo.getAuthorId();
			String name=authorVo.getAuthorName();
			String desc=authorVo.getAuthorDesc();
			System.out.println(id+" "+name+" "+desc);
		}
		
		authorDao.authorInsert("이효리", "제주도민");
		authorDao.authorDelete(13);
		*/
		
		/*
		AuthorDao authorDao=new AuthorDao();
		authorDao.authorInsert("이문열", "경북영양");
		authorDao.authorInsert("박경리", "토지작가");

		authorDao.authorDelete(2);
		authorDao.authorUpdate(1, "기안84", "웹툰작가");
		List<AuthorVo> authorList=authorList
		*/
		
	}

}
