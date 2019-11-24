package com.quinobo.jack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.quinobo.jack.util.Constants;
import com.quinobo.jack.util.PageMaker;
import com.quinobo.jack.vo.NpcEntity;

/**
 * Handles requests for the application home page.
 */

@Controller
public class LogController {

	@Autowired
	FileService fs;

	@Autowired
	BoardService bs;

	
	@RequestMapping(value = "/log", method = {RequestMethod.GET, RequestMethod.POST})
	public String logMain(String searchword, @RequestParam(defaultValue = "1") int curPage, Model model) {

		// 레코드의 갯수 계산
		int count = bs.selectBoardListCnt(searchword, Constants.TABLE_LOG);

		// 페이지 나누기 관련 처리
		PageMaker pageMaker = new PageMaker(count, curPage);
		Map<String, String> map = bs.selectLogDetail();

		model.addAttribute("log", map);
		model.addAttribute("count", count);
		model.addAttribute("pageMaker", pageMaker);

		//go logMain Page
		return "logs/log";
	}
	
	@RequestMapping(value = "/logWrite", method = {RequestMethod.GET, RequestMethod.POST})
	public String logWrite() {
		//go logWrite Page
		return "logs/logWrite";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/fileuploadLog.do", method = RequestMethod.POST)
	public void fileuploadLog(HttpServletRequest request,String CKEditorFuncNum,HttpServletResponse response) {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		
		MultipartFile image = multipartHttpServletRequest.getFile("upload");
		
		String storedFileName = fs.logFileProcess(image);
		System.out.println(storedFileName);
		//서버에 올리기 전에 작성한 임시 경로 패스.
		//fs.getUPLOAD_PATH() + storedFileName;
		
		JsonObject json = new JsonObject();
		if (storedFileName.contains("error")) {
			json.addProperty("uploaded", 0);
			JsonObject jsonError = new JsonObject();
			jsonError.addProperty("message", storedFileName);
			json.add("error", jsonError);
		} else {
			json.addProperty("uploaded", 1);
			json.addProperty("filename", storedFileName);
			String resultPath = "http://127.0.0.1/"+storedFileName;
			json.addProperty("url", resultPath);

		}
		System.out.println(json.toString());

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
	public String add_npc(String editor1, HttpSession session) {
		String owno = (String) session.getAttribute("owno");
		owno = "1";
		String wchar = (String) session.getAttribute("wchar");
		wchar = "testJackCr";
		Map<String, String> map = new HashMap<String, String>(4);
		map.put("OWNO", owno);
		map.put("WCHAR", wchar);
		map.put("CONTENTS", editor1);
		bs.insertLog(map);
		//DB구조도 바꾸기. 사진이랑 Board랑 테이블을 분리해야함.
		return "redirect:/";
	}
	

}
