function getJsonMini(url) {
    $.get(url,
        function (responseJSON) {

            $("#idUnic").attr("value", responseJSON.idUnic);
            if (!isEmpty(responseJSON.price)) {
                $("#price").text(responseJSON.price + " грн.");
            }
            $("#image").attr("src", responseJSON.image);
            $("#available").text(responseJSON.amountAvail);
            console.log("lybyf  " + responseJSON.colors.length);
        });
}

function getJson(url) {

    $.get(url,
        function (responseJSON) {

            $("#title").text(responseJSON.title);
            $("#productId").attr("value", responseJSON.idProd);
            $("#idUnic").attr("value", responseJSON.idUnic);
            $("#category").text("категория: " + responseJSON.category);
            $("#price").text(responseJSON.price + " грн.");
            $("#description").text(responseJSON.description);
            $("#image").attr("src", responseJSON.image);
            $("#available").text(responseJSON.amountAvail);
            for (let i = 0; i < responseJSON.colors.length; i++) {
                let option = new Option(responseJSON.colors[i]);
                if (responseJSON.colorSelect === option) {
                    option.selected = true;
                }

                $(option).appendTo('#color');
            }
            if (responseJSON.amountAvail === "Нет в наличии") {
                $("#price").css("color", "grey");
                $("#buy-button").attr("disabled", "disabled");
            }
            for (let i = 0; i < responseJSON.sizes.length; i++) {
                let option = new Option(responseJSON.sizes[i]);
                if (responseJSON.sizeSelect === option.value) {
                    option.selected = true;
                }
                $(option).appendTo('#size');
            }
            console.log(responseJSON.colors[0]);
            if (responseJSON.colors[0] === "null") {
                $("#color").remove();
                $("#size").remove();
                $(".measure-link").remove();
                $(".size-title").remove();
            }
        });


}

$(document).ready(function () {
    let url = window.location.href;
    url = url.replace("product.jsp", "product");
    getJson(url);
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

    getJsonMini(url);
}

let isEmpty = function (variable) {
    return variable === undefined || variable === null || variable === '' || variable.length === 0;
}

function addCart() {
    let av = $("#available").text().replace("На складе осталось", "");
    if (parseInt($("#amount").val()) > parseInt(av)) {
        alert("Слишком большое количество, выберите меньше")
    } else {
        $.get('cart', {
            amount: $("#amount").val(),
            productId: $("#idUnic").val()
        }, function (responseJSON) {
            alert("Успешно добавленно")
        });
    }
}
