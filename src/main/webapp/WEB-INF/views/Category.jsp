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
    <title>Cannabis bot Admin</title>
	<!-- BOOTSTRAP STYLES-->
    <link href="<c:url value="/assets/css/bootstrap.css" />" rel="stylesheet" />
     <!-- FONTAWESOME STYLES
    <link href="<c:url value="/assets/css/font-awesome.css" />" rel="stylesheet" /> -->
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
     <!-- MORRIS CHART STYLES-->
   
        <!-- CUSTOM STYLES-->
    <link href="<c:url value="/assets/css/custom.css" />" rel="stylesheet" />
     <!-- GOOGLE FONTS-->
   <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
     <!-- TABLE STYLES-->
    <link href="<c:url value="/assets/js/dataTables/dataTables.bootstrap.css" />" rel="stylesheet" />
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
	<style>
		.remove{
			width:30px;
			height: 30px;
			font-size: 20px;
			background-color: tomato;
			color:white;
			border: none;
			border-radius: 15px;
		}
		.pointer{
			cursor: pointer;
		}
		
	</style>
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
font-size: 16px;">&nbsp; <a href="logout" class="btn btn-danger square-btn-adjust">Logout</a> </div>
        </nav>   
           <!-- /. NAV TOP  -->
                <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
				<li class="text-center ">
                    <img src="assets/img/find_user.png" class="user-image img-responsive"/>
					</li>
				
					
                    <li>
                        <a  href="dashboard"><i class="fa fa-dashboard fa-2x"></i> Dashboard</a>
                    </li>
                   <c:if test="${sessionScope.role == 'admin'}">
					 <li><a  href="/Chat-Bot/users"><i
							class="fa fa-desktop fa-2x"></i> User Management</a></li> 
					</c:if>
					<c:if test="${sessionScope.role == 'superadmin'}">
					<li>
                        <a href="#"><i class="fa fa-desktop fa-2x"></i> User Management<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li >
                                <a href="users" ><i class="fa fa-user fa-2x" aria-hidden="true"></i> Admin Management</a>
                            </li>
                            <li>
                                <a href="agents"><i class="fa fa-user-secret fa-2x" aria-hidden="true"> </i> Agents Management</a>
                            </li>
                           
                        </ul>
                      </li> 
                      </c:if>
                    <li>
                        <a  class="active-menu" href="/Chat-Bot/product"><i class="fa fa-qrcode fa-2x"></i> Product Management</a>
                    </li>
					   <li>
                        <a  href="productQuantity"><i class="fa fa-barcode fa-2x"></i> Product Quantity</a>
                    </li>
					<li>
                        <a  href="orderManagement"><i class="fa fa-shopping-cart fa-2x"></i> Order Management</a>
                    </li>
					                   
            <!--         <li>
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
                        <a   href="blank.html"><i class="fa fa-square-o fa-3x"></i> Blank Page</a>
                    </li>	 -->
                </ul>
               
            </div>
            
        </nav>  
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" style="background-image:url(https://i.ibb.co/W3QBCJ8/Cannabot-backgraound.jpg)">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                    <c:if test="${sessionScope.role == 'superadmin'}">
						<a href="<c:url value='/product' />" class="btn btn-info">Back</a>
					</c:if>
                     <h2 style="font-style:normal; font-weight: bold;">Product Management</h2>   
                        <h5><c:out value="Welcome ${sessionScope.name }"></c:out>, Love to see you back. </h5>
                       
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
               
         <div class="row">
               
                <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                        <strong>  Add New Category</strong>  
                            </div>
                            <div class="panel-body">
                            
                               <form role="form" action="saveCategory"  method="POST">
<br/>								
									<c:forEach items="${categorylist}" var="category">
									<div class="row" id="row${category.id}">
									<div class="col-sm-3">
									
                                      	<div class="form-group input-group pointer "><input type="text" class="form-control " value="${category.name}"  disabled  /><span class="input-group-addon" data-toggle="tooltip" title="Delete" data-placement="bottom"><i id="${category.id}" class="delete" ><img src="assets/img/x-button.png" alt="Delete image" width="20" height="20"  ></i></span>
                                    	</div>
                                    	
                                    </div>
                                    </div>
									</c:forEach>
									<div class="row">
											
										<div class="col-sm-3">
											<div class="form-group input-group"><span class="input-group-addon"><i class="fa fa-circle-o-notch"  ></i></span><input type="text" class="form-control" placeholder="Name" name="name" value="" />
											</div>
                                        		</div>
                                        		
                                        		<div class="col-sm-3">
                                        		
                                        	<!-- 	<div class="form-group">
                                        			<select class="form-control" name="categoryId" >
                                            				<option value="" disabled selected>List of available Categories</option>
                                                		<c:forEach items="${categorylist}" var="category">
                                                			<option value="${category.id}" disabled>${category.name}</option>
                                            			</c:forEach>	
                                            					</select>
                                        		</div> -->
                                        		<div class="col-sm-3">
                                        			
                                        		</div>
                                        		</div>
                                       		</div>
                                    
                                    
                                    <input type="submit" value="Add Category" class="btn btn-success ">
                                    </form>
                               
                            </div>
                           
                        </div>
                    </div>
                
                
        </div>
        
                <!-- /. ROW  -->

                <!-- /. ROW  -->

                <!-- /. ROW  -->
        </div>
               
    </div>
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
     <!-- /. WRAPPER  -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <script src="<c:url value="/assets/js/jquery-1.10.2.js" />"></script>
      <!-- BOOTSTRAP SCRIPTS -->
    <script src="<c:url value="/assets/js/bootstrap.min.js" />"></script>
    <!-- METISMENU SCRIPTS -->
 
     <!-- DATA TABLE SCRIPTS -->
    <script src="<c:url value="/assets/js/dataTables/jquery.dataTables.js" />" ></script>
    <script src="<c:url value="/assets/js/dataTables/dataTables.bootstrap.js" />" ></script>
        <script>
        
    </script>
         <!-- CUSTOM SCRIPTS -->
    
    <script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js" />"></script>
    <script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" />"></script>
   <script src="<c:url value="/assets/js/DeleteCategory.js" />"></script>
   <script >
   	$(document).ready(function(){
	   $('[data-toggle="tooltip"]').tooltip();   
   });</script>
</body>
</html>