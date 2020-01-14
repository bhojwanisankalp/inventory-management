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
font-size: 16px;"> &nbsp; <a href="/Chat-Bot/logout" class="btn btn-danger square-btn-adjust">Logout</a> </div>
        </nav>   
           <!-- /. NAV TOP  -->
                <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
            <!-- Menu Items -->
                <ul class="nav" id="main-menu">
				<li class="text-center">
                    <img src="<c:url value="/assets/img/find_user.png" />" class="user-image img-responsive"/>
					</li>
				
					
                    <li>
                        <a  href="/Chat-Bot/dashboard"><i class="fa fa-dashboard fa-2x"></i>Dashboard</a>
                    </li>
                   <!-- <li>
                        <a class="active-menu" href="/Chat-Bot/users"><i class="fa fa-desktop fa-2x"></i> User Management</a>
                    </li>-->
                     <c:if test="${sessionScope.role == 'admin'}">
					 <li><a class="" href="users"><i
							class="fa fa-desktop fa-2x"></i> User Management</a></li> 
					</c:if>
					<c:if test="${sessionScope.role == 'superadmin'}">
					<li>
                        <a href="#" id="open-dropdown"><i class="fa fa-desktop fa-2x"></i> User Management<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li >
                                <a href="/Chat-Bot/users"  ><i class="fa fa-user fa-2x" aria-hidden="true"></i> Admin Management</a>
                            </li>
                            <li>
                                <a href="#" class="active-menu"><i class="fa fa-user-secret fa-2x" aria-hidden="true"> </i>Agents Management</a>
                            </li>
                           
                        </ul>
                      </li> 
                      </c:if>
                    <li>
                        <a  href="/Chat-Bot/product"><i class="fa fa-qrcode fa-2x"></i> Product Management</a>
                    </li>
					   <li>
                        <a  href="/Chat-Bot/productQuantity"><i class="fa fa-barcode fa-2x"></i> Product Quantity</a>
                    </li>
					<li>
                        <a  href="/Chat-Bot/orderManagement"><i class="fa fa-shopping-cart fa-2x"></i> Order Management</a>
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
            <!-- Menu Items End -->
        </nav>  
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" style="background-image:url(https://i.ibb.co/W3QBCJ8/Cannabot-backgraound.jpg)">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                    <c:if test="${sessionScope.role == 'superadmin'}">
						<a href="<c:url value='/agents' />" class="btn btn-info">Back</a>
					</c:if>
                     <h2 style="font-style:normal; font-weight: bold;">Agents Management</h2>   
                        <h5><c:out value="Welcome ${sessionScope.name }"></c:out>, Love to see you back. </h5>
                       
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
               
         <div class="row">
               
                <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                            
                            <!-- Table Heading Update -->
                            <c:if test="${not empty user}">
                        <strong>  Update Agent </strong>  
                        	</c:if>
                        	<!-- Table Heading Update End-->
                        	
                        	<!-- Table Heading Add -->
                        	<c:if test="${ empty user or not empty userAdd}">
                        <strong>  Add Agent </strong>  
                        	</c:if>
                        	<!-- Table Heading Add End -->
                            </div>
                            <div class="panel-body">
                            <c:if test="${not empty user}">
                            
                            
                            <!-- Update Agent Form -->
                               <form role="form" action="<c:url value='/updateAgent/${user.id}' />" method="POST">
<br/>										
                                        <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-user"  ></i></span>
                                            <input type="text" class="form-control" placeholder="First Name" name="firstname" value="${user.firstName}" required/>
                                        </div>
                                        <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-user"  ></i></span>
                                            <input type="text" class="form-control" placeholder="Last Name" name="lastname" value="${user.lastName}" required/>
                                        </div>
                                     <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-tag"  ></i></span>
                                            <input type="text" class="form-control" placeholder="Contact number" name="contact" value="${user.contact}" />
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon"><i class="fa fa-road" ></i></span>
                                            <input type="text" class="form-control" placeholder="Street" name="street" value="${user.street}" required/>
                                        </div>
                                         <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-building-o"></i></span>
                                            <input type="text" class="form-control" placeholder="City" name="city" value="${user.city}" required/>
                                        </div>
                                         <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-flag" ></i></span>
                                            <input type="text" class="form-control" placeholder="State" name="state" value="${user.state}" required/>
                                        </div>
                                        <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-map-pin " ></i></span>
                                            <input type="number" class="form-control" placeholder="Zip" name="zip" value="${user.zip}" required/>
                                        </div>
                                        <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-calendar"  ></i></span>
                                            <input type="date" class="form-control" placeholder="Date of Birth"  name="dob" value="${user.dob}" />
                                        </div>
                                          
                                            <input type="hidden" class="form-control" placeholder="Email" name="email" value="${user.email}" />
                                 			<input type="hidden" class="form-control" placeholder="role" name="role" value="${user.role}" />
                                        <!--  <c:if test="${sessionScope.role == 'admin' }">
                                         <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-circle-o-notch"  ></i></span>
                                            <input type="text" class="form-control" name="role" value="${user.role}" disabled />
                                        </div>
                                        </c:if>
                                        <c:if test="${sessionScope.role == 'superadmin'}" >
                                        <div class="form-group input-group">
                                        <span class="input-group-addon"><i class="fa fa-circle-o-notch"  ></i></span>
                                        <select class="form-control" name="role"  >
                                                <option value="${user.role}" selected>${user.role}</option>
                                            	<option value="superadmin" >Super Admin</option>
                                            	<option value="admin" >Admin</option>
                                            	<option value="user" >User</option>
                                            								
                                        </select>
                                        </div>
                                        </c:if>
                                     <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
                                            <input type="password" class="form-control" placeholder="Enter new Password" name="password" id="passwordinput" />
                                        </div>
                                     <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
                                            <input type="password" class="form-control" placeholder="Retype Password" id="ConfirmPassword" />
                                        </div>
                                        -->
                                     <input type="submit" value="Edit Agents" class="btn btn-success " >
                                    <!--   <a href="index.html" class="btn btn-success ">Register Me</a> -->
                                    <hr />
                                    </form>
                            </c:if>
                            
                            
                            <!-- Update Agent Form END-->
                           
                            <!-- Add Agent Form -->
                            <c:if test="${empty user or not empty userAdd}">
                            
                              <form role="form" action="addAgent" method="POST">
<br/>
                                        <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-user"  ></i></span>
                                            <input type="text" class="form-control" placeholder="First Name" name="firstname" value="${userAdd.firstName}" required/>
                                        </div>
                                        <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-user"  ></i></span>
                                            <input type="text" class="form-control" placeholder="Last Name" name="lastname" value="${userAdd.lastName}" required/>
                                        </div>
                                        <c:if test="${not empty errorContact}">
                                     	<div class="form-group has-error">
                                            <label class="control-label" for="inputError">Contact Already Exist</label>                                            
                                            <input type="text" class="form-control" placeholder="Contact number" name="contact" value="${userAdd.contact}" id="inputError" required/>
                                        </div>
                                        </c:if>
                                        <c:if test="${ empty errorContact}">
                                       <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-tag"  ></i></span>
                                            <input type="text" class="form-control" placeholder="Contact number" name="contact" value="${userAdd.contact}" required/>
                                        </div>
                                        </c:if>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon"><i class="fa fa-road" ></i></span>
                                            <input type="text" class="form-control" placeholder="Street" name="street" value="${userAdd.street}" required/>
                                        </div>
                                         <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-building-o"></i></span>
                                            <input type="text" class="form-control" placeholder="City" name="city" value="${userAdd.city}" required/>
                                        </div>
                                         <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-flag" ></i></span>
                                            <input type="text" class="form-control" placeholder="State" name="state" value="${userAdd.state}" required/>
                                        </div>
                                        <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-map-pin " ></i></span>
                                            <input type="number" class="form-control" placeholder="Zip" name="zip" value="${userAdd.zip}" required/>
                                        </div>
                                        
                                        <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-calendar"  ></i></span>
                                            <input type="date" class="form-control" placeholder="Date of Birth"  name="dob" value="${ userAdd.dob}" required/>
                                        </div>
                                         <c:if test="${not empty errorEmail}">
                                         <div class="form-group has-error">
                                         	<label class="control-label" for="inputError">Email Already Exist</label>
                                            
                                            <input type="text" class="form-control" placeholder="Email" name="email" value="${userAdd.email}" id="inputError" required/>
                                        </div>
                                        </c:if>
                                        <c:if test="${empty errorEmail}">
                                        <div class="form-group input-group">
                                            <span class="input-group-addon">@</span>
                                            <input type="email" class="form-control" placeholder="Email" name="email" value="${userAdd.email}" required/>
                                        </div>
                                        </c:if>
                                     <!--    <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-circle-o-notch"  ></i></span>
                                            <select class="form-control" name="role" class="required" >
                                                <option value="">Select role</option>
                                            	<option value="superadmin" >Super Admin</option>
                                            	<option value="admin" >Admin</option>
                                            	<option value="user" >User</option>
                                            								
                                            							</select>
                                        </div>
                                      <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
                                            <input type="password" class="form-control" placeholder="Enter Password" name="password" id="passwordinput" required/>
                                        </div>
                                     <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
                                            <input type="password" class="form-control" placeholder="Retype Password"  id="ConfirmPassword"/>
                                        </div>
                                        --> 
                                     <input type="submit" value="Add Agent" class="btn btn-success " >
                                    <!--   <a href="index.html" class="btn btn-success ">Register Me</a> -->
                                    <hr />
                                    </form>
                            </c:if>
                             <!-- Add Agent Form End-->
                            </div>
                           
                        </div>
                    </div>
                
                
        </div>
                
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
    <script src="<c:url value="/assets/js/jquery.metisMenu.js" />"></script>
     <!-- DATA TABLE SCRIPTS -->
    <script src="<c:url value="/assets/js/dataTables/jquery.dataTables.js" />" ></script>
    <script src="<c:url value="/assets/js/dataTables/dataTables.bootstrap.js" />" ></script>
    <script src="<c:url value="/assets/js/validation.js" />" ></script>
        <script>
        $(document).ready(function () {
            $("#open-dropdown").click();
        });
            $(document).ready(function () {
                $('#dataTables-example').dataTable({
                	
                });
            });
    </script>
         <!-- CUSTOM SCRIPTS -->
    <script src="<c:url value="/assets/js/custom.js" />" ></script>
    
   
</body>
</html>

