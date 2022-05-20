(function() {

	let tituloCategoria = document.getElementById('nombre');
	let botonEnviar = document.getElementById('botonEnviar');
	botonEnviar.addEventListener('click', function(event) {
		
		if (tituloCategoria.value.length < 3) {
			document.querySelector('.aviso').style.display = 'block';
			
			return false;
		} else {
			document.formularioCategoria.submit();
		
		}
	});

})();



