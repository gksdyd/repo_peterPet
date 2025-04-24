/* ===============================================-->
                      이미지 JS
   ===============================================--> */

function removeImage(e) {
  $(e).parent().remove();
}

$("#btnSubmit").on("click", function () {
  let imageSeq = document.querySelectorAll(".imageSeq");
  let imageArray = []
  imageSeq.forEach(element => {
    imageArray.push(element.getAttribute("data-seq"));
  });
  $("#removeSeq").val(imageArray);
});