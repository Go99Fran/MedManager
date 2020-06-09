
function comprobarMails() {
	var mail1 = document.getElementById("mail").value
	var mail2 = document.getElementById("mail2").value
	if (mail1 == mail2) {
		document.getElementById("mailsIguales").innerHTML = "";
	} else {
		document.getElementById("mailsIguales").innerHTML = "Los mails no coinciden";
	}
}
