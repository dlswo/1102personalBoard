package org.injae.service;

import java.util.List;

import org.injae.domain.Board;
import org.injae.domain.BoardAttachVO;
import org.injae.domain.Param;

public interface BoardService extends CommonService<Board, Param>{
	
	public int getTotal();
	
	public List<BoardAttachVO> getAttachList(Param param);
	
	public int updateViews(Param param);
	
}
