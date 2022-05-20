(function() {

	let tituloCategoria = document.getElementById('hilo');
	let botonEnviar = document.getElementById('botonHilo');
	botonHilo.addEventListener('click', function(event) {
		
		if (tituloCategoria.value.length < 3) {
			document.querySelector('.aviso').style.display = 'block';
			
			return false;
		} else {
			document.formularioHilo.submit();
		
		}
	});

})();