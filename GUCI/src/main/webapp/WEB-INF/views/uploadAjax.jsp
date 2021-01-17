<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Upload with Ajax</h1>
<!--  (524) 일반 파일인 경우 이미지를 보이게 하기 -->
	<style>
.uploadResult {
	width: 100%;
	background-color: gray;
}

.uploadResult ul {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}

.uploadResult ul li {
	list-style: none;
	padding: 10px;
	align-content: center;
	text-align: center;
}

.uploadResult ul li img {
	width: 100px;
}
.uploadResult ul li span{
	color:white;
}
/* P542 CSS */
.bigPictureWrapper {
  position: absolute;
  display: none;
  justify-content: center;
  align-items: center;
  top:0%;
  width:100%;
  height:100%;
  background-color: gray; 
  z-index: 100;
  background:rgba(255,255,255,0.5);
}
.bigPicture {
  position: relative;
  display:flex;
  justify-content: center;
  align-items: center;
}
.bigPicture img {
	width:600px;
}
</style>

<!-- (542) HTML -->
<div class='bigPictureWrapper'>
	<div class='bigPicture'>
	</div>
</div>

<!--  (501) form 방식의 업로드 -->
	<div class='uploadDiv'>
		<input type="file" name='uploadFile' multiple>
	</div>	
<!--  (522) 파일 이름 추가 되도록 추가 -->
	<div class="uploadResult">
		<ul>
		
		</ul>
	</div>
	
		<button id='uploadBtn'>upload</button>
	<!-- (502) JQuery 추가 -->	
	<script 
		src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
	<script>
	// (541) 원본 이미지 보여주기 위한 코드
	// (544) showImage()를 통해 화면에 원본 이미지를 보여주기 위한 코드 수정
	function showImage(fileCallPath){
		// alert(fileCallPath);
		$(".bigPictureWrapper").css("display", "flex").show();
		
		$(".bigPicture")
		.html("<img src='/display?fileName="+ encodeURI(fileCallPath)+"'>")
		.animate({width:'100%', height:'100%'}, 1000);
		//(544) 클릭하면 다시 사라지게 하는 이벤트
		$(".bigPictureWrapper").on("click", function(e){
			$(".bigPicture").animate({width:'0%', height:'0%'}, 1000);
			setTimeout(() => {
				$(this).hide();
			}, 1000);
		// IE에서 정상 테스트를 위한 코드
// 		$(".bigPictureWrapper").on("click", function(e){
// 			$(".bigPicture").animate({width:'100%', height:'100%'}, 1000);
// 			setTimeOut(function(){
// 				$('.bigPictureWrapper').hide();
// 			}, 1000);
 		});
		// (547) 화면 삭제 기능 x 표시 이벤트 처리하기
		$(".uploadResult").on("click","span",function(e){
			var targetFile = $(this).data("file");
			var type = $(this).data("type");
			console.log(targetFile);
			
			$.ajax({
				url: '/deleteFile',
				data: {fileName: targetFile, type:type},
				dataType: 'text',
				type: 'POST',
				success: function(result){
					alert(result);
				}
			});
		});
	}
	$(document).ready(function(){
		// (506) 파일의 확장자 크기 사전처리
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize = 5242880;
		
		function checkExtension(fileName, fileSize){
			if(fileSize >= maxSize){
				alert("파일 사이즈 초과");
				return false;
			}
			if(regex.test(fileName)){
				alert("해당 종류의 파일은 업로드가 불가능합니다.");
				return false;
			}
			return true;
		}
		//(520) 첨부파일 초기화
		var cloneObj = $(".uploadDiv").clone();
		
		$("#uploadBtn").on("click", function(e){
			var formData = new FormData();
			var inputFile = $("input[name='uploadFile']");
			var files = inputFile[0].files;
			
			console.log(files);
			// (503) JQuery를 통한 첨부파일 전송하기
			for(var i = 0; i < files.length; i++){
				if(!checkExtension(files[i].name, files[i].size)){
					return false;
				}
				formData.append("uploadFile", files[i]);
			}
			// (521) 첨부파일 부분 초기화를 위한 일부 코드 수정
			$.ajax({
				url: '/uploadAjaxAction',
				processData: false,
				contentType: false,
				data: formData,
				type: 'POST',
				dataType:'json',
				success: function(result){
// 					alert("Uploaded");
					console.log(result);
					showUploadedFile(result);					
					
					$(".uploadDiv").html(cloneObj.html());
				}
			});
		});
			// (522)
			var uploadResult = $(".uploadResult ul");
// 				function showUploadedFile(uploadResultArr){
// 					var str = "";
					
// 					$(uploadResultArr).each(function (i, obj){
// 						str += "<li>" + obj.fileName + "</li>";
// 					});
// 					uploadResult.append(str);
// 				}
				//(528)
				//(537) 업로드 후 다운로드 처리가 되도록 설정
				//(541) 원본 이미지를 보여주기 위한 코드 수정
				//(546) 화면 삭제 기능
				function showUploadedFile(uploadResultArr){
					var str = "";
					$(uploadResultArr).each(function(i, obj){
						if(!obj.image){
							var fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
							
							var fileLink = fileCallPath.replace(new regExp(/\\/g), "/");
							
							str += "<li><a href='/download?fileName="+fileCallPath+"'>"+
									"<img src='/resources/img/attach.png'>"+obj.fileName + "</a>"+
									"<span data-file=\'"+fileCallPath+"\' data-type='file'> X </span>"+
									"<div></li>";
						} else {
// 							str += "<li>" + obj.fileName + "<li>";
							var fileCallPath = encodeURIComponent(obj.uploadPath+ "/s_"+obj.uuid+"_"+obj.fileName);
							
							var originPath = obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName;
							
							originPath = originPath.replace(new RegExp(/\\/g),"/");
							
							str += "<li><a href=\"javascript:showImage(\'"+originPath+"\')\">"+
									"<img src='/display?fileName="+fileCallPath+"'></a>"+
									"<span data-file=\'"+fileCallPath+"\' data-type='image'> X </span>"+
									"<div></li>";
						}
					});
					uploadResult.append(str);
				}


	});
	</script>	
		
</body>
</html>