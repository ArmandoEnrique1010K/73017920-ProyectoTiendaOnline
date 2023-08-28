const nav = document.querySelector("#movile-menu");
const abrir = document.querySelector("#abrir");
const cerrar = document.querySelector("#cerrar");

const toggleMenuElement = document.getElementById('abrir');
const toggleMenuElement2 = document.getElementById('cerrar');


const menuHamburguesa = document.querySelector('.menu-hamburguesa-caja-categorias');

// AÑADE EL VALOR visible A LA CLASE menu-hamburguesa-caja-categorias
abrir.addEventListener("click", () => {
    nav.classList.add("visible");
});

// QUITA EL VALOR visible A LA CLASE menu-hamburguesa-caja-categorias
cerrar.addEventListener("click", () => {
    nav.classList.remove("visible");
});

// Agregar evento click al div
toggleMenuElement.addEventListener('click', () => {
// Cambiar la visibilidad del menú

// Bloquear o permitir el scroll del documento según la visibilidad del menú
    if (menuHamburguesa.classList.contains('visible')) {
        document.documentElement.classList.add('scroll-bloqueado');
        document.body.classList.add('scroll-bloqueado');
    }
});


toggleMenuElement2.addEventListener('click', () => {
// Cambiar la visibilidad del menú

    if (!menuHamburguesa.classList.contains('visible')) {
        document.documentElement.classList.remove('scroll-bloqueado');
        document.body.classList.remove('scroll-bloqueado');
    }
});