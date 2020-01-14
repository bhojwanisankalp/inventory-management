<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ page session="true"%>
<c:if test="${sessionScope.userId == null}">
	<% response.sendRedirect("invaliduser"); %>
</c:if>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Cannabis bot Admin</title>
<!-- BOOTSTRAP STYLES-->
<link href="<c:url value="/assets/css/bootstrap.css" />"
	rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link href="<c:url value="/assets/css/font-awesome.css" />"
	rel="stylesheet" />
<!-- MORRIS CHART STYLES
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />-->
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link href="<c:url value="/assets/css/custom.css" />" rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<!-- TABLE STYLES-->
<link
	href="<c:url value="/assets/js/dataTables/dataTables.bootstrap.css" />"
	rel="stylesheet" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>

.pagination {
	display: inline-block;
}

.pagination a {
	color: black;
	float: left;
	padding: 8px 16px;
	text-decoration: none;
}

.pagination a.active {
	background-color: #4CAF50;
	color: white;
}

.pagination a:hover:not (.active ) {
	background-color: #ddd;
}
table.a {
  table-layout: auto;
  width: 180px;  
}
.body{
 
}
</style>
</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-cls-top " role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="dashboard">Admin</a>
			</div>
			<div
				style="color: white; padding: 15px 50px 5px 50px; float: right; font-size: 16px;">
				&nbsp; <a href="logout" class="btn btn-danger square-btn-adjust">Logout</a>
			</div>
		</nav>
		<!-- /. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="main-menu">
					<li class="text-center"><img src="assets/img/find_user.png"
						class="user-image img-responsive" /></li>


					<li><a href="dashboard"><i class="fa fa-dashboard fa-2x"></i>
							Dashboard</a></li>
					<c:if test="${sessionScope.role == 'admin'}">
					 <li><a class="active-menu" href="users"><i
							class="fa fa-desktop fa-2x"></i> User Management</a></li> 
					</c:if>
					<c:if test="${sessionScope.role == 'superadmin'}">
					<li>
                        <a href="#" id="open-dropdown"><i class="fa fa-desktop fa-2x"></i> User Management<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li >
                                <a href="users" class="active-menu"><i class="fa fa-user fa-2x" aria-hidden="true"></i> Admin Management</a>
                            </li>
                            <li>
                                <a href="agents"><i class="fa fa-user-secret fa-2x" aria-hidden="true"> </i>Agents Management</a>
                            </li>
                           
                        </ul>
                      </li> 
                      </c:if>
					<li><a href="product"><i class="fa fa-qrcode fa-2x"></i>
							Product Management</a></li>
					<li><a href="productQuantity"><i
							class="fa fa-barcode fa-2x"></i> Product Quantity</a></li>
					<li><a href="orderManagement"><i
							class="fa fa-shopping-cart fa-2x"></i> Order Management</a></li>

					   

				</ul>

			</div>

		</nav>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper" style="background-image:url(https://i.ibb.co/W3QBCJ8/Cannabot-backgraound.jpg)">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<h2 style="font-style:normal; font-weight: bold;">User Management</h2>
						<h5>
							<c:out value="Welcome ${sessionScope.name }"></c:out>
							, Love to see you back.
						</h5>

					</div>
				</div>
				<!-- /. ROW  -->
				<hr />

				<div class="row">
					<div class="col-md-12">
						<!-- Advanced Tables -->
						<div class="panel panel-default">
							<div class="panel-heading">User's Table</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12">
										<c:if test="${sessionScope.role == 'superadmin'}">
											<a href="<c:url value='add' />" class="btn btn-success"><i
												class="fa fa-edit "></i>Add User</a>
										</c:if>
									</div>
								</div>
								<br>

								<div class="table-responsive">
								<div id="afterStatusUpdate"></div>
								<!-- Messages after actions -->
									<c:if test="${not empty warning}">
										<div class="alert alert-warning alert-dismissible">
											<a href="#" class="close" data-dismiss="alert"
												aria-label="close">&times;</a> <strong><c:out
													value="${warning}!"></c:out></strong>
										</div>
									</c:if>
									<c:if test="${not empty success}">
										<div class="alert alert-success alert-dismissible">
											<a href="#" class="close" data-dismiss="alert"
												aria-label="close">&times;</a> <strong><c:out
													value=" ${success}!"></c:out></strong>
										</div>
									</c:if>
									<c:if test="${not empty error}">
										<div class="alert alert-danger alert-dismissible">
											<a href="#" class="close" data-dismiss="alert"
												aria-label="close">&times;</a> <strong><c:out
													value=" ${error}!"></c:out></strong>
										</div>
									</c:if>
									<!-- Messages after actions end-->
									<!-- Table division selector -->
									<div class="dataTables_length" id="dataTables_length">
										<label> <select name="dataTable_length"
											aria-controls="dataTable"
											class="custom-select custom-select-sm form-control form-control-sm"
											onchange="location = this.value;">
												<c:if test="${limit == 10}">
													<option value="" selected>10</option>
													<option
														value="<c:url value="/usersNextPage?startpage=0&limit=25&currentPageNumber=0&pageCount=${pageCount}" /> ">
														25</option>
													<option
														value="<c:url value="/usersNextPage?startpage=0&limit=50&currentPageNumber=0&pageCount=${pageCount}" /> ">
														50</option>
												</c:if>
												<c:if test="${limit == 25}">
													<option
														value="<c:url value="/usersNextPage?startpage=0&limit=10&currentPageNumber=${currentPageNumber}&pageCount=${pageCount}" /> ">
														10</option>
													<option value="" selected>25</option>
													<option
														value="<c:url value="/usersNextPage?startpage=0&limit=50&currentPageNumber=0&pageCount=${pageCount}" /> ">
														50</option>
												</c:if>
												<c:if test="${limit == 50}">
													<option
														value="<c:url value="/usersNextPage?startpage=0&limit=10&currentPageNumber=0&pageCount=${pageCount}" /> ">
														10</option>
													<option
														value="<c:url value="/usersNextPage?startpage=0&limit=25&currentPageNumber=0&pageCount=${pageCount}" /> ">
														25</option>
													<option value="" selected>50</option>
												</c:if>
										</select>


										</label>



										<c:if test="${not empty msg}">
											<font color="blue">* ${msg}</font>
										</c:if>


									</div>
									<!-- Table division selector end -->
									<!-- Search filter form -->
									<div id="dataTable_filter" class="dataTables_filter">
										<label>
											<form name="" action="searchUser">
												Search <span><button type="submit">
														<i class="fa fa-search"></i>
													</button></span><input id="myInput" class=""
													placeholder="Enter name, email or contact" name="search"
													value="<c:if test="${not empty search }">${search }</c:if>"
													required>

											</form>
										</label>

									</div>
									<!-- Search filter form end-->
									<!-- User table -->
									<table class="table table-striped table-bordered table-hover"
										id="">
										<c:if test="${not empty listUsers}">
											<thead>
												<tr>
													<th class="th-hover">Name</th>
													<!--<th class="th-hover">Contact</th>
                                            <th class="th-hover">Address</th>
                                            
                                              <th class="th-hover">Date of Birth<br>(YYYY-MM-DD)</th>-->
													<th class="th-hover">Email</th>
													<th class="th-hover">Role</th>
													<th class="th-hover">Status</th>

													<th class="th-hover"></th>
													<th class="th-hover"></th>
													<c:if test="${sessionScope.role eq 'superadmin'}">
														<th class="th-hover"></th>
													</c:if>
												</tr>
											</thead>
											<tbody>
												<%! int count =1; %>
												<c:forEach items="${listUsers}" var="user">
													<tr class="odd gradeX">
														<td>${user.name}</td>
														<!--<td>${user.contact}</td>
                                           		<td>${user.address}</td>
                                            	
                                            	  <td>${user.dob}</td>-->
														<td>${user.email}</td>
														<td>${user.role}</td>
														<c:if test="${user.accountStatus}">
															<td id="statusChange${user.id}">Enabled</td>
														</c:if>
														<c:if test="${not user.accountStatus}">
															<td id="statusChange${user.id}">Disabled</td>
														</c:if>
														<td><button class="btn btn-info" data-toggle="modal"
																data-target="#details${user.id}">
																<i class="fa fa-info "></i> Info
															</button></td>
														<td><a href="<c:url value='/edit/${user.id}' />"
															class="btn btn-primary"><i class="fa fa-edit "></i> Edit</a></td>
														<!--<a href="#" data-href="<c:url value='/remove/${user.id}' />" data-toggle="modal" data-target="#confirm-delete" >Delete</a>-->
														<c:if test="${sessionScope.role eq 'superadmin'}">
															<td>
																<button class="btn btn-danger delete"
																	data-href="<c:url value='/remove/${user.id}' />"
																	data-toggle="modal" data-target="#confirm-delete">
																	<i class="fa fa-trash"></i> Delete
																</button>
															</td>
														</c:if>

														<!-- <button class="btn btn-primary"><i class="fa fa-edit "></i> Edit</button> -->
													</tr>
													<% count++; %>
												<!-- Confirm delete modal -->
													<div class="modal fade" id="confirm-delete" tabindex="-1"
														role="dialog" aria-labelledby="myModalLabel"
														aria-hidden="true">
														<div class="modal-dialog">
															<div class="modal-content">
																<!--<div class="modal-header">
                										...
            										</div>-->
																<div class="modal-body">Are you sure you want to
																	remove this record?</div>
																<div class="modal-footer">
																	<button type="button" class="btn btn-default"
																		data-dismiss="modal">Cancel</button>
																	<a class="btn btn-danger btn-ok">Delete</a>
																</div>
															</div>
														</div>
													</div>
													<!-- Confirm delete modal end-->
												</c:forEach>

											</tbody>
										</c:if>



									</table>
									<!-- User table end-->
									
									<!-- User Details modal -->
									<c:forEach items="${listUsers}" var="user">
										<div  class="modal" id="details${user.id}" tabindex="-1"
											role="dialog" aria-labelledby="myModalLabel"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">
															<i class="text-danger fa fa-times"></i>
														</button>
														<h4 class="modal-title" id="myModalLabel">
															<i class="text-muted fa fa-user"></i> <strong>${user.name}</strong>
															
														</h4>
													</div>
														
													<div class="modal-body">
													<div id="afterStatusUpdate${user.id}"></div>
														<div class="col-md-4">
															<img src='<c:url value="/assets/img/UserIcon.png" />'
																width="150" height="150">
														</div>
														
														<div class="table-responsive">
														<table class="table table-hover">
															<tbody>
																<tr>
																	<td class="h6"><i class="fa fa-phone"></i></td>
																	<td></td>
																	<td class="h5">${user.contact}</td>
																</tr>
																
																<tr>
																	<td class="h6"><i class="fa fa-envelope"></i></td>
																	<td></td>
																	<td class="h5">${user.email}</td>
																</tr>

																<tr>
																	<td class="h6"><i class="fa fa-home"></i></td>
																	<td></td>
																	<td class="h5" >${user.address }</td>
																</tr>

																<tr>
																	<td class="h6"><i class="fa fa-user"></i></td>
																	<td></td>
																	<td class="h5">${user.role}</td>
																</tr>

																<tr>
																
																	
																	<c:if test="${user.accountStatus}">
																	<td class="h6" id="statusUpdatIcon${user.id }"><i class="fa fa-thumbs-up"></i></td>
																	<td></td>
																	<td class="h5" id="statusChangeModal${user.id}"><span id="replaceme">Enabled</span></td>
																	</c:if>
																	<c:if test="${not user.accountStatus}">
																	<td class="h6" id="statusUpdatIcon${user.id }"><i class="fa fa-thumbs-down"></i></td>
																	<td></td>
																	<td class="h5" id="statusChangeModal${user.id}">Disabled</td>
																	</c:if>
																</tr>

																

															</tbody>
														</table>
														</div>

														
														
														
													</div>
								
													<div class = "modal-footer">
														<c:if test="${sessionScope.role eq 'superadmin' }">
															<div class = "row">
															<div class = "col-sm-3">
																<select class="form-control status-update" id="${user.id}">
																	<c:if test="${user.accountStatus }">
																		<option value="true" selected>Enabled</option>
																		<option value="false" >Disable</option>
																	</c:if>
																	<c:if test="${not user.accountStatus}">
																		<option value="false" selected>Disabled</option>
																		<option value="true" >Enable</option>
																	</c:if>
																</select>
															</div>
															<div class = "col-sm-3 pull-right">
															<input type = "button" class = "btn btn-info"  value="Update" id="updateButton${user.id }"/>
															</div>
															</div>
														</c:if>
													</div>
													<!-- User Details modal End-->
												</div>
											</div>
										</div>
									</c:forEach>
									<div class="col-md-3">
										<div class="pagination">
											<c:if test="${currentPageNumber > 0}">
												<a
													href="<c:url value="/usersNextPage?startpage=${currentPageNumber - 1}&limit=${limit}&currentPageNumber=${currentPageNumber - 1}&pageCount=${pageCount}" /> ">&laquo;</a>
											</c:if>
											<a href="" class="active"><c:out
													value="${currentPageNumber + 1}" /></a>
											<c:if test="${currentPageNumber lt pageCount - 1}">
												<a
													href="<c:url value="/usersNextPage?startpage=${currentPageNumber + 1}&limit=${limit}&currentPageNumber=${currentPageNumber + 1 }&pageCount=${pageCount}" />">&raquo;</a>
											</c:if>
										</div>
										<p>Total pages ${pageCount}</p>
									</div>

								</div>

							</div>
						</div>
						<!--End Advanced Tables -->
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
	<script src="<c:url value="/assets/js/jquery.metisMenu.js" />"></script>
	<!-- DATA TABLE SCRIPTS -->
	<script
		src="<c:url value="/assets/js/dataTables/jquery.dataTables.js" />"></script>
	<script
		src="<c:url value="/assets/js/dataTables/dataTables.bootstrap.js" />"></script>
	<script src="<c:url value="/assets/js/UpdateStatus.js" />"></script>
	<script>
            $(document).ready(function () {
            	$("#open-dropdown").click();
              
            });
            $('#confirm-delete').on('show.bs.modal', function(e) {
                $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
            });
    </script>
	<!-- CUSTOM SCRIPTS -->
	



</body>
</html>

