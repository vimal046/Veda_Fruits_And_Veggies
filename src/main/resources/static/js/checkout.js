document.addEventListener('DOMContentLoaded', function() {
	var tbody = document.getElementById('cart-items');

	// Retrieve data from localStorage
	var shoppingCart = JSON.parse(localStorage.getItem('shoppingCart')) || [];

	// Clear existing rows
	tbody.innerHTML = '';

	// Populate table with stored data
	shoppingCart.forEach(function(item) {
		var row = document.createElement('tr');
		row.innerHTML = `
                <td>
                    <div class="d-flex align-items-center mt-2">
                        <img src="${item.imageUrl}" class="img-fluid rounded-circle" style="width: 90px; height: 90px;" alt="">
                    </div>
                </td>
                <td class="py-5">${item.productName}</td>
                <td class="py-5">₹${item.price.toFixed(2)}</td>
                <td class="py-5">${item.quantity}</td>
                <td class="py-5">₹${(item.price * item.quantity).toFixed(2)}</td>
            `;
		tbody.appendChild(row);
	});


	// Calculate subtotal and grand total
	var subtotal = shoppingCart.reduce((total, item) => total + (item.price * item.quantity), 0);
	var shipping = 30; // Flat rate shipping
	var grandTotal = subtotal + shipping;

	// Insert subtotal row
	var subtotalRow = document.createElement('tr');
	subtotalRow.innerHTML = `
       
    
      <th scope="row"></th>
    <td class="py-5"></td>
    <td class="py-5"></td>
    <td class="py-5">
    <p class="mb-0 text-dark py-3 subtotal">Sub total</p>
    </td>
    <td class="py-5">
    <div class="py-3 border-bottom border-top"> 
    <p class="mb-0 text-dark"> ₹${subtotal.toFixed(2)}</p>
    </div>
    </td>
     `;
	tbody.appendChild(subtotalRow);

	// Insert grand total row
	var grandTotalRow = document.createElement('tr');
	grandTotalRow.innerHTML = `

<tr>
    <th scope="row"></th>
    <td class="py-5">
        <p class="mb-0 text-dark text-uppercase py-3">Grand Total</p>
    </td>
    <td class="py-5"></td>
    <td class="py-5"></td>
    <td class="py-5">
        <div class="py-3 border-bottom border-top ">
             <p class="mb-0 text-dark">₹${grandTotal.toFixed(2)}</p> 
        </div>
    </td>
</tr>
     `;
	tbody.appendChild(grandTotalRow);
});


$(document).ready(function() {

	$('#placeOrder').on('submit', function(event) {
		event.preventDefault();

		var formData = {
			formObj: {
				fName: $('#fName').val().trim(),
				lName: $('#lName').val().trim(),
				cName: $('#cName').val().trim(),
				street: $('#street').val().trim(),
				city: $('#city').val().trim(),
				country: $('#country').val().trim(),
				postcode: $('#Postcode').val().trim(),
				mobile: $('#mobile').val().trim(),
				email: $('#email').val().trim(),
				notes: $('#notes').val().trim()
			},
			itemObj: []
		};

		var shoppingCart = JSON.parse(localStorage.getItem('shoppingCart')) || [];
		formData.itemObj = shoppingCart.map(item => {
			return {
				productName: item.productName,
				price: item.price,
				quantity: item.quantity
			};
		});

		var subtotal = shoppingCart.reduce((total, item) => total + (item.price * item.quantity), 0);
		var shipping = 30; // Flat rate shipping
		var grandTotal = subtotal + shipping;
		formData.formObj.grandTotal = grandTotal;

		console.log(formData);

		$.ajax({
			type: 'POST',
			url: '/placeOrder',
			data: JSON.stringify(formData),
			contentType: 'application/json',
			success: function(response) {
				console.log("Form submitted");

				// Clear local storage and update cart count
				localStorage.setItem('shoppingCart', JSON.stringify([]));
				localStorage.setItem('cartCount', '0');
				$('.cart-count').text('0');

				// Show the modal
				$('#myModal').modal('show');
			},
			error: function(xhr, status, error) {
				console.log('Error occurred', error);
			}
		});
	});

});

$('#exploreBtn').click(function(e) {
    e.preventDefault(); 

    $('#myModal').modal('hide');

    setTimeout(function() {
        window.location.href = 'index.html';
    }, 300);
});
