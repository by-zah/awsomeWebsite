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
    for (var i = 0; i <= data["Comics"].length-1; i++) {
        var html = "<div class='card' id='card"+ i +"'>";
        html += "<a class='card-link' href='#''>";
        html += "<div class='card-image-preview' id='card-image-preview' style='background-image=url(''"+ data["Comics"][i]["image"] + "'')'></div>";
        html += "<div class='card-description'>";    
        html += "<h2 class='content-card-link-title' id='card-title'>"+ data["Comics"][i]["title"] +"</h2>";
        html += "<h3 class='content-card-link-price' id='card-price'>"+ data["Comics"][i]["price"] +"</h3>";                 
        html +="</div></a></div>";
        document.getElementById('content-card-holder-box3').innerHTML += html;
        console.log(Object.keys(data).length);
    }
    for (var i = 0; i <= data["Statuette"].length-1; i++) {
        var html = "<div class='card' id='card"+ i +"'>";
        html += "<a class='card-link' href='#''>";
        html += "<div class='card-image-preview' id='card-image-preview' style='background-image=url(''"+ data["Statuette"][i]["image"] + "'')'></div>";
        html += "<div class='card-description'>";    
        html += "<h2 class='content-card-link-title' id='card-title'>"+ data["Statuette"][i]["title"] +"</h2>";
        html += "<h3 class='content-card-link-price' id='card-price'>"+ data["Statuette"][i]["price"] +"</h3>";                 
        html +="</div></a></div>";
        document.getElementById('content-card-holder-box1').innerHTML += html;
    }
    for (var i = 0; i <= data["Tshirt"].length-1; i++) {
        var html = "<div class='card' id='card"+ i +"'>";
        html += "<a class='card-link' href='#''>";
        html += "<div class='card-image-preview' id='card-image-preview' style='background-image=url(''"+ data["Tshirt"][i]["image"] + "'')'></div>";
        html += "<div class='card-description'>";    
        html += "<h2 class='content-card-link-title' id='card-title'>"+ data["Tshirt"][i]["title"] +"</h2>";
        html += "<h3 class='content-card-link-price' id='card-price'>"+ data["Tshirt"][i]["price"] +"</h3>";                 
        html +="</div></a></div>";
        document.getElementById('content-card-holder-box0').innerHTML += html;
    }
    for (var i = 0; i <= data["Toy"].length-1; i++) {
        var html = "<div class='card' id='card"+ i +"'>";
        html += "<a class='card-link' href='#''>";
        html += "<div class='card-image-preview' id='card-image-preview' style='background-image=url(''"+ data["Toy"][i]["image"] + "'')'></div>";
        html += "<div class='card-description'>";    
        html += "<h2 class='content-card-link-title' id='card-title'>"+ data["Toy"][i]["title"] +"</h2>";
        html += "<h3 class='content-card-link-price' id='card-price'>"+ data["Toy"][i]["price"] +"</h3>";                 
        html +="</div></a></div>";
        document.getElementById('content-card-holder-box2').innerHTML += html;
    }
    

});