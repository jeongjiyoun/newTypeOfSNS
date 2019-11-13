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
	    <h1>NPC 관리</h1>
		<p>NPC를 추가할 수 있습니다. NPC는 신규등록 버튼을 눌러 생성할 수 있습니다. NPC 이름을 눌러, 편집을 하거나 정보를 열람할 수 있습니다. NPC를 추가해야만이 다음 단계인 이벤트 대사를 입력할 수 있기 때문에 이쪽부터 진행해주세요. (나레이션 제외)</p>
					
<table id ="npcTable" class="tbl_type" border="1" cellspacing="0" summary="npc정보입니다." style="width:100%;">
<colgroup>
<col>
<col width="70%">
<col width="15%">
<col width="10%">
</colgroup>

	<thead>
		<tr>
		<th scope="col">No</th>
		<th scope="col">NPC명</th>
		<th scope="col">작성일자</th>
		<th scope="col">작성자</th>
		</tr>
	</thead><!-- Table Header -->

	<tbody>
	<c:forEach items="${npcList}" var="npc" varStatus="i">
		<tr class= "npc" npno="${npc.npno}">
			<td class="ranking">${npc.npno}</td>
			<td>${npc.name}</td>
			<td>${npc.wdate}</td>
			<td>${npc.writer}</td>
		</tr>
	</c:forEach>

	</tbody>
	<tfoot>
	<tr>
		<td colspan="4">
		  <!-- **처음페이지로 이동 : 현재 페이지가 1보다 크면  [처음]하이퍼링크를 화면에 출력-->
                <c:if test="${pageMaker.curBlock > 1}">
                    <a href="javascript:list('1')">[처음]</a>
                </c:if>
                
                <!-- **이전페이지 블록으로 이동 : 현재 페이지 블럭이 1보다 크면 [이전]하이퍼링크를 화면에 출력 -->
                <c:if test="${pageMaker.curBlock > 1}">
                    <a href="javascript:list('${pageMaker.prevPage}')">[이전]</a>
                </c:if>
                
		 <c:forEach var="num" begin="${pageMaker.blockBegin}" end="${pageMaker.blockEnd}">
		 <c:choose>
                        <c:when test="${num == pageMaker.curPage}">
                            <span style="color: red">${num}</span>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:list('${num}')">${num}</a>&nbsp;
                        </c:otherwise>
                    </c:choose>
		 </c:forEach>
		  <!-- **다음페이지 블록으로 이동 : 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 [다음]하이퍼링크를 화면에 출력 -->
                <c:if test="${pageMaker.curBlock <= pageMaker.totBlock}">
                    <a href="javascript:list('${pageMaker.nextPage}')">[다음]</a>
                </c:if>
                <!-- **끝페이지로 이동 : 현재 페이지가 전체 페이지보다 작거나 같으면 [끝]하이퍼링크를 화면에 출력 -->
                <c:if test="${pageMaker.curPage <= pageMaker.totPage}">
                    <a href="javascript:list('${pageMaker.totPage}')">[끝]</a>
                </c:if>
		</td>
	</tr>
	<tr>
	 <td colspan="4" style="padding: 10px;">
	 <form id="npcForm" method="post">
	 	<input type="text" name="keyword" placeholder="NPC이름" style="height: 20px;">
	 	<input type="hidden" id="extra" name="extra">
		<input type="hidden" id="curPage" name="curPage">
	 	<button class="button darkGrey" id="searchNPC">검색</button>
		<c:if test="${sessionScope.adminId != null}">
		<button class="button darkGrey" id="newNPC">신규등록</button>
	 	</c:if>
	 </form>
	 </td>
	</tr>
	</tfoot>
</table>
</section>

</main>

<script src="https://code.jquery.com/jquery-1.12.4.js" integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU=" crossorigin="anonymous"></script>
<script>

$(document).ready(function(){
	   $('table tbody tr').mouseover(function(){
	      $(this).css("backgroundColor","#f2f7ff");
	   });
	   $('table tbody tr').mouseout(function(){
	      $(this).css("backgroundColor","#fff");
	   });
	});

 $(".npc").on("click",function(){
	 var td = $(this).children();
	 var npno = td.eq(0).text();
	 document.getElementById("npcForm").action = "/unyonyazal/edit_npc";
	 document.getElementById("extra").value = npno;
	 document.getElementById("npcForm").submit();
 })

// **원하는 페이지로 이동시 검색조건, 키워드 값을 유지하기 위해 
  function list(page){
	 document.getElementById("npcForm").action = "/unyonyazal/npc_Admin";
	 document.getElementById("curPage").value = page;
	 document.getElementById("npcForm").submit();
  }

 document.getElementById("newNPC").onclick = function() {
	 document.getElementById("npcForm").action = "/unyonyazal/new_npc";
	 document.getElementById("npcForm").submit();
 }
 document.getElementById("searchNPC").onclick = function() {
	 document.getElementById("npcForm").action = "/unyonyazal/npc_Admin";
	 document.getElementById("npcForm").submit();
 }

 var eventTarget = document.querySelector('.npc');

 eventTarget.onclick = function(){
	
}

 
</script>
</body>
</html>