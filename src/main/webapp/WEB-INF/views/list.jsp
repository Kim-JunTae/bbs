<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 목록</title>
<style type="text/css">
	#bbs table {
	    width:580px;
	    margin-left:10px;
	    border:1px solid black;
	    border-collapse:collapse;
	    font-size:14px;
	    
	}
	
	#bbs table caption {
	    font-size:20px;
	    font-weight:bold;
	    margin-bottom:10px;
	}
	
	#bbs table th,#bbs table td {
	    text-align:center;
	    border:1px solid black;
	    padding:4px 10px;
	}
	
	.no {width:15%}
	.subject {width:30%}
	.writer {width:20%}
	.reg {width:20%}
	.hit {width:15%}
	.title{background:lightsteelblue}
	
	.odd {background:silver}
	
	/* paging */
	
	table tfoot ol.paging {
	    list-style:none;
	}
	
	table tfoot ol.paging li {
	    float:left;
	    margin-right:8px;
	}
	
	table tfoot ol.paging li a {
	    display:block;
	    padding:3px 7px;
	    border:1px solid #00B3DC;
	    color:#2f313e;
	    font-weight:bold;
	}
	
	table tfoot ol.paging li a:hover {
	    background:#00B3DC;
	    color:white;
	    font-weight:bold;
	}
	
	.disable {
	    padding:3px 7px;
	    border:1px solid silver;
	    color:silver;
	}
	
	.now {
	   padding:3px 7px;
	    border:1px solid #ff4aa5;
	    background:#ff4aa5;
	    color:white;
	    font-weight:bold;
	}
		
</style>
</head>
<body>
	<div id="bbs">
		<table summary="게시판 목록">
			<caption>게시판 목록</caption>
			<thead>
				<tr class="title">
					<th class="no">번호</th>
					<th class="subject">제목</th>
					<th class="writer">글쓴이</th>
					<th class="reg">날짜</th>
					<th class="hit">조회수</th>
				</tr>
			</thead>
			
			<tfoot>
                      <tr>
                          <td colspan="4">
                              <ol class="paging">
                                  
               

<li><a href="#">이전으로</a></li>

	<li class="now">1</li>
         
	<li><a href="#">2</a></li>


 
		<li><a href="#">다음으로</a></li>
	
                              </ol>
                          </td>
						  <td>
							<input type="button" value="글쓰기"
			onclick="javascript:location.href='write.inc'"/>
						  </td>
                      </tr>
                  </tfoot>
			<tbody>
			<c:if test="${list == null}">
				<tr>
					<td colspan="4">
						등록된 글이 없습니다.
					</td>
				</tr>
			</c:if>
			<c:if test="${list != null}">
				<c:forEach var="board" items="${list}">
					<tr>
						<td>번호 : ${board.b_idx}</td>
						<td>제목 : ${board.title}</td>
						<td>글쓴이 : 아이디  아직 없음</td>
						<td>날짜 : ${board.reg_date}</td>
						<td>조회수 : ${board.cnt}</td>
					</tr>
				</c:forEach>
			</c:if>
				<!-- <tr>
					<td>1</td>
					<td style="text-align: left">
						<a href="#">제목</a>
					</td>
					<td>글쓴이</td>
					<td>날짜</td>
					<td>0</td>
				</tr> -->
			</tbody>
		</table>
		
	</div>
</body>
</html>