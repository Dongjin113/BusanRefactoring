<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="../include/top.jsp" %>
    
<style>

</style>


<section>
<div align=center>
<br><br>

<table>
<tr><td width="150" align=center colspan=4>
<c:if test="${matjib.matJibId le 150}">
<img src="${matjib.matJibImgAddr}" alt="" title="이미지2" width=700 height=500 >
</c:if>
<c:if test="${matjib.matJibId gt 150}">
<img src="<%=path %>/FoodIMG/${matjib.matJibImgAddr}" alt="" title="이미지2" width=700 height=500 >
</c:if>
</td></tr>
</table>

<form>
<input type=hidden name=SEQ id=SEQ value=${matjib.matJibId }>
<table border=1>
<tr height=30><td width="120" align=center>음식점 이름</td><td colspan=3 width=200>${matjib.matJibMain_title }</td></tr>
<tr height=30><td width="120" align=center>음식점 전화번호</td><td colspan=3 width=200>${matjib.matJibTel }</td></tr>
<tr height=120><td width="120" align=center>음식점 소개글</td><td colspan=3 width=700>
${matjib.matJibIntroduce }</td></tr>
<tr height=30><td width="120" align=center>영업시간</td><td colspan=3 width=200>${matjib.matJibBusiness_hours }</td></tr>

<tr height=30>
<td width="120" align=center >지역</td><td align=center width=100>${matjib.matJibWard }</td>

<td width="120" align=center >주소</td><td colspan=3>${matjib.matJibAddr }</td>
</tr>

<tr height=30><td width="120" align=center>대표메뉴</td><td colspan=3>${matjib.matJibMenu }</td></tr>
<tr height=30><td colspan=4 align=center>


<input type=button value="목록으로" onClick="matjibList()">

</td>
</tr>
</table>
</form>
</div>
<br>
</section>

<script>
var path = '${pageContext.request.contextPath}';

function matjibList(){
	location.href=path+"/matjib.do";
}


</script>


<%@ include file="../include/bottom.jsp" %>
