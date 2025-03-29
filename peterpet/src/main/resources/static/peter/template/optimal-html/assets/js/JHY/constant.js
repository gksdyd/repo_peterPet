/* ===============================================-->
                        상수
   ===============================================--> */

// 삭제 모달


// search


// 로그인관련 경로
const URL_SIGNOUT_PROC_XDM = "/xdm/member/LogoutXdmProc";
const URL_SIGNIN_PROC_XDM = "/xdm/member/LoginXdmProc";
const URL_SIGNIN_FORM_XDM = "/peter/member/LoginPeterForm";
const URL_INDEX_VIEW_PETER = "/peter/index/IndexPeterView";

// 정규식 상수
const regex1 = /^[a-z|A-Z|0-9|]+$/;
const regex2 = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|~|/|(-)|,|]+$/;
const regex3 = /^[a-z|A-Z|0-9|~|/|(-)|,|]+$/;
const regex4 = /^[0-9|]+$/;
const regex5 = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|!-/|]+$/;
const regex6 = /^[가-힣|]+$/;
const regexPhone = /^01[016789]-\d{3,4}-\d{4}$/;
const regexEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;