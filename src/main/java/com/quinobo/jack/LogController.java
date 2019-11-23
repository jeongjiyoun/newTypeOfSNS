package com.quinobo.jack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;
import com.quinobo.jack.service.BoardService;
import com.quinobo.jack.service.FileService;

/**
 * Handles requests for the application home page.
 */

@Controller
public class LogController {

	@Autowired
	FileService fs;

	@Autowired
	BoardService bs;

	@ResponseBody
	@RequestMapping(value = "/fileuploadLog.do", method = RequestMethod.POST)
	public void fileuploadLog(HttpServletRequest request,String CKEditorFuncNum,HttpServletResponse response) {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		MultipartFile image = null;
		while(iterator.hasNext()){
			image = multipartHttpServletRequest.getFile(iterator.next());
		}
		
		String originalFileName = "test.jpg";
		String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		//확장자 검사로직
		if(originalFileExtension.toLowerCase().equals(".jpg")){
			//jpg 파일 일경우
		}
		else{
			//jpg 이외의 파일 일경우
		}
		
		String storedFileName = null;
		try {
			storedFileName = fs.uploadFile(originalFileName, image.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		//임시 경로 패스. 나중에는 그냥 storedFileName에다가 getUPLOADPATH하면 됨.
		String resultPath = "http://127.0.0.1/"+storedFileName;

		JsonObject json = new JsonObject();
		json.addProperty("uploaded", 1);
		json.addProperty("filename", storedFileName);
		json.addProperty("url", resultPath);
		
        PrintWriter printWriter;
		try {
			printWriter = response.getWriter();
			printWriter.println(json);
			printWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			//모든 exception처리는 나중에 특정 exception을 만들어서 로그 정리하는거랑 한번에 하기.
			e.printStackTrace();
		}

	}
	@RequestMapping(value = "/writeLog", method = RequestMethod.POST)
	public String add_npc(String logData, HttpSession session) {
		String owno = (String) session.getAttribute("owno");
		owno = "jack_Test";
		String wchar = (String) session.getAttribute("wchar");
		wchar = "testJackCr";
		Map<String, String> map = new HashMap<String, String>(4);
		map.put("CONTENTS", logData);
		map.put("WCHAR", wchar);
		map.put("OWNO", owno);
		bs.insertLog(map);
		//DB구조도 바꾸기. 사진이랑 Board랑 테이블을 분리해야함.
		return "redirect:/";
	}
	

}
