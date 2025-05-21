
function setCookie(value) {
    var today = new Date();
    today.setDate(today.getDate() + 7); //만료값 지정
    var cookie_value = value + "; path=/; exerpires=" + today.toUTCString() + ";"; 
    document.cookie = "product" + value + "=" + cookie_value;
    $("#cartMessage").stop(true, true).fadeIn(200), // 빠르게 표시
    setTimeout(() => {
        $("#cartMessage").fadeOut(1000); // 천천히 사라지게 (1초)
    }, 300) // 300ms 후 사라지기 시작
}

function getCookie() {  
    let cookies = document.cookie;
    let cart = cookies.replace(/\s/g, "").replace(/product[0-9]{1,3}=/g, "").split(";");

    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: "/peter/wishlist/CartPeterProc"
        ,data: { "cart" : cart }
        ,success: function(response) {
            $("#cartPage").html(response);
        }
        ,error : function(jqXHR){
            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
 }

 function removeCookie(value) {
    let cookie = "product" + value + "=";
    document.cookie = cookie + "; path=/; max-age=0";
    getCookie();
 }

 function putBascket(seq) {
    if (sessionSeq == null) {
        modalTitle.innerText = "알림";
        modalText.innerText = "로그인을 해야합니다.";
        modalCloseButton.style.display = "block";
        $(".bg-body-tertiary").css("backgroundColor", "#E4EEF7");
        $(".modal-body").css("backgroundColor", "#EBF5FF");
        $(".modal-footer").css("backgroundColor", "#EBF5FF");
        modal.style.display = "block";
        return;
    }
    setCookie(seq);
 }

 getCookie();