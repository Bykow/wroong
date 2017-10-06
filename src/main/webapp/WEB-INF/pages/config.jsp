<%--
  Created by IntelliJ IDEA.
  User: bertral
  Date: 04.10.17
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@include file="includes/header.jsp"%>

<h1 class="page-header">Generate tweets</h1>

<form action="./configsuccess" method="post">
    Number of tweets :<br>
    <input type="number" name="nbOfGeneratedTweets" min="1" step="1" pattern="\d+"><br>
    <input type="submit" value="Generate random">
</form>

<%@include file="includes/footer.jsp"%>