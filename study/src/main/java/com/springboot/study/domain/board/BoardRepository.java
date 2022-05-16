package com.springboot.study.domain.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardRepository {
	public List<Map<String, Object>> getBoardListAll() throws Exception;
	public List<Map<String, Object>> getBoardListByPage(int index) throws Exception;
	public int insertBoard(BoardMst boardMst) throws Exception;
	public Map<String, Object> getBoardByBoardCode(int boardCode) throws Exception;
	public int updateBoard(BoardMst boardMst) throws Exception;
	public int deleteBoard(int boardCode) throws Exception;
}
