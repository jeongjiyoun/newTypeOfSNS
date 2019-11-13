package com.quinobo.jack;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.quinobo.jack.baseController.AdminBaseController;
import com.quinobo.jack.database.ConnectionTest;
import com.quinobo.jack.database.SqlCreater;
import com.quinobo.jack.service.AdminService;
import com.quinobo.jack.service.BoardService;
import com.quinobo.jack.service.FileService;
import com.quinobo.jack.util.Constants;
import com.quinobo.jack.util.PageMaker;
import com.quinobo.jack.vo.NpcEntity;

/**
 * Handles requests for the application home page.
 */

@Controller
public class AdminController extends AdminBaseController implements Constants {

	@Autowired
	AdminService as;

	@Autowired
	FileService fs;
	
	@Autowired
	BoardService bs;
	
	@Resource(name = "uploadPath")
	private String uploadPath;	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "admin/login";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String adminLogin(String id, String pw, HttpSession session, Model model) {
		String name = as.adminLogin(id, pw);
		if (name != null) {
			session.setAttribute("adminName", name);
			session.setAttribute("adminId", id);
			model.addAttribute("msg", null);
			return "admin/main";
		} else {
			model.addAttribute("msg", "정보를 확인해주세요.");
			return "admin/login";
		}
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminLogin() {
		return "admin/main";
	}

	@RequestMapping(value = "/edit_npc", method = RequestMethod.POST)
	public String edit_npc(String extra, Model model) {
		NpcEntity npcEntity = bs.selectNpcDetail(extra, TABLE_NPC);
		model.addAttribute("npcEntity",npcEntity);
		model.addAttribute("uploadPath", uploadPath);
		return "admin/npc/npcEdit";
	}
	@RequestMapping(value = "/npcEdit", method = RequestMethod.POST)
	public String npcEdit(@RequestParam(defaultValue="null")MultipartFile pic, String name, String comment, Model model, HttpSession session) {
		/**
		 * 여기부터 내일 작업하면 됩니다.
		 * 11/13문제사항 : 사진파일이 보이지 않음.
		 */
		return "redirect:/unyonyazal/npc_Admin";
	}
	

	@RequestMapping(value = "/char_Admin", method = RequestMethod.GET)
	public String goChar_Admin() {
		return "admin/character";
	}
	
	@RequestMapping(value = "/npc_Admin", method = {RequestMethod.GET, RequestMethod.POST})
	public String goNPC_Admin(String keyword,
            @RequestParam(defaultValue="1") int curPage,
            Model model) {
		
		// 레코드의 갯수 계산
	    int count = bs.selectBoardListCnt(keyword,TABLE_NPC);
	    
	    // 페이지 나누기 관련 처리
	    PageMaker pageMaker = new PageMaker(count, curPage);
	    List<NpcEntity> list = bs.listNPC(curPage-1, keyword);
	    
	    model.addAttribute("npcList", list);
	    model.addAttribute("count", count);
	    model.addAttribute("keyword", keyword); 
	    model.addAttribute("pageMaker", pageMaker); 
	    
		return "admin/npc/npcMain";
	}

	@RequestMapping(value = "/new_npc", method = RequestMethod.POST)
	public String new_npc() {
		return "admin/npc/npcAdd";
	}

	@RequestMapping(value = "/add_npc", method = RequestMethod.POST)
	public String add_npc(@RequestParam(defaultValue="null")MultipartFile pic, String name, String comment, Model model, HttpSession session) throws IOException, Exception {
		Map<String, String> map = new HashMap<String, String>(4);
			map.put("WRITER", (String) session.getAttribute("adminId"));
			if (pic != null) {
				map.put("PIC_LINK", fs.uploadFile(pic.getOriginalFilename(), pic.getBytes()));
			}
			map.put("NAME", name);
			map.put("MEMO", comment);
			bs.insertNpc(map,TABLE_NPC);
			
		return "redirect:/unyonyazal/npc_Admin";
	}

	@RequestMapping(value = "/event_Admin", method = RequestMethod.GET)
	public String goEvent_Admin(
			@RequestParam(defaultValue = "1") int curPage, HttpServletRequest request, Model model) throws Exception {

		return "admin/event/eventMain";
	}

}
