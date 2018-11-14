package org.injae.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.injae.mapper.MemberMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"
})
@Log4j
public class MemberTests {

	@Setter(onMethod_= @Autowired)
	private PasswordEncoder pwEncoder;
	
	@Setter(onMethod_= @Autowired)
	private DataSource ds;
	
	@Setter(onMethod_= @Autowired)
	private MemberMapper mapper;
	
	@Test
	public void testRead() {
		
		log.info(mapper.read("admin"));
		
	}
	
	@Test
	public void testInsertMember() {
		
		log.info(ds);
		
		String sql = "insert into tbl_member (userid, userpw, username) values (?, ?, ?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "injae");
			pstmt.setString(2, pwEncoder.encode("injae"));
			pstmt.setString(3, "황인재");
			
			log.info(pstmt.executeUpdate());
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {try { pstmt.close();}catch(Exception e) {}}
			if(con != null) {try { con.close();}catch(Exception e) {}}
		}//end finally
			
		
	}
	
	@Test
	public void testInsertAuth() {
		
		String sql = "insert into tbl_member_auth (userid, auth) values (?, ?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "admin");
			pstmt.setString(2, "ROLE_MEMBER");
			
			log.info(pstmt.executeUpdate());
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {try { pstmt.close();}catch(Exception e) {}}
			if(con != null) {try { con.close();}catch(Exception e) {}}
		}//end finally
		
	}
	
}
