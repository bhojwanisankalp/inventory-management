<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@page isELIgnored="false" %>
    <c:if test="${sessionScope.userId == null}" >
    <% response.sendRedirect("invaliduser"); %>
    </c:if>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
      <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html";  
 			charset=ISO-8859-1">
    <title>Cannabis bot Admin</title>
	<!-- BOOTSTRAP STYLES-->
    <!--  <link href="assets/css/bootstrap.css" rel="stylesheet" /> -->
     <link href="<c:url value="/assets/css/bootstrap.css" />" rel="stylesheet" /> 
     <!-- FONTAWESOME STYLES-->
    <!--  <link href="assets/css/font-awesome.css" rel="stylesheet" /> -->
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
    <!--
    <link href="<c:url value="/assets/css/font-awesome.css" />" rel="stylesheet" /> 
      MORRIS CHART STYLES-->
    <!--  <link href="assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" /> -->
    	 <link href="<c:url value="/assets/js/morris/morris-0.4.3.min.css" />" rel="stylesheet" /> 
        <!-- CUSTOM STYLES-->
<!--    <link href="assets/css/custom.css" rel="stylesheet" />  --> 
     <link href="<c:url value="/assets/css/custom.css" />" rel="stylesheet" /> 
     <!-- GOOGLE FONTS-->
   <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
</head>
<body>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="dashboard">Admin</a> 
            </div>
  <div style="color: white;
padding: 15px 50px 5px 50px;
float: right;
font-size: 16px;"> &nbsp; <a href="logout" class="btn btn-danger square-btn-adjust">Logout</a> </div>
        </nav>   
           <!-- /. NAV TOP  -->
                <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
				<li class="text-center">
                    <img src="<c:url value="/assets/img/find_user.png" /> " class="user-image img-responsive" /> 
					<!-- <img src="assets/img/find_user.png" class="user-image img-responsive"/> -->
					</li>
				
					
                    <li>
                        <a class="active-menu"  href="dashboard"><i class="fa fa-dashboard fa-2x"></i> Dashboard</a>
                    </li>
                     <c:if test="${sessionScope.role == 'admin'}">
					 <li><a  href="users"><i
							class="fa fa-desktop fa-2x"></i> User Management</a></li> 
					</c:if>
					<c:if test="${sessionScope.role == 'superadmin'}">
					<li>
                        <a href="#"><i class="fa fa-desktop fa-2x"></i> User Management<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="users"><i class="fa fa-user fa-2x" aria-hidden="true"></i> Admin Management</a>
                            </li>
                            <li>
                                <a href="agents"> <i class="fa fa-user-secret fa-2x" aria-hidden="true"></i> Agents Management</a>
                            </li>
                           
                        </ul>
                      </li> 
                      </c:if>
                    <li>
                        <a  href="product"><i class="fa fa-qrcode fa-2x"></i> Product Management</a>
                    </li>
                    <li>
                        <a  href="productQuantity"><i class="fa fa-barcode fa-2x"></i> Product Quantity</a>
                    </li>
					<li>
                        <a  href="orderManagement"><i class="fa fa-shopping-cart fa-2x"></i> Order Management</a>
                    </li>
                   
					                   
               <!--       <li>
                        <a href="#"><i class="fa fa-sitemap fa-3x"></i> Multi-Level Dropdown<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="#">Second Level Link</a>
                            </li>
                            <li>
                                <a href="#">Second Level Link</a>
                            </li>
                            <li>
                                <a href="#">Second Level Link<span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li>
                                        <a href="#">Third Level Link</a>
                                    </li>
                                    <li>
                                        <a href="#">Third Level Link</a>
                                    </li>
                                    <li>
                                        <a href="#">Third Level Link</a>
                                    </li>

                                </ul>
                               
                            </li>
                        </ul>
                      </li>  
                  <li  >
                        <a  href="blank.html"><i class="fa fa-square-o fa-3x"></i> Blank Page</a>
                    </li>	-->
                </ul>
               
            </div>
            
        </nav>  
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" style="background-image:url(https://i.ibb.co/W3QBCJ8/Cannabot-backgraound.jpg)">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                     <h2 style="font-style:normal; font-weight: bold;">Admin Dashboard</h2>   
                           <c:if test="${not empty sessionScope.name}">
              		          	<h5><c:out value="Welcome ${sessionScope.name }"></c:out>, Love to see you back. </h5>
                        	</c:if>
                    </div>
                </div>              
                 <!-- /. ROW  -->
                  <hr />
                <div class="row">
                <div class="col-md-3 col-sm-6 col-xs-6">           
			<div class="panel panel-back noti-box">
                <span class="icon-box bg-color-red set-icon">
                    <i class="fa fa-envelope-o"></i>
                </span>
                <div class="text-box" >
                    <p class="main-text"><c:out value="${sessionScope.newUsersCount}"></c:out> New</p>
                    <p class="text-muted">Users</p>
                </div>
             </div>
		     </div>
                    <div class="col-md-3 col-sm-6 col-xs-6">           
			<div class="panel panel-back noti-box">
                <span class="icon-box bg-color-green set-icon">
                    <i class="fa fa-bars"></i>
                </span>
                <div class="text-box" >
                    <p class="main-text"><c:out value="${sessionScope.paymentPending }"></c:out> Payments</p>
                    <p class="text-muted">Remaining</p>
                </div>
             </div>
		     </div>
                    <div class="col-md-3 col-sm-6 col-xs-6">           
			<div class="panel panel-back noti-box">
                <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-bell-o"></i>
                </span>
                <div class="text-box" >
                    <p class="main-text"><c:out value="${sessionScope.productOutOfStock}"></c:out> Products</p>
                    <p class="text-muted">Inactive</p>
                </div>
             </div>
		     </div>
                    <div class="col-md-3 col-sm-6 col-xs-6">           
			<div class="panel panel-back noti-box">
                <span class="icon-box bg-color-brown set-icon">
                    <i class="fa fa-rocket"></i>
                </span>
                <div class="text-box" >
                    <p class="main-text"><c:out value="${sessionScope.newOrders}"></c:out> New Orders</p>
                    <p class="text-muted">Pending</p>
                </div>
             </div>
		     </div>
			</div>
                 <!-- /. ROW  -->
             
                 <!-- /. ROW  -->
               
               
                 <!-- /. ROW  -->
               
                 <!-- /. ROW  -->           
    </div>
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
        </div>
     <!-- /. WRAPPER  -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <!-- <script src="assets/js/jquery-1.10.2.js"></script>  -->
    <!-- <script src="assets/js/bootstrap.min.js"></script> -->
    <!-- <script src="assets/js/jquery.metisMenu.js"></script> -->
    <!-- <script src="assets/js/morris/raphael-2.1.0.min.js"></script> -->
    <!-- <script src="assets/js/morris/morris.js"></script> -->
    <!-- <script src="assets/js/custom.js"></script> -->
    <script src="<c:url value="/assets/js/jquery-1.10.2.js" />" ></script>
      <!-- BOOTSTRAP SCRIPTS -->
     <script src="<c:url value="/assets/js/bootstrap.min.js" />" ></script>
  
    <!-- METISMENU SCRIPTS -->
     <script src="<c:url value="/assets/js/jquery.metisMenu.js" />" ></script>
     <!-- MORRIS CHART SCRIPTS -->
    <script src="<c:url value="/assets/js/morris/raphael-2.1.0.min.js" />"></script> 
    <script src="<c:url value="/assets/js/morris/morris.js" />"></script> 
 
      <!-- CUSTOM SCRIPTS -->
    <script src="<c:url value="/assets/js/custom.js" />"></script> 

   
</body>
</html>


