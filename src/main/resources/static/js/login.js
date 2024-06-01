$(document).ready(function() {
	
	$('#login').on('submit', function(event) {
		event.preventDefault();

		var uName = $('#username').val().trim();
		var pwd = $('#password').val().trim();
		
		var formData=new FormData();
		formData.append("uName",uName);
		formData.append("pwd",pwd);
		
          
		$.ajax({
			type: 'POST',
			url: '/adminLogin',
			data: formData, 
			processData: false, 
			contentType: false, 
			success: function(response) {
				if(response==='admin.html'){
					window.location.href='/admin.html';
				}else{
					window.location.href='/error.html';
				}
			},
			error: function(xhr, status, error) {
				console.log('Error occured', error);
			}
		});

	});
});