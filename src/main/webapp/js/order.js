$(document).ready(function () {

    let newselect;
    for (let i = 1; i<10; i++){
        newselect += "<option class='expiration-select-option' value=0" + i + ">0"+ i +"</option>"
    }
    document.getElementById("cc-expiration-1").innerHTML += newselect;
    newselect = "";
    for (let i = 20; i<36; i++){
        newselect += "<option class='expiration-select-option' value='20" + i + "'>20" + i +"</option>"
    }
    document.getElementById("cc-expiration-2").innerHTML += newselect;
});

function showDiv(divId, element) {
    document.getElementById("hidden-div").style.display = element.value == "PICKUP" ? 'none' : 'block';
    document.getElementById("discount-value").innerHTML = element.value == "PICKUP" ? '0' : '1%';
    document.getElementById("arrival-value").innerHTML = element.value == "PICKUP" ? '0 грн.' : '100 грн.';
}