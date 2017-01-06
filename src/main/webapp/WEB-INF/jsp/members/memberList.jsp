<%--
  Created by IntelliJ IDEA.
  User: khk
  Date: 2017-01-04
  Time: 오후 3:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/head.jsp"/>
<body>

<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp" />
    <div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>아이디</th>
                <th>이름</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${members}" var="member">
                <tr>
                    <td>${member.id}</td>
                    <td>${member.name}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <jsp:include page="../fragments/footer.jsp" />

</div> <!-- /container -->


</body>
</html>