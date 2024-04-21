function init() {
    if (sessionStorage.getItem("jwt") === null) location.href = "index.html";
    cargarPosts();
}

function postTwitter() {
    let inputComentarios = document.getElementById("comentarios");
    let endPoint = "/hilo/1/post";
    let peticion = solicitarEndPointPOST(endPoint, {
        usuario: sessionStorage.getItem("usuario"),
        comentario: inputComentarios.value
    }, "POST");
    peticion.then(respuesta => {
        cargarPosts();
    })
}

function cargarPosts() {
    let endPoint = "/hilo/1";
    let posts = document.getElementById("posts");
    let peticion2 = solicitarEndPointGET(endPoint, "GET");
    posts.textContent = "";
    peticion2.then(respuesta =>{
        if (respuesta != undefined) {
            let hilo = JSON.parse(respuesta);
            let hiloString = "";
            for (const post of hilo.posts) {
              const listItem = document.createElement("div");
              const boldText = document.createElement("strong");
              boldText.textContent = post.usuario;
              const regularText = document.createTextNode(post.comentario);
              listItem.appendChild(boldText);
              listItem.appendChild(document.createElement("br"));
              listItem.appendChild(regularText);
              posts.appendChild(listItem);
            }
        }
    })
}

async function solicitarEndPointPOST(endPoint, body, methodRequest){
    return await fetch(window.location.origin + endPoint, {
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + sessionStorage.getItem("jwt")
        },
        method: "POST",
        body: JSON.stringify(body)
    })
    .then(respuesta => {
        if(!respuesta.ok){
            throw new Error();
        }
        return respuesta.text();
    })
    .catch(error => alert("¡Hubo un error!"));
}

async function solicitarEndPointGET(endPoint, body){
    return await fetch(window.location.origin + endPoint, {
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + sessionStorage.getItem("jwt")
        },
        method: "GET"
    })
    .then(respuesta => {
        if(!respuesta.ok){
            throw new Error();
        }
        return respuesta.text();
    })
    .catch(error => alert("¡Hubo un error!"));
}