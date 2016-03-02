<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="success-alert" class="alert alert-info alert-dismissible flyover flyover-centered" role="alert"> 
	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		<span aria-hidden="true">×</span>
	</button> 
	<p><strong>생성완료!!</strong></p>
</div>
<div class="starter-template">
	<h1>Jira REST APIs 연동 테스트</h1>

</div>


<button id="loadIssues" class="btn btn-primary btn-sm has-spinner">
	Load Issues<span class="spinner"><i	class="icon-spin icon-refresh"></i></span>
</button>
<button id="btnCreate" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">Create</button>
<!-- 
<button id="btndlg" class="btn btn-primary btn-sm" >alert</button>
 -->
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Create Issue</h4>
			</div>
			<div class="modal-body">
			
				<!-- form -->
				<form>
					<div class="form-group">
						<label for="inputPJKey" class="control-label">Project Key</label>
						<input type="text" name="pkey" class="form-control input-sm"
								id="inputPJKey" placeholder="project key" required>
					</div>
					<div class="form-group">
						<label for="inputSummary" class="control-label">Summary</label>
						<input type="text" name="summary" class="form-control input-sm"
								id="inputSummary" placeholder="summary" required>
					</div>
					<div class="form-group">
						<label for="inputDesc" class="control-label">Description</label>
						<input type="text" name="desc" class="form-control input-sm"
								id="inputDesc" placeholder="description">
					</div>
					<div class="form-group">
						<label for="inputIssueType" class="control-label">Issue Type</label>
						<select name="itype" class="form-control">
						  <option>Improvement</option>
						  <option>Task</option>
						  <option>New Feature</option>
						  <option>Bug</option>
						</select>
					</div>
				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" id="btnCreate2" class="btn btn-primary">Save changes</button>
			</div>
		</div>
	</div>
</div>


<div class="divTbl">
	<table id="issuesTbl" class="table">
		<caption>jira/rest/api/2/search 를 통해 이슈 목록을 가져온다.</caption>
		<thead>
			<tr>
				<th width="10%">#</th>
				<th width="10%">T</th>
				<th width="10%">Key</th>
				<th width="40%">Summary</th>
				<th width="20%">Assignee</th>
				<th width="10%">Status</th>
			</tr>
		</thead>
		<tbody>
			<!-- 
				<tr>
					<th scope="row">1</th>
					<td>Mark</td>
					<td>Otto</td>
					<td>@mdo</td>
					<td>@mdo</td>
					<td>@mdo</td>
				</tr>
	 			-->
		</tbody>
	</table>
</div>

