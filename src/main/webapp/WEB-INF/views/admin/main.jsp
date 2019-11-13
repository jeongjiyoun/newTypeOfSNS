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
	    <h1>${adminName}님 어서오세요.</h1>
		<p>좌측 메뉴를 이용해서 이동하세요.</p>

		<p>Nullam sed metus a dui auctor sodales quis nec tellus. Duis porttitor tortor pulvinar auctor mattis. Sed ultrices urna in augue venenatis tempus. Proin fringilla sodales eros. Ut aliquet odio nec sagittis dictum. Cras ullamcorper, neque ac imperdiet hendrerit, sapien nunc porta sapien, quis maximus nulla purus sit amet lectus. Mauris rhoncus lectus non vehicula lacinia. Suspendisse eu mollis ex, sit amet ultrices lorem. Cras elit risus, bibendum ut massa nec, commodo commodo augue.</p>

		<p>Sed lacinia, ligula id venenatis auctor, libero turpis aliquet nunc, sit amet ullamcorper dolor ligula quis felis. Vivamus condimentum mi vel felis vehicula, eu placerat lacus semper. Sed quis lacinia mauris. Donec aliquam vulputate metus, non imperdiet lorem maximus a. Integer eget dignissim erat. Proin id finibus dui, pretium consectetur turpis. Vivamus in tincidunt odio, eu iaculis nisi. Integer in scelerisque mauris. Vivamus ac eros congue, mattis nisl ac, venenatis lacus.</p>

		<p>Nunc viverra vestibulum tempor. Nulla consectetur sit amet mauris at rutrum. Mauris eu rhoncus eros. Integer convallis magna ac tincidunt laoreet. Proin molestie vitae erat id venenatis. Donec eu imperdiet risus. Fusce gravida placerat dui eget sollicitudin. Cras leo ligula, laoreet dapibus euismod ut, vehicula sit amet nunc. Maecenas in nisl fringilla, aliquet diam ut, facilisis ex. In dui risus, porttitor convallis ultricies nec, ornare eu leo.</p>
	</section>

</main>

</body>
</html>