document.addEventListener('DOMContentLoaded', function () {
    var totalAmountField = document.getElementById('amount');
    var netAmountField = document.getElementById('netAmount');

    // Set the current date in the date input field
    var currentDateField = document.getElementById('date');
    var currentDate = new Date().toISOString().split('T')[0]; // Get today's date in YYYY-MM-DD format
    currentDateField.value = currentDate;

    // Update net amount dynamically when total amount changes
    totalAmountField.addEventListener('input', function () {
        var totalAmount = parseFloat(totalAmountField.value) || 0; // Fallback to 0 if empty
        netAmountField.value = totalAmount;
    });

    // Handle payment button click
    document.getElementById('payBtn').onclick = function (e) {
        e.preventDefault();  // Prevent the form from submitting normally

        // Get values from the form
        var name = document.getElementById('name').value;
        var email = document.getElementById('email').value;
        var mobile = document.getElementById('mobile').value;
        var amount = document.getElementById('amount').value * 100; // Razorpay accepts amount in paise
        var notify = document.getElementById('notify').checked ? 'Yes' : 'No';
        

        // Validation for amount (should be greater than 0)
        if (!amount || amount <= 0) {
            alert("Please enter a valid donation amount.");
            return;
        }

        // Razorpay options object
        var options = {
            "key": "rzp_test_lQ3wGVXATkvAeB",  // Replace with your Razorpay Key ID
            "amount": amount,  // Amount is in paise
            "currency": "INR",
            "name": name,  // Customer's name
            "description": "Eduaid Donation",
            "image": "https://your_logo_url.com/logo.png",  // Optional logo URL
            "handler": function (response) {
                alert("Payment Successful! Payment ID: " + response.razorpay_payment_id);
                // Send this response to your backend if needed
            },
            "prefill": {
                "name": name,
                "email": email,
                "contact": mobile
            },
            "notes": {
                "notification": notify
            },
            "theme": {
                "color": "#3399cc"  // Customize checkout theme color
            }
        };

        // Open Razorpay checkout
        var rzp1 = new Razorpay(options);
        rzp1.open();
    };

    // Handle back button click to go back to landing page
    document.getElementById('backBtn').onclick = function () {
        window.location.href = './public_html/index.html';  // Replace 'landing_page.html' with your actual landing page URL
    };
});
