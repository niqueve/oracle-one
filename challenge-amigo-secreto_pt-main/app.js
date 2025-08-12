//O principal objetivo deste desafio é fortalecer suas habilidades em lógica de programação. Aqui você deverá desenvolver a lógica para resolver o problema.

let listaAmigos = [];
let listaAmigosUl = document.getElementById("listaAmigos");
let resultadoUl = document.getElementById("resultado");
let inputAmigo = document.getElementById("amigo");

//-------------------------------------- Função para receber amigos e adicionar a lista
function adicionarAmigo(){
    let amigo = inputAmigo.value;
    if (amigo === ""){
        alert("Digite um nome válido");
    }else{
        listaAmigos.push(amigo);
        listaAmigosUl.innerHTML += (amigo + ", ");
        inputAmigo.value = "";
    }
};

//--------------------------------------------- Função para sortear amigo secreto
function sortearAmigo(){
    let numSorteado = Math.floor(Math.random()* listaAmigos.length);
    let amigoSorteado = listaAmigos[numSorteado];
    resultadoUl.innerHTML = amigoSorteado;
};