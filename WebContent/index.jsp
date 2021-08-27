<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Simple Java Servlet CRUD</title>
<script type="text/javascript" src="static/js/jquery-2.1.4.min.js"></script>
<link href="static/bootstrap-3.0.3-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="static/bootstrap-3.0.3-dist/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container">
		<!-- header -->
		<div class="row">
			<div class="col-md-12">
				<h1>Simple Java Servlet CRUD</h1>
			</div>
		</div>

		<!-- button -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary" id="add_btn">add</button>
				<button class="btn btn-danger" id="batch_delete_btn">batch delete</button>
			</div>
		</div>

		<!-- table  -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
						<tr>
							<th><input type="checkbox" id="check_all" /></th>
							<th>#</th>
							<th>employee name</th>
							<th>gender</th>
							<th>email</th>
							<th>department name</th>
							<th>operation</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
				<ul id="pagination" class="pagination-sm"></ul>
			</div>
		</div>

		<!-- paginate  -->
		<div class="row">
			<div class="col-md-6" id="page_info_area"></div>
			<div class="col-md-6" id="page_nav_area"></div>
		</div>

		<!-- add Modal -->
		<div class="modal fade" id="add_modal" tabindex="-1" role="dialog">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">employee add</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal">
							<div class="form-group">
								<label for="empName_add_input" class="col-sm-4 control-label">employee name</label>
								<div class="col-sm-8">
									<input type="text" name="name" class="form-control" placeholder="employee name">
									<span class="help-block"></span>
								</div>
							</div>
							<div class="form-group">
								<label for="email_add_input" class="col-sm-4 control-label">email</label>
								<div class="col-sm-8">
									<input type="text" name="email" class="form-control" placeholder="email@yahoo.com">
									<span class="help-block"></span>
								</div>
							</div>
							<div class="form-group">
								<label for="gender_add_input" class="col-sm-4 control-label">gender</label>
								<div class="col-sm-8">
									<label class="radio-inline"> <input type="radio" name="gender" value="1" checked>男</label>
									<label class="radio-inline"> <input type="radio" name="gender" value="2">女</label>
								</div>
							</div>
							<div class="form-group">
								<label for="dept_add_input" class="col-sm-4 control-label">department name</label>
								<div class="col-sm-4">
									<select class="form-control" name="deptId"></select>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary" id="save_btn">Save</button>
					</div>
				</div>
			</div>
		</div>

		<!-- edit Modal -->
		<div class="modal fade" id="edit_modal" role="dialog">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">employee edit</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal">
							<input type="hidden" name="id">
							<div class="form-group">
								<label for="empName_edit_input" class="col-sm-4 control-label">employee name</label>
								<div class="col-sm-8">
									<input type="text" name="name" class="form-control" placeholder="employee name">
									<span class="help-block"></span>
								</div>
							</div>
							<div class="form-group">
								<label for="email_edit_input" class="col-sm-4 control-label">email</label>
								<div class="col-sm-8">
									<input type="text" name="email" class="form-control" placeholder="email@yahoo.com">
									<span class="help-block"></span>
								</div>
							</div>
							<div class="form-group">
								<label for="gender_edit_input" class="col-sm-4 control-label">gender</label>
								<div class="col-sm-8">
									<label class="radio-inline"> <input type="radio" name="gender" value="1" checked="checked">男</label>
									<label class="radio-inline"> <input type="radio" name="gender" value="2">女</label>
								</div>
							</div>
							<div class="form-group">
								<label for="dept_edit_input" class="col-sm-4 control-label">department name</label>
								<div class="col-sm-4">
									<select class="form-control" name="deptId"></select>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary" id="update_btn">Save</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	
	<script>
	
	var totalRecord,currentPage;
	var APP_URL='<%=request.getContextPath()%>';
	
	$(function() {
		to_page(1);
	});
	
	function to_page(pn) {
		$.ajax({
			url : APP_URL+"/emp?method=empData",
			data : "pn=" + pn,
			type : "GET",
			dataType:"json",
			success : function(result) {
				bulid_emps_table(result);
				bulid_page_info(result);
				build_page_nav(result);
			}
		});
	}
	
	function bulid_emps_table(result) {
		$("#emps_table tbody").empty();
		$.each(result.list, function(index, item) {
			let checkBoxTD=$("<td><input type='checkbox' class='check_item' /></td>");
			let empIdTD = $("<td></td>").append(item.id);
			let empNameTD = $("<td></td>").append(item.name);
			let genderTD = $("<td></td>").append(item.gender == 1 ? "男" : "女");
			let emailTD = $("<td></td>").append(item.email);
			let deptNameTD = $("<td></td>").append(item.dept.name);

			let editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn").append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("edit");
			editBtn.attr("edit-id",item.id);
			let delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn").append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("del");
			delBtn.attr("delete-id",item.id);
			let btnTD = $("<td></td>").append(editBtn).append(" ").append(delBtn);

			$("<tr></tr>").append(checkBoxTD).append(empIdTD).append(empNameTD).append(genderTD).append(emailTD).append(deptNameTD).append(btnTD).appendTo("#emps_table tbody");
		});
	}
	
	function bulid_page_info(result) {
		$("#page_info_area").empty();
		$("#page_info_area").append(
				"目前在" + result.currentPageNum + "頁，總共"
						+ result.totalPageNum + "頁，總"
						+ result.totalRecords + "筆記錄");
		totalRecord=result.totalRecords;
		currentPage=result.currentPageNum;
	}
	
	function build_page_nav(result) {
		$("#page_nav_area").empty();
		let ul = $("<ul></ul>").addClass("pagination");

		let firstPageLi = $("<li></li>").append($("<a></a>").append("首頁").attr("href", "#"));
		let prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
		
		if (result.startIndex == 0) {
			firstPageLi.addClass("disabled");
			prePageLi.addClass("disabled");
		} else {
			firstPageLi.click(function() {
				to_page(1);
			});
			prePageLi.click(function() {
				to_page(result.currentPageNum - 1);
			});
		}

		let nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
		let lastPageLi = $("<li></li>").append($("<a></a>").append("末頁").attr("href", "#"));
		if (result.nextPageNum > result.totalPageNum) {
			nextPageLi.addClass("disabled");
			lastPageLi.addClass("disabled");
		} else {
			nextPageLi.click(function() {
				to_page(result.currentPageNum + 1);
			});
			lastPageLi.click(function() {
				to_page(result.totalPageNum);
			});
		}

		ul.append(firstPageLi).append(prePageLi);
		
		for(let i=parseInt(result.startPage); i<=parseInt(result.endPage); i++ ){
			let numLi = $("<li></li>").append($("<a></a>").append(i));
			if (result.currentPageNum == i) {
				numLi.addClass("active");
			}
			numLi.click(function() {
				to_page(i);
			});
			ul.append(numLi);
		}

		ul.append(nextPageLi).append(lastPageLi);
		let navEE = $("<nav></nav>").append(ul);
		navEE.appendTo("#page_nav_area");
	}	
	
	$("#add_btn").click(function() {
		reset_form("#add_modal form");
		getDepts("#add_modal select");
		$("#add_modal").modal({
			backdrop : "static"
		});
	});
		
	
	$("#save_btn").click(function(){		
		if($(this).attr("ajax-va")=="error"){
			return false;
		}
		
		$.ajax({
			url : APP_URL+"/emp?method=saveData",
			type:"POST",
			data:$("#add_modal form").serialize(),
			success:function(result){
				if(result==0){
					$("#add_modal").modal("hide");
					to_page(1);
				}else{
					let emp = JSON.parse(result);
					if(undefined!=emp.name){					
						show_validate_msg("#add_modal input[name='name']","error",emp.name);
					}else{
						show_validate_msg("#add_modal input[name='name']","successs","");
					}
					if(undefined!=emp.email){
						show_validate_msg("#add_modal input[name='email']","error",emp.email);
					}else{
						show_validate_msg("#add_modal input[name='email']","successs","");
					}
				}
			}
		});
	});
	
	$(document).on("click",".edit_btn",function(){
		getDepts("#edit_modal select");
		getEmp($(this).attr("edit-id"));
		
		$("#edit_modal").modal({
			backdrop : "static"
		});
		reset_form("#edit_modal form");
	});
	
	$("#update_btn").click(function(){		
		if($(this).attr("ajax-va")=="error"){
			return false;
		}
		
		$.ajax({
			url:APP_URL+"/emp?method=updateData",
			type:"POST",
			data:$("#edit_modal form").serialize(),
			success:function(result){					
				//$("#edit_modal").modal("hide");				
				if(result==0){
					$("#edit_modal").modal("hide");
					to_page(1);
				}else{
					let emp = JSON.parse(result);
					if(undefined!=emp.name){					
						show_validate_msg("#edit_modal input[name='name']","error",emp.name);
					}else{
						show_validate_msg("#edit_modal input[name='name']","successs","");
					}
					if(undefined!=emp.email){
						show_validate_msg("#edit_modal input[name='email']","error",emp.email);
					}else{
						show_validate_msg("#edit_modal input[name='email']","successs","");
					}
				}
			}
		});
	})
	
	$(document).on("click",".delete_btn",function(){
		let empName=$(this).parents("tr").find("td:eq(2)").text();
		if(confirm("確認刪除"+empName+"嗎?")){
			$.ajax({
				url : APP_URL+"/emp?method=deleteData",
				type:"POST",
				data:{id:$(this).attr("delete-id")},
				success:function(result){					
					to_page(1);
				}
			});
		}
	});
	
	function getEmp(id){
		$.ajax({
			url:APP_URL+"/emp?method=empData2",
			data:{id:id},
			type:"GET",
			dataType:"json",
			success:function(result){					
				$("#edit_modal input[name='id']").val(result.id);
				$("#edit_modal input[name='name']").val(result.name);
				$("#edit_modal input[name='email']").val(result.email);
				$("#edit_modal input[name='gender']").val([result.gender]);	
				$("#edit_modal select[name='deptId']").val([result.deptId]);
			}
		});
	}
	
	function getDepts(element){
		$(element).empty();
		$.ajax({
			url:APP_URL+"/dept?method=deptData",
			type:"GET",
			dataType:"json",
			success:function(result){
				$.each(result, function(index, item) {
					let optionEle=$("<option></option>").append(item.name).attr("value",item.id);
					optionEle.appendTo(element);
				});
			}
		});
	}
	
	$("#add_modal input[name='name']").change(function(){
		let empName=this.value;
		$.ajax({
			url : APP_URL+"/emp?method=checkEmp",
			type:"POST",
			data:"name="+empName,
			success:function(result){		
				if(result==0){
					show_validate_msg("#add_modal input[name='name']" ,"success","員工名稱可以使用");
					$("save_btn").attr("ajax-va","success");
				}else{
					show_validate_msg("#add_modal input[name='name']" ,"error","員工名稱已存在");
					$("save_btn").attr("ajax-va","error");
				}
			}
		});
	})
		
	function show_validate_msg(element,status,msg){
		$(element).parent().removeClass("has-success has-error");
		$(element).next("span").text("");
		if("success"==status){
			$(element).parent().addClass("has-success");
			$(element).next("span").text(msg);
		}else if("error"==status){
			$(element).parent().addClass("has-error");
			$(element).next("span").text(msg);
		}
	}
	
	function reset_form(ele){
		//表單資料
		$(ele)[0].reset();
		
		//表單樣式
		$(ele).find("*").removeClass("has-success has-error");
		$(ele).find(".help-block").text("");
	}
	
	$("#check_all").click(function(){
		$(".check_item").prop("checked",$(this).prop("checked"));
	});
	
	$(document).on("click",".check_item",function(){
		let flag=$(".check_item:checked").length==$(".check_item").length;
		$("#check_all").prop("checked",flag);
	});
	
	$("#batch_delete_btn").click(function(){
		
		let empNames="";
		let idstr="";
		$.each($(".check_item:checked"),function(){
			empNames +=$(this).parents("tr").find("td:eq(2)").text()+","
			idstr +=$(this).parents("tr").find("td:eq(1)").text()+","
		});
		
		empNames=empNames.substring(0,empNames.length-1);
		idstr=idstr.substring(0,idstr.length-1);
		if(confirm("確認刪除"+empNames+"嗎??")){
			$.ajax({
				url : APP_URL+"/emp?method=deleteBatchData",
				type:"POST",
				data:{ids:idstr},
				success:function(result){					
					to_page(1);
				}
			});
		}		
	});
	
	
	</script>
</body>
</html>