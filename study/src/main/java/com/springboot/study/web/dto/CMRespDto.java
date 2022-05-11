/*
 * ClassName: CMRespDto(CustomResponseDto)
 * 
 * Version Information: 1.0.0
 * 
 * Copyright Notice: gaheul(2022.05.11 ~ 2027.05.11)
 * 
 */

//패키지 정보
package com.springboot.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import 정보
//import java.util.Scanner;

/**
 * Class Description 클래스 정보
 * 
 * @author 깃허브
 * @version 1.0.0
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CMRespDto<T> {
	/*
	 *  1 OR -1 리턴
	 *  1 -> 성공
	 *  -1 -> 실패
	 */
	private int code;
	/*
	 * 응답 메세지 내용
	 */
	private String msg;
	/*
	 * 응답 데이터 
	 */
	private T data;
}
