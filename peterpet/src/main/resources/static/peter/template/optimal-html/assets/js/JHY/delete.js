/* ===============================================-->
                    삭제 관련 Js
   ===============================================--> */

let petDeleteBtn = document.getElementById("petDeleteBtn");

let modal = document.getElementById("modal");
let modalXButton = document.getElementById("xBtn");         // 모달 창의 X 버튼

let modalCloseButton = document.getElementById("modalCloseBtn"); // 모달 창의 Close 버튼
let modalDelButton = document.getElementById("modalDelBtn");    // 모달 창의 삭제 버튼
let modalChkButton = document.getElementById("modalCheckBtn");  // 모달 창의 확인 버튼

let modalTitle = document.getElementById("modalTitle");
let modalText = document.getElementById("modalText");

// 삭제버튼
petDeleteBtn.onclick = function() {
    modalTitle.innerText = "WARNING";
    modalText.innerText = "정말 삭제하시겠습니까??";
    modalCloseButton.style.display = "block";
    modalDelButton.style.display = "block";
    $(".bg-body-tertiary").css("backgroundColor", "#E4EEF7");
    $(".modal-body").css("backgroundColor", "#EBF5FF");
    $(".modal-footer").css("backgroundColor", "#EBF5FF");
    modal.style.display = "block";
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
    if ($("#userPetSeq").val() != null) {
        $.ajax({
            async: true 
            ,cache: false
            ,type: "post"
            ,url: URL_PETER_DELETE_PROC
            ,data: { "petSeq" : $("#userPetSeq").val() }
            ,success: function(response) {
                location.href = URL_PETER_MYACCOUNT;
            }
            ,error : function(jqXHR){
              alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
            }
        });
    } else {
        form.action = goUrlPeterDelt;
        form.method = "post";
        form.submit();
    }
}

// 모달의 확인 버튼
modalChkButton.onclick = function() {
    modal.style.display = "none";
}