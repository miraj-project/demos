// sweetest.js

handleClick = function(e) {
    console.log("sweetest polymer click");
    alert("Hello World (sweetest polymer)!");
}

var el = document.getElementById("btn");
el.addEventListener("click", handleClick, false);
