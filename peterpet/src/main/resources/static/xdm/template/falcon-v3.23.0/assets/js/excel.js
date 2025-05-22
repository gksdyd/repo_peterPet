function excel() {
    let today = new Date();
    let date = today.getFullYear().toString() + (today.getMonth() + 1).toString().padStart(2, '0') + today.getDate().toString().padStart(2, '0');
    $("table").table2excel({
        exclude: "",
        name: "",
        filename: $("table").attr("data-value") + date, //확장자를 여기서 붙여줘야한다.
        fileext: ".xls",
        exclude_img: true,
        exclude_links: true,
        exclude_inputs: true
    });
}

function excelSpring(name) {
    form.method = "POST";
    form.action = "/xdm/code/CodeXdmExcelProc";
    let head = $(".text-900");
    let arr = [];
    for (let i = 0; i < head.length; i++) {
        arr.push(head.eq(i).text());
    }
    $("#header").attr("value", arr);
    $("#name").attr("value", name);
    
    form.submit();
}