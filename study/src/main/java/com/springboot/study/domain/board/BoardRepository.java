package com.springboot.study.domain.board;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardRepository {
	public int insertBoard(BoardMst boardMst) throws Exception;
	public Map<String, Object> getBoardByBoardCode(int boardCode) throws Exception;
	
}
