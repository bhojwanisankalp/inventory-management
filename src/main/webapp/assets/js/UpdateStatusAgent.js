 $(document).ready(function(){	
	 var selectedStatus;
	 var userId ;
	 $("select.status-update").change(function(){
		  selectedStatus= $(this).children("option:selected").val();
		  userId = $(this).attr("id");
		  $("#updateButton"+userId).click(function(){
		 		submitForm(selectedStatus,userId);
		 		return false;
		 	});
	     });
    });
            function submitForm(selectedStatus, userId){
            	 $.ajax({
            		type: "POST",
            		url: "/Chat-Bot/agentStatusUpdate",
            		cache:false,
            		data: {update : selectedStatus, id : userId},
            		success: function(response){
            			//Show success message for 2 seconds
            			$("#afterStatusUpdate").addClass("alert alert-success alert-dismissible");
            			$("#afterStatusUpdate").html(response.result);
            			$("#afterStatusUpdate"+userId).addClass("alert alert-success alert-dismissible");
            			$("#afterStatusUpdate"+userId).html(response.result);
            			setTimeout(function(){ 
            				$('#afterStatusUpdate').removeClass('alert alert-success alert-dismissible');
            				$('#afterStatusUpdate').empty();
            				$('#afterStatusUpdate'+userId).removeClass('alert alert-success alert-dismissible');
            				$('#afterStatusUpdate'+userId).empty();
            			}, 2000);
            			//Change status value from pop-up and td of table
            			if(response.status){
            				$("#statusUpdatIcon"+response.id).empty();
            				$("#statusUpdatIcon"+response.id).html('<i class="fa fa-thumbs-up"></i>');
            				$("#statusChangeModal"+response.id).html('Enabled');
            				$("#statusChange"+response.id).html('Enabled');
            			}else{
            				$("#statusUpdatIcon"+response.id).empty();
            				$("#statusUpdatIcon"+response.id).html('<i class="fa fa-thumbs-down"></i>');
            				$("#statusChangeModal"+response.id).html('Disabled');
                			$("#statusChange"+response.id).html('Disabled');
                		}
            		},
            		error: function(){
            			alert("Error");
            		}
            	});
            }