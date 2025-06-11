
function setCookie(value) {
    var today = new Date();
    today.setDate(today.getDate() + 7); //만료값 지정
    document.cookie = "id" + value + "=" + sessionId + "; path=/peter; expires =" + today.toUTCString() + ";";
    document.cookie = "product" + value + "=" + value + "; path=/peter; expires =" + today.toUTCString() + ";";
    document.cookie = "count" + value + "=" + $("#prodCount").val() + "; path=/peter; expires =" + today.toUTCString() + ";";
    document.cookie = "price" + value + "=" + price + "; path=/peter; expires =" + today.toUTCString() + ";";

    if ($("#selectWeight").length == 0) {
        document.cookie = "weight" + value + "=" + 0 + "; path=/peter; expires =" + today.toUTCString() + ";";
    } else {
        document.cookie = "weight" + value + "=" + $("#selectWeight").val() + "; path=/peter; expires =" + today.toUTCString() + ";";
    }
    $("#cartMessage").stop(true, true).fadeIn(200), // 빠르게 표시
    setTimeout(() => {
        $("#cartMessage").fadeOut(1000); // 천천히 사라지게 (1초)
    }, 300) // 300ms 후 사라지기 시작

    getCookie();
}

function getCookie() {  
    let cookies = document.cookie;
    if (cookies === "") {
        return;
    }
    let cart = cookies.replace(/\s/g, "").split(";");
    let products = [];
    let counts = [];
    let weights = [];
    let prices = [];
    for (let i = 0; i < cart.length; i++) {
        if (cart[i].includes("product")) {
            products.push(cart[i].replace(/product[0-9]{1,3}=/g, ""));
        } else if (cart[i].includes("count")) {
            counts.push(cart[i].replace(/count[0-9]{1,3}=/g, ""));
        } else if (cart[i].includes("weight")) {
            weights.push(cart[i].replace(/weight[0-9]{1,3}=/g, ""));
        } else if (cart[i].includes("price")) {
            prices.push(cart[i].replace(/price[0-9]{1,3}=/g, ""));
        } else if (cart[i].includes("id")) {
            if (cart[i].replace(/id[0-9]{1,3}=/g, "") !== sessionId) {
                i += 5;
                continue;
            }
        }
    }
    
    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: "/peter/wishlist/CartPeterProc"
        ,data: { "products" : products, "counts" : counts, "weights" : weights, "prices" : prices }
        ,success: function(response) {
            $("#cart-drawer").html(response);
        }
        ,error : function(jqXHR){
            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
 }

 function removeCookie(value) {
    document.cookie = "id" + value + "=; path=/peter; max-age=0";
    document.cookie = "product" + value + "=; path=/peter; max-age=0";
    document.cookie = "count" + value + "=; path=/peter; max-age=0";
    document.cookie = "weight" + value + "=; path=/peter; max-age=0";
    document.cookie = "price" + value + "=; path=/peter; max-age=0";
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