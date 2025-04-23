/* ===============================================-->
                      이미지 JS
   ===============================================--> */

$("input[type=file]").on("change", function(event) {
  let files = event.target.files;
  let currImage;
  if ($(this).attr("id") === "uploadImg1") {
    currImage = $(".currImage1");
  } else if ($(this).attr("id") === "uploadImg2") {
    currImage = $(".currImage2");
  }

  currImage.empty();
  for (let i = 0; i < files.length; i++) {
    let file = files[i];
    let reader = new FileReader();
    reader.onload = function(event){
      let text = "<div class='col-4'><img src='" + event.target.result + "' style='width: 100%; height: 150px; object-fit:contain;'></div>";
      currImage.html(currImage.html() + text);
    }
    reader.readAsDataURL(file);
  }
});