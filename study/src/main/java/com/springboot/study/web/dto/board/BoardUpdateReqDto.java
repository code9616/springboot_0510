package com.springboot.study.web.dto.board;

import javax.validation.constraints.NotBlank;

import com.springboot.study.domain.board.BoardMst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardUpdateReqDto {
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	
	public BoardMst toBoardMstEntity(int boardCode) {
		return BoardMst.builder()
				.board_code(boardCode)
				.board_title(title)
				.board_content(content)
				.build();
	}
}
