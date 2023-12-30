package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.jpa.JpaMemberRepository;
import com.example.demo.vo.Member;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired
	JpaMemberRepository jpaMember;

	@RequestMapping(value="insertMemberForm")
	public ModelAndView insertMemberFrom() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("insertMember");
		return mav;
	}
	
	@RequestMapping(value="insertMemberControl")
	public ModelAndView insertMember(Member member) {
		String input_pw = member.getPw();
		System.out.println("사용자 입력 비번 = "+input_pw);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encode_pw = encoder.encode(input_pw);
		System.out.println("암호화 비번 = "+encode_pw);
		member.setPw(encode_pw);
		jpaMember.save(member);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:/");
		return mav;
	}
	
	@RequestMapping(value="loginForm")
	public ModelAndView loginForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping(value="loginControl")
	public ModelAndView login(Member mem, HttpSession session) {
		String inputId = mem.getId();
		boolean pwMatch = false;
		Member dbMember = null;
		
		try {
			dbMember = jpaMember.getById(inputId);
			String dbPw = dbMember.getPw();
			String inputPw = mem.getPw();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			pwMatch = encoder.matches(inputPw, dbPw);
		} catch (EntityNotFoundException e) {
			System.out.println("아이디가 일치하는 회원이 없습니다");
		}
		
		ModelAndView mav = new ModelAndView();
		
		if(pwMatch==true) {
			session.setAttribute("login_member", dbMember);
			mav.setViewName("forward:/");
		}else {
			mav.addObject("loginError","아이디 혹은 비밀번호가 일치하지 않습니다");
			mav.setViewName("login");
		}
		return mav;	
	}
}
