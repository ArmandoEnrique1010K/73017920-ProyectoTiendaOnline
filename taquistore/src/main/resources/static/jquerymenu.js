const nav = document.querySelector("#movile-menu");
const abrir = document.querySelector("#abrir");
const cerrar = document.querySelector("#cerrar");

// AÑADE EL VALOR visible A LA CLASE menu-hamburguesa-caja-categorias
abrir.addEventListener("click", () => {
    nav.classList.add("visible");
});

// QUITA EL VALOR visible A LA CLASE menu-hamburguesa-caja-categorias
cerrar.addEventListener("click", () => {
    nav.classList.remove("visible");
});
