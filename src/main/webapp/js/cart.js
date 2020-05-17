function updateCart(inp) {
    let avail = $(inp).attr("available");
    let newVal = $(inp).val();

    if (parseInt(newVal) < 1 || !$.isNumeric(newVal)) {
        newVal = 1;
        $(inp).val(1);
    }
    if (parseInt(newVal) > parseInt(avail)) {
        alert("Слишком большое количество, выберите меньше");
        $(inp).val(1);
        newVal = 1;
    }
    $.get('cart', {
        amount: newVal,
        productId: $(inp).attr("productId")
    }, function (responseJSON) {
        if (responseJSON.done === "false") {
            alert("Упс, кто-то уже выкупил нужное вам колличество")
            $('#available' + $(inp).attr("productId")).text(responseJSON.amountAvail);
            $('#priceOne' + $(inp).attr("productId")).attr("available", responseJSON.amountAvail);
        } else {
            $("#cartSum").html(responseJSON.cartSum);
            $("#price" + $(inp).attr("productId")).html(responseJSON.price);
            $('#available' + $(inp).attr("productId")).text(responseJSON.amountAvail);
            $('#priceOne' + $(inp).attr("productId")).attr("available", responseJSON.amountAvail);
        }
    });

}

function cleanCart() {
    location.replace("http://localhost:8080/index.jsp");
    alert("Корзина очищена")
    $.get('cart', {
        productId: -1
    }, function (responseJSON) {

    })

}

function plus(it) {
    let v = it.closest("div");
    let inp = v.find("input");
    let r = parseInt(inp.val());
    r = r + 1;
    $(inp).val(r);
    updateCart(inp);
}

function minus(it) {
    let v = it.closest("div");
    let inp = v.find("input");
    let r = parseInt(inp.val());
    r = r - 1;
    if (r < 1) {
        r = 1;
    }
    $(inp).val(r);
    updateCart(inp);
}

$(document).ready(function () {
    let url = window.location.href;
    url = url.replace("cart", "cart?availableAll=true");
    getJson(url);
    $("div input").change(
        function () {
            updateCart($(this))
        }
    )

})
function deleteFromCart(btn) {
    $.get('cart', {
        amount: -1,
        productId: $(btn).attr("productId")
    }, function (responseJSON) {
        if (responseJSON.cartCount > 0) {
            $('#' + $(btn).attr("productId")).remove();
            $('#cartSum').html(responseJSON.cartSum);
            $("#cartCount").text(responseJSON.cartCount);
        } else {
            history.pushState(null, null, "http://localhost:8080/cart");
            window.location.reload();
        }
    });

}

function getJson(url) {
    $.get(url,
        function (responseJSON) {
            for (let i = 0; i < responseJSON.length; i++) {
                $('#available' + responseJSON[i].productId).text(responseJSON[i].amountAvail);
                $('#priceOne' + responseJSON[i].productId).attr("available", responseJSON[i].amountAvail);
            }
        });


}