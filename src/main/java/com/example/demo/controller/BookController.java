package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.jpa.JpaBookRepository;
import com.example.demo.vo.Book;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BookController {
	
	@Autowired
	JpaBookRepository jpaBook;

	@RequestMapping(value="/insertBookForm")
	public ModelAndView insertBook1() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("insertBook");
		
		return mav;
	}
	
	@RequestMapping(value="/insertBookControl")
	public ModelAndView insertBook2(HttpServletRequest request) {
		String num = request.getParameter("bookid");
		String name = request.getParameter("bookname");
		String pub = request.getParameter("publisher");
		String pri = request.getParameter("price");
		
		Book book = new Book();
		book.setBookid(num);
		book.setBookname(name);
		book.setPublisher(pub);
		book.setPrice(pri);
		jpaBook.save(book);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result","도서 정보가 추가 되었습니다");
		mav.setViewName("insertBookResult");
		
		return mav;		
	}

	@RequestMapping(value="selectBook")
	public ModelAndView viewBook1(HttpServletRequest request) {
		String bookid = request.getParameter("bookid");
		
		Book book = jpaBook.getById(bookid);
		ModelAndView mav = new ModelAndView();
		mav.addObject("book",book);
		mav.setViewName("selectBookOne");
		
		return mav;		
	}
	
	@RequestMapping(value="updateBookForm")
	public ModelAndView updateBook1(HttpServletRequest request) {
		String bookid = request.getParameter("bookid");
		
		Book book = jpaBook.getById(bookid);
		ModelAndView mav = new ModelAndView();
		mav.addObject("book",book);
		mav.setViewName("updateBook");
		
		return mav;
	}
	
	@RequestMapping(value="updateBookControl")
	public ModelAndView updateBook2(HttpServletRequest request) {
		String num = request.getParameter("bookid");
		String name = request.getParameter("bookname");
		String pub = request.getParameter("publisher");
		String pri = request.getParameter("price");
		
		Book book = new Book();
		book.setBookid(num);
		book.setBookname(name);
		book.setPublisher(pub);
		book.setPrice(pri);
		jpaBook.save(book);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result","도서 정보가 수정 되었습니다");
		mav.setViewName("updateBookResult");
		
		return mav;
	}
	
	@RequestMapping(value="selectAll")
	public ModelAndView allbook1() {
		List<Book> allList = jpaBook.findAll();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("allBook", allList);
		mav.setViewName("selectBookAll");
		
		return mav;
	}
}






