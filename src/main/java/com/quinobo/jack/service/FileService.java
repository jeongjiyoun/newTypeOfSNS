package com.quinobo.jack.service;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service
public class FileService {
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	public FileService() {
		// TODO Auto-generated constructor stub
	}

	public String uploadFile(String originalName, byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + originalName.substring(originalName.lastIndexOf(".")); //확장자만 추출해서 붙임.
		File target = new File(uploadPath, savedName);
		FileCopyUtils.copy(fileData, target);
		return savedName;
		//https://wanna-b.tistory.com/7 참조
	}
	
}
