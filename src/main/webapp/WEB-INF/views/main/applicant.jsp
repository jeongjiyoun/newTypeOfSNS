<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/adminMain.css"/>"/>
<meta charset="UTF-8">
<style type="text/css">

td input[type=text]{
	width: 92%; 
	height:50px; 
	border: 1px solid #dee2e6; 
	padding-left : 10px; 
	font-size: 1.2rem;
	padding-right: 7px;
}

</style>
<title>Index Page</title>
</head>
<body>

	<header class="header small">
	헤더부분입니다.
	</header>

	<div class="contents-main" style="margin-top: 10%;">
		<h1>신청서 작성</h1>
		<p style="font-size: 20px; color : red;">신청서 작성페이지</p>
		<br>
		<div class="table-responsive"  style="width: 80%;">
			<form method="post" enctype="multipart/form-data" id="applicantForm">
				<table class="table table-striped" style="border : 1px solid #dee2e6; "> 
				<colgroup>
					<col width="10%;">
					<col width="30%;">
					<col width="10%;">
					<col width="30%;">
				</colgroup>
					<thead class="thead">
						<tr id="table-title"></tr>
					</thead>
					<tbody id="context">
						<tr>
							<td>캐릭터 이름</td>
							<td>
								<input type="text" name="chName" value="${Applicant.name}" placeholder="캐릭터의 이름을 입력해주세요">
							</td>
							<td style="width: 20%;" rowspan="7" colspan="2">
								<img id="faceInfo" src="${Character.pic_name}">
								<div class="filebox" style="margin-top: 10px; ">
								   <input type="file" id="pic_name" name = "pic_link" style="width:80%; height: 80%;"
								    src="Character.pic_link">
								</div>
							</td>
						</tr>
						<tr>
							<td>종족</td>
							<td>
								<label><input type="radio" name="spices" value="1"> 인간 </label>
								<label><input type="radio" name="spices" value="2"> 용족 </label>
							</td>
						</tr>
						<tr>
							<td>나이</td>
							<td style="padding-top: 0px; padding-bottom: 0px;">
								<input type="text" name="age" value="${Applicant.age}" placeholder="나이를 입력하세요.">
							</td>
						</tr>
						<tr>
							<td style="width: 120px;">출신 국가</td>
							<td colspan="3">
								<label><input type="radio" name="country" value="1"> 마을1 </label>
								<label><input type="radio" name="country" value="2"> 마을2 </label>
								<label><input type="radio" name="country" value="3"> 마을3 </label>
								<label><input type="radio" name="country" value="4"> 마을4 </label>
						</tr>

						<tr>
							<td>키</td>
							<td>
								<input type="text" name="height" value="${Character.height}" placeholder="키를 입력하세요.(텍스트서술가능)">
						</tr>
						<tr>
							<td>몸무게</td>
							<td>
								<input type="text" name="weight" value="${Character.weight}" placeholder="몸무게를 입력하세요.(텍스트서술가능)">
							</td>
						</tr>
						<tr>
							<td>트위터 계정</td>
							<td>
								<input type="text" name="twtId" value="${Applicant.twtId}" placeholder="트위터 계정을 입력하세요. 신청용 계정 등, 미사용 계정은 불가합니다">
							</td>
						</tr>
					</tbody>
				</table>

				<table>
					<tbody>
						<tr>
							<td colspan = "4"> 성격 </td>
						</tr>
					<c:forEach begin="1" end="5" step="1" var="cha" items="${detailCh}">
						<tr>
							<td colspan = "4"></td>
						</tr>
						<tr>
							<td colspan = "4">
								<input type="text" name="detailCh" value="${cha.keyword}" placeholder="키워드로 서술해주세요.">
							</td>
						</tr>
						<tr>
							<td colspan = "4">
							<textarea cols="1" rows="1" style="width: 100%; height: 100%; min-height: 30px;">${cha.detail}</textarea>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>

				<table>
					<tbody>
							<tr>
								<td colspan = "4"> 외형묘사</td>
							</tr>
						<c:forEach begin="1" end="5" step="1" var="app" items="${detailAp}">
							<tr>
								<td colspan = "4"></td>
							</tr>
							<tr>
								<td colspan = "4">
									<input type="text" name="detailCh" value="${app.keyword}" placeholder="키워드로 서술해주세요.">
								</td>
							</tr>
							<tr>
								<td colspan = "4">
								<textarea cols="1" rows="1" style="width: 100%; height: 100%; min-height: 30px;">${app.detail}</textarea>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				<table>
				<tbody>
					<tr>
						<td>ISBN</td>
						<td>
							<input type="text" name="twtId" value="${Applicant.isbn}" placeholder="ISBN 입력">
						</td>
					</tr>
					<tr>
						<td colspan="4">동의사항</td>
					</tr>
					<tr>
						<td colspan="4"><label>본 커뮤는 성인커뮤이며, 연령제한 미준수와 관련해 발생하는 모든 책임은 허위 인증을 한 러너에게 있습니다. <input type="checkbox"></label></td>
					</tr>
					<tr>                                               
						<td colspan="4"><label>사상 대립에서 발생하는 차별성 발언을 허용하며, 이로 인한 불쾌감이 발생할 수 있습니다.<input type="checkbox"></label></td>
					</tr>
					<tr>                                               
						<td colspan="4"><label>본 커뮤는 사상 대립이 포함되어 있으며, 이에 캐와 오너간의 분리를 엄격하게 요구합니다. <input type="checkbox"></label></td>
					</tr>
					<tr>                                               
						<td colspan="4"><label>러닝 중에는 공지사항을 준수하며, 주의사항을 숙지함을 원칙으로 합니다.<input type="checkbox"></label></td>
					</tr>
					<tr>                                               
						<td colspan="4"><label>해킹 및 악성프로그램의 유포에 대하여는 대한민국 법에 의해 처벌받을 수 있습니다.<input type="checkbox"></label></td>
					</tr>
					<tr>
						<td>캐릭터 성향</td>
						<td colspan="3">
						<input id="applicantMajor" style="width: 80%; height:50px; border: 1px solid #dee2e6; padding-left : 10px;" placeholder="우측의 버튼을 눌러 성향을 입력하세요." disabled="disabled" value="${result}">
							<input type="hidden" id="majorSeq" value="${Applicant.majorSeq}">
							<button class="bStyle" id="goCheckMajor" type="button" style="border-radius: 3px;">성향 검색</button></td>
					</tr>
					</tbody>
				</table>
			</form>
			최종적으로 제출한 신청서만 집계됩니다.
			<c:if test="${Applicant==null}">
			<button class="bStyle" id="tSubmit1">일시보존</button>
			</c:if>
			<c:if test="${Applicant!=null}">
			<button class="bStyle" id="tSubmit2">최종제출</button>
			</c:if>
		</div>

	</div>

</body>
</html>