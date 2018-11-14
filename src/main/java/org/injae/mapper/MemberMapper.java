package org.injae.mapper;

import org.injae.domain.MemberVO;

public interface MemberMapper extends CommonMapper<MemberVO, String> {
	
	public int insertAuth(MemberVO vo);

}
