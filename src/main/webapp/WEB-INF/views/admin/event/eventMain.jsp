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
	    <h1>이벤트 관리</h1>
		<p>새롭게 이벤트를 생성하고 싶은 경우에는, 이벤트명만을 입력한 후 생성을 눌러 새로운 이벤트를 생성한 이후 진행할 수 있습니다. 이벤트 생성 이후 수정하기 기능을 이용해 이벤트 상세 정보를 설정할 수 있습니다.이벤트 수정을 희망할 경우에는, 아래쪽에 있는 이벤트 목록에서 하나를 골라 클릭하면 수정창으로 넘어갑니다. </p>
					
<table cellspacing='0' style="width:100%;"> <!-- cellspacing='0' is important, must stay -->
	<thead>
		<tr>
			<th style="width:80%">이벤트명</th>
			<th style="width:10%">작성일자</th>
			<th style="width:10%">작성자</th>
		</tr>
	</thead><!-- Table Header -->

	<tbody>
	<c:forEach items="${eventList}" var="event" varStatus="i">
			<tr>
			<td>Create pretty table design</td>
			<td>100%</td>
			<td>Yes</td>
		</tr><!-- Table Row -->
	</c:forEach>

		<tr>
			<td>Take the dog for a walk</td>
			<td>100%</td>
			<td>Yes</td>
		</tr><!-- Darker Table Row -->

		<tr>
			<td>Waste half the day on Twitter</td>
			<td>20%</td>
			<td>No</td>
		</tr>

		<tr>
			<td>Feel inferior after viewing Dribble</td>
			<td>80%</td>
			<td>No</td>
		</tr>

		<tr>
			<td>Wince at "to do" list</td>
			<td>100%</td>
			<td>Yes</td>
		</tr>

		<tr>
			<td>Vow to complete personal project</td>
			<td>23%</td>
			<td>yes</td>
		</tr>

		<tr>
			<td>Procrastinate</td>
			<td>80%</td>
			<td>No</td>
		</tr>

		<tr>
			<td><a href="#yep-iit-doesnt-exist">Hyperlink Example</a></td>
			<td>80%</td>
			<td><a href="#inexistent-id">Another</a></td>
		</tr>
	</tbody>
</table>
		

	</section>

</main>

</body>
</html>