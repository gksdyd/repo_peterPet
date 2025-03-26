document.getElementById("registerBtn").onclick = function() {
    document.getElementById("registerOrModifyFlag").value = 1;
    form.action = goUrlXdmForm;
    form.submit();
}