<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class = "f ">
	<c:forEach items="${commentList }" var="comment" >
		<c:if test="${comment.DEPTH eq 1 }">
			<input type="hidden" id="Cdepth${comment.COMMENTNO }" value="${comment.DEPTH }">
			
			<table style="border: 1px solid #fff; margin-bottom: 20px;" class="comment">
				<tr>
					<td>${comment.USERID} <small>(<fmt:formatDate value="${comment.WRITE_DATE }" pattern="yyyy.MM.dd"/>)</small> <small style="cursor: pointer; font-size: 10px;" onclick="showComCom(${comment.COMMENTNO})">답글달기</small> 
						<c:if test="${comment.ISREC eq 1}">
						<img class="displayNone" id="comemptyHeart${comment.COMMENTNO }" style="cursor: pointer;" width="15px" height="15px" alt="추천하기" src="<%=request.getContextPath() %>/resources/img/emptyheart.png" onclick="comRecommend(${comment.COMMENTNO}, event)">
						<img id="comfillHeart${comment.COMMENTNO }" style="cursor: pointer;" width="15px" height="15px" alt="추천하기" src="<%=request.getContextPath() %>/resources/img/fillheart.png" onclick="comRecommend(${comment.COMMENTNO}, event)">
						</c:if>
						<c:if test="${comment.ISREC eq 0 || empty comment.ISREC }">
						<img id="comemptyHeart${comment.COMMENTNO }" style="cursor: pointer;" width="15px" height="15px" alt="추천하기" src="<%=request.getContextPath() %>/resources/img/emptyheart.png" onclick="comRecommend(${comment.COMMENTNO}, event)">
						<img class="displayNone" id="comfillHeart${comment.COMMENTNO }" style="cursor: pointer;" width="15px" height="15px" alt="추천하기" src="<%=request.getContextPath() %>/resources/img/fillheart.png" onclick="comRecommend(${comment.COMMENTNO}, event)">
						</c:if>
						<small id="comrec${comment.COMMENTNO }">${comment.RECCNT }</small>
					</td>
					<td style="text-align: right; width:35px;">
						<c:if test="${comment.USERNO eq userno }">
							<span style="color: #555; cursor: pointer; font-size: 11px;" onclick="deleteComment(${comment.COMMENTNO})" >
								삭제
							</span>
						</c:if>
					</td>
				</tr>
				<tr>
					<td><span>${comment.CONTENT }</span></td>
					<td style="text-align: right; width: 35px;">
							<span style="color: #555; cursor: pointer; font-size: 11px; color: #e33e3e;" onclick="reportComment(${comment.COMMENTNO})">
								신고
							</span>
					</td>
				</tr>
				<tr id="ComCom${comment.COMMENTNO }" class="displayNone">
					<td colspan="2">
					 └> <input type="text" name="content" id="Ccontent${comment.COMMENTNO }" onkeypress="enterkeyPress(event, ${comment.COMMENTNO})"> 
						<button id="ComComSubmit${comment.COMMENTNO }" onclick="comcomInput(${userno}, ${comment.BOARDNO }, ${comment.COMMENTNO }, ${comment.ORGANIZATION })" >입력</button>
					 </td>
				</tr>
			</table>
		</c:if>
		<c:if test="${comment.DEPTH eq 2}">
			<input type="hidden" id="Cdepth${comment.COMMENTNO }" value="${comment.DEPTH }">
			<div style="padding-left:20px; padding-bottom: 20px;">
			<hr>
			<table style="border: 1px solid #fff; margin-bottom: 20px; width: 780px;" class="Comcomment">
				<tr>
					<td>↳ ${comment.USERID} <small>(<fmt:formatDate value="${comment.WRITE_DATE }" pattern="yyyy.MM.dd"/>)</small> <small style="cursor: pointer; font-size: 10px;" onclick="showComCom(${comment.COMMENTNO})">답글달기</small>
						<c:if test="${comment.ISREC eq 1}">
							<img class="displayNone" id="comemptyHeart${comment.COMMENTNO }" style="cursor: pointer;" width="15px" height="15px" alt="추천하기" src="<%=request.getContextPath() %>/resources/img/emptyheart.png" onclick="comRecommend(${comment.COMMENTNO}, event)">
							<img id="comfillHeart${comment.COMMENTNO }" style="cursor: pointer;" width="15px" height="15px" alt="추천하기" src="<%=request.getContextPath() %>/resources/img/fillheart.png" onclick="comRecommend(${comment.COMMENTNO}, event)">
						</c:if>
						<c:if test="${comment.ISREC eq 0 || empty comment.ISREC }">
							<img id="comemptyHeart${comment.COMMENTNO }" style="cursor: pointer;" width="15px" height="15px" alt="추천하기" src="<%=request.getContextPath() %>/resources/img/emptyheart.png" onclick="comRecommend(${comment.COMMENTNO}, event)">
							<img class="displayNone" id="comfillHeart${comment.COMMENTNO }" style="cursor: pointer;" width="15px" height="15px" alt="추천하기" src="<%=request.getContextPath() %>/resources/img/fillheart.png" onclick="comRecommend(${comment.COMMENTNO}, event)">
						</c:if>
						<small id="comrec${comment.COMMENTNO }">${comment.RECCNT }</small>
					</td>
					<td style="text-align: right; width:35px;">
						<c:if test="${comment.USERNO eq userno }">
							<span style="color: #555; cursor: pointer; font-size: 11px;" onclick="deleteComment(${comment.COMMENTNO})" >
								삭제
							</span>
						</c:if>
					</td>
				</tr>
				<tr>
					<td><span>${comment.CONTENT }</span></td>
					<td style="text-align: right; width: 35px;">
							<span style="color: #555; cursor: pointer; font-size: 11px; color: #e33e3e;" onclick="reportComment(${comment.COMMENTNO})">
								신고
							</span>
					</td>
				</tr>
				<tr id="ComCom${comment.COMMENTNO }" class="displayNone">
					<td colspan="2">
					 └> <input type="text" name="content" id="Ccontent${comment.COMMENTNO }" onkeypress="enterkeyPress(event, ${comment.COMMENTNO})"> 
						<button id="ComComSubmit${comment.COMMENTNO }" onclick="comcomInput(${userno}, ${comment.BOARDNO }, ${comment.COMMENTNO }, ${comment.ORGANIZATION })" >입력</button>
					 </td>
				</tr>
			</table>
			</div>
		</c:if>
				<c:if test="${comment.DEPTH eq 3}">
			<input type="hidden" id="Cdepth${comment.COMMENTNO }" value="${comment.DEPTH }">
			<div style="padding-left:80px; padding-bottom: 20px;">
			<hr>
			<table style="border: 1px solid #fff; margin-bottom: 20px; width: 720px;" class="Comcomment">
				<tr>
					<td>↳ ${comment.USERID} <small>(<fmt:formatDate value="${comment.WRITE_DATE }" pattern="yyyy.MM.dd"/>)</small>
						<c:if test="${comment.ISREC eq 1}">
							<img class="displayNone" id="comemptyHeart${comment.COMMENTNO }" style="cursor: pointer;" width="15px" height="15px" alt="추천하기" src="<%=request.getContextPath() %>/resources/img/emptyheart.png" onclick="comRecommend(${comment.COMMENTNO}, event)">
							<img id="comfillHeart${comment.COMMENTNO }" style="cursor: pointer;" width="15px" height="15px" alt="추천하기" src="<%=request.getContextPath() %>/resources/img/fillheart.png" onclick="comRecommend(${comment.COMMENTNO}, event)">
						</c:if>
						<c:if test="${comment.ISREC eq 0 || empty comment.ISREC }">
							<img id="comemptyHeart${comment.COMMENTNO }" style="cursor: pointer;" width="15px" height="15px" alt="추천하기" src="<%=request.getContextPath() %>/resources/img/emptyheart.png" onclick="comRecommend(${comment.COMMENTNO}, event)">
							<img class="displayNone" id="comfillHeart${comment.COMMENTNO }" style="cursor: pointer;" width="15px" height="15px" alt="추천하기" src="<%=request.getContextPath() %>/resources/img/fillheart.png" onclick="comRecommend(${comment.COMMENTNO}, event)">
						</c:if>
						<small id="comrec${comment.COMMENTNO }">${comment.RECCNT }</small>
					</td>
					<td style="text-align: right; width:35px;">
						<c:if test="${comment.USERNO eq userno }">
							<span style="color: #555; cursor: pointer; font-size: 11px;" onclick="deleteComment(${comment.COMMENTNO})" >
								삭제
							</span>
						</c:if>
					</td>
				</tr>
				<tr>
					<td><span>${comment.CONTENT }</span></td>
					<td style="text-align: right; width: 35px;">
							<span style="color: #555; cursor: pointer; font-size: 11px; color: #e33e3e;" onclick="reportComment(${comment.COMMENTNO})">
								신고
							</span>
					</td>
				</tr>
			</table>
			</div>
		</c:if>
	</c:forEach>
</div>