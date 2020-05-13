function addCart() {
    let av = $("#available").text().replace("На складе осталось", "");
    alert($("#amount").val());
    alert(av);
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