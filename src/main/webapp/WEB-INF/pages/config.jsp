<%--
  User: Antoine Friant, Lawrence Stalder
  Date: 04.10.17
  Time: 14:54
--%>
<%@include file="includes/header.jsp" %>

<div class="container-fluid">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Configuration</h1>
            <div class="panel panel-default">
                <div class="panel-heading">
                    Populate database
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <form role="form" action="./configsuccess" method="post">
                                <div class="form-group">
                                    <label>Number of tweets to add</label>
                                    <input type="number" name="nbOfGeneratedTweets" class="form-control" min="1"
                                           step="1" pattern="\d+">
                                </div>
                                <button type="sumbit" class="btn btn-outline btn-primary">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    Clear database
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <form role="form" action="./clear" method="post">
                                <button type="sumbit" class="btn btn-danger">Delete all tweets</button>
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
