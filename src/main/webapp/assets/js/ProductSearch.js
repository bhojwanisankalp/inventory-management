$(document)
		.ready(
				function() {
					var html = $('tbody').html();
					// Delete
					$('#myInput')
							.keyup(
									function(event) {
										if ($(this).val()) {

											var name = $(this).val();
											submit(name);
										} else {
											$('tbody').html(html);
										}
										console.log(name);

										// return false;
										function submit(name) {
											$
													.ajax({
														type : "GET",
														url : "/Chat-Bot/searchProduct",
														data : {
															search : name
														},
														dataType : "json",
														success : function(
																response) {
															console
															.log(response);
															// response =
															// $.parseJSON(response);
															for (var i = 0; i < response.length; i++) {
																var row = '<tr class="odd gradeX"><td>'
																		+ response[i].name
																		+ '</td><td>'
																		+ response[i].categoryName
																		+ '</td><td><button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#DetailsModal'
																		+ response[i].id
																		+ '">Details</button></td><td><a href="/Chat-Bot/editProduct/'
																		+ response[i].id
																		+ '" class="btn btn-primary" ><i class="fa fa-edit "></i>Edit</a></td><td><a href="/Chat-Bot/removeProduct/'
																		+ response[i].id
																		+ '" class="btn btn-danger" ><i class="fa fa-pencil"></i>Delete</a></td></tr>'
																		+'<div class="modal fade" id="DetailsModal'+response[i].id
																		+'"role="dialog">'
																		+'<div class="modal-dialog">'
																		+'<div class="modal-content">'
																	    +'<div class="modal-header">'
																		+'<button type="button" class="close"'
																		+'data-dismiss="modal">&times;</button>'
																		+'<h4><span class="glyphicon glyphicon-qrcode"></span> '+response[i].name+'</h4>'
																		+'</div><div class="modal-body"><div class="table">';
																for (var j = 0; j < response[i].attribute.length ; j++ ){
																					var attribute = response[i].attribute;
																					var modal = '<div class="table-row">'
																								 +  '<div class="table-cell">'
																									+	'<div class="form-group">'
																										+	'<label for="usrname"><span'
																											+	'class="glyphicon glyphicon-tree-conifer">'
																												+	'Weight</span> </label> <input type="text" class="form-control"'
																												 + 'id="usrname" value="'+attribute[j].weight+'"'
																												+'readonly>'
																												+'</div>'
																												+'</div>'
																												+'<div class="table-cell">'
																										+'<div class="form-group">'
																										+	'<label for="Price"><span'
																											+'	class="glyphicon glyphicon-usd"></span> Price</label> <input'
																											+'	type="text" class="form-control" id="psw"'
																											+'	value="'+attribute[j].price+'" readonly></div></div></div>';
															}
																var end= '</div></div></div></div></div>';
																row = row+modal+end;
																console
																		.log('>>>>>>>>>>>>>>'
																				+ row);
																$('tbody')
																		.empty();
																$('tbody')
																		.append(
																				row);
															}

														},
														error : function() {
															alert("Error");
														}
													});
										}
									});

				}); // Selecting image source

// AJAX request

