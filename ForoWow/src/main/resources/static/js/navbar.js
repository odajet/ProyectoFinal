let searchInput = document.querySelector('input[type="search"]');
searchInput.addEventListener('input', function(e) {
  getSearchResults(
    '/buscar-todo', /* Ruta de java*/
    "busqueda=" + e.srcElement.value /* Lo que el tio escribe */
  );
})
let searchResults = document.querySelector('.searchResults');

function getSearchResults(url, data) {
  searchResults.innerHTML = '';
  let request = new XMLHttpRequest();
  request.open('POST', url, true);
  request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
  request.onreadystatechange = function() {
    if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
      // Respuesta recibida
      response = request.responseText
      if (response) {
		console.log(response);
        dataResponse = JSON.parse(response);
        setSearchResults(dataResponse.resultado);
      }

    } else {
      // Servidor no responde
      //console.log('server error');
    }
  };

  request.onerror = function() {
    // Ha ocurrido un error en la petición
    //console.log('something went wrong');
  };

  request.send(data);
}

function setSearchResults(results) {
  results.forEach(r => {
    {
      /* Espera objetos en un array [{title: "patata", category: "patata", route: "patata"}...]
			Cambiar esta parte según como venga el objeto*/
      let result = createResult(r.titulo, r.tipo, r.ruta);
      searchResults.append(result);
    }
  })
}

function createResult(title, subtitle, route) {
  let newResult = document.createElement('div');
  newResult.classList.add('searchResult');
  let link = document.createElement('a');
  link.setAttribute('href', route);
  let titleDiv = document.createElement('div');
  titleDiv.classList.add('resultTitle');
  titleDiv.textContent = title;
  let subtitleDiv = document.createElement('div');
  subtitleDiv.classList.add('resultSubtitle');
  subtitleDiv.textContent = subtitle;

  link.append(titleDiv);
  link.append(subtitleDiv);
  newResult.append(link);
  return newResult;
}
