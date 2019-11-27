<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="kr">
	<head>
	<meta charset="UTF-8">
	<title>커뮤 총괄 페이지</title>
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/adminMain.css"/>"/>
</head>
<body>
<input type="checkbox" id="menu_state" checked>
<nav>
	<label for="menu_state"><i class="fa"></i></label>
	<ul>
		<li >
			<a href="/unyonyazal/admin" id="goHome">
				<i class="fa fa-home"></i>
				<span>홈화면으로</span>
			</a>
		</li>
		<li data-content="${char_num}" <c:if test="${char_num != null}">class="active unread"</c:if>>
			<a href="/unyonyazal/char_Admin" id="goChar_Admin">
				<i class="fa fa-user"></i>
				<span>캐릭터</span>
			</a>
		</li>
		<li>
			<a href="/unyonyazal/event_Admin" id="goEvent_Admin">
				<i class="fa fa-pencil"></i>
				<span>이벤트</span>
			</a>
		</li>
		<li>
			<a href="javascript:void(0)" id="goNotice_Admin">
				<i class="fa fa-paper-plane"></i>
				<span>공지사항(미구현)</span>
			</a>
		</li>
		<li>
			<a href="/unyonyazal/npc_Admin">
				<i class="fa fa-book"></i>
				<span>NPC관리</span>
			</a>
		</li>
		<li>
			<a href="javascript:void(0)">
				<i class="fa fa-trash"></i>
				<span>임시</span>
			</a>
		</li>
	</ul>
</nav>
<main>
	<header></header>
	<section id="home">
	    <h1>로그 페이지</h1>
		<p>로그는 가장 최신 로그 1개를 보여주고, 그 외에는 선택지로 넘어갈 수 있게 함</p>	
		<p>Nullam sed metus a dui auctor sodales quis nec tellus. Duis porttitor tortor pulvinar auctor mattis. Sed ultrices urna in augue venenatis tempus. Proin fringilla sodales eros. Ut aliquet odio nec sagittis dictum. Cras ullamcorper, neque ac imperdiet hendrerit, sapien nunc porta sapien, quis maximus nulla purus sit amet lectus. Mauris rhoncus lectus non vehicula lacinia. Suspendisse eu mollis ex, sit amet ultrices lorem. Cras elit risus, bibendum ut massa nec, commodo commodo augue.</p>
			
		<table class="tbl_type" border="1" cellspacing="0" style="width:100%;">
		<colgroup>
		<col width="30%">
		<col width="20%">
		<col width="20%">
		<col width="30%">
		</colgroup>
	<thead>
		<tr>
		<th colspan="4" rowspan="2">로그</th>
		</tr>
	</thead><!-- Table Header -->

	<tbody>
		<tr class= "log">
			<td class="ranking" colspan="2" >${log.WCHAR}</td>
			<td colspan="2">${log.WDATE}</td>
		</tr>


		<tr>
		<td  colspan ="4">			${log.CONTENTS}
		</td>
		</tr>
<!-- 덧글 -->
	<tr>
	<td colspan="4">
	 덧글란입니다. 형식을 지정해주세요.
	</td>
	</tr>

		
	</tbody>
	<tfoot>
	<tr>
		<td colspan ="4">
		  <!-- **처음페이지로 이동 : 현재 페이지가 1보다 크면  [처음]하이퍼링크를 화면에 출력-->
<%--                 <c:if test="${curPage > 1}"> --%>
<!--                     <a href="javascript:list('1')">[처음]</a> -->
<%--                 </c:if> --%>
                <c:if test = "${curPage != 1}">
                <a href="javascript:list('before')">[이전]</a>&nbsp;
                </c:if>
                <c:if test = "${curPage != count}">
                <a href="javascript:list('after')">[다음]</a>&nbsp;
                </c:if>
<%--                 <c:if test="${curPage < count}"> --%>
<%--                     <a href="javascript:list('${count}')">[끝]</a> --%>
<%--                 </c:if> --%>
		</td>
	</tr>
	<tr>
	 <td colspan="4" style="padding: 10px;">
	 <form id="logForm" method="get">
		<input type="hidden" id="pageFlag" name="pageFlag">
		<input type="hidden" id="curPage" name ="curPage" value ="${curPage}">
		<input type="hidden" id="bdno" name="bdno" value ="${log.BDNO}">

<!-- 	 	<input type="text" id = "searchword" name="searchword" placeholder="키워드 검색" style="height: 20px;">
	 	<button class="button darkGrey" type="button" id="searchNPC">검색</button>
 -->
		이동 방식/검색 방식을 결정해주세요. 
 		<c:if test="${sessionScope.owno != null}">
		<button class="button darkGrey" id="newLog">로그 작성</button>
	 	</c:if>
	 </form>
	 </td>
	</tr>
	</tfoot>
</table>
</section>

</main>
<script>

var isOk = ${not empty sessionScope.owno};

// **원하는 페이지로 이동시 검색조건, 키워드 값을 유지하기 위해 
  function list(pageFlag){
	 document.getElementById("logForm").action = "/log";
	 document.getElementById("pageFlag").value = pageFlag;
	 document.getElementById("logForm").submit();
  }
 if(isOk){
	 document.getElementById("newLog").onclick = function() {
		 document.getElementById("logForm").action = "/logWrite";
		 document.getElementById("logForm").submit();
	 }
 }

//  document.getElementById("searchNPC").onclick = function() {
// 	 document.getElementById("npcForm").action = "/unyonyazal/searchNpc";
// 	 document.getElementById("npcForm").submit();
// }

 
</script>

</body>
</html>