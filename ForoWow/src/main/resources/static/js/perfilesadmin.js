(function() {
let form = document.getElementsByClassName("form");
let lineas = document.querySelectorAll('.tabla tr');
lineas.forEach(function(linea){
	linea.addEventListener("click", function (event) {
  if(event.target.type==='td'){
	let row = event.target.parentElement;
}
  console.log(event.target);
});
})

})();

