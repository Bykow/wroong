<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bertral
  Date: 04.10.17
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@include file="includes/header.jsp"%>
                <h1 class="page-header">Welcome to Wroong</h1>

<c:out value="${nbOfGeneratedTweets}"/> tweets generated.

<%@include file="includes/footer.jsp"%>