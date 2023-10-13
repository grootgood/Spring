package org.galapagos.domain;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

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
	
	public String getFormatSize() {
		if (size <= 0)
			return "0";
		final String[] units = new String[] { "Bytes", "KB", "MB", "GB", "TB" };
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#")
						.format(size / Math.pow(1024, digitGroups)) +
					 " " + units[digitGroups];
	}
	
	public void download(HttpServletResponse response) throws Exception {
		
//		response.setContentType("application/download");
		response.setContentType(contentType);
		response.setContentLength(size.intValue());
		
//		String filename = URLEncoder.encode(this.filename, "UTF-8"); // this.filename -> 원본의 이름
		//저장대화상자 뜨게 하는 코드
//		response.setHeader("Content-disposition", "attachment;filename=\"" + filename + "\"");
		// filename -> 디폴트 저장파일명
		
		try(OutputStream os = response.getOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(os)) {
			File file = new File(path); // path -> 실제 경로
			Files.copy(file.toPath(), bos);
			// file.toPath() -> 소스, bos -> 출력 스트림
		}
	}
}
