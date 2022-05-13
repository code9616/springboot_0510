package com.springboot.study.service.board;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.springboot.study.domain.board.BoardMst;
import com.springboot.study.domain.board.BoardRepository;
import com.springboot.study.web.dto.board.BoardInsertReqDto;
import com.springboot.study.web.dto.board.BoardRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardRepository boardRepository;
	
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
				.build();
	}
	
}
