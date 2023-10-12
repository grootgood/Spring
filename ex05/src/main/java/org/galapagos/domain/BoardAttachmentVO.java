package org.galapagos.domain;

import java.io.File;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardAttachmentVO {
	public static final String UPLOAD_PATH = "c:/upload/board";
	
	private Long no;
	private String filename;
	private String path;
	private String contentType;
	private Long size;
	private Long bno;
	private Date regDate;
	
	// 업로드 할때 사용
	public BoardAttachmentVO(Long bno, MultipartFile part) throws Exception {
		filename = part.getOriginalFilename();
		contentType = part.getContentType();
		size = part.getSize();
		this.bno = bno; // this를 붙이지 않으면 지역변수인 자기 자신에 저장하는 꼴
		path = UPLOAD_PATH + "/" + System.currentTimeMillis() + "_" + filename; // 파일명 중복 피하기 위해 시스템 시간 추가
		part.transferTo(new File(path)); // 실제 저장
	}
}
