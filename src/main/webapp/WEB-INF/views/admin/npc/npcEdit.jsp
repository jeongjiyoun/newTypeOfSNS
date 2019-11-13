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
	    <h1>NPC 등록</h1>
		<p>NPC를 등록할 수 있습니다. 여기서 등록된 NPC는 직전 화면의 NPC목록에서 확인할 수 있습니다. 주의사항 : NPC등록시에 <strong>이름은 필수 항목</strong>입니다.</p>

<form id="npcForm" method="post" enctype="multipart/form-data">		
<table class="tbl_type" border="1" cellspacing="0" summary="npc정보입니다." style="width:100%;">
	<colgroup>
		<col width="20%">
		<col width="20%">
		<col width="60%">
	</colgroup>

	<thead>
		<tr>
		<th colspan ="3" scope="col">NPC 신규 등록</th>
		</tr>
	</thead><!-- Table Header -->
	
	<tbody>
		<tr>
			<td rowspan="6" class="ranking" scope="row" style="height: 200px; padding:10px;">
				<div id='View_area' style='width: 180px; height: 200px; min-height:165px; margin-bottom: 5px; color: black; border: 0px solid black; dispaly: inline; '>
				<img src="${uploadPath}${npcEntity.pic_Link}" id="picture">
				</div>
				<div class="file_input">
					<label> 사진 교체
				        <input type="file" onchange="previewImage(this,'View_area')" name="pic" id="profile_pt" multiple="multiple">
				    </label>
			    </div>
			</td>
			<td rowspan="1"><strong>이름</strong></td>
			<td rowspan="1" ><input type="text" name="name" style="width:96%; height: 25px; padding-top:2px; border: none;" value="${npcEntity.name}"></td>
		</tr>
		<tr>
		</tr>
		<tr>
			<td rowspan="2"><strong>설명</strong></td>
			<td rowspan="2"><textarea name="comment" rows="1" cols="1" style="width: 96%; height: 200px; border: none; overflow:none;">${npcEntity.memo}</textarea></td>
		</tr>
	</tbody>
	<tfoot>
	<tr>
	 <td colspan="3" style="padding: 10px;">
	 	<button class="button darkGrey" id="editNPC">수정</button>
	 	<button class="button darkGrey" id="delNPC">삭제</button>
	 </td>
	</tr>
	</tfoot>
</table>
</form>
</section>
</main>

<script>

 document.getElementById("editNPC").onclick = function() {
	 document.getElementById("npcForm").action = "/unyonyazal/npcEdit";
	 document.getElementById("npcForm").submit();
 }

 function previewImage(targetObj, View_area) {
		var preview = document.getElementById(View_area); //div id
		var ua = window.navigator.userAgent;

		document.getElementById("picture").remove();
	  //ie일때(IE8 이하에서만 작동)
		if (ua.indexOf("MSIE") > -1) {
			targetObj.select();
			try {
				var src = document.selection.createRange().text; // get file full path(IE9, IE10에서 사용 불가)
				var ie_preview_error = document.getElementById("ie_preview_error_" + View_area);


				if (ie_preview_error) {
					preview.removeChild(ie_preview_error); //error가 있으면 delete
				}

				var img = document.getElementById(View_area); //이미지가 뿌려질 곳

				//이미지 로딩, sizingMethod는 div에 맞춰서 사이즈를 자동조절 하는 역할
				img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+src+"', sizingMethod='scale')";
			} catch (e) {
				if (!document.getElementById("ie_preview_error_" + View_area)) {
					var info = document.createElement("<p>");
					info.id = "ie_preview_error_" + View_area;
					info.innerHTML = e.name;
					preview.insertBefore(info, null);
				}
			}
	  //ie가 아닐때(크롬, 사파리, FF)
		} else {
			var files = targetObj.files;
			for ( var i = 0; i < files.length; i++) {
				var file = files[i];
				var imageType = /image.*/; //이미지 파일일경우만.. 뿌려준다.
				if (!file.type.match(imageType))
					continue;
				var prevImg = document.getElementById("prev_" + View_area); //이전에 미리보기가 있다면 삭제
				if (prevImg) {
					preview.removeChild(prevImg);
				}
				var img = document.createElement("img"); 
				img.id = "prev_" + View_area;
				img.classList.add("obj");
				img.file = file;
				img.style.width = '180px'; 
				img.style.height = '200px';
				preview.appendChild(img);
				if (window.FileReader) { // FireFox, Chrome, Opera 확인.
					var reader = new FileReader();
					reader.onloadend = (function(aImg) {
						return function(e) {
							aImg.src = e.target.result;
						};
					})(img);
					reader.readAsDataURL(file);
				} else { // safari is not supported FileReader
					//alert('not supported FileReader');
					if (!document.getElementById("sfr_preview_error_"
							+ View_area)) {
						var info = document.createElement("p");
						info.id = "sfr_preview_error_" + View_area;
						info.innerHTML = "not supported FileReader";
						preview.insertBefore(info, null);
					}
				}
			}
		}
	}
 

</script>
</body>
</html>