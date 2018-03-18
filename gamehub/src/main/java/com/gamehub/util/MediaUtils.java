package com.gamehub.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class MediaUtils {
	//넘어온 데이터 타입을 비교할 데이터 타입 맵을 생성한다.
	private static Map<String, MediaType> mediaMap;
	
	static{
		//그림파일만 찾아내기 위해 그림 파일명만 맵에 등록시켜놓는다.
		mediaMap = new HashMap<String, MediaType>();		
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}
	//데이터 타입을 매개변수로 받는 메소드 선언
	public static MediaType getMediaType(String type){
		//매개변수로 넘어온 데이터 타입을 대분자로 변경하여 맵의 키로 사용한다.
		//그림파일의 데이터 타입이라면 맵의 해당 데이터가 리턴될것이고 그림이 아니라면 null이 리턴될것이다.
		return mediaMap.get(type.toUpperCase());
	}
}



