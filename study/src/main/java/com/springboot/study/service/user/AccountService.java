package com.springboot.study.service.user;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.study.config.auth.PrincipalDetails;

public interface AccountService {
	public boolean updateProfileImg(MultipartFile file, PrincipalDetails principalDetails);
}
