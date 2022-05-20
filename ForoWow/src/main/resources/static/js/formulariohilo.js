(function() {

	let tituloCategoria = document.getElementById('hil');
	let botonEnviar = document.getElementById('botHilo');
	botHilo.addEventListener('click', function(event) {
		
		if (tituloCategoria.value.length < 3) {
			document.querySelector('.aviso').style.display = 'block';
			
			return false;
		} else {
			document.formularioHilo.submit();
		
		}
	});

})();