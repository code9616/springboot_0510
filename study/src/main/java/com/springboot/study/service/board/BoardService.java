package com.springboot.study.service.board;

import com.springboot.study.web.dto.board.BoardInsertReqDto;
import com.springboot.study.web.dto.board.BoardRespDto;

public interface BoardService {
	public int createBoard(BoardInsertReqDto boardInsertReqDto) throws Exception;
	public BoardRespDto getBoard(int boardCode) throws Exception;
}
