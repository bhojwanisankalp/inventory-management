<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib uri = "http://www.springframework.org/tags/form" prefix = "mvc"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Free Bootstrap Admin Template : Binary Admin</title>
	<!-- BOOTSTRAP STYLES-->
    <link href="<c:url value="/assets/css/bootstrap.css" />" rel="stylesheet" />
     <!-- FONTAWESOME STYLES-->
    <link href="<c:url value="/assets/css/font-awesome.css" />" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
    <link href="<c:url value="/assets/css/custom.css" />" rel="stylesheet" />
     <!-- GOOGLE FONTS-->
   <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
<style type="text/css">
	body{
	 background-color: #00394d 
	 }
</style>
</head>
<body>
    <div class="container">
        <div class="row text-center ">
            <div class="col-md-12">
                <br /><br />
              

  					


                 <br />
            </div>
        </div>
         <div class="row ">
               
                  <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                        <strong>   Admin Login </strong>  
                            </div>
                            <div class="panel-body">
                                <form role="form" method="post" action="login">
                                       <br />
                                     <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-tag"  ></i></span>
                                            <input type="text" class="form-control" placeholder="Your email " name="email" />
                                        </div>
                                                                              <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
                                            <input type="password" class="form-control"  placeholder="Your Password" name="password" />
                                        </div>
                                    <div class="form-group">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" /> Remember me
                                            </label>
                                          <!--   <span class="pull-right">
                                                   <a href="#" >Forget password ? </a> 
                                            </span> -->
                                        </div>
                                     
                              <!--    <a href="index.html" class="btn btn-primary ">Login Now</a> -->
                              		<input type="submit" value=Login class="btn btn-primary " />
                              		<br>
                                <!--     Not register ? <a href="register" >click here </a> --> 
                                    </form>
                            </div>
                            						<!-- 				  <c:if test="${!empty error}">
             								<font color="red">Error:${error}</font>               
            								</c:if> -->
            								<!--<c:if test="${!empty msg}">
             								<font color="blue">*${msg}</font>               
            								</c:if>-->
            								  <c:if test="${not empty warning}">   
												<div class="alert alert-warning alert-dismissible" >
												<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  												<strong><c:out value="${warning}!"></c:out></strong>
											</div>
											</c:if>
											<c:if test="${not empty msg}">
											<div class="alert alert-success alert-dismissible">
											<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  											<strong><c:out value=" ${msg}!"></c:out></strong>
											</div>
											</c:if>
											<c:if test="${not empty error}">
											<div class="alert alert-danger alert-dismissible">
											<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				  								<strong><c:out value=" ${error}!"></c:out></strong>
											</div>
											</c:if>
 
                        </div>
                    </div>
                
                
        </div>
    </div>


     <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <script src="<c:url value="/assets/js/jquery-1.10.2.js" />" ></script>
      <!-- BOOTSTRAP SCRIPTS -->
    <script src="<c:url value="/assets/js/bootstrap.min.js" />" ></script>
    <!-- METISMENU SCRIPTS -->
    <script src="<c:url value="/assets/js/jquery.metisMenu.js" />" ></script>
      <!-- CUSTOM SCRIPTS -->
    <script src="<c:url value="assets/js/custom.js" />" ></script>
   
</body>
</html>
