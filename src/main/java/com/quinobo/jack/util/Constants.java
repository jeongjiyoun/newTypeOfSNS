package com.quinobo.jack.util;
/*
 * 커뮤에서 사용하는 모든 상수(Constrants)를 모은 인터페이스.
 * 
 */
public interface Constants {
	//관리자계정. 형식 {아이디, 이름}
	//비밀번호는 모두 Wjfejfk(쩔더라) 로 고정.
	final static String ADMINACCOUNT[][] = {{"dlsslazo","주인"},{"cbslazo","츄"},{"dkclslazo","아치"},{"jack","잭"}};

	// 업로드 설정
    final int MEMORY_THRESHOLD   = 1024 * 1024 * 1;  // 1MB
    final int MAX_FILE_SIZE      = 1024 * 1024 * 3; // 최대 파일 크기 3MB
    final int MAX_REQUEST_SIZE   = 1024 * 1024 * 4; // request 전송되는 파일 사이즈 : 파일사이즈 + form data , 4MB
	
	//table
	static final String TABLE_NPC = "NPC";
	static final String TABLE_LOG = "BOARD";
	static final String TABLE_LOGPIC = "BOARD_PIC";


}
