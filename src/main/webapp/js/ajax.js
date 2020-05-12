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
ajax_get('/getRandomProductOnMain', function(data) {
    for (var i = 0; i <= data["Фигурки"].length-1; i++) {
        var html = "<div class='card' id='card"+ i +"'>";
        html += "<a class='card-link' href='http://localhost:8080/product.jsp?productId=" + data["Фигурки"][i]["id"] + "'/>";
        html += "<div class='card-image-preview' id='card-image-preview' style='background-image: url("+ data["Фигурки"][i]["image"] + ")'></div>";
        html += "<div class='card-description'>";    
        html += "<h2 class='content-card-link-title' id='card-title'>"+ data["Фигурки"][i]["title"] +"</h2>";
        html += "<h3 class='content-card-link-price' id='card-price'>"+ data["Фигурки"][i]["price"] +" грн.</h3>";
        html +="</div></a></div>";
        document.getElementById('content-card-holder-box1').innerHTML += html;
    }
    for (var i = 0; i <= data["Одежда"].length-1; i++) {
        var html = "<div class='card' id='card"+ i +"'>";
        html += "<a class='card-link' href='http://localhost:8080/product.jsp?productId=" + data["Одежда"][i]["id"] + "'/>";
        html += "<div class='card-image-preview' id='card-image-preview' style='background-image: url("+ data["Одежда"][i]["image"] + ")'></div>";
        html += "<div class='card-description'>";    
        html += "<h2 class='content-card-link-title' id='card-title'>"+ data["Одежда"][i]["title"] +"</h2>";
        html += "<div class='colors-array-container'>";
        for(let j = 0; j < data["Одежда"][i].color.length; j++ ){
            html += "<div class='color-array' id='" + data["Одежда"][i].color[j] + "' style='background-color:"+ data["Одежда"][i].color[j] + "'></div>";
        }
        html += "</div>";
        html += "<h3 class='content-card-link-price' id='card-price'>"+ data["Одежда"][i]["price"] +" грн.</h3>";
        html +="</div></a></div>";
        document.getElementById('content-card-holder-box0').innerHTML += html;
    }
    for (var i = 0; i <= data["Игрушки"].length-1; i++) {
        var html = "<div class='card' id='card"+ i +"'>";
        html += "<a class='card-link' href='http://localhost:8080/product.jsp?productId=" + data["Игрушки"][i]["id"] + "'/>";
        html += "<div class='card-image-preview' id='card-image-preview' style='background-image: url("+ data["Игрушки"][i]["image"] + ")'></div>";
        html += "<div class='card-description'>";    
        html += "<h2 class='content-card-link-title' id='card-title'>"+ data["Игрушки"][i]["title"] +"</h2>";
        html += "<h3 class='content-card-link-price' id='card-price'>"+ data["Игрушки"][i]["price"] +" грн.</h3>";
        html +="</div></a></div>";
        document.getElementById('content-card-holder-box2').innerHTML += html;
    }
    for (var i = 0; i <= data["Аксессуары"].length-1; i++) {
        var html = "<div class='card' id='card"+ i +"'>";
        html += "<a class='card-link' href='http://localhost:8080/product.jsp?productId=" + data["Аксессуары"][i]["id"] + "'/>";
        html += "<div class='card-image-preview' id='card-image-preview' style='background-image: url("+ data["Аксессуары"][i]["image"] + ")'></div>";
        html += "<div class='card-description'>";
        html += "<h2 class='content-card-link-title' id='card-title'>"+ data["Аксессуары"][i]["title"] +"</h2>";
        html += "<h3 class='content-card-link-price' id='card-price'>"+ data["Аксессуары"][i]["price"] +" грн.</h3>";
        html +="</div></a></div>";
        document.getElementById('content-card-holder-box3').innerHTML += html;
    }
});