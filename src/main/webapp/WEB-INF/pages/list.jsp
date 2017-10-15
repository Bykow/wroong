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
                    <table width="100%" class="table table-striped table-bordered table-hover"
                           id="dataTables-example">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Tweet content</th>
                            <th>Created on</th>
                            <th width="110">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="count" value="0"/>
                        <c:forEach items="${tweets}" var="tweet">
                            <tr class="<c:out escapeXml="true" value="${count % 2 == 0 ? 'even' : 'odd'}"/>">
                                <td>${tweet.id}</td>
                                <td>${tweet.content}</td>
                                <td>${tweet.date}</td>
                                <td>
                                    <form role="form" action="./delete" method="post" style="display:inline">
                                        <input type="hidden" name ="id" value="${tweet.id}"/>
                                        <button type="sumbit" class="btn btn-default">Delete</button>
                                    </form>
                                    <form role="form" action="./edit" method="get" style="display:inline">
                                        <input type="hidden" name ="id" value="${tweet.id}"/>
                                        <button type="sumbit" class="btn btn-default">Edit</button>
                                    </form>
                                </td>
                            </tr>
                            <c:set var="count" value="${count + 1}"/>
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
<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
    $(document).ready(function () {
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
</script>

</body>
</html>
