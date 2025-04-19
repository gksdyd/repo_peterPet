let prodSeq = [];
let funcName = [];
let infoWeight = [];
let feedBrand = [];
let prodName = [];
let infoDiscount = [];
let infoPrice = [];
let prodScore = [];
let feedSalaryAge;

let start;
let end;
let page;
let total;

function noEvent() { // 새로 고침 방지
    if (event.keyCode == 116) {
        form.action = goUrlProductList;
        form.submit();
        return false;
    } else if (event.ctrlKey && (event.keyCode == 78 || event.keyCode == 82)) {
        form.action = goUrlProductList;
        form.submit();
        return false;
    }
}
document.onkeydown=noEvent;

$("#shSortBy").on("change", function () {
    checkBox();
    create();
});

$("input[name=feedSalaryAge]").change(function() {
    checkBox();
    create();
    pagination();
});

checkBox = function() {
    let temp = document.querySelectorAll("input[name=feedSalaryAge]");
    for (let i = 0; i < temp.length; i++) {
        if (temp[i].checked) {
            $("#shFeedSalaryAge").val(temp[i].value);
            feedSalaryAge = temp[i].value;
            break;
        }
    }
}

create = function() {
    let createProduct = $(".productInfo");
    createProduct.empty();
    getSettingValue();
    for (let i = 0; i < prodSeq.length; i++) {
        let body = document.createElement('div');
        body.setAttribute("class", "col-6 col-sm-6 col-md-4 col-lg-4 item");
        body.append(initImage(prodSeq[i]));
        body.append(initDetail(prodSeq[i], funcName[i], infoWeight[i], feedBrand[i], prodName[i], infoDiscount[i], infoPrice[i], prodScore[i]));
        createProduct.append(body);
    }
    initArray();
}

getSettingValue = function() {
    $.ajax({
        async: false 
        ,cache: false
        ,type: "post"
        ,url: URL_PETER_INFO
        ,data: { "shSortBy" : $("#shSortBy").val(), "thisPage" : $("#thisPage").val(), "shFeedSalaryAge" : feedSalaryAge }
        ,success: function(response) {
            $("#productNum").text(response.vo.totalRows);

            start = response.vo.startPage;
            end = response.vo.endPage;
            page = response.vo.thisPage;
            total = response.vo.totalPages;

            for (let i = 0; i < response.dtos.length; i++) {
                prodSeq.push(response.dtos[i].prodSeq);
                funcName.push(response.dtos[i].funcName);
                infoWeight.push(response.dtos[i].infoWeight);
                feedBrand.push(response.dtos[i].feedBrand);
                prodName.push(response.dtos[i].prodName);
                infoDiscount.push(response.dtos[i].infoDiscount);
                infoPrice.push(response.dtos[i].infoPrice);
                prodScore.push(response.dtos[i].prodScore);
            }
        }
        ,error : function(jqXHR){
          alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
}

initArray = function() {
    prodSeq = [];
    funcName = [];
    infoWeight = [];
    feedBrand = [];
    prodName = [];
    infoDiscount = [];
    infoPrice = [];
    prodScore = [];
}

initImage = function(seq) {
    let imageGroup = document.createElement('div');
    imageGroup.setAttribute("class", "product-image");
    
    let createAddr = document.createElement('a');
    createAddr.setAttribute("href", window.href.replace('value', seq));

    let createImage = document.createElement('img');
    createImage.setAttribute("class", "blur-up lazyload");
    createImage.setAttribute("src", "/peter/template/optimal-html/assets/images/사료/loyalcanin-dog-indore-puppy-3kg-immunity.jpeg");

    let createLabel = document.createElement("div");
    createLabel.setAttribute("class", "product-labels");

    let createLabelText = document.createElement("span");
    createLabelText.setAttribute("class", "lbl on-sale rounded");
    createLabelText.innerHTML = "최저가도전!";

    createLabel.append(createLabelText);
    createAddr.append(createImage);
    createAddr.append(createLabel);
    imageGroup.append(createAddr);
    
    return imageGroup;
}

initDetail = function(seq, fName, weight, brand, name, discount, price, score) {
    let detailGroup = document.createElement('div');
    detailGroup.setAttribute("class", "product-details text-center");

    let detailName = document.createElement('div');
    detailName.setAttribute("class", "product-name text-uppercase");

    let text = "";
    let detailNameText = document.createElement('a');
    detailNameText.setAttribute("href", window.href.replace('value', seq));
    text = window.weight.replace('1', brand) + ' ' + name;
    if (weight != null) {
        text += ' ' + weight + 'kg';
    }
    if (fName != null) {
        text += ' ' + window.func.replace('1', fName);
    }
    detailNameText.innerHTML = text;

    detailName.append(detailNameText);
    detailGroup.append(detailName);

    let detailPrice = document.createElement('div');
    detailPrice.setAttribute("class", "product-price");

    let detailCurrPrice = document.createElement('span');
    detailCurrPrice.setAttribute("class", "price");
    if (discount == 0) {
        detailCurrPrice.innerHTML = price;
    } else {
        let detailOldPrice = document.createElement('div');
        detailOldPrice.setAttribute("class", "old-price");
        detailOldPrice.innerHTML = price;
        detailPrice.append(detailOldPrice);

        detailCurrPrice.innerHTML = discount + '% ' +  (price * (100 - discount) / 100);
    }
    detailPrice.append(detailCurrPrice);
    detailGroup.append(detailPrice);

    let detailReview = document.createElement('div');
    detailReview.setAttribute("class", "product-review d-flex align-items-center justify-content-center");

    let detailStar = document.createElement('span');
    for (let i = 1; i <= 5; i++) {
        let star = document.createElement('i');
        if (score >= i) {
            star.setAttribute("class", "an an-star");
        } else {
            star.setAttribute("class", "an an-star-o");
        }
        detailStar.append(star);
    }
    detailReview.append(detailStar);
    detailGroup.append(detailReview);

    let detailDelivery = document.createElement('div');
    detailDelivery.setAttribute("class", "product-review d-flex align-items-center justify-content-center");
    detailDelivery.style.color = "rgb(229, 135, 151)";

    let deliveryImage = document.createElement('i');
    deliveryImage.setAttribute("class", "an an-ship-fast fs-4");
    
    detailDelivery.append(deliveryImage);
    detailDelivery.innerHTML += "&emsp;빠른 배송";
    detailGroup.append(detailDelivery);
    return detailGroup;
}

pagination = function() {
    let pagination = $(".pagination > ul");
    pagination.empty();

    if (start > 1) {
        let li = document.createElement('li');
        li.setAttribute("class", "prev page-item");

        let a = document.createElement('a');
        a.setAttribute("href", "javascript:void(0)");
        a.setAttribute("onclick", `goList(${start - 1})`);

        let i = document.createElement('i');
        i.setAttribute("class", "icon align-middle an an-caret-left");
        i.setAttribute("aria-hidden", "true")
        
        a.append(i);
        li.append(a);
        pagination.append(li);
    }

    for (let i = start; i <= end; i++) {
        let li = document.createElement('li');
        li.setAttribute("class", "page-item");
        if (i === page) li.classList.add("active");

        let a = document.createElement('a');
        a.setAttribute("href", "javascript:void(0)");
        a.setAttribute("onclick", `goList(${i})`);
        a.innerHTML = i;
        
        li.append(a);
        pagination.append(li);
    }

    if (end < total) {
        let li = document.createElement('li');
        li.setAttribute("class", "next page-item");

        let a = document.createElement('a');
        a.setAttribute("href", "javascript:void(0)");
        a.setAttribute("onclick", `goList(${end + 1})`);

        let i = document.createElement('i');
        i.setAttribute("class", "icon align-middle an an-caret-right");
        i.setAttribute("aria-hidden", "true")
        
        a.append(i);
        li.append(a);
        pagination.append(li);
    }
}