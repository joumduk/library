<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : index
    Created on : 2018. 6. 7, 오전 5:18:47
    Author     : peter
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Library</title>
    <meta name="description" content="Sufee Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="apple-touch-icon" href="apple-icon.png">
    <link rel="shortcut icon" href="favicon.ico">

    <link rel="stylesheet" href="assets/css/normalize.css">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/themify-icons.css">
    <link rel="stylesheet" href="assets/css/flag-icon.min.css">
    <link rel="stylesheet" href="assets/css/cs-skin-elastic.css">
    <!-- <link rel="stylesheet" href="assets/css/bootstrap-select.less"> -->
    <link rel="stylesheet" href="assets/scss/style.css">
    <link href="assets/css/lib/vector-map/jqvmap.min.css" rel="stylesheet">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>

    <!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script> -->

</head>
<body>


        <!-- Left Panel -->

    <%@ include file="../menu.jspf" %>

    <!-- Left Panel -->

    <!-- Right Panel -->

    <div id="right-panel" class="right-panel">

        <!-- Header-->
        <%@include file="../header.jspf" %>
        <!-- Header-->

        <div class="breadcrumbs">
            <div class="col-sm-4">
                <div class="page-header float-left">
                    <div class="page-title">
                        <h1>Student List</h1>
                    </div>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="page-header float-right">
                    <div class="page-title">
                        <ol class="breadcrumb text-right">
                            <li class="active">Student List</li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>

        <div class="content mt-3">

            <div class="col-sm-12"> 
                <div class="card">
                    <form action="newstudent" method="post" class="form-horizontal">
                    <div class="card-header">
                      <strong>Student Detail</strong> Student
                    </div>
                    <div class="card-body card-block">
                        <div class="row form-group">
                          <div class="col col-md-3"><label for="student_id" class=" form-control-label">Student Id</label></div>
                          <div class="col-12 col-md-9"><input type="text" id="student_id" name="student_id" placeholder="Student Id" class="form-control" disabled="" value="${student.studentId}"><small class="form-text text-muted"></small></div>
                        </div>
                        <div class="row form-group">
                          <div class="col col-md-3"><label for="name" class=" form-control-label">Name</label></div>
                          <div class="col-12 col-md-9"><input type="text" id="name" name="name" placeholder="Enter name" class="form-control" disabled="" value="${student.name}"><small class="help-block form-text"></small></div>
                        </div>
                        <div class="row form-group">
                          <div class="col col-md-3"><label for="phone" class=" form-control-label">Phone</label></div>
                          <div class="col-12 col-md-9"><input type="text" id="phone" name="phone" placeholder="Phone" class="form-control"  disabled="" value="${student.phone}"><small class="help-block form-text"></small></div>
                        </div>
                        <div class="row form-group">
                          <div class="col col-md-3"><label for="email" class=" form-control-label">Email</label></div>
                          <div class="col-12 col-md-9"><input type="email" id="email" name="email" placeholder="E-mail"class="form-control" disabled="" value="${student.email}"></div>
                        </div>
                    </div>
                    <div class="card-footer">
                        <a href="updatestudent?${student.id}"><button type="button" class="btn btn-primary btn-sm">
                        <i class="fa fa-dot-circle-o"></i> edit
                      </button></a>
                        <a href="student"><button type="button" class="btn btn-danger btn-sm">
                        <i class="fa fa-ban"></i> cancel
                      </button></a>
                    </div>
                    </form>
                  </div>
            </div>
            <!--/.col-->
        </div> <!-- .content -->
    </div><!-- /#right-panel -->

    <!-- Right Panel -->

    <script src="assets/js/vendor/jquery-2.1.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
    <script src="assets/js/plugins.js"></script>
    <script src="assets/js/main.js"></script>


    <script src="assets/js/lib/chart-js/Chart.bundle.js"></script>
    <script src="assets/js/dashboard.js"></script>
    <script src="assets/js/widgets.js"></script>
    <script src="assets/js/lib/vector-map/jquery.vmap.js"></script>
    <script src="assets/js/lib/vector-map/jquery.vmap.min.js"></script>
    <script src="assets/js/lib/vector-map/jquery.vmap.sampledata.js"></script>
    <script src="assets/js/lib/vector-map/country/jquery.vmap.world.js"></script>
    <script>
        ( function ( $ ) {
            "use strict";

           
        } )( jQuery );
    </script>

</body>
</html>