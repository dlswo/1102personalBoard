package org.injae.service;

import org.injae.domain.Param;
import org.injae.domain.ReplyPageDTO;
import org.injae.domain.ReplyVO;

public interface ReplyService extends CommonService<ReplyVO, Param> {
	
	public ReplyPageDTO getListPage(Param param);
	
}
