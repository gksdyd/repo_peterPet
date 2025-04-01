/* ===============================================-->
                        상수
   ===============================================--> */
// 공통
const ON = 1;
const OFF = 0;

// 삭제 모달
const DELETE_FLAG = 1;
const UELETE_FLAG = 2;

// search
const CODE = 1;
const CODE_GROUP = 2;
const MEMBER = 3;
const PET = 4;
const PRODUCT = 5;

// 로그인관련 경로
const URL_SIGNIN_FORM_XDM = "/xdm/member/LoginXdmForm";
const URL_SIGNIN_PROC_XDM = "/xdm/member/LoginXdmProc";
const URL_SIGNOUT_PROC_XDM = "/xdm/member/LogoutXdmProc";
const URL_INDEX_VIEW_XDM = "/xdm/index/IndexXdmView";

// 상품관련 경로
const URL_PRODUCT_FUNCTION_XDM = "/xdm/product/ProductXdmProc";

// 상품 기능 뱃지 추가/제거
const ADD_FUNCTION = 1;
const MAINTAIN_FUNCTION = 0;
const REMOVE_FUNCTION = -1;
const FUNCTION_ID_ARRAY = ["prodTeethFlag", "prodTearsFlag", "prodBrainFlag", "prodImmunityFlag", "prodBoneFlag", "prodStressFlag", "prodKidneyFlag", "prodHeartFlag",
                           "prodAllergyFlag", "prodPregnancyFlag", "prodIntestineFlag", "prodNeuteringFlag", "prodWeightFlag", "prodSkinFlag"];

// 정규식 상수
const regex1 = /^[a-z|A-Z|0-9|]+$/;
const regex2 = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|~|/|(-)|,|]+$/;
const regex3 = /^[a-z|A-Z|0-9|~|/|(-)|,|]+$/;
const regex4 = /^[0-9|]+$/;
const regex5 = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|!-/|]+$/;