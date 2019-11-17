package com.quinobo.jack.database;

import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.quinobo.jack.util.Constants;

@Service
public class SqlCreater implements Constants {

	public SqlCreater() {
		super();
	}

	/**
	 * insertSql 작성
	 * @param map 데이터 맵
	 * @param table insert할 테이블
	 * @return sql
	 */
	public String sqlInsertCreate(Map<String, String> map, String table) {
		String sql = null;
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ");
		switch (table) {
		case TABLE_NPC:
			sb.append(TABLE_NPC);
			sb.append(" ");
			sb.append("( ");

			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {
				sb.append(it.next());
				sb.append(", ");
			}
			sb.append("WDATE ");
			sb.append(") ");
			sb.append("values ");
			sb.append("( ");

			Iterator<String> it2 = map.keySet().iterator();
			while (it2.hasNext()) {
				sb.append("'");
				sb.append(map.get(it2.next()));
				sb.append("' ");
				sb.append(", ");
			}
			sb.append("now() ");
			sb.append(") ");

			break;

		default:
			break;
		}
		sql = sb.toString();
		return sql;
	}

	/**
	 * getFullData from primary Key
	 * @param orNo : primary Key
	 * @param tableFlag : 추출 테이블
	 * @return sql
	 */
	public String sqlSelectDetailCreate(String orNo, String tableFlag) {
		String sql = null;
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ");
		switch (tableFlag) {
		case TABLE_NPC:
			sb.append("NPNO, ");
			sb.append("NAME, ");
			sb.append("PIC_LINK, ");
			sb.append("MEMO, ");
			sb.append("WRITER, ");
			sb.append("DATE_FORMAT(WDATE, '%Y-%m-%d') ");
			sb.append("FROM ");
			sb.append(tableFlag);
			sb.append(" WHERE ");
			sb.append("NPNO = ");
			break;

		default:
		}
		sb.append(orNo);

		sql = sb.toString();
		return sql;
	}

	/**
	 * select Sql 작성
	 * @param map 데이터 맵, 조건들이 들어있다.
	 * @return sql
	 */
	public String sqlSelectCreate(Map<String, String> map) {
		String sql = null;
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ");
		switch (map.get("tableFlag")) {
		case TABLE_NPC:
			sb.append(map.get("select"));
			sb.append(" FROM ");
			sb.append(map.get("tableFlag"));
			if (map.get("keyword") != null) {
				sb.append(" WHERE NAME Like ");
				sb.append("'%");
				sb.append(map.get("keyword"));
				sb.append("%' ");
			}
			break;

		default:
			break;
		}
		sql = sb.toString();
		System.out.println(sql);
		return sql;
	}
	
	/**
	 * select Sql 작성
	 * @param start : 처음으로 검색할 레코드
	 * @param keyword : 검색 키워드, 공란 가능
	 * @param tableFlag : 검색할 테이블
	 * @return sql
	 */
	public String sqlSelectCreate(int start, String keyword, String tableFlag) {
		String sql = null;
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ");
		switch (tableFlag) {
		case TABLE_NPC:
			sb.append("NPNO, ");
			sb.append("NAME, ");
			sb.append("WRITER, ");
			sb.append("DATE_FORMAT(WDATE, '%Y-%m-%d') ");
			sb.append("FROM ");
			sb.append(tableFlag);
			if (keyword != null) {
				sb.append(" WHERE ");
				sb.append("NAME Like '%");
				sb.append(keyword);
				sb.append("%' ");
			}
			sb.append(" ORDER BY ");
			sb.append("NPNO ");
			sb.append("DESC ");
			sb.append("LIMIT ");
			sb.append(start);
			sb.append(", 10 ");
			break;

		default:
		}
		sql = sb.toString();
		return sql;
	}

	public String sqlUpdateCreate(Map<String, String> map, String orno, String tableFlag) {
		String sql = null;
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE ");
		sb.append(tableFlag);
		sb.append(" SET ");
		switch (tableFlag) {
		case TABLE_NPC:
			Iterator<String> ir = map.keySet().iterator();
			boolean haveNext = true;
			while (haveNext) {
				String key = ir.next();
				sb.append(key);
				sb.append(" = ");
				sb.append("'");
				sb.append(map.get(key));
				sb.append("'");
				if (haveNext = ir.hasNext()) {
					sb.append(",");
				}
			}
			sb.substring(0, sb.length() - 2);
			sb.append(" WHERE ");
			sb.append("NPNO = '");
			sb.append(orno);
			sb.append("'");
			break;

		default:
		}
		sql = sb.toString();
		return sql;
	}

	public String sqlDeleteCreate(String npno, String tableFlag) {
		String sql = null;
		StringBuffer sb = new StringBuffer();
		sb.append("DELETE ");
		sb.append("FROM ");
		sb.append(tableFlag);
		sb.append("WHERE ");
		switch (tableFlag) {
		case TABLE_NPC:
			sb.append("NPNO = '");
			sb.append(npno);
			sb.append("'");
			break;

		default:
		}
		sql = sb.toString();
		System.out.println(sql);
		return sql;
	}
}
