package com.springboot.study.web.controller.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.study.service.board.BoardService;
import com.springboot.study.web.dto.CMRespDto;
import com.springboot.study.web.dto.board.BoardInsertReqDto;
import com.springboot.study.web.dto.board.BoardRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@PostMapping("/board")
	public ResponseEntity<?> createBoard(@Valid BoardInsertReqDto boardInsertReqDto, BindingResult bindingResult) throws Exception{
		int boardCode = boardService.createBoard(boardInsertReqDto);
		return new ResponseEntity<>(new CMRespDto<Integer>(1, "게시글 작성 완료", boardCode), HttpStatus.OK);
	}
	
	@GetMapping("/board/{boardCode}")
	public ResponseEntity<?> getBoard(@PathVariable int boardCode) throws Exception{
		BoardRespDto boardRespDto = boardService.getBoard(boardCode);
		return new ResponseEntity<>(new CMRespDto<BoardRespDto>(1, "게시글 조회 성공", boardRespDto), HttpStatus.OK);
	}
}
