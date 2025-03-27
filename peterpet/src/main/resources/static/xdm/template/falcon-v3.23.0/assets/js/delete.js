/* ===============================================-->
                    삭제 관련 Js
   ===============================================--> */

let delButton = document.getElementById("deleteBtn");           // 삭제(x) 버튼
let ueleteBtn = document.getElementById("ueleteBtn");           // 삭제(쓰레기통) 버튼
let modal = document.getElementById("modal");       // 삭제 모달 창
let modalXButton = document.getElementById("xBtn");         // 삭제 모달 창의 X 버튼

let modalCloseButton = document.getElementById("modalCloseBtn"); // 모달 창의 Close 버튼
let modalDelButton = document.getElementById("modalDelBtn");    // 모달 창의 삭제 버튼
let modalChkButton = document.getElementById("modalCheckBtn");  // 모달 창의 확인 버튼

let modalTitle = document.getElementById("modalTitle");  // 모달 창의 확인 버튼
let modalText = document.getElementById("modalText");  // 모달 창의 확인 버튼

// 삭제버튼(쓰레기통)
ueleteBtn.onclick = function() {
    modalTitle.innerText = "WARNING";
    modalText.innerText = "정말 삭제하시겠습니까??";
    modalCloseButton.style.display = "block";
    modalDelButton.style.display = "block";
    modal.style.display = "block";
    modalDelButton.value = UELETE_FLAG;
}

// 영구 삭제버튼(x)
deleteBtn.onclick = function() {
    modal.style.display = "block";
    modalDelButton.value = DELETE_FLAG;
}

// 모달의 x버튼
modalXButton.onclick = function() {
    modal.style.display = "none";
}

// 모달의 close 버튼
modalCloseButton.onclick = function() {
    modal.style.display = "none";
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

// 모달의 확인 버튼
modalChkButton.onclick = function() {
    modal.style.display = "none";
}