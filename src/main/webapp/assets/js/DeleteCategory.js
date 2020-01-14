$(document).ready(function(){

  // Delete
  $('.delete').click(function(e){
	  e.preventDefault();
    var imageId = $(this).attr('id');
    console.log(imageId);
    
    // Selecting image source
   
 
    // AJAX request
    $.ajax({
      url: '/Chat-Bot/deleteCategory',
      type: 'post',
      data: {id : imageId},
      success: function(response){
    	  $('#row'+imageId).fadeOut();
        
      },
      error:function(){
    	  
      }
    });
  });
});