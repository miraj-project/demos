// sweetest.js

handleClick = function(e) {
    console.log("sweetest html click");
    alert("Hello, Sweetest HTML World!");
}

var el = document.getElementById("btn");
el.addEventListener("click", handleClick, false);
