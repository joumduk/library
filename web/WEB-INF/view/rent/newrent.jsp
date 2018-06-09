<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                        <h1>New Rent</h1>
                    </div>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="page-header float-right">
                    <div class="page-title">
                        <ol class="breadcrumb text-right">
                            <li class="active">New Rent</li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>

        <div class="content mt-3">
            <div class="col-sm-6">
                <div class="card">
                    <form action="savenewrent" method="post" class="form-horizontal">
                    <div class="card-body card-block">
                        <div class="row form-group">
                          <div class="col col-md-3"><label class=" form-control-label">Rent Date</label></div>
                          <div class="col-12 col-md-9"><input type="text"  class="form-control" value="${rent.rentDate.getDate()}/${rent.rentDate.getMonth()}/${rent.rentDate.getYear()}"><small class="form-text text-muted"></small></div>
                        </div>
                    
                        <div class="row form-group">
                          <div class="col col-md-3"><label  class=" form-control-label">Return Date</label></div>
                          <div class="col-12 col-md-9"><input type="text" class="form-control" value="${rent.expireDate.getDate()}/${rent.expireDate.getMonth()}/${rent.expireDate.getYear()}"><small class="form-text text-muted"></small></div>
                        </div>
                        <div class="row form-group">
                          <div class="col col-md-3"><label for="id_student" class=" form-control-label">Student</label></div>
                          <div class="col-12 col-md-9">
                              <select class="form-control" id="id_student" name="id_student">
                                <c:forEach var="student" items="${students}" varStatus="iter">
                                    <option value="${student.id}">${student.studentId} - ${student.name}</option>
                               </c:forEach>
                                  <option></option>
                              </select></div>
                        </div>
                    </div>
                        
                    <div class="card-footer">
                      <button type="submit" class="btn btn-primary btn-sm">
                        <i class="fa fa-dot-circle-o"></i> Submit
                      </button>
                      <button type="reset" class="btn btn-danger btn-sm">
                        <i class="fa fa-ban"></i> Reset
                      </button>
                    </div>
                    </form>
                </div>
                <div class="card">
                    <div class="card-body">
                        <table class="table table-bordered">
                            <tr>
                                <th>Book No</th>
                                <th>Title</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach var="item" items="${items}" varStatus="iter">
                                 <tr>
                                    <td>${item.idBook}</td>
                                    <td>${item.bookName}</td>
                                    <td>${item.quantity}</td>
                                    <td><A class="btn btn-link" href="removeitem?${item.idBook}">Remove</A></td>
                                </tr>
                            </c:forEach>
                        </table>

                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="card">
                    <form action="newrent" method="get" class="form-horizontal">
                    <div class="card-body card-block">
                        <div class="row form-group">
                          <div class="col col-md-3"><label for="keyword" class=" form-control-label">Keyword</label></div>
                          <div class="col-12 col-md-9"><input type="text" id="keyword" name="keyword" placeholder="keyword" class="form-control" value="${keyword}"><small class="form-text text-muted"></small></div>
                        </div>
                    </div>
                    <div class="card-footer">
                      <button type="submit" class="btn btn-primary btn-sm">
                        <i class="fa fa-dot-circle-o"></i> Submit
                      </button>
                      <button type="reset" class="btn btn-danger btn-sm">
                        <i class="fa fa-ban"></i> Reset
                      </button>
                    </div>
                    </form>
                </div>
                <table class="table table-bordered">
                    <tr>
                        <th>No</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Pages</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="book" items="${books}" varStatus="iter">
                         <tr>
                            <td><c:out value="${book.id}"></c:out></td>
                            <td>${book.title}</td>
                            <td>${book.author}</td>
                            <td>${book.pages}</td>
                            <td>${book.quantity}</td>
                            <td><c:if test="${book.quantity > 0}" ><A class="btn btn-link" href="additem?${book.id}">Add</A></c:if></td>
                        </tr>
                    </c:forEach>
                </table>
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