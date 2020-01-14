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
    <link href="<c:url value="/assets/css/font-awesome.css" />" rel="stylesheet" />-->
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
     <!-- MORRIS CHART STYLES-->
   
        <!-- CUSTOM STYLES-->
    <link href="<c:url value="/assets/css/custom.css" />" rel="stylesheet" />
     <!-- GOOGLE FONTS-->
   <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
     <!-- TABLE STYLES-->
    <link href="<c:url value="/assets/js/dataTables/dataTables.bootstrap.css" />" rel="stylesheet" />
   <!--  <link href="<c:url value="/assets/css/ListView.css" />" rel="stylesheet" /> -->
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

.pagination a:hover:not(.active) {background-color: #ddd;}
			
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
				<li class="text-center">
                    <img src="assets/img/find_user.png" class="user-image img-responsive"/>
					</li>
				
					
                    <li>
                        <a  href="dashboard"><i class="fa fa-dashboard fa-2x"></i> Dashboard</a>
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
                                <a href="agents"><i class="fa fa-user-secret fa-2x" aria-hidden="true"> </i> Agents Management</a>
                            </li>
                           
                        </ul>
                      </li> 
                      </c:if>
                    <li>
                        <a class="active-menu" href="product"><i class="fa fa-qrcode fa-2x"></i> Product Management</a>
                    </li>
                       <li>
                        <a  href="productQuantity"><i class="fa fa-barcode fa-2x"></i> Product Quantity</a>
                    </li>
					<li>
                        <a  href="orderManagement"><i class="fa fa-shopping-cart fa-2x"></i> Order Management</a>
                    </li>
					                   
                 <!--    <li>
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
                     <h2 style="font-style:normal; font-weight: bold;">Product Management</h2>   
                        <h5><c:out value="Welcome ${sessionScope.name }"></c:out>, Love to see you back. </h5>
                       
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
               
            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                             Product's Table
                        </div>
                        <div class="panel-body">
                         <a href="<c:url value='addProduct' />" class="btn btn-success" ><i class="fa fa-edit "></i>Add Product</a>
                         <a href="<c:url value='category' />" class="btn btn-primary" ><i class="fa fa-edit "></i>Add Category</a>
                            <br>
                            <br>
                            <div class="table-responsive">
                            <c:if test="${not empty warning}">   
							<div class="alert alert-warning alert-dismissible" >
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  								<strong><c:out value="${warning}!"></c:out></strong>
							</div>
							</c:if>
							<c:if test="${not empty success}">
							<div class="alert alert-success alert-dismissible">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  								<strong><c:out value=" ${success}!"></c:out></strong>
							</div>
							</c:if>
							<c:if test="${not empty error}">
							<div class="alert alert-danger alert-dismissible">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  								<strong><c:out value=" ${error}!"></c:out></strong>
							</div>
							</c:if>
                              <div class="dataTables_length" id ="dataTables_length">
                                    <label>
                                        
                                        <select name="dataTable_length" aria-controls="dataTable" class="custom-select custom-select-sm form-control form-control-sm" onchange="location = this.value;" >
                                            <c:if test="${limit == 10}">
                                            <option value="" selected>
                                            10</option>
                                            <option value="<c:url value="productNextPage?startpage=0&limit=25&currentPageNumber=0&pageCount=${pageCount}" /> " >
                                            25</option >
                                            <option value="<c:url value="productNextPage?startpage=0&limit=50&currentPageNumber=0&pageCount=${pageCount}" /> " >
                                            50</option>
                                            </c:if>
                                            <c:if test="${limit == 25}">
 											<option value="<c:url value="productNextPage?startpage=0&limit=10&currentPageNumber=${currentPageNumber}&pageCount=${pageCount}" /> " >
                                            10</option>
                                            <option value="" selected>
                                            25</option >
                                            <option value="<c:url value="productNextPage?startpage=0&limit=50&currentPageNumber=0&pageCount=${pageCount}" /> " >
                                            50</option>
                                            </c:if>
                                            <c:if test="${limit == 50}">
                                            <option value="<c:url value="productNextPage?startpage=0&limit=10&currentPageNumber=0&pageCount=${pageCount}" /> " >
                                            10</option>
                                            <option value="<c:url value="productNextPage?startpage=0&limit=25&currentPageNumber=0&pageCount=${pageCount}" /> " >
                                            25</option >
                                            <option value="" selected>
                                            50</option>
                                        	</c:if>
                                        </select>


                                    </label> 
                                   
                                   
                                    <div id="dataTable_filter" class="dataTables_filter">
                                    <label>
									<form name="" action="searchProduct">
                                        Search
                                        <span><button type="submit"><i class="fa fa-search"></i></button></span><input id="myInput" class="" placeholder="Enter Product Name" name="search" required>
                                        
									</form>
                                    </label>
                                  
                                </div>
                                <table class="table table-striped table-bordered table-hover" id="">
                             	<c:if test="${not empty productlist}">
                                    <thead>
                                        <tr>
                                        	
                                            <th class="th-hover">Name</th>
                                            
                                            <th class="th-hover">Category</th>
                                          <!--   <th class="th-hover">Creation Time</th>
                                            <th class="th-hover">Update Time</th> -->
                                            <th class="th-hover"></th>
                                            <th class="th-hover"></th>
                                            <th class="th-hover"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                   <%! int count =1; %>
                                    	<c:forEach items="${productlist}" var="product"  >
                                        	<tr class="odd gradeX">
                                        	
                                        		<td>${product.name}</td>
                                        		<td>${product.categoryName}</td>
                                        <!--    		<td>${product.productId}</td> 
                                        		
                                            	<td>${product.createdAt}</td>
                                            	<td>${product.updateAt}</td>-->
                                            <!-- 	<td><button type="button" class="btn btn-default" id="myBtn" >Details</button></td>
                                            	-->
                                            	<td><button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#DetailsModal${product.id}"><i class="fa fa-info "></i> Info</button></td>
                                            	<td><a href="<c:url value='/editProduct/${product.id}' />" class="btn btn-primary" ><i class="fa fa-edit "></i> Edit</a></td>
												<td><!--  <a href="#" class="btn btn-danger" ><i class="fa fa-pencil"></i>Delete</a>-->
													<button class="btn btn-danger delete" data-href="<c:url value='/removeProduct/${product.id}' />"  data-toggle="modal" data-target="#confirm-delete"><i class="fa fa-trash"></i> Delete</button>
												</td>
                                      			<!-- <button class="btn btn-primary"><i class="fa fa-edit "></i> Edit</button> -->
                                      	  </tr>
                                      	  <% count++; %>
													<div class="modal fade" id="DetailsModal${product.id}"
														role="dialog">
														<div class="modal-dialog">

															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close"
																		data-dismiss="modal">&times;</button>
																	<h4>
																		<span class="glyphicon glyphicon-qrcode"></span> ${product.name}</h4>
																</div>
																<div class="modal-body">
																<div class="table">
																	<c:forEach items="${product.attribute}" var="attribute">
																						<div class="table-row">
																						<div class="table-cell">
																						<div class="form-group">
																							<label for="usrname"><span
																								class="glyphicon glyphicon-tree-conifer">
																									Weight</span> </label> <input type="text" class="form-control"
																								id="usrname" value="${attribute.weight}"
																								readonly>
																						</div>
																						</div>
																						<div class="table-cell">
																						<div class="form-group">
																							<label for="Price"><span
																								class="glyphicon glyphicon-usd"></span> Price</label> <input
																								type="text" class="form-control" id="psw"
																								value="${attribute.price}" readonly>
																						</div>
																						</div>
																					</div>
																	</c:forEach>
																</div>
																</div>
																
															</div>
														</div>
													</div>
																	 <div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    											<div class="modal-dialog">
        										<div class="modal-content">
            									<!--<div class="modal-header">
                										...
            										</div>-->
										            <div class="modal-body">
										               Are you sure you want to remove this record?
										            </div>
										            <div class="modal-footer">
										                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
										                <a class="btn btn-danger btn-ok">Delete</a>
										            </div>
										        </div>
										    </div>
										</div>	
												</c:forEach>  
												
                                     </tbody>
                                </c:if>
                             	 
                                     
                                    
                                </table>
                                <div class="col-md-12">
                                	 <div class="pagination">
                                	 	<c:if test="${currentPageNumber > 0}">
  											<a href="<c:url value="/productNextPage?startpage=${currentPageNumber - 1}&limit=${limit}&currentPageNumber=${currentPageNumber - 1}&pageCount=${pageCount}" /> ">&laquo;</a>
  											</c:if>
  											<a href="" class="active"><c:out value = "${currentPageNumber + 1}"/></a>
  											<c:if test="${currentPageNumber lt pageCount - 1}">
										    <a href="<c:url value="/productNextPage?startpage=${currentPageNumber + 1}&limit=${limit}&currentPageNumber=${currentPageNumber + 1 }&pageCount=${pageCount}" />">&raquo;</a>
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
    
        <script>
            $(document).ready(function () {
                $('#dataTables-example').dataTable({
                	
                });
            });
            $('#confirm-delete').on('show.bs.modal', function(e) {
                $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
            });
    </script>
         <!-- CUSTOM SCRIPTS -->
    <script src="<c:url value="/assets/js/custom.js" />" ></script>
    
   
</body>
</html>