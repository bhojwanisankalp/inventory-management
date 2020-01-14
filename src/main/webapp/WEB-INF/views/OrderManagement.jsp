<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
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
<!-- FONTAWESOME STYLES-
    <link href="<c:url value="/assets/css/font-awesome.css" />" rel="stylesheet" /> -->
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" />
<!-- MORRIS CHART STYLES-->

<!-- CUSTOM STYLES-->
<link href="<c:url value="/assets/css/custom.css" />" rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<!-- TABLE STYLES-->
<link
	href="<c:url value="/assets/js/dataTables/dataTables.bootstrap.css" />"
	rel="stylesheet" />
<!-- <link href="<c:url value="/assets/css/ListView.css" />" rel="stylesheet" /> -->
<style type="text/css">
.table {
	display: table;
}

.table-row {
	display: table-row;
}

.table-cell {
	display: table-cell;
	padding: 10px;
}

.table-row:nth-child(even) {
	
}

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


					<li><a href="/Chat-Bot/dashboard"><i
							class="fa fa-dashboard fa-2x"></i>Dashboard</a></li>
					<!--  <li>
                        <a  href="/Chat-Bot/users"><i class="fa fa-desktop fa-2x"></i> User Management</a>
                    </li> -->
					<c:if test="${sessionScope.role == 'admin'}">
						<li><a class="" href="users"><i
								class="fa fa-desktop fa-2x"></i> User Management</a></li>
					</c:if>
					<c:if test="${sessionScope.role == 'superadmin'}">
						<li><a href="#"><i class="fa fa-desktop fa-2x"></i> User
								Management<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="/Chat-Bot/users"><i
										class="fa fa-user fa-2x" aria-hidden="true"></i> Admin
										Management</a></li>
								<li><a href="agents"><i class="fa fa-user-secret fa-2x"
										aria-hidden="true"> </i>Agents Management</a></li>

							</ul></li>
					</c:if>
					<li><a href="/Chat-Bot/product"><i
							class="fa fa-qrcode fa-2x"></i> Product Management</a></li>
					<li><a href="productQuantity"><i
							class="fa fa-barcode fa-2x"></i> Product Quantity</a></li>
					<li><a class="active-menu" href="orderManagement"><i
							class="fa fa-shopping-cart fa-2x"></i> Order Management</a></li>

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
		<div id="page-wrapper"
			style="background-image: url(https://i.ibb.co/W3QBCJ8/Cannabot-backgraound.jpg)">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<h2 style="font-style: normal; font-weight: bold;">Order
							Management</h2>
						<h5>
							<c:out value=" Welcome ${sessionScope.name}"></c:out>
							, Love to see you back.
						</h5>

					</div>
				</div>
				<!-- /. ROW  -->
				<hr />
				<div class="row">
					<div class="col-md-12">
						<!-- Orders Table start -->
						<div class="panel panel-default">
							<div class="panel-heading">Order's Table</div>
							<div class="panel-body">



								<div class="table-responsive">
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
									<div class="dataTables_length" id="dataTables_length">
										<label> <select name="dataTable_length"
											aria-controls="dataTable"
											class="custom-select custom-select-sm form-control form-control-sm"
											onchange="location = this.value;">
												<c:if test="${limit == 10}">
													<option value="" selected>10</option>
													<option
														value="<c:url value="ordersNextPage?startpage=0&limit=25&currentPageNumber=0&pageCount=${pageCount}" /> ">
														25</option>
													<option
														value="<c:url value="ordersNextPage?startpage=0&limit=50&currentPageNumber=0&pageCount=${pageCount}" /> ">
														50</option>
												</c:if>
												<c:if test="${limit == 25}">
													<option
														value="<c:url value="ordersNextPage?startpage=0&limit=10&currentPageNumber=${currentPageNumber}&pageCount=${pageCount}" /> ">
														10</option>
													<option value="" selected>25</option>
													<option
														value="<c:url value="ordersNextPage?startpage=0&limit=50&currentPageNumber=0&pageCount=${pageCount}" /> ">
														50</option>
												</c:if>
												<c:if test="${limit == 50}">
													<option
														value="<c:url value="ordersNextPage?startpage=0&limit=10&currentPageNumber=0&pageCount=${pageCount}" /> ">
														10</option>
													<option
														value="<c:url value="ordersNextPage?startpage=0&limit=25&currentPageNumber=0&pageCount=${pageCount}" /> ">
														25</option>
													<option value="" selected>50</option>
												</c:if>
										</select>


										</label>


										<div id="dataTable_filter" class="dataTables_filter">
											<label>
												<form name="" action="searchOrder" method="GET">
													Search <span><button type="submit">
															<i class="fa fa-search"></i>
														</button></span><input id="myInput" type="search"
														placeholder="Enter Order id" name="search" required>
												</form>
											</label>

										</div>




									</div>
									<!-- Main table start -->
									<table class="table table-striped table-bordered table-hover"
										id="">
										<c:if test="${not empty orderslist}">
											<thead>
												<tr>

													<th class="th-hover">Order-ID</th>

													<th class="th-hover">Customer Name</th>
													<th class="th-hover">Customer Email</th>
													<th class="th-hover">Products</th>
													<!--   <th class="th-hover">For Weight</th>-->
													<!--   <th class="th-hover">Creation Time</th>
                                            <th class="th-hover">Update Time</th> -->

													<th class="th-hover">Total Amount(in $)</th>
													<th class="th-hover">Order Status</th>
													<th class="th-hover">Order Date</th>
													<th></th>

												</tr>
											</thead>
											<tbody>
												<%! int count =1; %>
												<c:forEach items="${orderslist}" var="order">
													<tr class="odd gradeX">

														<td>${order.id}</td>
														<td>${order.userName}</td>
														<td>${order.userEmail}</td>
														<td>${order.productNames}</td>
														<td>${order.totalAmount}</td>
														<c:if test="${order.orderStatus == 0}">
															<td>In Cart</td>
														</c:if>
														<c:if test="${order.orderStatus == 1}">
															<td>Payment Pending</td>
														</c:if>
														<c:if test="${order.orderStatus == 2}">
															<td>Payment Completed</td>
														</c:if>
														<c:if test="${order.orderStatus == 3}">
															<td>Ready to Dispatch</td>
														</c:if>
														<c:if test="${order.orderStatus == 4}">
															<td>Dispatched</td>
														</c:if>
														<c:if test="${order.orderStatus == 5}">
															<td>Delivered</td>
														</c:if>
														<td>${order.createdAt}</td>
														<td><button type="button"
																class="btn btn-primary btn-sm" data-toggle="modal"
																data-target="#DetailsModal${order.id}">Details</button></td>
														<!--  <button class="btn btn-primary"><i class="fa fa-edit "></i> Edit</button> -->
													</tr>
													<% count++; %>

												</c:forEach>
											</tbody>
										</c:if>


									</table>
									<!-- Main Table end -->
									<c:forEach items="${orderslist}" var="order">
									<!-- Order Details Modal start -->
										<div class="modal fade" id="DetailsModal${order.id}"
											role="dialog">
											<div class="modal-dialog">

												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal">&times;</button>
														<h4>
															<span class="glyphicon glyphicon-qrcode"></span>
														</h4>
													</div>
													<form name="quant" role="form" action="OrderStatus"
														method="POST" onsubmit="myButton.disabled = true; return true;">
														<div class="modal-body">
															<div class="form-group">
																<input type="hidden" class="form-control"
																	value="${order.id}" name="orderId" readonly>
															</div>

															<div class="table-responsive">
																<table class="table table-condensed">
																	<thead>
																		<tr>
																			<td><strong>Name</strong></td>
																			<td class="text-center"><strong>Weight</strong></td>
																			<td class="text-center"><strong>Ordered
																					Quantity</strong></td>
																			<td class="text-right"><strong>Price(In $)</strong></td>
																		</tr>
																	</thead>
																	<tbody>
																		<!-- foreach ($order->lineItems as $line) or some such thing here -->
																		<c:forEach items="${order.product_ordered}"
																			var="product_ordered">
																			<tr>
																				<td>${product_ordered.name}</td>
																				<td class="text-center">${product_ordered.weight}</td>
																				<td class="text-center">${product_ordered.quantitySelected}</td>
																				<td class="text-right">${product_ordered.price}</td>
																			</tr>
																		</c:forEach>

																		<tr>
																			<td class="thick-line"></td>
																			<td class="thick-line"></td>
																			<td class="thick-line text-center"><strong>Subtotal</strong></td>
																			<td class="thick-line text-right">${order.totalAmount}</td>
																		</tr>
																		<tr>
																			<td class="no-line"></td>
																			<td class="no-line"></td>
																			<td class="no-line text-center"><strong>Shipping</strong></td>
																			<td class="no-line text-right">0</td>
																		</tr>
																		<tr>
																			<td class="no-line"></td>
																			<td class="no-line"></td>
																			<td class="no-line text-center"><strong>Total</strong></td>
																			<td class="no-line text-right">${order.totalAmount}</td>
																		</tr>
																	</tbody>
																</table>
															</div>
															</div>
															<div class="modal-footer">
																<div class="table-row">
																	<div class="table-cell">
																		<c:if test="${order.orderStatus == 0}">
																			<select class="form-control" name="orderStatus"
																				readonly>
																				<option value="0" selected>Still In Cart</option>
																			</select>
																		</c:if>
																		<c:if test="${order.orderStatus != 0}">
																			<select class="form-control" name="orderStatus">

																				<c:if test="${order.orderStatus == 1}">

																					<option value="1" selected>Payment Pending</option>
																					<option value="2">Payment Completed</option>
																					<option value="3">Ready to Dispatch</option>
																					<option value="4">Dispatched</option>
																					<option value="5">Delivered</option>
																				</c:if>
																				<c:if test="${order.orderStatus == 2}">

																					<option value="1">Payment Pending</option>
																					<option value="2" selected>Payment
																						Completed</option>
																					<option value="3">Ready to Dispatch</option>
																					<option value="4">Dispatched</option>
																					<option value="5">Delivered</option>
																				</c:if>
																				<c:if test="${order.orderStatus == 3}">
																					<option value="1">Payment Pending</option>
																					<option value="2">Payment Completed</option>
																					<option value="3" selected>Ready to
																						Dispatch</option>
																					<option value="4">Dispatched</option>
																					<option value="5">Delivered</option>
																				</c:if>
																				<c:if test="${order.orderStatus == 4}">
																					<option value="1">Payment Pending</option>
																					<option value="2">Payment Completed</option>
																					<option value="3">Ready to Dispatch</option>
																					<option value="4" selected>Dispatched</option>
																					<option value="5">Delivered</option>
																				</c:if>
																				<c:if test="${order.orderStatus == 5}">
																					<option value="1">Payment Pending</option>
																					<option value="2">Payment Completed</option>
																					<option value="3">Ready to Dispatch</option>
																					<option value="4">Dispatched</option>
																					<option value="5" selected>Delivered</option>
																				</c:if>
																			</select>
																		</c:if>
																	</div>
																	<div class="table-cell">
																		<input type="submit" class="btn btn-default"
																			value="Update" name="myButton">
																	</div>
																</div>
															</div>
													</form>
												</div>
											</div>
										</div>
									<!-- Order Details Modal end -->
									</c:forEach>

									<div class="pagination">
										<c:if test="${currentPageNumber > 0}">
											<a
												href="<c:url value="ordersNextPage?startpage=${currentPageNumber - 1}&limit=${limit}&currentPageNumber=${currentPageNumber - 1}&pageCount=${pageCount}" /> ">&laquo;</a>
										</c:if>
										<a href="" class="active"><c:out
												value="${currentPageNumber + 1}" /></a>
										<c:if test="${currentPageNumber lt pageCount - 1}">
											<a
												href="<c:url value="ordersNextPage?startpage=${currentPageNumber + 1}&limit=${limit}&currentPageNumber=${currentPageNumber + 1 }&pageCount=${pageCount}" />">&raquo;</a>
										</c:if>
									</div>
									<p>Total pages ${pageCount}</p>
								</div>

							</div>
						</div>
						<!--Orders table end -->
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
	<script>
            $(document).ready(function () {
                $('#dataTables-example').dataTable({
                	
                });
            });
    </script>
	<!-- CUSTOM SCRIPTS -->
	<script src="<c:url value="/assets/js/custom.js" />"></script>


</body>
</html>

