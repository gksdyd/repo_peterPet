goList = function (thisPage) {
    document.querySelector("input[name=thisPage]").value = thisPage;
    form.action = goUrlXdmList;
    form.submit();
}

changeRow = function () {
    document.getElementById("rowNumToShow").value = document.getElementById("changeRowNum").value;
    form.action = goUrlXdmList;
    form.submit();
}