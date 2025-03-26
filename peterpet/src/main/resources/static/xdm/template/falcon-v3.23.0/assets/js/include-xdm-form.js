
// 공통

const regex1 = /^[a-z|A-Z|0-9|]+$/;
const regex2 = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|~|/|(-)|,|]+$/;
const regex3 = /^[a-z|A-Z|0-9|~|/|(-)|,|]+$/;
const regex4 = /^[0-9|]+$/;

// 모든 공백 제거
allGapRemove = function(text) {
    return text.replace(/ /g, "");
}

// 양 옆 공백 제거
bothSidesGapRemove = function(text) {
    return text.trim();
}

// 길이 체크
checkLength = function(text) {
    if (text.length == 0) {
        return false;
    }
    return true;
}

// 영어, 숫자만
onlyKoEngNumValid = function(text) {
    if (!regex1.test(text)) {
        return false;
    }
    return true;
}

// 영어, 한글, 숫자, ~, /, (, ) 만
onlyEngKoNumSpecialChar = function(text) {
    if (!regex2.test(text)) {
        return false;
    }
    return true;
}

// 영어, 숫자, ~, /, (, ) 만
onlyEngNumSpecialChar = function(text) {
    if (!regex3.test(text)) {
        return false;
    }
    return true;
}

// 숫자만
onlyNum = function(text) {
    if (!regex4.test(text)) {
        return false;
    }
    return true;
}

calculateToday = function() {
  let today = new Date();
  let year = today.getFullYear();
  let month = (today.getMonth() + 1).toString().padStart(2, '0');
  let day = today.getDate().toString().padStart(2, '0');
  return year + month + day;
}

document.getElementById("btnSubmit").onclick = function() {
    validationInit();

    if (!validation()) {
        return false;
    }
    
    if (document.getElementById("registerOrModifyFlag").value == 1) {
        registerDate.value = calculateToday();
        form.action = goUrlXdmInst;
    } else {
        modifyDate.value = calculateToday();
        form.action = goUrlXdmUpdt;
    }
    
    form.method = "post";
    form.submit();
  }

  const deleteFlag = 1;
  const ueleteFlag = 2;

  document.getElementById("ueleteBtn").onclick = function() {
    modalDelete.style.display = "block";
    modalDelButton.value = ueleteFlag;
  }

  document.getElementById("deleteBtn").onclick = function() {
    modalDelete.style.display = "block";
    modalDelButton.value = deleteFlag;
  }

  modalXButton.onclick = function() {
   modalDelete.style.display = "none";
  }

  document.getElementById("close-button").onclick = function() {
    modalDelete.style.display = "none";
  }

  document.getElementById("modalDelBtn").onclick = function() {
    if (modalDelButton.value == deleteFlag) {
        form.action = goUrlXdmDelt;
    } else if (modalDelButton.value == ueleteFlag) {
        form.action = goUrlXdmUelt;
    }
    form.submit();
  }