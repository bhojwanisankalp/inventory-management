
$(document).ready(function(){
	var html = $('tbody').html();
	  // Delete
	  $('#myInput').keyup(function(event){
		if($(this).val()){
			
  		var name= $(this).val();
  		submit(name);
		}else{
			$('tbody').html(html);
		}
  		console.log(name);
		
		//return false;
 function submit(name){
    	 $.ajax({
    		type: "GET",
    		url: "/Chat-Bot/searchOrder",
    		data: {search : name},
    		dataType: "json",
    		success: function(response){
    			//response = $.parseJSON(response); 
    			for (var i = 0; i < response.length; i++) {
    				var status = '';
    				if(response[i].orderStatus == 0){
    					status = status + '<td>Payment Pending</td>';
    				}else if(response[i].orderStatus == 1){
    					status = status
    				}
    				var row = ''
    				console.log('>>>>>>>>>>>>>>'+row);
    				$('tbody').empty();
    				$('tbody').append(row);
    			}
    		
    		},
    		error: function(){
    			alert("Error");
    		}
    	});
	  }
	});
	    
});    // Selecting image source
	   
	  
     
	    // AJAX request
	 
	