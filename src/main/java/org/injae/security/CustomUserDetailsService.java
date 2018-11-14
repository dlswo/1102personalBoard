package org.injae.security;

import org.injae.domain.MemberVO;
import org.injae.mapper.MemberMapper;
import org.injae.security.domain.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("Load User By UserName : " + username);
		
		MemberVO vo = mapper.read(username);
		
		log.info("vo : " + vo);
		
		return new CustomUser(vo);
	}

}
