(function() {

	let tituloCategoria = document.getElementById('nombreEditar');
	let botonEnviar = document.getElementById('botonEditarEnviar');
	botonEnviar.addEventListener('click', function(event) {
		
		if (tituloCategoria.value.length < 3) {
			document.querySelector('.aviso').style.display = 'block';
			
			return false;
		} else {
			document.formularioCategoria.submit();
		
		}
	});

})();