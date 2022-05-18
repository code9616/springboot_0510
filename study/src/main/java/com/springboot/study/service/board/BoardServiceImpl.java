package com.springboot.study.service.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.springboot.study.domain.board.BoardMst;
import com.springboot.study.domain.board.BoardRepository;
import com.springboot.study.web.dto.board.BoardInsertReqDto;
import com.springboot.study.web.dto.board.BoardRespDto;
import com.springboot.study.web.dto.board.BoardUpdateReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardRepository boardRepository;
	
	@Override
	public List<BoardRespDto> getBoardListAll() throws Exception {
		List<BoardRespDto> boardRespDtos = new ArrayList<BoardRespDto>();
		List<Map<String, Object>> boardListAll = boardRepository.getBoardListAll();
		for(Map<String, Object> boardMap : boardListAll) {
			boardRespDtos.add(BoardRespDto.builder()
			.boardCode((Integer)(boardMap.get("board_code")))
			.title((String)(boardMap.get("board_title")))
			.content((String)(boardMap.get("board_content")))
			.usercode((Integer)(boardMap.get("board_writer")))
			.username((String)(boardMap.get("board_username")))
			.boardCount((Integer) (boardMap.get("board_count")))
			.build());
		}
		return boardRespDtos;
	}
	
	@Override
	public List<BoardRespDto> getBoardListByPage(int page) throws Exception {
		List<BoardRespDto> boardRespDtos = new ArrayList<BoardRespDto>();
		List<Map<String, Object>> boardListAll = boardRepository.getBoardListByPage((page - 1)* 5);
		for(Map<String, Object> boardMap : boardListAll) {
			boardRespDtos.add(BoardRespDto.builder()
			.boardCode((Integer)(boardMap.get("board_code")))
			.title((String)(boardMap.get("board_title")))
			.content((String)(boardMap.get("board_content")))
			.usercode((Integer)(boardMap.get("board_writer")))
			.username((String)(boardMap.get("board_username")))
			.boardCount((Integer) (boardMap.get("board_count")))
			.boardCountAll((long) (boardMap.get("board_count_all")))
			.build());
		}
		return boardRespDtos;
	}
	
	@Override
	public int createBoard(BoardInsertReqDto boardInsertReqDto) throws Exception{
		
		BoardMst boardMst = boardInsertReqDto.toBoardMstEntity();
		
		int result = boardRepository.insertBoard(boardMst);
		
		if(result > 0) {
			return boardMst.getBoard_code();
		}
		return 0;
	}
	
	@Override
	public BoardRespDto getBoard(int boardCode) throws Exception {
		Map<String, Object> boardMap = boardRepository.getBoardByBoardCode(boardCode);
		return BoardRespDto.builder()
				.boardCode((Integer)(boardMap.get("board_code")))
				.title((String)(boardMap.get("board_title")))
				.content((String)(boardMap.get("board_content")))
				.usercode((Integer)(boardMap.get("board_writer")))
				.username((String)(boardMap.get("board_username")))
				.boardCount((Integer) (boardMap.get("board_count")))
				.build();
	}
	
	@Override
	public int updateBoard(int boardCode, BoardUpdateReqDto boardUpdateReqDto) throws Exception {
		BoardMst boardMst = boardUpdateReqDto.toBoardMstEntity(boardCode);
		return boardRepository.updateBoard(boardMst) > 0 ? boardCode : 0;
	}
	
	@Override
	public int deleteBoard(int boardCode) throws Exception {
		return boardRepository.deleteBoard(boardCode) > 0 ? boardCode : 0;
	}
	
}
