//$(document).ready(function() {
//					var html = $('tbody').html();
//					// Delete
//					$('#myInput').keyup(function(event) {
//										if ($(this).val()) {
//											var name = $(this).val();
//											submit(name);
//										} else {
//											$('tbody').html(html);
//										}
//										console.log(name);
//										// return false;
//										function submit(name) {
//											$.ajax({
//													type : "GET",
//													url : "/Chat-Bot/searchProductQuantity",
//													data : {
//															search : name
//														   },
//														dataType : "json",
//														success : function(
//																response) {
//															console.log(response);
//															// response =
//															// $.parseJSON(response);
//															for (var i = 0; i < response.length; i++) {
//																var status =''; 
//																var active ='';
//					                                            if(response[i].isActive == 0){
//					                                            	status ='<td>Active</td>'
//					                                            }else if(response[i].isActive == 0){
//					                                            	status ='<td>Inactive</td>'
//					                                            };
//					                                            if (response[i].isActive == 0){
//													  				 active = '<option value="0"  selected>Active</option><option value="1" >Inactive</option>';
//													  			}
//													  			else if(response[i].isActive == 1){
//													  				 active = '<option value="0" >Active</option><option value="1"  selected>Inactive</option>'; 
//													  			};
//																var row = '<tr class="odd gradeX">'                    
//																		  +'<td>'+response[i].name+'</td>'
//																		  +'<td>'+response[i].categoryName+'</td>'
//																		  +status+'<td>'+response[i].totalQuantity}+'</td>' 
//																		  +'<td><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#DetailsModal'+response[i].id}+'">Update Quantity</button></td></tr>'         
//																		  +'<div class="modal fade" id="DetailsModal'+response[i].id+' "role="dialog">'
//																		  +'<div class="modal-dialog">'
//																		  +'<div class="modal-content">'
//																		  +'<div class="modal-header">'
//																		  +'<button type="button" class="close" '
//																		  +'data-dismiss="modal">&times;</button>'
//																		  +'<h4><span class="glyphicon glyphicon-qrcode"></span> '+response[i].name+'</h4>'
//																		  +'</div><form  name="quant" role="form" action="quantity" method="POST"><div class="modal-body"><div class="table">';
//																for(var j= 0; j < response[i].attribute.length ; j++ ){
//																					var attribute = response[i].attribute;
//																					var modal = '<div class="form-group">'
//																						 +'<input type="hidden" class="form-control"'
//																						+	' value="'+response[i].id+'" name="productId"></div>'
//																							+'<div class="table-row">'
//																								 +  '<div class="table-cell">'
//																									+	'<div class="form-group">'
//																										+	'<label for="usrname"><span'
//																											+	'class="glyphicon glyphicon-tree-conifer">'
//																												+	'Weight</span> </label> <input type="text" class="form-control"'
//																												 + 'id="usrname" value="'+attribute[j].weight+'"'
//																												+'readonly>'
//																												+'</div>'
//																												+'</div>'
//																												+'<div class="table-cell">'
//																										+'<div class="form-group">'
//																										+	'<label for="Price"><span'
//																											+'	class="glyphicon glyphicon-usd"></span> Price</label> <input'
//																											+'	type="text" class="form-control" id="psw"'
//																											+'	value="'+attribute[j].price+'" readonly></div></div>
//																											+'<div class="table-cell">'
//																											+'<div class="form-group">'
//																											+'<label for="Price"><span'
//																											+	'class="glyphicon glyphicon-grain"></span> Quantity</label> <input'
//																											+	'type="number" class="form-control" id="quant" name="quantityInput[]"'
//																											+	'value="'+attribute[j].productQuantity+'" >'
//																										+'</div>'
//																										+'</div>'
//																											+'</div>';
//															}
//																var end= '</div></div>'
//																	 	+'<div class="modal-footer">'
//																	 	+'<div class="table-row">'
//																	 	+ '<div class="table-cell">'
//																	 	+'<select class="form-control" name="isActive" >';
//														  			
//                                    							var end = end + active 	                                      			
//                                    								
//                                    							+'</select>'
//                                    							+'</div>'
//                                    							+'<div class="table-cell">'
//                                    							+'<input type="submit" class="btn btn-default"  value="Update" id="'+response[i].id+'" >'
//																+'</div>'
//															+'</div>'
//															+'</div> </form>'
//																	+'</div></div></div>';
//																row = row+modal+end;
//																console.log('>>>>>>>>>>>>>>'+ row);
//																$('tbody').empty();
//																$('tbody').append(row);
//															
//															}
//														},
//														error : function() {
//															alert("Error");
//														}
//													});
//										}
//									});
//
//				}); // Selecting image source
//
//// AJAX request
//
