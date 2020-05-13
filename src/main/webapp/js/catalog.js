// function ajax_get(url, callback) {
//     var xmlhttp = new XMLHttpRequest();
//     xmlhttp.onreadystatechange = function() {
//         if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
//             console.log('responseText:' + xmlhttp.responseText);
//             try {
//                 var data = JSON.parse(xmlhttp.responseText);
//             } catch(err) {
//                 console.log(err.message + " in " + xmlhttp.responseText);
//                 return;
//             }
//             callback(data);
//         }
//     };
//     alert(url);
//
//     xmlhttp.open("GET", url, true);
//     xmlhttp.send();
// }
// ajax_get('/catalog', function(data) {
//     for (var i = 0; i <= data["Tshirt"].length-1; i++) {
//         var html = "<div class='card' id='card"+ i +"'>";
//         html += "<a class='card-link' href='#''>";
//         html += "<div class='card-image-preview' id='card-image-preview' style='background-image=url(''"+ data["Tshirt"][i]["image"] + "'')'></div>";
//         html += "<div class='card-description'>";
//         html += "<h2 class='content-card-link-title' id='card-title'>"+ data["Tshirt"][i]["title"] +"</h2>";
//         html += "<h3 class='content-card-link-price' id='card-price'>"+ data["Tshirt"][i]["price"] +"</h3>";
//         html +="</div></a></div>";
//         document.getElementById('content-card-holder-box0').innerHTML += html;
//     }
// });

function getAll() {
    var contentCard = document.getElementById('content-card-holder-box0');
    if(contentCard.innerHTML != ""){
        contentCard.innerHTML = "";
    }

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

function getJson() {
    $.get(window.location.href,
        function (responseJSON) {
            let i;
            for (i = 0; i < responseJSON.length; i++) {
                if (isEmpty(responseJSON[i].title) === false) {
                    let html = "<div class='card' id='card" + i + "'>";
                    html += "<a class='card-link' href='http://localhost:8080/product.jsp?productId=" + responseJSON[i].id + "'/>";
                    html += "<div class='card-image-preview' " +
                        "id='card-image-preview' " +
                        "style='background-image: url(" + responseJSON[i].image + ")'></div>";
                    html += "<div class='card-description'>";
                    html += "<h2 class='content-card-link-title' id='card-title'>" + responseJSON[i].title + "</h2>";
                    if(responseJSON[i].category == "Tshirt"){
                        html += "<div class='colors-array-container'>";
                        for(let j = 0; j < responseJSON[i].color.length; j++ ){
                            html += "<div class='color-array' id='" + responseJSON[i].color[j] + "' style='background-color:"+ responseJSON[i].color[j] + "'></div>";
                        }
                        html += "</div>";
                    }
                    html += "<h3 class='content-card-link-price' id='card-price'>" + responseJSON[i].price + "</h3>";
                    html += "</div></a></div>";
                    document.getElementById('content-card-holder-box0').innerHTML += html;

                    console.log(responseJSON[i].title);
                    console.log(responseJSON[i].id);
                }
            }
        });
    let url = window.location.href;
    url = url.replace("catalog", "catalog.jsp");
    history.pushState(null, null, url);

}

$(document).ready(function () {
    let url = window.location.href;
    url = url.replace("catalog.jsp", "catalog");
    if (url.includes("Tshirt")) {
        $("#Tshirt").prop("checked", true);
    }
    if (url.includes("Toy")) {
        $("#Toy").prop("checked", true);
    }
    if (url.includes("Figure")) {
        $("#Figure").prop("checked", true);
    }
    if (url.includes("Accessory")) {
        $("#Accessory").prop("checked", true);
    }
    alert(url);
    history.pushState(null, null, url);
    getJson();
});
