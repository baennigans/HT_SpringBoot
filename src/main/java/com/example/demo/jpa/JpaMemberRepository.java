package com.example.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.Member;

@Repository
public interface JpaMemberRepository extends JpaRepository<Member, String>{

}
