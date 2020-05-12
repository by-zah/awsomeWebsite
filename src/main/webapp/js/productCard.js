let isEmpty = function (variable) {
    return variable === undefined || variable === null || variable === '' || variable.length === 0;
}

function getJson() {
    $.get(window.location.href,
        function (responseJSON) {
            alert(responseJSON);
            $("#title").text(responseJSON.title);
            $("#category").text(responseJSON.category);
            $("#price").text(responseJSON.price);
            $("#description").text(responseJSON.description);
            $("#image").attr("src", responseJSON.image);
        });
    let url = window.location.href;
    url = url.replace("product", "product.jsp");
    history.pushState(null, null, url);

}

$(document).ready(function () {
    let url = window.location.href;
    url = url.replace("product.jsp", "product");
    alert(url);
    history.pushState(null, null, url);
    getJson();
});

//собирает значения с селекта размера и цвета и отправляет на сервер
function getAll() {
    //чистить контент
    let url = new URL('http://localhost:8080/catalog?');
    let params = new URLSearchParams(url.search.slice(1));
    url.searchParams.set('sortType', $('#sortType').val());
    $.each($('.search-checkbox:checked'), function () {
        url.searchParams.append('category', $(this).val());
        if ($(this).val() === "Tshirt") {
            $.each($("#color option:selected"), function () {
                url.searchParams.append('color', $(this).val());
            });
            $.each($("#size option:selected"), function () {
                url.searchParams.append('size', $(this).val());
            });
        }
    });

    if (!!$("#name").val()) {
        url.searchParams.append('productName', $("#name").val());
    }
    if (!!$("#priceFrom").val()) {
        url.searchParams.append('priceFrom', $("#priceFrom").val());
    }
    if (!!$("#priceTo").val()) {
        url.searchParams.append('priceTo', $("#priceTo").val());
    }
    alert(url);
    history.pushState(null, null, url);//заменяет урду сверху но не переходит
    getJson();
}

let isEmpty = function (variable) {
    return variable === undefined || variable === null || variable === '' || variable.length === 0;
}
