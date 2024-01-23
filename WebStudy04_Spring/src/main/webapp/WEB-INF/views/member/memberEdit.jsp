<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:choose>
	<c:when test="${not empty message }">
		<pre>
		message = ${message }
		member = ${member }
		contentPage = <%=request.getAttribute("contentPage")%>, ${contentPage }
		errors = ${errors }
			<c:remove var="message" scope="session"/>
		</pre>
	</c:when>
	<c:otherwise>
		메시지 없음.
	</c:otherwise>
</c:choose>	
<form:form method="post" modelAttribute="member">
	<table>
		<tr>
			<th>비밀번호</th>
			<td>
				<form:input path="memPass" required class="form-control" />
				<form:errors path="memPass" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>
				<form:input path="memName" class="form-control" />
				<form:errors path="memName" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td>
				<form:input path="memRegno1" class="form-control" />
				<form:errors path="memRegno1" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td>
				<form:input path="memRegno2" class="form-control" />
				<form:errors path="memRegno2" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>
				<form:input type="datetime-local" path="memBir" class="form-control" />
				<form:errors path="memBir" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>
				<form:input path="memZip" required class="form-control" />
				<form:errors path="memZip" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>
				<form:input path="memAdd1" required class="form-control" />
				<form:errors path="memAdd1" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>
				<form:input path="memAdd2" required class="form-control" />
				<form:errors path="memAdd2" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th>집전번</th>
			<td>
				<form:input path="memHometel" class="form-control" />
				<form:errors path="memHometel" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th>회사전번</th>
			<td>
				<form:input path="memComtel" class="form-control" />
				<form:errors path="memComtel" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td>
				<form:input path="memHp" required class="form-control" />
				<form:errors path="memHp" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>
				<form:input path="memMail" class="form-control" />
				<form:errors path="memMail" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th>직업</th>
			<td>
				<form:input path="memJob" class="form-control" />
				<form:errors path="memJob" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th>취미</th>
			<td>
				<form:input path="memLike" class="form-control" />
				<form:errors path="memLike" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th>기념일</th>
			<td>
				<form:input path="memMemorial" class="form-control" />
				<form:errors path="memMemorial" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td>
				<form:input type="date" path="memMemorialday" class="form-control" />
				<form:errors path="memMemorialday" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>1000</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit" class="btn btn-primary">전송</button>
				<button type="reset" class="btn btn-danger">취소</button>
			</td>
		</tr>
	</table>
</form:form>










