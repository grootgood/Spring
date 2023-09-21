package org.galapagos.security;

import java.io.File;
import java.io.IOException;

import org.galapagos.config.RootConfig;
import org.galapagos.config.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.coobird.thumbnailator.Thumbnails;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class}) 
						// RootConfig에 SecurityConfig도 들어있어서 추가해줘야한다.
public class AvatarTest {
	public static final String AVATAR_UPLOAD_DIR = "c:/upload/avatar"; 
	
	@Test
	public void testUpload() throws IOException { // File 조작이므로 예외처리가 필요하다. 
		String username = "unknown_avatar";
		
		File src = new File("c:/Temp/unknown_avatar.png"); // 자신이 지정한 avatar 이미지 파일명
		File dest = new File(AVATAR_UPLOAD_DIR, username + ".png");
		
		Thumbnails.of(src)
				.size(50, 50)
				.toFile(dest); 
	}
}
