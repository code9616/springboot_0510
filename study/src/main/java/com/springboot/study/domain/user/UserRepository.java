package com.springboot.study.domain.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
	public int insertUser(User user);
	public User findUserByUsername(String username);
	public User findOAuth2UserByOAuth2Username(String oAuth2Username);
}
