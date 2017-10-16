<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: Antoine Friant, Lawrence Stalder
  Date: 04.10.17
  Time: 14:54
--%>
<%@include file="includes/header.jsp" %>

<!-- DataTables CSS -->
<link href="./vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="./vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

<div class="container-fluid">
    <div class="row">
        <div class="col-lg-12">
            <h1>Tweet List</h1>
            <div class="panel panel-default">
                <div class="panel-heading">
                    Fake News Explorer
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <form role="form" action="./list" method="get">
                        <input type="text" name="filter" class="form-inline" value="${filter}">
                        <button type="sumbit" class="btn btn-default btn-sm">Search</button>
                    </form>

                    <label>Page ${p} of ${count/15.0} showing 15 of ${count} tweets</label><br/>

                    <c:choose>
                        <c:when test="${p > 1}">
                            <form role="form" action="./list" method="get" style="display:inline">
                                <input type="hidden" name="p" value="${p - 1}"/>
                                <input type="hidden" name="filter" value="${filter}"/>
                                <input type="hidden" name="orderby" value="${orderby}"/>
                                <input type="hidden" name="descending" value="${descending}"/>
                                <button type="sumbit" class="btn btn-default btn-sm">Previous</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form role="form" style="display:inline">
                                <button type="sumbit" class="btn btn-default btn-sm disabled" disabled>Previous</button>
                            </form>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${p*15 < count}">
                            <form role="form" action="./list" method="get" style="display:inline">
                                <input type="hidden" name="p" value="${p + 1}"/>
                                <input type="hidden" name="filter" value="${filter}"/>
                                <input type="hidden" name="orderby" value="${orderby}"/>
                                <input type="hidden" name="descending" value="${descending}"/>
                                <button type="sumbit" class="btn btn-default btn-sm">Next</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form role="form" style="display:inline">
                                <button type="sumbit" class="btn btn-default btn-sm disabled" disabled>Next</button>
                            </form>
                        </c:otherwise>
                    </c:choose>


                    <table width="100%" class="table table-striped table-bordered table-hover"
                           id="dataTables-example">
                        <thead>
                        <tr>
                            <th>
                                <form role="form" action="./list" method="get">
                                    <input type="hidden" name="p" value="${p}"/>
                                    <input type="hidden" name="filter" value="${filter}"/>
                                    <input type="hidden" name="orderby" value="id"/>
                                    <input type="hidden" name="descending" value="${!descending}"/>
                                    <c:choose>
                                        <c:when test="${orderby == 'id' && descending}">
                                            <button type="sumbit" class="btn btn-default">Id <i class="fa fa-angle-down"></i></button>
                                        </c:when>
                                        <c:when test="${orderby == 'id' && !descending}">
                                            <button type="sumbit" class="btn btn-default">Id <i class="fa fa-angle-up"></i></button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="sumbit" class="btn btn-default">Id</button>
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                            </th>
                            <th>
                                <form role="form" action="./list" method="get">
                                    <input type="hidden" name="p" value="${p}"/>
                                    <input type="hidden" name="filter" value="${filter}"/>
                                    <input type="hidden" name="orderby" value="content"/>
                                    <input type="hidden" name="descending" value="${!descending}"/>
                                    <c:choose>
                                        <c:when test="${orderby == 'content' && descending}">
                                            <button type="sumbit" class="btn btn-default">Content <i class="fa fa-angle-down"></i></button>
                                        </c:when>
                                        <c:when test="${orderby == 'content' && !descending}">
                                            <button type="sumbit" class="btn btn-default">Content <i class="fa fa-angle-up"></i></button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="sumbit" class="btn btn-default">Content</button>
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                            </th>
                            <th>
                                <form role="form" action="./list" method="get">
                                    <input type="hidden" name="p" value="${p}"/>
                                    <input type="hidden" name="filter" value="${filter}"/>
                                    <input type="hidden" name="orderby" value="created_on"/>
                                    <input type="hidden" name="descending" value="${!descending}"/>
                                    <c:choose>
                                        <c:when test="${orderby == 'created_on' && descending}">
                                            <button type="sumbit" class="btn btn-default">Created on <i class="fa fa-angle-down"></i></button>
                                        </c:when>
                                        <c:when test="${orderby == 'created_on' && !descending}">
                                            <button type="sumbit" class="btn btn-default">Created on <i class="fa fa-angle-up"></i></button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="sumbit" class="btn btn-default">Created on</button>
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                            </th>
                            <th>Edit/Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="i" value="0"/>
                        <c:forEach items="${tweets}" var="tweet">
                            <tr class="<c:out escapeXml="true" value="${i % 2 == 0 ? 'even' : 'odd'}"/>">
                                <td>${tweet.id}</td>
                                <td>${tweet.content}</td>
                                <td>${tweet.date}</td>
                                <td>
                                    <form role="form" action="./edit" method="get" style="display:inline">
                                        <input type="hidden" name="id" value="${tweet.id}"/>
                                        <button type="sumbit" class="btn btn-circle btn-primary"><i
                                                class="fa fa-edit"></i></button>
                                    </form>
                                    <form role="form" action="./delete" method="post" style="display:inline">
                                        <input type="hidden" name="id" value="${tweet.id}"/>
                                        <button type="sumbit" class="btn btn-circle btn-danger"><i
                                                class="fa fa-times"></i></button>
                                    </form>
                                </td>
                            </tr>
                            <c:set var="i" value="${i + 1}"/>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!-- /.table-responsive -->
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- row -->
</div>
<!-- /.container-fluid -->

</div>
<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="./vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="./vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="./vendor/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="./dist/js/sb-admin-2.js"></script>

<script src="./vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="./vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
<script src="./vendor/datatables-responsive/dataTables.responsive.js"></script>


</body>
</html>
