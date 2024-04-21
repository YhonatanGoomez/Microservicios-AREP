function login(){
    let inputUserName = document.getElementById("username");
    let inputPassword = document.getElementById("password");
    let endPoint = "/usuario/login";
    let peticion = solicitarEndPoint(endPoint, {
        username: inputUserName.value,
        password: inputPassword.value
    });
    peticion.then(respuesta =>{
        if (respuesta != undefined) {
            let resp = JSON.parse(respuesta);
            sessionStorage.setItem("jwt", resp.jwt);
            sessionStorage.setItem("usuario", inputUserName.value);
            location.href = "chattwitter.html";
        }
    })
}

async function solicitarEndPoint(endPoint, body){
    return await fetch(window.location.origin + endPoint, {
        headers: {
            "Content-Type": "application/json"
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
    .catch(error => alert("usuario o contraseña incorrectos"));
}