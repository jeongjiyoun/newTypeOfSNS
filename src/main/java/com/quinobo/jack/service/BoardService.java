package com.quinobo.jack.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.quinobo.jack.database.ConnectionTest;
import com.quinobo.jack.database.DataTable;
import com.quinobo.jack.database.SqlCreater;
import com.quinobo.jack.util.Constants;
import com.quinobo.jack.vo.NpcEntity;

@Service
public class BoardService implements Constants {

	private SqlCreater sc = new SqlCreater();
	private ConnectionTest connect = new ConnectionTest();

	// 총 게시물수
	public int selectBoardListCnt(String keyword, String tableFlag) {
		int num = 0;
		String sql;
		Map<String, String> map;
		switch (tableFlag) {
		case TABLE_NPC:
			map = new HashMap<String, String>();
			map.put("select", "count(*)");
			if (keyword != null) {
				map.put("keyword", keyword);
			}
			map.put("tableFlag", tableFlag);
			sql = sc.sqlSelectCreate(map);
			num = connect.excuteSingleQuery(sql);
			break;
		case TABLE_LOG:
			map = new HashMap<String, String>();
			map.put("select", "count(*)");
			if (keyword != null) {
				map.put("keyword", keyword);
			}
			map.put("tableFlag", tableFlag);
			sql = sc.sqlSelectCreate(map);
			num = connect.excuteSingleQuery(sql);
			break;

		default:
			break;
		}
		return num;
	}

	public List<NpcEntity> listNPC(int start, String keyword) {
		List<NpcEntity> result = new ArrayList<NpcEntity>();
		String sql = sc.sqlSelectCreate(start, keyword, TABLE_NPC);
		DataTable dataTable = connect.excuteMultipleQuery(sql);
		List<String[]> records = dataTable.getRecords();
		for (int i = 0; i < records.size(); i++) {
			NpcEntity npcEntity = new NpcEntity();
			npcEntity.setNpno((records.get(i)[0]));
			npcEntity.setName(records.get(i)[1]);
			String writer = "";
			for (int j = 0; j < ADMINACCOUNT.length; j++) {
				if (ADMINACCOUNT[j][0].equals(records.get(i)[2])) {
					writer = ADMINACCOUNT[j][1];
				}
			}
			npcEntity.setWriter(writer);
			npcEntity.setWdate((records.get(i)[3]));
			result.add(npcEntity);
		}
		return result;
	}

	public boolean insertNpc(Map<String, String> map) {
		String sql = sc.sqlInsertCreate(map, TABLE_NPC);
		int i = connect.executeUpdate(sql);
		return isOk(i);
	}

	public boolean updateNPC(Map<String, String> map, String npno) {
		String sql = sc.sqlUpdateCreate(map, npno, TABLE_NPC);
		int i = connect.executeUpdate(sql);
		return isOk(i);
	}

	public NpcEntity selectNpcDetail(String npno) {
		NpcEntity result = new NpcEntity();
		String sql = sc.sqlSelectDetailCreate(npno, TABLE_NPC);
		DataTable dataTable = connect.excuteMultipleQuery(sql);
		List<String[]> records = dataTable.getRecords();
		result.setNpno((records.get(0)[0]));
		result.setName(records.get(0)[1]);
		result.setPic_Link(records.get(0)[2]);
		result.setMemo(records.get(0)[3]);
		String writer = "";
		for (int j = 0; j < ADMINACCOUNT.length; j++) {
			if (ADMINACCOUNT[j][0].equals(records.get(0)[4])) {
				writer = ADMINACCOUNT[j][1];
			}
		}
		result.setWriter(writer);
		result.setWdate((records.get(0)[5]));
		return result;
	}

	public Map<String, String> selectLogDetail(String brno, int curPage, String pageFlag) {
		Map<String, String> result = new HashMap<String, String>();
		String sql = null;
		if (pageFlag != null) { //이전글 혹은 다음글을 눌러서 들어온 경우
			sql = sc.sqlLogBeforeAfterCreate(curPage, TABLE_LOG, pageFlag);
		} else { //
			sql = sc.sqlSelectDetailCreate(brno, TABLE_LOG);
		}
		
		DataTable dataTable = connect.excuteMultipleQuery(sql);
		List<String[]> records = dataTable.getRecords();
		result.put("BDNO", (records.get(0)[0]));
		result.put("WCHAR", records.get(0)[1]);
		result.put("CONTENTS", records.get(0)[2]);
		result.put("WDATE", records.get(0)[3]);
		result.put("OWNO", (records.get(0)[4]));
		if(pageFlag != null) {
			double temp = Double.parseDouble((records.get(0)[5]));
			int temp2 = (int) Math.round(temp);
			result.put("CURPAGE", Integer.toString(temp2));
		} else {
			result.put("CURPAGE", "1");
		}
		return result;
	}

	public boolean deleteNPC(String npno) {
		String sql = sc.sqlDeleteCreate(npno, TABLE_NPC);
		int i = connect.executeUpdate(sql);
		return isOk(i);
	}

	public boolean insertLog(Map<String, String> map) {
		String sql = sc.sqlInsertCreate(map, TABLE_LOG);
		int i = connect.executeUpdate(sql);
		return isOk(i);
	}

	public boolean insertLogPic(Map<String, String> map) {
		String sql = sc.sqlInsertCreate(map, TABLE_LOGPIC);
		int i = connect.executeUpdate(sql);
		return isOk(i);
	}

	/**
	 * int로 변형될 수 있는지 확인
	 * @param seqString 변형을 시도할 파라미터
	 * @return 변형 가능 여부
	 */
	public boolean isInteger(String seqString) {
		boolean isInteger = false;
		try {
			Integer.parseInt(seqString);
			isInteger = true;
		} catch (Exception e) {
			isInteger = false;
		}
		return isInteger;
	}

	/**
	 * 삭제, 입력, 갱신의 작업에서, 작업이 성공적으로 이루어졌는지 판단하는 파라미터
	 * @param i execute후 반환되는 int값
	 * @return 성공 유무 boolean값
	 */
	private boolean isOk(int i) {
		if (i != 0) {
			return true;
		}
		return false;
	}
}
