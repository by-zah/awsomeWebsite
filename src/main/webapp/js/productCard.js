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
    $("#size").change(
        function () {

            getAll();
        }
    );
    $("#color").change(
        function () {

            getAll();
        }
    );
});

//собирает значения с селекта размера и цвета и отправляет на сервер
function getAll() {
    //чистить контент
    let url = new URL('http://localhost:8080/product?');
    let params = new URLSearchParams(url.search.slice(1));
    $.each($("#color option:selected"), function () {
        url.searchParams.append('color', $(this).val());
    });
    $.each($("#size option:selected"), function () {
        url.searchParams.append('size', $(this).val());
    });
    url.searchParams.append('productId', $("#productId").val());
    alert(url);
    history.pushState(null, null, url);//заменяет урду сверху но не переходит
    getJson();
}

let isEmpty = function (variable) {
    return variable === undefined || variable === null || variable === '' || variable.length === 0;
}
