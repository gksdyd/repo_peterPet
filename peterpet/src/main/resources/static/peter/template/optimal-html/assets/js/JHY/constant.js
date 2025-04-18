/* ===============================================-->
                        상수
   ===============================================--> */

// 모달
const ID_SEARCH_VARIETY = "searchVariety";
const ID_SEARCH_PERSONAL = "searchPersonal";
const ID_SEARCH_DISEASE = "searchDisease";

const VALUE_BUTTON_VARIETY = 1;
const VALUE_BUTTON_PERSONAL = 2;
const VALUE_BUTTON_DISEASE = 3;

// search


// 로그인관련 경로
const URL_ID_CHECK_PETER = "/peter/member/CheckIdPeterProc";
const URL_EMAIL_CHECK_PETER = "/peter/member/CheckEmailPeterProc";
const URL_PHONE_CHECK_PETER = "/peter/member/CheckPhonePeterProc";
const URL_PASSWORD_CHANGE_PETER = "/peter/member/PasswordChangePeterProc";
const URL_SIGNOUT_PROC_XDM = "/peter/member/LogoutPeterProc";
const URL_SIGNIN_PROC_XDM = "/peter/member/LoginPeterProc";
const URL_SIGNIN_FORM_XDM = "/peter/member/LoginPeterForm";
const URL_INDEX_VIEW_PETER = "/peter/index/IndexPeterView";

// 삭제관련 경로
const URL_PETER_DELETE_PROC = "/peter/pet/PetPeterDeltProc";
const URL_PETER_MYACCOUNT = "/peter/member/MyAccountPeterForm";
const URL_PETER_USER_DELETE_PROC = "/peter/member/UserPeterDeltProc";

// shop관련 경로
const URL_PETER_SHOP = "/peter/shop/ShopPeterList";
const URL_PETER_TIME = "/peter/shop/ShopPeterTimeProc";
const URL_PETER_INFO = "/peter/shop/ShopPeterInfoProc";

// 정규식 상수
const regex1 = /^[a-z|A-Z|0-9|]+$/;
const regex2 = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|~|/|(-)|,|]+$/;
const regex3 = /^[a-z|A-Z|0-9|~|/|(-)|,|]+$/;
const regex4 = /^[0-9|]+$/;
const regex5 = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|!-/|]+$/;
const regex6 = /^[가-힣|]+$/;
const regexPhone = /^01[016789]-\d{3,4}-\d{4}$/;
const regexEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
const regexPassword = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;

// 펫 관련 플래그
const REGISTER = 1;
const MODIFY = 0;