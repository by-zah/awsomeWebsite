/*
let isEmpty = function (variable) {
    return variable === undefined || variable === null || variable === '' || variable.length === 0;
}

function getJson() {
    $.get(window.location.href,
        function (data) {
            let i;
            for (i = 0; i < data["Tshirts"].length; i++) {
                if (isEmpty(data[i].title) === false) {
                	let html = "<div class='order' id='" + i + "'>";
					html += "<div class='order-image-container'><img class='order-image' src='" + data["Tshirts"][i].image + "'></div>";
					html += "<div class='goods-description-container'>";
					html += "<div class='order-product-title-container'><h3 class='order-product-title'>" + data["Tshirts"][i].title + "</h3></div>";
					html += "<div class='order-product-description-container'>";
					if(data["Tshirts"][i].category == "Tshirt"){
                        html += "<div class='order-product-color-container'>";
                        for(let j = 0; j < data["Tshirts"][i].color.length; j++ ){
                        	html += "<h3 class='order-color-title'>цвет:</h3><div class='order-product-color' id='" + data["Tshirts"][i].color[j] + "' style='background-color:"+ data["Tshirts"][i].color[j] + "'></div>";
                        }
                        html += "</div>";
                    }
					html += "<h3 class='order-product-price'>цена:  " + data["Tshirts"][i].price + "</h3>";	
					html += "<div class='order-product-amount-container'><h3 class='order-amount-title'>кол-во:</h3><input class='order-product-amount' value='4' disabled></div>";		
					html += "</div></div></div>";
                    document.getElementByID("orders-container").innerHTML += html;

                    console.log(data[i].title);
                    console.log(data[i].id);
                }
            }
        });
    let url = window.location.href;
    url = url.replace("order", "order.jsp");
    history.pushState(null, null, url);

}

$(document).ready(function () {

    let url = window.location.href;
    url = url.replace("catalog.jsp", "catalog");
    alert(url);
    history.pushState(null, null, url);

    getJson();
});

*/
function ajax_get(url, callback) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            console.log('responseText:' + xmlhttp.responseText);
            try {
                var data = JSON.parse(xmlhttp.responseText);
            } catch(err) {
                console.log(err.message + " in " + xmlhttp.responseText);
                return;
            }
            callback(data);
        }
    };
 
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}
ajax_get('js/data.json', function(data) {
    for (var i = 0; i <= data["Tshirt"].length-1; i++) {
        let html = "<div class='order-wrapper'><div class='order' id='" + i + "'>";
		html += "<div class='order-image-container'><img class='order-image' src='" + data["Tshirt"][i].image + "'></div>";
		html += "<div class='goods-description-container'>";
		html += "<div class='order-product-title-container'><h3 class='order-product-title'>" + data["Tshirt"][i].title + "</h3></div>";
		html += "<div class='order-product-description-container'>";
		if(data["Tshirt"][i].category == "Tshirt"){
            html += "<div class='order-product-color-container'><h3 class='order-color-title'>цвет:</h3>";
            html += "<div class='order-product-color' id='" + data["Tshirt"][i].color[0] + "' style='background-color:"+ data["Tshirt"][i].color[0] + "'></div>";
            html += "</div>";
        }
		html += "<h3 class='order-product-price'>цена:  " + data["Tshirt"][i].price + "</h3>";	
		html += "<div class='order-product-amount-container'><h3 class='order-amount-title'>кол-во:</h3><input class='order-product-amount' value='4' disabled></div>";		
		html += "</div></div></div></div>";
        document.getElementById("orders-container").innerHTML += html;
        console.log(Object.keys(data).length);
    }
});