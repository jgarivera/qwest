var html = document.querySelector("html");
var toggle = document.getElementById("toggle-dark-light-mode");
var toggleIcon = toggle.getElementsByTagName("i")[0];

function setIcon(theme) {
    if (theme == "dark") {
        toggleIcon.classList.replace("fa-sun", "fa-moon");
    } else {
        toggleIcon.classList.replace("fa-moon", "fa-sun");
    }
}

toggle.addEventListener("click", function () {
    var theme = null;

    if (html.getAttribute("data-theme") == "dark") {
        theme = "light";
    } else {
        theme = "dark";
    }

    setIcon(theme);
    html.setAttribute("data-theme", theme);
    localStorage.setItem("data-theme", theme);
});

window.onload = function() {
    var storedTheme = localStorage.getItem("data-theme");

    if (storedTheme != null) {
        setIcon(storedTheme);
        html.setAttribute("data-theme", storedTheme);
    }
};
