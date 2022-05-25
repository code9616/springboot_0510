package com.springboot.study.service.user;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.study.config.auth.PrincipalDetails;
import com.springboot.study.domain.user.User;
import com.springboot.study.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
	
	@Value("${file.path}") //springboot bean의 value // 서비스가 생성될때 filePath에 ${file.path}넣음
	private String filePath;
	
	private final UserRepository userRepository;
	
	@Override
	public boolean updateProfileImg(MultipartFile file, PrincipalDetails principalDetails) {
		if(file != null) {
			String originalFileName = file.getOriginalFilename();
			String tempFileName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFileName; //UUID 숫자영어이름
			Path uploadPath = Paths.get(filePath, "profile/" + tempFileName);
			
			File f = new File(filePath + "profile");
			if(!f.exists()) { //!f.exists() 해당 경로가 존재하면 true
				f.mkdirs();
			}
			
			System.out.println(uploadPath);
		
			try {
				Files.write(uploadPath, file.getBytes());
				
				User user = principalDetails.getUser();
				user.setProfile_img_url(tempFileName);
				
				return userRepository.updateProfileImg(user) > 0 ? true : false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
		
	}
}
