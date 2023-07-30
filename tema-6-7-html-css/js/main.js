const body = document.body;
const svgElement = document.getElementById('my-svg');

let isDarkMode = false;

function toggleDarkMode() {
    isDarkMode = !isDarkMode;
    if (isDarkMode) {
        body.classList.add('dark-mode');
        svgElement.classList.add('dark-mode');
    }
}

function toggleLightMode() {
    isDarkMode = false;
    body.classList.remove('dark-mode');
}