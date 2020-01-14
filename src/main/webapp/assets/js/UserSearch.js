
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
    		url: "/Chat-Bot/searchUser",
    		data: {search : name},
    		dataType: "json",
    		success: function(response){
    			//response = $.parseJSON(response); 
    			for (var i = 0; i < response.length; i++) {
    				var row = '<tr class="odd gradeX"><td>'+response[i].name+'</td><td>'+response[i].contact+'</td><td>'+response[i].address+'</td><td>'+response[i].email+'</td><td>'+response[i].role+'</td><td><a href="/Chat-Bot/edit/'+response[i].id+'" class="btn btn-primary" ><i class="fa fa-edit "></i>Edit</a></td><td><a href="/Chat-Bot/remove/'+response[i].id+'" class="btn btn-danger" ><i class="fa fa-pencil"></i>Delete</a></td></tr>'
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
	 
	