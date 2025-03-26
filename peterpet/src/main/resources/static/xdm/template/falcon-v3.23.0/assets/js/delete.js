/* ===============================================-->
                    삭제 관련 Js
   ===============================================--> */

let modalDelete = document.getElementById("modalDelete");       // 삭제 모달 창의 Id
let delButton = document.getElementById("deleteBtn");           // 삭제(x) 버튼의 Id
let ueleteBtn = document.getElementById("ueleteBtn");           // 삭제(쓰레기통) 버튼의 Id
let modalCloseButton = document.getElementById("close-button"); // 삭제 모달 창의 Close 버튼
let modalXButton = document.getElementById("x-button");         // 삭제 모달 창의 X 버튼
let modalDelButton = document.getElementById("modalDelBtn");    // 삭제 모달 창의 삭제 버튼

// 삭제버튼(쓰레기통)
ueleteBtn.onclick = function() {
    modalDelete.style.display = "block";
    modalDelButton.value = UELETE_FLAG;
}

// 영구 삭제버튼(x)
deleteBtn.onclick = function() {
    modalDelete.style.display = "block";
    modalDelButton.value = DELETE_FLAG;
}

// 모달의 x버튼
modalXButton.onclick = function() {
    modalDelete.style.display = "none";
}

// 모달의 close 버튼
modalCloseButton.onclick = function() {
    modalDelete.style.display = "none";
}

// 모달의 삭제 버튼
modalDelButton.onclick = function() {
    if (modalDelButton.value == DELETE_FLAG) {
        form.action = goUrlXdmDelt;
    } else if (modalDelButton.value == UELETE_FLAG) {
        form.action = goUrlXdmUelt;
    }
    form.method = "post";
    form.submit();
}