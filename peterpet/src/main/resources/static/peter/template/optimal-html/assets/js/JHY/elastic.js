$("#searchProd").on("click", function() {
    search();
});

$("input[name=q]").on("keypress", function(e) {
    if (e.key === "Enter") {
        search();
    }
});

function search() {
    let type = $("#rgsearch-category").val();
    let name = $("input[name=q]").val();

    if (name === "") {
        return;
    }

    location.href = "/peter/shop/SearchPeterProduct?type=" + type + "&name=" + name;
}