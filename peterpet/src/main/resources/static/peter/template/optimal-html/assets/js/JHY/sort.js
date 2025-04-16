$("#shSortBy").on("change", function () {
    form.action = URL_PETER_SHOP;
    form.method = "post";
    form.submit();
});