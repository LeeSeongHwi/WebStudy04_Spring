<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<form:form modelAttribute="member" enctype="multipart/form-data">
   <table>
      <tr>
         <th>프로필</th>
         <td>
            <input type="file" name="memImage"/>
            <form:errors path="memImage" element="span" cssClass="text-danger" />
         </td>
      </tr>
      <tr>
         <th>회원아이디</th>
         <td><form:input type="text" path="memId" 
               cssClass="form-control" />
            <form:errors path="memId" element="span" cssClass="text-danger" /></td>
      </tr>
      <tr>
         <th>비밀번호</th>
         <td><form:input type="text" path="memPass" 
               cssClass="form-control" />
            <form:errors path="memPass" element="span" cssClass="text-danger" /></td>
      </tr>
      <tr>
         <th>이름</th>
         <td><form:input type="text" path="memName"
               cssClass="form-control" />
            <form:errors path="memName" element="span" cssClass="text-danger" /></td>
      </tr>
      <tr>
         <th>주민번호1</th>
         <td><form:input type="text" path="memRegno1"
               cssClass="form-control" />
            <form:errors path="memRegno1" element="span" cssClass="text-danger" /></td>
      </tr>
      <tr>
         <th>주민번호2</th>
         <td><form:input type="text" path="memRegno2"
               cssClass="form-control" />
            <form:errors path="memRegno2" element="span" cssClass="text-danger" /></td>
      </tr>
      <tr>
         <th>생일</th>
         <td><form:input type="datetime-local" path="memBir"
               cssClass="form-control" />
            <form:errors path="memBir" element="span" cssClass="text-danger" /></td>
      </tr>
      <tr>
         <th>우편번호</th>
         <td><form:input type="text" path="memZip" 
               cssClass="form-control" />
            <form:errors path="memZip" element="span" cssClass="text-danger" /></td>
      </tr>
      <tr>
         <th>주소1</th>
         <td><form:input type="text" path="memAdd1" 
               cssClass="form-control" />
            <form:errors path="memAdd1" element="span" cssClass="text-danger" /></td>
      </tr>
      <tr>
         <th>주소2</th>
         <td><form:input type="text" path="memAdd2" 
               cssClass="form-control" />
            <form:errors path="memAdd2" element="span" cssClass="text-danger" /></td>
      </tr>
      <tr>
         <th>집전번</th>
         <td><form:input type="text" path="memHometel"
               cssClass="form-control" />
            <form:errors path="memHometel" element="span" cssClass="text-danger" /></td>
      </tr>
      <tr>
         <th>회사전번</th>
         <td><form:input type="text" path="memComtel"
               cssClass="form-control" />
            <form:errors path="memComtel" element="span" cssClass="text-danger" /></td>
      </tr>
      <tr>
         <th>휴대폰</th>
         <td><form:input type="text" path="memHp" 
               cssClass="form-control" />
            <form:errors path="memHp" element="span" cssClass="text-danger" /></td>
      </tr>
      <tr>
         <th>이메일</th>
         <td><form:input type="text" path="memMail"
               cssClass="form-control" />
            <form:errors path="memMail" element="span" cssClass="text-danger" /></td>
      </tr>
      <tr>
         <th>직업</th>
         <td><form:input type="text" path="memJob"
               cssClass="form-control" />
            <form:errors path="memJob" element="span" cssClass="text-danger" /></td>
      </tr>
      <tr>
         <th>취미</th>
         <td><form:input type="text" path="memLike"
               cssClass="form-control" />
            <form:errors path="memLike" element="span" cssClass="text-danger" /></td>
      </tr>
      <tr>
         <th>기념일</th>
         <td><form:input type="text" path="memMemorial"
               cssClass="form-control" />
            <form:errors path="memMemorial" element="span"
               cssClass="text-danger" /></td>
      </tr>
      <tr>
         <th>기념일자</th>
         <td><form:input type="date" path="memMemorialday"
               cssClass="form-control" />
            <form:errors path="memMemorialday" element="span"
               cssClass="text-danger" /></td>
      </tr>
      <tr>
         <th>마일리지</th>
         <td>1000</td>
      </tr>
      <tr>
         <td colspan='2'>
            <button type="submit" class="btn btn-primary">전송</button>
            <button type="reset" class="btn btn-danger">취소</button>
         </td>
      </tr>
   </table>
</form:form>





