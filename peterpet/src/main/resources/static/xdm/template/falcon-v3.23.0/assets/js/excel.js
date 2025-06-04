const MAX_FILE_SIZE = 5 * 1024 * 1024;

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

function excelPoi(name) {
    form.method = "POST";
    form.action = "/xdm/product/excelDownload";
    let head = $(".text-900");
    let arr = [];
    for (let i = 0; i < head.length; i++) {
        arr.push(head.eq(i).text());
    }
    $("#header").attr("value", arr);
    $("#name").attr("value", name);
    
    form.submit();
}

function insertFile() {
    document.getElementById('customFileSm').click();
}

function viewFile(inputFile) {
    if(inputFile.files.length == 1){
        var reader = new FileReader();        
        reader.onload = function(e){
            if (fileExtCheck() == false) {
                let text = "엑셀 파일만 등록 가능합니다!";
                warningModal(text);
                return;
            }

            if (fileSizeCheck() == false) {
                let text = "파일의 크기는 최대 5MB입니다!";
                warningModal(text);
                return;
            }

            fileCheck();
        }
    }
    reader.readAsDataURL(inputFile.files[0]);
}

function fileExtCheck() {
    let filePath = $("#customFileSm").val();
    let fileName = filePath.substring(filePath.lastIndexOf('\\')+1, filePath.length)
    let fileExt = fileName.split(".");

    if (fileExt[1] !== "xls" && fileExt[1] !== "xlsx") {
        return false;
    }
    return true;
}

function fileSizeCheck() {
    let fileSize = $("#customFileSm")[0].files[0].size;
    
    if (fileSize > MAX_FILE_SIZE) {
        return false;
    }
    return true;
}

function warningModal(text) {
    document.getElementById("modalTitle").innerText = "WARNING";
    document.getElementById("modalText").innerText = text;
    document.getElementById("modalCloseBtn").style.display = "block";
    document.getElementById("modal").style.display = "block";
}

modalCloseButton.onclick = function() {
    modal.style.display = "none";
}

function fileCheck() {
    let formData = new FormData();
    formData.append("file", $("#customFileSm")[0].files[0]);

    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: "/xdm/code/ReadXdmExcel"
        ,data: formData
        ,processData: false
        ,contentType: false
        ,success: function(response) {
            $("table").html(response);

            if ($("tbody").html().trim() == "") {
                location.href = "/xdm/code/CodeXdmList"
            }
        }
        ,error : function(jqXHR){
          alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
}