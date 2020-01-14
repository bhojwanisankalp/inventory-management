 $(document).ready(function(){	
            	$("#quantityForm").submit(function(event){
            		submitForm();
            		return false;
            	});
            });
            function submitForm(){
            	 $.ajax({
            		type: "POST",
            		url: "quantity",
            		cache:false,
            		data: $('form#quantityForm').serialize(),
            		success: function(response){
            			$("#quant").html(response)
            			$("#DetailsModal${product.id}").modal('hide');
            		},
            		error: function(){
            			alert("Error");
            		}
            	});
            }