<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../includes/header.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Spring Bullet Board Service</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Board List Page
				<button id='regBtn' type="button" class="btn btn-xs pull-right">Register
					New Board</button>
			</div>

			<!-- /.panel-heading -->
			<div class="panel-body">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>#번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>카테고리</th>
							
						</tr>
					</thead>
					<!-- p237 ~ 238 -->
					<!-- p486 수정 -->
					<c:forEach items="${list}" var="board">
						<tr>
							<td><c:out value="${board.quesNo }" /></td>
							<!--  254 -->
 							<td>
								<a class='move' href='<c:out value="${board.quesNo}"/>'>
								
								<c:out value="${board.quesTit }" />
								<b>[ <c:out value="${board.replyCnt}" /> ] </b>
								</a>
							</td> 
										<!--  p314 -->
							<td><c:out value="${board.quesWri }" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${board.quesDate}" /></td>
									<td><c:out value="${board.quesCateCode }" /></td>
						
						</tr>
					</c:forEach>
				</table>
				<!-- P 340 -->
				<!-- P 343 -->
								<div class='row'>
					<div class="col-lg-12">

						<form id='searchForm' action="/question/list" method='get'>
							<select name='type'>
								<option value=""
								<c:out value="${pageMaker.cri.type == null ?'selected':''}"/>>--</option>
								<option value="T"
								<c:out value="${pageMaker.cri.type eq 'T'?'selected':''}"/>>제목</option>
								<option value="C"
								<c:out value="${pageMaker.cri.type eq 'C' ?'selected':''}"/>>내용</option>
								<option value="W"
								<c:out value="${pageMaker.cri.type eq 'W' ?'selected':''}"/>>작성자</option>
								<option value="TC"
								<c:out value="${pageMaker.cri.type eq 'TC' ?'selected':''}"/>>제목 or 내용</option>
								<option value="TW"
								<c:out value="${pageMaker.cri.type eq 'TW' ?'selected':''}"/>>제목 or 작성자</option>
								<option value="TWC"
								<c:out value="${pageMaker.cri.type eq 'TWC' ?'selected':''}"/>>제목 or 내용 or 작성자</option>
							</select>
								<input type='text' name='keyword' value='<c:out value="${pageMaker.cri.keyword}"/>' />
								<input	type='hidden' name='pageNum' value='<c:out value="${pageMaker.cri.pageNum}"/>' />
								<input type='hidden' name='amount' value='<c:out value="${pageMaker.cri.amount}"/>' />
								<button class='btn btn-default'>Search</button>
						</form>
					</div>
				</div>
				<!--  308 페이지 번호 출력 (310, 311 일부 수정) -->
				<div class='pull-right'>
					<ul class="pagination">

						<c:if test="${pageMaker.prev}">
							<li class="paginate_button previous"><a
								href="${pageMaker.startPage -1}">Previous</a></li>
						</c:if>

						<c:forEach var="num" begin="${pageMaker.startPage}"
							end="${pageMaker.endPage}">
							<li class="paginate_button  ${pageMaker.cri.pageNum == num ? "active":""} ">
								<a href="${num}">${num}</a>
							</li>
						</c:forEach>

						<c:if test="${pageMaker.next}">
							<li class="paginate_button next"><a href="${pageMaker.endPage +1 }">Next</a></li>
						</c:if>

					</ul>
				</div>
				<!--  end Pagination -->
			</div>

			<!--  p311 -->
			<form id='actionForm' action="/question/list" method='get'>
				<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
				<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
				<!--  P.344 -->
				<input type='hidden' name='type' value='<c:out value="${ pageMaker.cri.type }"/>'>
				<input type='hidden' name='keyword' value='<c:out value="${ pageMaker.cri.keyword }"/>'>
			</form>	

			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Modal title</h4>
						</div>
						<div class="modal-body">처리가 완료 되었습니다.</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary">Save
								changes</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->

		</div>
	</div>
</div>
</div>
<!-- Total Result 250(246, 248)  -->
<!-- Paging Result 308, 311, 312  -->
<script type="text/javascript">
		$(document).ready(function(){
			var result = '<c:out value="${result}"/>';
			
			checkModal(result);
			
			history.replaceState({}, null, null);
			
		function checkModal(result) {
			if(result === '' || history.state) {
				return;
			}
			if(parseInt(result) > 0) {
				$(".modal-body").html("게시글 " + parseInt(result) + " 번이 등록되었습니다.");
			}
			$("#myModal").modal("show");
		}
		$("#regBtn").on("click", function(){
				self.location = "/question/register";
			});
		var actionForm = $("#actionForm");
		$(".paginate_button a").on("click", function(e){
			e.preventDefault();
			console.log('click');
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.submit();
		});
		// 315 Page
		$(".move").on("click", function(e){
			e.preventDefault();
			actionForm.append("<input type='hidden' name='quesNo' value='"+$(this).attr("href")+"'>");
			actionForm.attr("action", "/question/get");
			actionForm.submit();
		});
		// 342 Page
		var searchForm = $("#searchForm");
		$("#searchForm button").on("click", function(e){
			if(!searchForm.find("option:selected").val()){
				alert("검색 종류를 선택하세요");
				return false;
			}
			if(!searchForm.find("input[name='keyword']").val()){
				alert("키워드를 입력하세요");
				return false;
			}
			
			searchForm.find("input[name='pageNum']").val("1");
			e.preventDefault();
			
			searchForm.submit();
		});
		});
	</script>


<%@ include file="../includes/footer.jsp"%>