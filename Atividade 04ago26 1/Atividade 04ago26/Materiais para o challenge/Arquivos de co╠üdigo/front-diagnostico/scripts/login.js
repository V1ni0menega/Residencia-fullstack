const submitButton = document.getElementsByClassName("btn-login").item(0);
const email = document.getElementById("catmail");
const password = document.getElementById("catsenha");

email.addEventListener("input", checkInputs);
password.addEventListener("input", checkInputs);

submitButton?.addEventListener("click", (event) => {
  event.preventDefault();
  localStorage.setItem("userRole", "USER");
  window.location.href = "home.html";
});

// Acessei no dia 28/04/2026

function isFilled() {
  return email.value.trim() !== "" && password.value.trim() !== "";
}

function checkInputs() {
  if (isFilled()) {
    submitButton.removeAttribute("disabled");
  } else {
    submitButton.setAttribute("disabled", true);
  }
}
