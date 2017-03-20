// sweetest.js

handleClick = function(e) {
    console.log("sweetest html click");
    alert("Hello World (sweetest html)!");
}

var el = document.getElementById("btn");
el.addEventListener("click", handleClick, false);
