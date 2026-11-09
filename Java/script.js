// All the bookings and their prices are kept in an array
var bookings = [
    { id: "ultimate", name: "Ultimate Gamer Pass", price: 1500 },
    { id: "vip", name: "VIP Gaming Experience", price: 1500 },
    { id: "esports", name: "Esports Training Package", price: 1500 },
    { id: "birthday", name: "Birthday Party Package", price: 1500 },
    { id: "vr", name: "Virtual Reality Experience", price: 750 },
    { id: "racing", name: "Racing Simulator Challenge", price: 750 },
    { id: "escape", name: "Escape Room Challenge", price: 750 }
];

var form = document.getElementById("quoteForm");

if (form) {
    form.addEventListener("submit", function (event) {
        event.preventDefault();
        calculateQuote();
    });

    form.addEventListener("reset", function () {
        document.getElementById("formMessage").textContent = "";
        document.getElementById("quoteOutput").innerHTML = "";
    });
}

function calculateQuote() {
    var name = document.getElementById("customerName").value;
    var phone = document.getElementById("customerPhone").value;
    var email = document.getElementById("customerEmail").value;
    var message = document.getElementById("formMessage");
    var output = document.getElementById("quoteOutput");

    // Validation
    if (name === "") {
        message.textContent = "Please enter your name.";
        return;
    }
    if (phone.length !== 10 || isNaN(phone)) {
        message.textContent = "Phone number must be 10 digits, for example 0821234567.";
        return;
    }
    if (email.indexOf("@") === -1) {
        message.textContent = "Please enter a valid email address.";
        return;
    }

    // Find which bookings were ticked and add up the price
    var selected = [];
    var subtotal = 0;
    var rows = "";

    for (var i = 0; i < bookings.length; i++) {
        var box = document.getElementById("cb_" + bookings[i].id);
        if (box.checked) {
            selected.push(bookings[i]);
            subtotal = subtotal + bookings[i].price;
            rows = rows + "<tr><td>" + bookings[i].name + "</td><td>R" + bookings[i].price + "</td></tr>";
        }
    }

    if (selected.length === 0) {
        message.textContent = "Please tick at least one booking.";
        return;
    }

    // Discount
    var discountPercent = 0;
    if (selected.length === 2) {
        discountPercent = 5;
    } else if (selected.length === 3) {
        discountPercent = 10;
    } else if (selected.length > 3) {
        discountPercent = 15;
    }

    var discount = subtotal * discountPercent / 100;
    var afterDiscount = subtotal - discount;
    var vat = afterDiscount * 0.15;
    var total = afterDiscount + vat;

    // Invoice
    message.textContent = "";
    output.innerHTML =
        "<h2>Quotation</h2>" +
        "<p>Name: " + name + "<br>Phone: " + phone + "<br>Email: " + email + "</p>" +
        "<table><tr><th>Booking</th><th>Price</th></tr>" + rows + "</table>" +
        "<p>Subtotal: R" + subtotal.toFixed(2) + "<br>" +
        "Discount (" + discountPercent + "%): -R" + discount.toFixed(2) + "<br>" +
        "After discount: R" + afterDiscount.toFixed(2) + "<br>" +
        "VAT (15%): R" + vat.toFixed(2) + "</p>" +
        "<h3>Total: R" + total.toFixed(2) + "</h3>";
}
