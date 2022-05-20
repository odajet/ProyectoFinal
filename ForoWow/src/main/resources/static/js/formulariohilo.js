(function() {

	let tituloCategoria = document.getElementById('hil');
	let contenidoHilo = document.getElementById('men');
	let fechaHilo = document.getElementById('fechaCreacion');
	let botonEnviar = document.getElementById('botHilo');
	let check1=true;
	let check2=true;
	let check3=true;
	botHilo.addEventListener('click', function(event) {
		
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