package com.quinobo.jack.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.quinobo.jack.util.Constants;

@Service
public class FileService implements Constants{

	private final String UPLOAD_PATH;

	public FileService() {
		UPLOAD_PATH = getHomeDir() + "/files/";
		makeFolder();
	}

	/**
	 * 현재의 홈 디렉토리 경로를 반환함
	 * 
	 * @return 현재 디렉토리 경로의 홈 디렉토리
	 */
	private String getHomeDir() {
		String urls = null;
		try {
			urls = new ClassPathResource("com/quinobo/jack/service/FileService.class").getURI().getPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String homeDir = urls.substring(1,
				urls.lastIndexOf("/WEB-INF/classes/com/quinobo/jack/service/FileService.class"));
		return homeDir;
	}
	
	public String getDir() {
		return UPLOAD_PATH;
	}

	/**
	 * 해당 위치에 폴더가 없을 경우 생성함
	 * 
	 */
	private void makeFolder() {
		File Folder = new File(UPLOAD_PATH);
		if (!Folder.exists()) {
			try {
				Folder.mkdir(); // 폴더 생성합니다.
				System.out.println("폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}
	}

	/**
	 * 파일 업로드
	 * 
	 * @param originalName 올린 파일의 이름
	 * @param fileData     올릴 파일
	 * @return savedName 저장된 이름
	 * @throws Exception
	 */
	public String uploadFile(String originalName, byte[] fileData) {
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + originalName.substring(originalName.lastIndexOf(".")); // 확장자만 추출해서 붙임.
		File target = new File(UPLOAD_PATH, savedName);
		try {
			FileCopyUtils.copy(fileData, target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return savedName;
	}

	/**
	 * 파일 업로드 경로 반환
	 * 
	 * @return UPLOAD_PATH : 각 파일이 각납될 경로
	 */
	public String getUPLOAD_PATH() {
		return UPLOAD_PATH;
	}
	
	public static String upload(ServletContext servletContext, MultipartFile multipartFile) {
		try {
			UUID uid = UUID.randomUUID();
			String name = multipartFile.getOriginalFilename();
			String fileName = uid.toString() + name.substring(name.lastIndexOf("."));
			Path path = Paths.get(servletContext.getRealPath("/WEB-INF/uploads/" + fileName));
			Files.write(path, multipartFile.getBytes());
			return fileName;
		} catch(Exception e) {
			return null;
		}
	}

	public void fileDupDelete(String npno) {
		BoardService bs = new BoardService();
		com.quinobo.jack.vo.NpcEntity npcEntity = bs.selectNpcDetail(npno);
		String fileUrl = UPLOAD_PATH + npcEntity.getPic_Link();
		File file = new File(fileUrl);
		if (file.exists()) {
			if (file.delete()) {
				System.out.println("파일삭제 성공");
			} else {
				System.out.println("파일삭제 실패");
			}
		} else {
			System.out.println("파일이 존재하지 않습니다.");
		}
	}
	
	private boolean isImageExten(String originalFileName) {
		boolean result = false;
		System.out.println(originalFileName);
		String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();
		//확장자 검사로직
		if(originalFileExtension.equals(".png")||originalFileExtension.equals(".jepg")||
				originalFileExtension.equals(".jpg")||originalFileExtension.equals(".bmp")||
				originalFileExtension.equals(".gif")){
			result = true;
		}
		return result;
	}

	/**
	 * 로그파일을 관리하는 프로세스
	 * @param image : multifile, 현재 업로드한 파일
	 * @return 파일 링크 혹은 error메시지
	 */
	public String logFileProcess(MultipartFile image) {
		long filesize = image.getSize();
		String originalFileName = image.getOriginalFilename();
		String storedFileName = null;
		if (filesize > MAX_FILE_SIZE) {
			return "error : '첨부 파일은" + MAX_FILE_SIZE/(1024 * 1024) + "MB를 넘지 않도록 해주세요."; 
		} 
		
		if (!isImageExten(originalFileName)) {
			return "error : '지원하지 않는 확장자입니다. png를 권장하며, jpg, bmp, gif를 지원합니다.";
		}
		try {
			storedFileName = uploadFile(originalFileName, image.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return storedFileName;
	}

}
