<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html  >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<%--  background="${pageContext.request.contextPath}/jsp/java9r.png" --%>
</head>
<body background="${pageContext.request.contextPath}/jsp/java9r.jpg" >
<div class="container" >

<div  >
<h2>Product List Using Spring MVC Framework With Bootstrap And Hibernate </h2>
<table   class="table">
<tr>
<th></th>
<th class="col-md-2">Id</th>
<th class="col-md-2">Name</th>
<th class="col-md-2">Price</th>
<th class="col-md-2">Quantity</th>

<th  class="col-md-4" align="center"><%-- <a href="${pageContext.request.contextPath}/add.html">add</a> --%>
  <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#AddModal">Add</button>
</th>
</tr>
<c:forEach var="pr" items="${listproducts}">
<tr>
<td><a href="${pageContext.request.contextPath}/delete/${pr.id }.html" class="btn btn-info btn-sm"  onclick="return confirm('Are you sure')">-</a></td>
<td>${pr.id }</td>
<td>${pr.name }</td>
<td>${pr.price }</td>
<td>${pr.quantity }</td>
<td> 
<%-- <a href="${pageContext.request.contextPath}/edit/${pr.id }.html" >Edit</a>
 --%>  <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#EditModal${pr.id }">Edit</button>
 <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#DetailsModal${pr.id }">Details</button>
 

</td>
</tr>


  
  
  <!-- Modal Detalis -->
  <div class="modal fade" id="DetailsModal${pr.id }" role="dialog">
    <div class="modal-dialog">

      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Product Detalis</h4>
        </div>
        <div class="modal-body">
        
        
            <div class="form-group">
  <label for="usr"> Product Id:   ${pr.id }</label>

</div>

        <div class="form-group">
  <label for="usr">Name : ${pr.name }</label>


</div>

<div class="form-group">
  <label for="usr">Price: ${pr.price }</label>
  
   
</div>
<div class="form-group">
  <label for="usr">Quantity: ${pr.quantity }</label>


</div>


<div class="form-group">
  <label for="usr">Description: </label>
  ${pr.description }
</div>


        </div>
        <div class="modal-footer">
        <input type="button" class="btn btn-default"  data-toggle="modal" data-target="#EditModal${pr.id }" value="Edit">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
  
  
  
  <!-- Modal edit -->
  <div class="modal fade" id="EditModal${pr.id }" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
  
      <form  id="pr"  method="post" action="edit.html">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Edit Product</h4>
        </div>
        <div class="modal-body">
        
        <div class="form-group">
  <label for="usr">Id:</label>
  
   <input type="text" class="form-control" value="${pr.id }" name="id" id="usr" readonly="readonly">

</div>

       <div class="form-group">
  <label for="usr">Name:</label>
  
  <input type="text" class="form-control" value="${pr.name }" name="name" id="usr">

</div>

<div class="form-group">
  <label for="usr">Price:</label>
  <input type="text" class="form-control" value="${pr.price }" name="price" id="usr">
   
</div>
<div class="form-group">
  <label for="usr">Quantity:</label>
  <input type="text" class="form-control" value="${pr.quantity }" name="quantity" id="usr">

</div>


<div class="form-group">
  <label for="usr">Description:</label>
  <input type="text" class="form-control" value="${pr.description }" name="description" id="usr">
</div>


        </div>
        <div class="modal-footer">
        <input type="submit" class="btn btn-default" value="Save">
                <input type="reset" class="btn btn-default" value="Reset">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      </form>
    </div>
  </div>
  
  
</c:forEach>
</table>
</div>




  
  
  
  
    <!-- Modal -->
  <div class="modal fade" id="AddModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      
      <form method="post" action="add.html">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add New Product</h4>
        </div>
        <div class="modal-body">
        
        <div class="form-group">
  <label for="usr">Name:</label>
  <input type="text" class="form-control" name="name" id="usr">

</div>

<div class="form-group">
  <label for="usr">Price:</label>
  <input type="text" class="form-control" name="price" id="usr">
   
</div>
<div class="form-group">
  <label for="usr">Quantity:</label>
  <input type="text" class="form-control" name="quantity" id="usr">

</div>


<div class="form-group">
  <label for="usr">Description:</label>
  <input type="text" class="form-control" name="description" id="usr">
</div>


        </div>
        <div class="modal-footer">
        <input type="submit" class="btn btn-default" value="Save">
         <input type="reset" class="btn btn-default" value="Reset">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      </form>
    </div>
  </div>
   <div class="modal fade" id="DetailsModal${product.id}" role="dialog">
    <div class="modal-dialog">

      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Product Detalis</h4>
        </div>
        <div class="modal-body">
        
        
            <div class="form-group">
  <label for="usr"> Product Id:   ${product.id }</label>

</div>
	<c:forEach items="${product.attribute}" var="attribute">
        <div class="form-group">
  <label for="usr">Name : ${product.name}</label>


</div>

<div class="form-group">
  <label for="usr">Weight : ${attribute.weight}</label>
  
   
</div>
<div class="form-group">
  <label for="usr">Price : ${attribute.price}</label>


</div>


<div class="form-group">
  <label for="usr">Description: </label>
  ${pr.description }
</div>
</c:forEach>

        </div>
        <div class="modal-footer">
        <input type="button" class="btn btn-default"  data-toggle="modal" data-target="#EditModal${pr.id }" value="Edit">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
  
  
</div>

<input type="button" class="btn btn-default"  data-toggle="modal" data-target="#myModal" value="Testing>>>>>>>>>">
<div class="modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="text-danger fa fa-times"></i></button>
                    <h4 class="modal-title" id="myModalLabel"><i class="text-muted fa fa-shopping-cart"></i> <strong>02051</strong> - Nome do Produto </h4>
                  </div>
                  <div class="modal-body">
                  
                    <table class="pull-left col-md-8 ">
                         <tbody>
                             <tr>
                                 <td class="h6"><strong>Código</strong></td>
                                 <td> </td>
                                 <td class="h5">02051</td>
                             </tr>
                             
                             <tr>
                                 <td class="h6"><strong>Descrição</strong></td>
                                 <td> </td>
                                 <td class="h5">descrição do produto</td>
                             </tr>
                             
                             <tr>
                                 <td class="h6"><strong>Marca/Fornecedor</strong></td>
                                 <td> </td>
                                 <td class="h5">Marca do produto</td>
                             </tr>
                             
                             <tr>
                                 <td class="h6"><strong>Número Original</strong></td>
                                 <td> </td>
                                 <td class="h5">0230316</td>
                             </tr>
                             
                             <tr>
                                 <td class="h6"><strong>Código N.C.M</strong></td>
                                 <td> </td>
                                 <td class="h5">032165</td>
                             </tr>
                             
                             <tr>
                                 <td class="h6"><strong>Código de Barras</strong></td>
                                 <td> </td>
                                 <td class="h5">0321649843</td>
                             </tr>  

                             <tr>
                                 <td class="h6"><strong>Unid. por Embalagem</strong></td>
                                 <td> </td>
                                 <td class="h5">50</td>
                             </tr>                            

                             <tr>
                                 <td class="h6"><strong>Quantidade Mínima</strong></td>
                                 <td> </td>
                                 <td class="h5">50</td>
                             </tr>

                             <tr>
                                 <td class="h6"><strong>Preço Atacado</strong></td>
                                 <td> </td>
                                 <td class="h5">R$ 35,00</td>
                             </tr> 
                            
                             <tr>
                                 <td class="btn-mais-info text-primary">
                                     <i class="open_info fa fa-plus-square-o"></i>
                                     <i class="open_info hide fa fa-minus-square-o"></i> informações
                                 </td>
                                 <td> </td>
                                 <td class="h5"></td>
                             </tr> 

                         </tbody>
                    </table>
                             
                         
                    <div class="col-md-4"> 
                        <img src="http://lorempixel.com/150/150/technics/" alt="teste" class="img-thumbnail">  
                    </div>
                    
                    <div class="clearfix"></div>
                   <p class="open_info hide">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
                  </div>
                    
                  <div class="modal-footer">       
                      
                    <div class="text-right pull-right col-md-3">
                        Varejo: <br/> 
                        <span class="h3 text-muted"><strong> R$50,00 </strong></span></span> 
                    </div> 
                      
                    <div class="text-right pull-right col-md-3">
                        Atacado: <br/> 
                        <span class="h3 text-muted"><strong>R$35,00</strong></span>
                    </div>
                     
                </div>
              </div>
            </div>
            </div>
            
            <!-- Modal Start -->
            <input type="button" class="btn btn-default"  data-toggle="modal" data-target="#invoice" value="Testing>>>>>>>>>">
             
    		<div class="modal fade" id="invoice" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
         <div class="table-responsive">
    					<table class="table table-condensed">
    						<thead>
                                <tr>
        							<td><strong>Item</strong></td>
        							<td class="text-center"><strong>Price</strong></td>
        							<td class="text-center"><strong>Quantity</strong></td>
        							<td class="text-right"><strong>Totals</strong></td>
                                </tr>
    						</thead>
    						<tbody>
    							<!-- foreach ($order->lineItems as $line) or some such thing here -->
    							<tr>
    								<td>BS-200</td>
    								<td class="text-center">$10.99</td>
    								<td class="text-center">1</td>
    								<td class="text-right">$10.99</td>
    							</tr>
                                <tr>
        							<td>BS-400</td>
    								<td class="text-center">$20.00</td>
    								<td class="text-center">3</td>
    								<td class="text-right">$60.00</td>
    							</tr>
                                <tr>
            						<td>BS-1000</td>
    								<td class="text-center">$600.00</td>
    								<td class="text-center">1</td>
    								<td class="text-right">$600.00</td>
    							</tr>
    							<tr>
    								<td class="thick-line"></td>
    								<td class="thick-line"></td>
    								<td class="thick-line text-center"><strong>Subtotal</strong></td>
    								<td class="thick-line text-right">$670.99</td>
    							</tr>
    							<tr>
    								<td class="no-line"></td>
    								<td class="no-line"></td>
    								<td class="no-line text-center"><strong>Shipping</strong></td>
    								<td class="no-line text-right">$15</td>
    							</tr>
    							<tr>
    								<td class="no-line"></td>
    								<td class="no-line"></td>
    								<td class="no-line text-center"><strong>Total</strong></td>
    								<td class="no-line text-right">$685.99</td>
    							</tr>
    						</tbody>
    					</table>
    				</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>		
            <!-- Modal End -->
</body>
</html>
