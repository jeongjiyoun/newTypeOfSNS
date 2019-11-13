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

	// 총 게시물수
	public int selectBoardListCnt(String keyword, String tableFlag) {
		int num = 0;
		switch (tableFlag) {
		case TABLE_NPC:
			Map<String, String> map = new HashMap<String, String>();
			map.put("select", "count(*)");
			map.put("keyword", keyword);
			map.put("tableFlag", tableFlag);
			String sql = sc.sqlSelectCreate(map);
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
		List<String[]>records = dataTable.getRecords();
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

	public boolean insertNpc(Map<String, String> map, String tableNpc) {
		String sql = sc.sqlInsertCreate(map, TABLE_NPC);
		ConnectionTest connect = new ConnectionTest();
		int i = connect.executeUpdate(sql);
		connect.connectCommit();
		return isOk(i);
	}

	private boolean isOk(int i) {
		if (i != 0) { 
			return true;
		}
		return false;
	}

	public NpcEntity selectNpcDetail(String npno, String tableFlag) {
		NpcEntity result = new NpcEntity();
		String sql = sc.sqlSelectDetailCreate(npno, TABLE_NPC);
		DataTable dataTable = connect.excuteMultipleQuery(sql);
		List<String[]>records = dataTable.getRecords();
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
}
