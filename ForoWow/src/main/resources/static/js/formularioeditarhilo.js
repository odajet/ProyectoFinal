(function() {

	let tituloCategoria = document.getElementById('hilo');
	let contenidoHilo = document.getElementById('contenido');
	let fechaHilo = document.getElementById('fechaCrea');
	let botonEnviar = document.getElementById('botonHilo');
	let check1=true;
	let check2=true;
	let check3=true;
	botonEnviar.addEventListener('click', function(event) {
		
		if (tituloCategoria.value.length < 3) {
			document.querySelector('.aviso1').style.display = 'block';
			check1=false;
		}else{
			check1 = true;
			document.querySelector('.aviso1').style.display = 'none';
		}
		if(contenidoHilo.value.length < 3) {
			document.querySelector('.aviso2').style.display = 'block';
			check2=false;
		}else{
			 check2=true;
			 document.querySelector('.aviso2').style.display = 'none';
		}
		if(fechaHilo.value==""){
			document.querySelector('.aviso3').style.display = 'block';
			check3=false;
		}else{
			check3=true;
			document.querySelector('.aviso3').style.display = 'none';
		} 
		 if(check1 == true && check2 == true && check3 == true){
			document.formularioHilo.submit();
		
		}
		
	});

})();