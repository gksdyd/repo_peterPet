const regex1 = /^[a-z|A-Z|0-9|]+$/;
const regex2 = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|~|/|(-)|,|]+$/;
const regex3 = /^[a-z|A-Z|0-9|~|/|(-)|,|]+$/;
const regex4 = /^[0-9|]+$/;
const regex5 = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|!-/|]+$/;

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

onlyEngKoNumAllSpecialChar = function(text) {
    if (!regex5.test(text)) {
        return false;
    }
    return true;
}