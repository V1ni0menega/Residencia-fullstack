const admin_div = document.getElementById("admin_role");
const user_div = document.getElementById("user_role");
const role_localstorage = localStorage.getItem("userRole") || "USER";

admin_div.style.display = "none";
user_div.style.display = "none";

if (role_localstorage === "USER") {
  user_div.style.display = "block";
} else if (role_localstorage === "ADMIN") {
  admin_div.style.display = "block";
}
