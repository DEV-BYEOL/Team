<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../includes/header.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Reader</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Board Read Page


				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="form-group">
						<label>quesNo</label> <input class="form-control" name='quesNo'
							value='<c:out value="${board.quesNo}"/>' readonly="readonly" />
					</div>
					<div class="form-group">
						<label>quesTit</label> <input class="form-control" name='quesTit'
							value='<c:out value="${board.quesTit}"/>' readonly="readonly" />
					</div>
                      
                    <div class="form-group">
						<label>카테고리</label> <input class="form-control" name='quesCateCode'
							value='<c:out value="${board.quesCateCode}"/>' readonly="readonly" />
					</div>
                      
							
					<div class="form-group">
						<label>Text Area</label>
						<textarea class="form-control" rows="3" name='quesCon'
							readonly="readonly">
						<c:out value="${board.quesCon}" />
					</textarea>
					</div>
					<div class="form-group">
						<label>Writer</label> <input class="form-control" name='quesWri'
							value='<c:out value="${board.quesWri}"/>' readonly="readonly" />
					</div>


					<button data-oper='modify' class="btn btn-default"
						onclick="location.href='/question/modify?quesNo=<c:out value="${board.quesNo}"/>'">Modify</button>
					<button data-oper='list' class="btn btn-info"
						onclick="location.href='/question/list'">List</button>

					<form id='operForm' action="/board/modify" method="get">
						<input type='hidden' id='quesNo' name='quesNo'
							value='<c:out value="${board.quesNo}"/>'> <input
							type='hidden' name='pageNum'
							value='<c:out value="${cri.pageNum}"/>'> <input
							type='hidden' name='amount'
							value='<c:out value="${cri.amount}"/>'>
						<!-- P.345 추가 -->
						<input type='hidden' name='keyword'
							value='<c:out value="${cri.keyword}"/>'> <input
							type='hidden' name='type' value='<c:out value="${cri.type}"/>'>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!--  (572~573) get.jsp에 첨부파일 보이게 하는 창 만들기 -->
	<div class='bigPictureWrapper'>
		<div class='bigPicture'></div>
	</div>

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

.uploadResult ul li span {
	color: white;
}

.bigPictureWrapper {
	position: absolute;
	display: none;
	justify-content: center;
	align-items: center;
	top: 0%;
	width: 100%;
	height: 100%;
	background-color: gray;
	z-index: 100;
	background: rgba(255, 255, 255, 0.5);
}

.bigPicture {
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
}

.bigPicture img {
	width: 600px;
}
</style>

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">

				<div class="panel-heading">Files</div>
				<!-- /.panel-heading -->
				<div class="panel-body">

					<div class='uploadResult'>
						<ul>
						</ul>
					</div>
				</div>
				<!--  end panel-body -->
			</div>
			<!--  end panel-body -->
		</div>
		<!-- end panel -->
	</div>
	<!-- /.row -->

	<div class='row'>

		<div class="col-lg-12">

			<!-- p414~415 댓글 목록 처리 추가 -->
			<!-- /.panel -->
			<div class="panel panel-default">
				<!--        <div class="panel-heading"> -->
				<!--         <i class="fa fa-comments fa-fw"></i> Reply -->
				<!--       </div> -->

				<div class="panel-heading">
					<i class="fa fa-comments fa-fw"></i> Reply
					<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New
						Reply</button>
				</div>


				<!-- /.panel-heading -->
				<div class="panel-body">

					<ul class="chat">
						<!-- 						<li class="left clearfix" data-replyNo='12'> -->
						<!-- 							<div> -->
						<!-- 								<div class="header"> -->
						<!-- 									<strong class="primary-font">user00</strong> <small -->
						<!-- 										class="pull-right text-muted">2020-12-19 21:50</small> -->
						<!-- 								</div> -->
						<!-- 								<p>댓글이 보여야합니다.</p> -->
						<!-- 							</div> -->
						</li>
					</ul>
					<!-- ./ end ul -->
				</div>
				<!-- /.panel .chat-panel -->

				<!-- p439 footer 추가 -->
				<div class="panel-footer"></div>
			</div>
		</div>
		<!-- ./ end row -->
	</div>

</div>
<!-- p419 댓글 추가 기능 -->
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Reply Modal</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>ReplyCon</label> <input class="form-control" name='replyCon'
						value='new Reply!!!'>
				</div>
				<div class="form-group">
					<label>Replyer</label> <input class="form-control" name='replyer'
						value='replyer'>
				</div>
				<div class="form-group">
					<label>Reply Date</label> <input class="form-control"
						name='replyDate' value='2018-01-01 13:13'>
				</div>
			</div>
			<div class="modal-footer">
				<button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
				<button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
				<button id='modalRegisterBtn' type="button" class="btn btn-primary">Register</button>
				<button id='modalCloseBtn' type="button" class="btn btn-default"
					data-dismiss='modal'>Close</button>
				<button id='modalClassBtn' type="button" class="btn btn-default"
					data-dismiss='modal'>Close</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!--  아래의 댓글 처리 코드 시작점 -->
<!--  p404 reply.js 등록 처리-->
<!--  p407 reply.js 댓글 목록 처리-->
<!--  p409 reply.js 댓글 삭제와 갱신처리 (등록 되어있는 번호를 확인하고 작업할것!!!) -->
<!--  p411~12 reply.js 댓글 수정처리 (등록 되어있는 번호를 확인하고 작업할것!!!) -->
<!--  p411~12 reply.js 댓글 조회처리 (등록 되어있는 번호를 확인하고 작업할것!!!) -->
<!--  p415~16 댓글 이벤트 처리 -->
<!--  p418 댓글 시간 처리 -->
<!--  p421 댓글 추가 이벤트 처리 -->
<!--  p423 댓글 등록 및 목록 갱신 처리 -->
<script type="text/javascript" src="/resources/js/reply.js"></script>
<script>
	
// 	console.log("===========================");
// 	console.log("JS Test");
	$(document).ready(function() {
	var quesNoValue = '<c:out value="${board.quesNo}"/>';
	var replyUL = $(".chat");
	
	showList(1);
	
	// (438) 댓글 페이지 호출을 위한 수정
	function showList(page){
		console.log("show list : " + page);
		
		replyService.getList({quesNo:quesNoValue,page: page||1}, function(replyCnt, list) {
			console.log("replyCnt : " + replyCnt);
			console.log("list : " + list);
			console.log(list);
			
			if(page === -1){
				pageNum = Math.ceil(replyCnt/10.0);
				showList(pageNum);
				return;
			}
			
			var str="";
			if(list == null || list.length == 0){
				
				return;
			}
			for(var i = 0, len = list.length||0; i < len; i++){
				str += "<li class='left clearfix' data-replyNo='"+list[i].replyNo+"'>";
				str += "<div><div class='header'><strong class='primary-font'>["+list[i].replyNo+"] "+list[i].replyWri+"</strong>";
				str += "<small class='pull-right text-muted'>"+replyService.displayTime(list[i].replyDate)+"</small></div>";
				str += "<p>"+list[i].reply+"</p></div></li>";
			}
			replyUL.html(str);
			
	        showReplyPage(replyCnt);
		});  // function end
	}// show list end
	 
	var pageNum = 1;
	var replyPageFooter = $(".panel-footer");
	
	function showReplyPage(replyCnt){
		var endNum = Math.ceil(pageNum / 10.0) * 10;
		var startNum = endNum - 9;
		
		var prev = startNum != 1;
		var next = false;
		
		if(endNum * 10 >= replyCnt){
			endNum = Math.ceil(replyCnt/10.0);
		}
		if(endNum * 10 < replyCnt) {
			next = true;
		}
		
		var str = "<ul class='pagination pull-right'>";
		
		if(prev) {
			str += "<li class='page-item'><a class='page-link'href='"+(startNum - 1)+"'>Previous</a></li>";
		}
		
		for(var i = startNum; i <= endNum; i++){
			var active = pageNum == i ? "active":"";
			str += "<li class='page-item"+active+"'><a class='page-link'href='"+i+"'>"+i+"</a></li>";
		}
		if(next){
			str += "<li class='page-item'><a class='page-link' href='"+(endNum + 1)+"'>Next</a></li>";
		}
		
		str += "</ul></div>";
		
		console.log(str);
		
		replyPageFooter.html(str);
		
		// (441)
	    replyPageFooter.on("click","li a", function(e){
	    	e.preventDefault();
	    	console.log("page click");
	    	
	    	var targetPageNum = $(this).attr("href");
	    	
	    	console.log("targetPageNum : " + targetPageNum);
	    	
	    	pageNum = targetPageNum;
	    	
	    	showList(pageNum);
	    });
	}
	
	// 버튼 이벤트 처리
	var modal = $(".modal");
	var modalInputReplyCon = modal.find("input[name='replyCon']");
	var modalInputReplyWri = modal.find("input[name='replyWri']");
	var modalInputReplyDate = modal.find("input[name='replyDate']");
	
	var modalModBtn = $("#modalModBtn");
	var modalRemoveBtn = $("#modalRemoveBtn");
	var modalRegisterBtn = $("#modalRegisterBtn");
	
	$("#addReplyBtn").on("click", function(e){
		modal.find("input").val("");
		modalInputReplyDate.closest("div").hide();
		modal.find("button[id !='modalCloseBtn']").hide();
		
		modalRegisterBtn.show();
		
		$(".modal").modal("show");
	});
	
	// 댓글 등록 및 갱신 추가 처리 p423
	// p439 페이징 관련 코드 수정
	modalRegisterBtn.on("click", function(e){
		var reply = {
				reply : modalInputReplyCon.val(),
				replyer : modalInputReplyWri.val(),
				quesNo : quesNoValue
		};
	replyService.add(replyCon, function(result){
		alert(result);
		
		modal.find("input").val("");
		modal.modal("hide");
		
//		showList(1);
		showList(-1);
		});
	});
	// 특정 댓글의 클릭 이벤트 처리 (425)
	// 댓글 조회 클릭 이벤트 처리 (426)
	$(".chat").on("click", "li", function(e){
		var replyNo = $(this).data("replyNo");
		replyService.get(replyNo, function(reply){
			modalInputReply.val(reply.replyCon);
			modalInputReplyer.val(reply.replyWri);
			modalInputReplyDate.val(replyService.displayTime(reply.replyDate))
			.attr("readonly", "readonly");
			modal.data("replyNo", reply.replyNo);
			
			modal.find("button[id != 'modalCloseBtn']").hide();
			modalModBtn.show();
			modalRemoveBtn.show();
			
			$(".modal").modal("show");
		});
	});
	
	// 댓글 수정 삭제 이벤트 처리 (427)
	// 댓글 수정 삭제 이벤트 처리 (442)
	modalModBtn.on("click", function(e){

		var reply = {replyNo:modal.data("replyNo"), reply: modalInputReply.val()};
		replyService.update(reply, function(result){
			alert(result);
			modal.modal("hide");
			showList(pageNum);
		});
	});
	
	modalRemoveBtn.on("click", function(e){
		var replyNo = modal.data("replyNo");
		replyService.remove(replyNo, function(result){
			alert(result);
			modal.modal("hide");
			showList(pageNum);
		});
	});
	
	});
	
	// 삭제된 댓글은 다시 실행하면 에러가 발생하니 작업 완료되었다면 주석을 처리하자
// 	replyService.remove(16, function(count){
// 		console.log(count);
// 		if(count === "success"){
// 			alert("remove complete");
// 		}
// 	}, function(err){
// 		alert('Error');
// 	});
	
	// 수정된 댓글을 다시 실행하면 에러가 발생하니 주석 처리를 하자
// 	replyService.update({
// 		replyNo : 13,
// 		quesNo : quesNoValue,
// 		reply : "해당 댓글은 수정되었습니다."
// 	}, function(result){
// 		alert("수정 완료...");
// 	});
	
	// replyService 댓글 추가 테스트 - 댓글을 추가 하고 싶을 때 주석을 풀어서 실행을 시키면 된다.
// 	replyService.add(
// 		{reply:"JS Test", replyer:"tester", quesNo:quesNoValue}
// 		,		
// 		function(result){
// 			alert("Result : " + result);
// 		}
// 	);
	 // replyService 댓글 조회 테스트
// 	replyService.get(10, function(data){
// 		console.log(data);
// 	});
	<!--  아래의 댓글 처리 코드 종료부분 -->
	</script>
<script type="text/javascript">
		$(document).ready(function(){
			
			var operForm = $("#operForm");
			
			$("button[data-oper='modify']").on("click", function(e){
				operForm.attr("action", "/question/modify").submit();
			});
			
			$("button[data-oper='list']").on("click", function(e){
				operForm.find("#quesNo").remove();
				operForm.attr("action","/question/list")
				operForm.submit();
			});
		});
	</script>
<!-- p571 -->
<script>
	$(document).ready(function(){
		 (function(){
			 var quesNo = '<c:out value="${board.quesNo}"/>';
			 $.getJSON("/question/getAttachList", {quesNo:quesNo}, function(arr){
				 console.log(arr);
				 // (574~5) 게시글에 첨부되어있는 첨부파일을 보여주기 위한 코드
				 var str = "";
				 
				 $(arr).each(function(i, attach){
					 //image type
			         if(attach.fileType){
			           var fileCallPath =  encodeURIComponent( attach.uploadPath+ "/s_"+attach.uuid +"_"+attach.fileName);
			           
			           str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
			           str += "<img src='/display?fileName="+fileCallPath+"'>";
			           str += "</div>";
			           str +"</li>";
			         }else{
			             
			           str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
			           str += "<span> "+ attach.fileName+"</span><br/>";
			           str += "<img src='/resources/img/attach.png'></a>";
			           str += "</div>";
			           str +"</li>";
			         }
			       });
			 		$(".uploadResult ul").html(str);
				 
			 }); // json end point
		 })();// function end point
		 
		 
		 // (575~6) 첨부파일 클릭하면 이미지 크게 보여주기
		 $(".uploadResult").on("click","li", function(e){
			 console.log("view image");
		 	
			 var liObj = $(this);
			 
			 var path = encodeURIComponent(liObj.data("path") + "/" + liObj.data("uuid")+"_" + liObj.data("filename"));
			 
			 if(liObj.data("type")){
				// showImage(path.replace(new RegExp(/\\/g),"/"));
				 showImage(path);
			 } else {
				 // 첨부파일 다운로드를 위한 코드
				 self.location ="/download?fileName="+path
			 }
		 });
		 
		  function showImage(fileCallPath){
			    
			 //   alert(fileCallPath);
			    
			    $(".bigPictureWrapper").css("display","flex").show();
			    
			    $(".bigPicture")
			    .html("<img src='/display?fileName="+fileCallPath+"'>")
			    .animate({width:'100%', height: '100%'}, 1000);
			    
			  }
		// (577) 원본 이미지 창 닫아주는 이벤트 처리코드
		$(".bigPictureWrapper").on("click", function(e){
			$(".bigPicture").animate({width:'0%', height: '0%'}, 1000);
			setTimeout(function(){
				$('.bigPictureWrapper').hide();
			}, 1000);
		});
	});
	</script>
<%@ include file="../includes/footer.jsp"%>