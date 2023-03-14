<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="../include/top.jsp" %>
    
<style>
section{
width:95%;
}
.introduce{
padding:20px;
}
#matjibwrite{
padding-right:60px;
}
a.introduce.div:link, a.introduce.div:visited{
	
	color: black;
	text-decoration: none;
	font-weight: bold;
}
#GoodBad{
text-align:right;
}

</style>

<script>
var path = '${pageContext.request.contextPath}';
	function matjibWrite(){
		
		location.href=path+"/matjibwrite.do";
	}
	
</script>




<section>
<div align=center>

<div class="tag" align=center>
<c:forEach  items="${matJibWard}"  var="wd" varStatus="status">

<c:url  value="matjib.do"  var="url">
<%--    <c:param name="startIdx"  value="1"   />--%>
    <c:param name="searchCondition"  value="ward"   />
    <c:param name="searchKeyword"  value="${wd.ward }"   />
</c:url>

<a style='text-decoration:none' href="${url }" >#${wd.ward } &emsp;</a>

<c:if test="${status.count eq 8}"> <br></c:if>

</c:forEach>
</div>

<br>


<table>
<tr><td colspan=2> <div align=left style="font-size:20px"><b>&emsp;&emsp;맛집소개</b> 

<div align=right>
<form action="<%=path%>/matjib.do"> 

<select name= searchCondition>

<option value="main_title">가게명</option>
<option value="menu">메뉴</option>
<option value="ward">지역</option>
</select>


<input type=text name="searchKeyword" >
<input type=submit value="찾기">
</form>
</div>

</div>
<div style="line-height:20%"><br></div></td></tr>
<c:forEach  items="${matJibBoard.content}"  var="m"  varStatus="status" >
<tr><td colspan=2>
<hr width=1100></td></tr>

<tr>


<td>
<%if(role == null || role.equals("일반회원")){ %>
<c:url  value="matjibdetail.do"  var="url">
<c:param name="matJibNum"  value="${m.matJibId}"   />
<c:param name="page"  value="${nowPage}"   />
<c:param name="searchCondition"  value="${searchCondition}"   />
<c:param name="searchKeyword"  value="${searchKeyWord}"   />
<c:param name="id" value="${id}"/>
</c:url>

<c:if test="${m.matJibId le 150}">
<a href="${url }"><img src="${m.matJibImgAddr}" alt="" title="이미지2" width=300 height=200 align="left"></a>
</c:if>

<c:if test="${m.matJibId gt 150}">
<a href="${url }"><img src="<%=path %>/FoodIMG/${m.matJibImgAddr}" alt="" title="이미지2" width=300 height=200 align="left"></a>
</c:if>

<%}else if(role.equals("관리자")){ %>
<c:url  value="matjibEdit.do"  var="url">
<c:param name="matJibId"  value="${m.matJibId}"   />
</c:url>

<c:if test="${m.matJibId le 150}">
<a href="${url }"><img src="${m.matJibImgAddr}" alt="" title="이미지2" width=300 height=200 align="left"></a>
</c:if>

<c:if test="${m.matJibId gt 150}">
<a href="${url }"><img src="<%=path %>/FoodIMG/${m.matJibImgAddr}" alt="" title="이미지2" width=300 height=200 align="left"></a>
</c:if>

<%}%>
</td>

<td width=800>
<div class=introduce>
<a href="${url }">
<div style="font-size:20px" >
<b>${m.matJibMain_title}</b>
</div>

<div style="line-height:40%"><br>
<div style="font-size:15px">
${m.matJibMenu}
</div>
<br><br>
</div>

${m.matJibIntroduce}  <br>

<div style="line-height:40%"><br></div>
<b>지역 : </b> ${m.matJibWard}&emsp;
<b>주소 : </b>${m.matJibAddr} &emsp;
<b>tel : </b>${m.matJibTel} <br>
<div style="line-height:20%"><br></div>
<b>영업시간 : </b>${m.matJibBusiness_hours}
</a>
<div align=right>
<br>조회수 : ${m.matJibCnt }
</div>
</div>


</div>
</td>

</tr>
</c:forEach>

<tr><td colspan=2 align=right>
<hr width=1100>


<%if(role == null || role.equals("일반회원")){ %>
<%}else if(role.equals("관리자")){ %>
<div id="matjibwrite"><input type="button" value="글쓰기" onClick="matjibWrite()"></div>
<%}%>
</td>
</tr>

</table>

<br>

  <c:url  value="matjib.do"  var="url">
    <c:param name="nowPage"  value="0"   />
      <c:param name="searchCondition"  value="${searchCondition}"   />
      <c:param name="searchKeyword"  value="${searchKeyWord}"   />
  </c:url>
 
 	<c:if test="${nowPage > 0 }">
  	<a href="${url}" >처음으로</a> &emsp;
	</c:if>
	
   	<c:if test="${nowPage <= 0 }">
  	처음으로 &emsp;
	</c:if>
   
   
  <c:if test="${nowPage > 0 }">
  
    <c:url  value="matjib.do"  var="url">
      <c:param name="page"  value="${nowPage-1}"   />
        <c:param name="searchCondition"  value="${searchCondition}"   />
        <c:param name="searchKeyword"  value="${searchKeyWord}"   />
    </c:url>
    <a href="${url}"> 이전 </a> &emsp;
  
  </c:if>
  <c:if test="${nowPage == 0 }">
  이전 &emsp;
  </c:if>

<%--  <c:forEach var="pageNum" begin="1" end="${totalPage }" step="1">--%>
<%--  --%>
<%--  <c:set var="pageNumIdx" value="${pageNum-1}" />--%>
<%--  <c:url  value="matjib.do"  var="url">--%>
<%--    <c:param name="startIdx"  value="${5*pageNumIdx}"   />--%>
<%--    <c:param name="searchCondition"  value="${searchCondition}"   />--%>
<%--    <c:param name="searchKeyword"  value="${searchKeyword}"   />--%>
<%--  </c:url>--%>
<%--  <c:set var="startNum" value="${(nowPage-1)/10+1}" />--%>
<%--  <c:set var="endNum" value="${startNum+9}" />--%>
<%--  --%>
<%--  <c:forEach var="selectNum" begin="${startNum }" end="${endNum}" step="1">--%>
<%--  <a href="${url }">${selectNum}</a>--%>
<%--  </c:forEach>  --%>
<%--  </c:forEach>--%>
  
  
  ${nowPage+1} / ${TotalPage} &emsp;
  
 <c:if test="${nowPage < TotalPage-1 }">
  
  <c:url  value="matjib.do"  var="url">
    <c:param name="page"  value="${nowPage+1}"   />
    <c:param name="searchCondition"  value="${searchCondition}"   />
    <c:param name="searchKeyword"  value="${searchKeyWord}"   />
  </c:url>

  <a href="${url}"> 다음 </a> &emsp;
  
  
 </c:if>
 
 <c:if test="${nowPage == TotalPage-1 }">
 다음 &emsp;
 </c:if>
 
 <c:url  value="matjib.do"  var="url">
   <c:param name="page"  value="${TotalPage-1}"   />
   <c:param name="searchCondition"  value="${searchCondition}"   />
   <c:param name="searchKeyword"  value="${searchKeyWord}"   />
 </c:url>
 
	<c:if test="${nowPage < TotalPage-1 }">
  <a href="${url}">마지막</a>
   </c:if>
   
   <c:if test="${nowPage == TotalPage-1 }">
   마지막
   </c:if>

<br><br>
 
 
</div>
<br>
<table>
<tr><td colspan=17 align=center>지역별 음식점 수 / 전체 음식점 수 : ${totalCount}</td></tr>
<tr height=10></tr>
<tr>
<c:forEach  items="${matJibWard}"  var="wd" varStatus="status">
<td align=center width=100>${wd.ward }</td>
</c:forEach>
</tr>
<c:forEach  items="${matJibWard}"  var="wd" varStatus="status">
<td align=center>
${wd.matJibWardCount }
</td>
</c:forEach>
</table>


</section>


<%@ include file="../include/bottom.jsp" %>
