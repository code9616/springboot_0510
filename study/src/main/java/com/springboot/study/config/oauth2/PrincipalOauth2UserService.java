package com.springboot.study.config.oauth2;

import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.springboot.study.config.auth.PrincipalDetails;
import com.springboot.study.domain.user.User;
import com.springboot.study.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{
	
	private final UserRepository userRepository;


	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		System.out.println("oAuth2User -> " + oAuth2User);
		System.out.println("Attributes -> " + oAuth2User.getAttributes());
		
		String provider = userRequest.getClientRegistration().getRegistrationId();
		
		Map<String, Object> attributes = oAuth2User.getAttributes();
		
		String oAuth2_username = createOAuth2Username(provider, attributes);	
		User userEntity = userRepository.findOAuth2UserByOAuth2Username(oAuth2_username);
		
		if(userEntity == null) {
			//회원가입
			User user = User.builder()
					.email((String)attributes.get("email"))
					.name((String)attributes.get("name"))
					.username(oAuth2_username)
					.oAuth2_username(oAuth2_username)
					.password(new BCryptPasswordEncoder().encode("1234"))
					.roles("ROLE_USER")
					.provider(provider)
					.build();
			if(userRepository.insertUser(user) == 0) {
				throw new OAuth2AuthenticationException(new OAuth2Error("400", "회원가입 실패", "/auth/signup"), "회원가입 실패");
			}
			userEntity = user;
			
		}
		
		return new PrincipalDetails(userEntity, attributes);
	}
	
	private String createOAuth2Username(String provider, Map<String, Object> attributes) {
		if(provider.equals("naver")) {
			return null;
		}else if(provider.equals("google")) {
			return provider + "_" + (String)attributes.get("sub");
		}else {
			return null;
		}
		
	}
}

