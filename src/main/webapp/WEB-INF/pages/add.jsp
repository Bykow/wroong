<%--
  Created by IntelliJ IDEA.
  User: bertral
  Date: 04.10.17
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@include file="includes/header.jsp" %>

<div class="container-fluid">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Configuration</h1>
            <div class="panel panel-default">
                <div class="panel-heading">
                    New tweet ...
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <form role="form" action="./addsuccess" method="post">
                                <div class="form-group">
                                    <label>Content (max 250 characters)</label>
                                    <textarea name="content" class="form-control" rows="3" maxlength="250"></textarea>
                                </div>
                                <button type="sumbit" class="btn btn-default">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
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

</body>
</html>
