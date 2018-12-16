function getCookie(name) {
    var matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}


function auth() {
    if(getCookie('auth') != undefined){
        authAjax();
    }
}

function authAjax() {
    $.ajax({
        type: 'post',
        url: '/auth',
        data: {
            cookieValue: getCookie('auth')
        }
    }).done(function (data) {
        var contentTableHTML = '<a href=\'/user\'><span>' + data.name +'</span></a>';
        var contentTableDiv = document.getElementById("login");
        contentTableDiv.innerHTML = contentTableHTML;
    }).fail(function () {
        alert("НЕ ОЧ");
    });
}