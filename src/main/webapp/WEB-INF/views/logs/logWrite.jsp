<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="kr">
	<head>
	<meta charset="UTF-8">
	<title>커뮤 로그 작성 페이지(디자인 미적용)</title>
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<c:url value="${pagecontext.request.contextpath}/resources/css/adminMain.css"/>"/>
<script type="text/javascript" src="<c:url value="${pagecontext.request.contextpath}/resources/ckeditor/ckeditor.js"/>"></script>
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
	    <h1>로그 작성</h1>
		<p>로그 작성시에는 다음의 사항을 지켜주세요.<br>
		1. 첨부 파일 사이즈는 3MB를 넘지 않게 해주세요.<br>
		2. 첨부 파일 확장자는 PNG형식(.png)을 권장하며, jpg(jepg포함), bmp, gif를 지원합니다.
		</p>

<!-- 
		<p>Nunc viverra vestibulum tempor. Nulla consectetur sit amet mauris at rutrum. Mauris eu rhoncus eros. Integer convallis magna ac tincidunt laoreet. Proin molestie vitae erat id venenatis. Donec eu imperdiet risus. Fusce gravida placerat dui eget sollicitudin. Cras leo ligula, laoreet dapibus euismod ut, vehicula sit amet nunc. Maecenas in nisl fringilla, aliquet diam ut, facilisis ex. In dui risus, porttitor convallis ultricies nec, ornare eu leo.</p>
 -->

		 <form action="writeLog" onsubmit="return check()" method="post">
            <textarea name="editor1" id="editor1" rows="10" cols="80">
                
            </textarea>
            
            <button class = "button darkGrey rightF" type="submit">작성</button>
        </form>

		<p style="color: white">Sed lacinia, ligula id venenatis auctor, libero turpis aliquet nunc, sit amet ullamcorper dolor ligula quis felis. Vivamus condimentum mi vel felis vehicula, eu placerat lacus semper. Sed quis lacinia mauris. Donec aliquam vulputate metus, non imperdiet lorem maximus a. Integer eget dignissim erat. Proin id finibus dui, pretium consectetur turpis. Vivamus in tincidunt odio, eu iaculis nisi. Integer in scelerisque mauris. Vivamus ac eros congue, mattis nisl ac, venenatis lacus.</p>

	</section>

</main>
<script>

 window.onload = function(){
      ck = CKEDITOR.replace("editor1");
 };

 function check(){
	 var text = CKEDITOR.instances["editor1"].getData();
	 if(text.length < 10){
		 alert("로그는 10자 미만으로 작성할 수 없습니다.");
		 return false;
     }
	 return true;
 }

 </script>
</body>
</html>