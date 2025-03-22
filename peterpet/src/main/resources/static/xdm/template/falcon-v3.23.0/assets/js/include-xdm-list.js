let form = document.querySelector("form[name=formList]");
goList = function (thisPage, address) {
    document.querySelector("input[name=thisPage]").value = thisPage;
    form.action = address;
    form.submit();
}
  
changeProductType = function () {	
  document.getElementById("feedFilter").style.display = "none";
  document.getElementById("feedList").style.display = "none";

  if (document.getElementById("prodType").value == 1) {
    document.getElementById("feedFilter").style.display = "block";
    document.getElementById("feedList").style.display = "block";
  }
}