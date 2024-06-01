$(document).ready(function () {
    $('#feedbackForm').on('submit', function (event) {
        event.preventDefault();

        var yName = $('#yName').val().trim();
        var yEmail = $('#yEmail').val().trim();
        var yReview = $('#yReview').val().trim();

        var formData = {
            name: yName,
            email: yEmail,
            review: yReview
        };

        $.ajax({
            type: "POST", 
            url: "/feedback",
            contentType: "application/json", 
            data: JSON.stringify(formData),
            success: function (response) {
                
                console.log('Data sent successfully!');
                console.log(response);
                
                $('#myModal').modal('show');
            },
            error: function (xhr, status, error) {
                
                console.error('Error:', error);
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