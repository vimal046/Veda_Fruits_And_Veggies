
$('#order-container, #feedback-container').hide();

function showContainer(containerId) {
	$('#product-container, #order-container, #feedback-container').hide();
	$(containerId).show();
}

$("#home").click(function() {
	fetchproduct();

	showContainer('#product-container');
});

fetchproduct();

//Fetch the product data
function fetchproduct() {
	$.ajax({
		url: "/fetchProduct",
		type: "GET",
		dataType: 'json',
		success: function(response) {
			productTBody(response);


		},
		error: function(xhr, status, error) {
			console.log("Error", error);
		}
	});
}

function productTBody(data) {
	var tbody = $('#product-container table tbody');
	tbody.empty();
	if (data.length > 0) {
		$.each(data, function(index, product) {
			var row = '<tr>' +
				'<td>' + product.id + '</td>' +
				'<td>' + product.name + '</td>' +
				'<td>₹' + product.price + '</td>' +
				'<td>' + product.imgLoc + '</td>' +
				'<td>' + product.description + '</td>' +
				'<td>' +
				'<a href="#editModal" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>' +
				'<a href="#deleteModal" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>' +
				'</td>' +
				'</tr>';
			tbody.append(row);
		});
	} else {
		tbody.html('<tr><td colspan="6">No data available</td></tr>');
	}
}




$(document).ready(function() {




	// Update operation data fetch to modal
	$('.edit').on('click', function() {

		var row = $(this).closest('tr');

		var id = row.find('td:eq(0)').text();
		var name = row.find('td:eq(1)').text();
		var price = row.find('td:eq(2)').text().replace('₹', '');
		var imageLocation = row.find('td:eq(3)').text();
		var description = row.find('td:eq(4)').text();

		$('#editModal #id').val(id);
		$('#editModal #u_productName').val(name);
		$('#editModal #u_price').val(price);
		$('#editModal #u_imgLoc').val(imageLocation);
		$('#editModal #desc').val(description);

	});


	//delete data append to modal
	$('.delete').on('click', function() {
		var row = $(this).closest('tr');
		var id = row.find('td:eq(0)').text();
		var imgLoc = row.find('td:eq(3)').text().replace('img/u_img/', '').trim();
        
        console.log(id);
        console.log(imgLoc);
        
		$('#deleteModal #d_id').val(id);
		$('#deleteModal #d_imgLoc').val(imgLoc);


	});


	//Add form submission

	$('#addForm').submit(function(e) {
		e.preventDefault();

		var name = document.getElementById('productName').value;
		var price = parseInt(document.getElementById('price').value);
		var imgLoc = document.getElementById('imgLoc').files[0];
		var description = document.getElementById('description').value;

		var formData = new FormData();
		formData.append('name', name);
		formData.append('price', price);
		formData.append('imgLoc', imgLoc);
		formData.append('description', description);

		for (const entry of formData.entries()) {
			console.log(entry[0] + ':', entry[1]);
		}

		fetch("/add/product", {
			method: "POST",
			body: formData
		})

			.then(response => {
				if (!response.ok) {
					throw new Error("Network response was not ok");
				}

			})
			.then(data => {
				console.log("Form data submitted successfully:", data);
				location.reload();
			})
			.catch(error => {
				console.error("There was a problem with form submission:", error);
			});
	});



	//Update form submission
	$('#updateForm').submit(function(e) {
		e.preventDefault();

		var id = parseInt(document.getElementById('id').value.trim());
		var productName = document.getElementById('u_productName').value.trim();
		var price = parseInt(document.getElementById('u_price').value.trim());
		var imgLoc = document.getElementById('u_imgLoc').value.trim();
		var description = document.getElementById('desc').value.trim();

		var formData = {
			id: id,
			name: productName,
			price: price,
			imgLoc: imgLoc,
			description: description
		};

		fetch("/update", {
			method: "PUT",
			body: JSON.stringify(formData),
			headers: {
				"Content-Type": "application/json"
			}
		})

			.then(response => {
				if (!response.ok) {
					throw new Error("Network response was not ok");
				}
				location.reload();
			})
			.then(data => {
				console.log("Form data submitted successfully:", data);
			})
			.catch(error => {
				console.error("There was a problem with form submission:", error);
			});
	});




	//Delete particular data 
	$('#deleteForm').submit(function(e) {
		e.preventDefault();

		var id = parseInt($('#d_id').val());
		var imgLoc = $('#d_imgLoc').val();



		var formData = new FormData();
		formData.append('id', id);
		formData.append('imgLoc', imgLoc);

		for (const entry of formData.entries()) {
			console.log(entry[0] + ':', entry[1]);
		}

		$.ajax({
			type: "DELETE",
			url: "deleteProduct",
			data: formData,
			processData: false,
			contentType: false,
			success: function(response) {
				console.log("Successfully sent")
				location.reload();
			},
			error: function(xhr, status, error) {
				console.log("Error", error);
			}
		});


	});


	$("#orders").click(function() {

		fetchorder();
		showContainer('#order-container');

	});

	//Populate form Data
	function fetchorder() {
		$.ajax({
			url: "/orders",
			type: "GET",
			dataType: "json",

			success: function(data) {
				console.log(data);

				$('#order-container').empty();
				// Loop through each order data
				data.forEach(function(order) {
					// Append formObj details
					var formDetails = '<div class="container mt-5" id="order-container">' +
						'<div id="formObj">' +
						'<div class="order-form">' +
						'<table class="table table-bordered">' +
						'<tbody>' +
						'<tr><th scope="row">Order Id</th><td>' + order.formObj.orderId + '</td></tr>' +
						'<tr><th scope="row">First Name</th><td>' + order.formObj.fName + '</td></tr>' +
						'<tr><th scope="row">Last Name</th><td>' + order.formObj.lName + '</td></tr>' +
						'<tr><th scope="row">Company Name</th><td>' + order.formObj.cName + '</td></tr>' +
						'<tr><th scope="row">Street</th><td>' + order.formObj.street + '</td></tr>' +
						'<tr><th scope="row">City</th><td>' + order.formObj.city + '</td></tr>' +
						'<tr><th scope="row">Country</th><td>' + order.formObj.country + '</td></tr>' +
						'<tr><th scope="row">Postcode</th><td>' + order.formObj.postcode + '</td></tr>' +
						'<tr><th scope="row">Mobile</th><td>' + order.formObj.mobile + '</td></tr>' +
						'<tr><th scope="row">Email</th><td>' + order.formObj.email + '</td></tr>' +
						'<tr><th scope="row">Notes</th><td>' + order.formObj.notes + '</td></tr>' +
						'<tr><th scope="row">Grand Total</th><td>₹' + order.formObj.grandTotal + '</td></tr>' +
						'</tbody></table></div>';

					// Append order items
					var itemDetails = '<div class="order-item">' +
						'<table class="table table-bordered"><thead>' +
						'<tr class="table-danger text-center align-middle">' +
						'<th scope="col">Item ID</th>' +
						'<th scope="col">Product Name</th>' +
						'<th scope="col">Price</th>' +
						'<th scope="col">Quantity</th></tr></thead><tbody>';

					order.itemObj.forEach(function(item) {
						itemDetails += '<tr class="table-info text-center align-middle">' +
							'<td>' + item.itemId + '</td>' +
							'<td>' + item.productName + '</td>' +
							'<td>' + item.price + '</td>' +
							'<td>' + item.quantity + '</td></tr>';
					});

					itemDetails += '</tbody></table></div><hr></div></div>';

					// Append form and item details to the body
					$('#order-container').append(formDetails + itemDetails);



				});


			},
		});
	}

	//populate feedBack data
	$("#feedback").click(function() {
		$.ajax({
			url: "/feedbackData",
			type: "GET",
			dataType: "json",

			success: function(data) {
				// Clear existing table rows
				$("#user-table tbody").empty();

				// Loop through the data array
				data.forEach(function(item) {
					// Create a new table row
					var row = $("<tr>");

					// Append table data for each property of the object
					row.append($("<td>").text(item.id));
					row.append($("<td>").text(item.name));
					row.append($("<td>").text(item.email));
					row.append($("<td>").text(item.review));

					// Append the new row to the table body
					$("#user-table tbody").append(row);
				});

				showContainer('#feedback-container');

			},

		});

	});
	
	//logout operation
	$("#logoutButton").click(function() {
		$.ajax({
				type: 'POST',
				url: '/logout',
				success: function(response) {
					window.location.href = response;
				},
				error: function(xhr, status, error) {
					console.log('Error occured', error);
				}
			});
		});
	
});

