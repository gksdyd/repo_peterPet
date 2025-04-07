/* ===============================================-->
                        상수
   ===============================================--> */
// 공통
const ON = 1;
const OFF = 0;

// 삭제 모달
const DELETE_FLAG = 1;
const UELETE_FLAG = 2;
const SEVERAL_DELETE_FLAG = 3;
const SEVERAL_UELETE_FLAG = 4;

// 로그인관련 경로
const URL_SIGNIN_FORM_XDM = "/xdm/member/LoginXdmForm";
const URL_SIGNIN_PROC_XDM = "/xdm/member/LoginXdmProc";
const URL_SIGNOUT_PROC_XDM = "/xdm/member/LogoutXdmProc";
const URL_INDEX_VIEW_XDM = "/xdm/index/IndexXdmView";

// 상품관련 경로
const URL_PRODUCT_FUNCTION_XDM = "/xdm/product/ProductXdmProc";
const URL_PRODUCT_FUNCTION_BACKUP_XDM = "/xdm/product/ProductBackupXdmProc";
const URL_PRODUCT_XDM_FORM = "/xdm/product/ProductXdmForm";
const URL_PRODUCT_XDM_LIST = "/xdm/product/ProductXdmList";

// 정규식 상수
const regex1 = /^[a-z|A-Z|0-9|]+$/;
const regex2 = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|~|/|(-)|,|]+$/;
const regex3 = /^[a-z|A-Z|0-9|~|/|(-)|,|]+$/;
const regex4 = /^[0-9|]+$/;
const regex5 = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|!-/|]+$/;