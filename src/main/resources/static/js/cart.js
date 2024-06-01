$(document).ready(function () {
    // Retrieve data from local storage
    var shoppingCart = JSON.parse(localStorage.getItem('shoppingCart')) || [];

    // Function to update cart icon count
    function updateCartIcon() {
        var totalCount = shoppingCart.length;
        $('.cart-count').text(totalCount);
        localStorage.setItem('cartCount', totalCount);
    }

    // Function to save cart data to local storage
    function saveCartData() {
        localStorage.setItem('shoppingCart', JSON.stringify(shoppingCart));
    }

    // Function to remove item from shopping cart
    function removeItem(index) {
        shoppingCart.splice(index, 1); // Remove item from array
        saveCartData(); // Save updated cart data to local storage
        populateCartPage(); // Repopulate cart page
        updateCartIcon(); // Update cart icon
    }

    // Add to Cart button click event
    $('.add-to-cart').click(function () {
        var productName = $(this).closest('.fruite-item').find('h4').text();
        var price = parseFloat($(this).closest('.fruite-item').find('.fs-5').text().replace('₹', '').trim());
        var imageUrl = $(this).closest('.fruite-item').find('img').attr('src');
        var item = {
            productName: productName,
            price: price,
            quantity: 1,
            imageUrl: imageUrl
        };
        shoppingCart.push(item);
        saveCartData(); // Save cart data to local storage
        updateCartIcon(); // Update cart icon
    });

    // Function to calculate subtotal
    function calculateSubtotal() {
        return shoppingCart.reduce((total, item) => total + (item.price * item.quantity), 0);
    }


    // Populate cart page
    function populateCartPage() {
        var tbody = $('.cart-table');
        tbody.empty(); // Clear existing rows

        // Loop through shopping cart items and populate table rows
        shoppingCart.forEach(function (item, index) {
            var row = $('<tr>');
            row.append('<td><img src="' + item.imageUrl + '" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt=""></td>');
            row.append('<td><p class="mb-0 mt-4">' + item.productName + '</p></td>');
            row.append('<td><p class="mb-0 mt-4">' + item.price + ' ₹</p></td>');
            row.append('<td><div class="input-group quantity mt-4" style="width: 100px;"><div class="input-group-btn"><button class="btn btn-sm btn-minus rounded-circle bg-light border" ><i class="fa fa-minus"></i></button></div><input type="text" class="form-control form-control-sm text-center border-0" value="' + item.quantity + '"><div class="input-group-btn"><button class="btn btn-sm btn-plus rounded-circle bg-light border"><i class="fa fa-plus"></i></button></div></div></td>');
            row.append('<td><p class="mb-0 mt-4 item-total">' + (item.price * item.quantity) + ' ₹</p></td>');
            row.append('<td><button class="btn btn-md rounded-circle bg-light border mt-4 remove-item"><i class="fa fa-times text-danger"></i></button></td>');

            // Attach click event to remove button
            row.find('.remove-item').click(function () {
                removeItem(index); // Remove item from shopping cart
            });

            tbody.append(row);
        });

        var subtotal = calculateSubtotal();
        $('.subtotal-value').text(subtotal.toFixed(2) + ' ₹');
        // Calculate grand total
        var shipping = 30; // Flat rate shipping
        var grandTotal = subtotal + shipping;
        $('.grand-total-value').text(grandTotal.toFixed(2) + ' ₹');

        // Inside populateCartPage function after calculating subtotal and grand total
        $('.subtotal-value').text(subtotal.toFixed(2) + ' ₹');
        $('.grand-total-value').text(grandTotal.toFixed(2) + ' ₹');

        // Save subtotal and grand total to local storage
        localStorage.setItem('subtotal', subtotal.toFixed(2));
        localStorage.setItem('grandTotal', grandTotal.toFixed(2));


    }


    // Call populateCartPage on page load
    populateCartPage();




    // Product Quantity
    $(document).on('click', '.quantity button', function () {
        var button = $(this);
        var inputField = button.parent().parent().find('input');
        var oldValue = parseFloat(inputField.val());
        var newVal;

        if (button.hasClass('btn-plus')) {
            newVal = oldValue + 1;
        } else {
            // Check if current value is greater than 1 before decrementing
            newVal = oldValue > 1 ? oldValue - 1 : 1;
        }

        inputField.val(newVal);

        var index = button.closest('tr').index();
        shoppingCart[index].quantity = newVal;

        saveCartData();
        populateCartPage();
        updateCartIcon();
    });
});


