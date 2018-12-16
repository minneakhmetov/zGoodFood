function loadCart() {
    $.ajax({
        type: 'post',
        url: '/loadCart',
        data: {
            cookieValue: getCookie('auth')
        }
    }).done(function (data) {
        var switcher = true;
        var total = 0;
        var contentTableHTML = '';
        var first = true;
        if(data.length === 0){
            contentTableHTML += '<article class="background-white">';
            contentTableHTML += '<div class="art-content">';
            contentTableHTML += '<div class="row">';
            contentTableHTML += '<h1>The cart is empty</h1>'
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '</article>'
        } else {
            for (var i = 0; i < data.length; i++) {
                total += Number(data[i].price);
                if(first) {
                    contentTableHTML += '<article class="background-gray">';
                    contentTableHTML += '<div class="art-header">';
                    contentTableHTML += '<hr class="line-2">';
                    contentTableHTML += '<h2>Cart</h2>';
                    contentTableHTML += '</div>';
                    contentTableHTML += '</article>'
                    first = false;
                }
                if (switcher) {
                    contentTableHTML += '<article class="background-white">';
                    switcher = false;
                } else {
                    contentTableHTML += '<article class="background-gray">';
                    switcher = true;
                }

                contentTableHTML += '<div class="art-content">';
                contentTableHTML += '<div class="row">';
                contentTableHTML += '<div class="col-1-5">';
                contentTableHTML += '<div class="wrap-col">';
                contentTableHTML += '<div>';
                contentTableHTML += '<img src="' + data[i].photoUrl + '">';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '<div class="col-3-5">';
                contentTableHTML += '<div class="wrap-col">';
                contentTableHTML += '<div class="row">';
                contentTableHTML += '<div class="t-center" style="padding-top: 10px;">';
                contentTableHTML += '<h3 style="font-size: 26px;">' + data[i].name + '</h3>';
                contentTableHTML += '<p>' + data[i].price + ' RUB</p>';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '<div class="col-1-5">';
                contentTableHTML += '<div class="wrap-col">';
                contentTableHTML += '<div class="row">';
                contentTableHTML += '<div class="t-center" style="padding-top: 10px;">';
                contentTableHTML += '<a class="button" onclick="deleteFromCart(' + data[i].itemId + ')"> Delete </a>';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '</article>'
            }
            if (switcher) {
                contentTableHTML += '<article class="background-white">';
            } else {
                contentTableHTML += '<article class="background-gray">';
            }

            contentTableHTML += '<div class="art-content">';
            contentTableHTML += '<div class="row">';
            contentTableHTML += '<div class="col-1-2">';
            contentTableHTML += '<div class="wrap-col">';
            contentTableHTML += '<div>';
            contentTableHTML += '<h1 style="text-align: right">Total: </h1>';
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '<div class="col-1-2">';
            contentTableHTML += '<div class="wrap-col">';
            contentTableHTML += '<div>';
            contentTableHTML += '<h1 style="text-align: right">' + total + ' RUB</h1>';
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '</article>';
        }

        var contentTableDiv = document.getElementById("cart");
        contentTableDiv.innerHTML = contentTableHTML;
    }).fail(function () {
        alert("НЕ ОЧ");
    });
}

function deleteFromCart(itemId) {
    $.ajax({
        type: 'post',
        url: '/deleteCart',
        data: {
            cookieValue: getCookie('auth'),
            itemId: itemId
        }
    }).done(function (data) {
        var switcher = true;
        var total = 0;
        var contentTableHTML = '';
        if(data.length === 0){
            contentTableHTML += '<article class="background-white">';
            contentTableHTML += '<div class="art-content">';
            contentTableHTML += '<div class="row">';
            contentTableHTML += '<h1>The cart is empty</h1>'
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '</article>'
        } else {
            for (var i = 0; i < data.length; i++) {
                total += Number(data[i].price);

                if (switcher) {
                    contentTableHTML += '<article class="background-white">';
                    switcher = false;
                } else {
                    contentTableHTML += '<article class="background-gray">';
                    switcher = true;
                }
                contentTableHTML += '<div class="art-content">';
                contentTableHTML += '<div class="row">';
                contentTableHTML += '<div class="col-1-5">';
                contentTableHTML += '<div class="wrap-col">';
                contentTableHTML += '<div>';
                contentTableHTML += '<img src="' + data[i].photoUrl + '">';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '<div class="col-3-5">';
                contentTableHTML += '<div class="wrap-col">';
                contentTableHTML += '<div class="row">';
                contentTableHTML += '<div class="t-center" style="padding-top: 10px;">';
                contentTableHTML += '<h3 style="font-size: 26px;">' + data[i].name + '</h3>';
                contentTableHTML += '<p>' + data[i].price + ' RUB</p>';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '<div class="col-1-5">';
                contentTableHTML += '<div class="wrap-col">';
                contentTableHTML += '<div class="row">';
                contentTableHTML += '<div class="t-center" style="padding-top: 10px;">';
                contentTableHTML += '<a class="button" onclick="deleteFromCart(' + data[i].itemId + ')"> Delete </a>';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '</div>';
                contentTableHTML += '</article>'
            }
            if (switcher) {
                contentTableHTML += '<article class="background-white">';
            } else {
                contentTableHTML += '<article class="background-gray">';
            }
            contentTableHTML += '<div class="art-content">';
            contentTableHTML += '<div class="row">';
            contentTableHTML += '<div class="col-1-2">';
            contentTableHTML += '<div class="wrap-col">';
            contentTableHTML += '<div>';
            contentTableHTML += '<h1 style="text-align: right">Total: </h1>';
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '<div class="col-1-2">';
            contentTableHTML += '<div class="wrap-col">';
            contentTableHTML += '<div>';
            contentTableHTML += '<h1 style="text-align: right">' + total + ' RUB</h1>';
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '</article>';
        }

        var contentTableDiv = document.getElementById("cart");
        contentTableDiv.innerHTML = contentTableHTML;
    }).fail(function () {
        alert("НЕ ОЧ");
    });
}

function innerHtml(data) {
    var switcher = true;
    var total = 0;
    var contentTableHTML = '';
    if(data.length === 0){
        contentTableHTML += '<article class="background-white">';
        contentTableHTML += '<div class="art-content">';
        contentTableHTML += '<div class="row">';
        contentTableHTML += '<h1>The cart is empty</h1>'
        contentTableHTML += '</div>';
        contentTableHTML += '</div>';
        contentTableHTML += '</article>'
    } else {
        for (var i = 0; i < data.length; i++) {
            total += Number(data[i].price);

            if (switcher) {
                contentTableHTML += '<article class="background-white">';
                switcher = false;
            } else {
                contentTableHTML += '<article class="background-gray">';
                switcher = true;
            }
            contentTableHTML += '<div class="art-content">';
            contentTableHTML += '<div class="row">';
            contentTableHTML += '<div class="col-1-5">';
            contentTableHTML += '<div class="wrap-col">';
            contentTableHTML += '<div>';
            contentTableHTML += '<img src="' + data[i].photoUrl + '">';
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '<div class="col-3-5">';
            contentTableHTML += '<div class="wrap-col">';
            contentTableHTML += '<div class="row">';
            contentTableHTML += '<div class="t-center" style="padding-top: 10px;">';
            contentTableHTML += '<h3 style="font-size: 26px;">' + data[i].name + '</h3>';
            contentTableHTML += '<p>' + data[i].price + ' RUB</p>';
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '<div class="col-1-5">';
            contentTableHTML += '<div class="wrap-col">';
            contentTableHTML += '<div class="row">';
            contentTableHTML += '<div class="t-center" style="padding-top: 10px;">';
            contentTableHTML += '<a class="button" onclick="deleteFromCart(' + data[i].itemId + ')"> Delete </a>';
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '</div>';
            contentTableHTML += '</article>'
        }
        if (switcher) {
            contentTableHTML += '<article class="background-white">';
        } else {
            contentTableHTML += '<article class="background-gray">';
        }

        contentTableHTML += '<div class="art-content">';
        contentTableHTML += '<div class="row">';
        contentTableHTML += '<div class="col-1-2">';
        contentTableHTML += '<div class="wrap-col">';
        contentTableHTML += '<div>';
        contentTableHTML += '<h1 style="text-align: right">Total: </h1>';
        contentTableHTML += '</div>';
        contentTableHTML += '</div>';
        contentTableHTML += '</div>';
        contentTableHTML += '<div class="col-1-2">';
        contentTableHTML += '<div class="wrap-col">';
        contentTableHTML += '<div>';
        contentTableHTML += '<h1 style="text-align: right">' + total + ' RUB</h1>';
        contentTableHTML += '</div>';
        contentTableHTML += '</div>';
        contentTableHTML += '</div>';
        contentTableHTML += '</article>';
    }

    return contentTableHTML;
}