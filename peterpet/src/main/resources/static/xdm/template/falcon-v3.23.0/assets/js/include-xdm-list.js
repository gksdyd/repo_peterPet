let form = document.querySelector("form[name=formList]");
goList = function (thisPage, address) {
    document.querySelector("input[name=thisPage]").value = thisPage;
    form.action = address;
    form.submit();
}
  
changeProductType = function () {
  form.action = "/xdm/product/ProductXdmList";
  form.submit();
}