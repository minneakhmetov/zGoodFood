
function addToCart(productId) {
    $.ajax({
        type: 'post',
        url: '/cart',
        data: {
            productId: productId,
            cookieValue: getCookie('auth')
        }
    }).done(function (data) {
        var contentTableHTML = 'Added to cart';
        var contentTableDiv = document.getElementById("message");
        contentTableDiv.innerHTML = contentTableHTML;
    }).fail(function () {
        alert("НЕ ОЧ");
    }); }

