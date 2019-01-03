var msg_pwd="비밀번호를 입력하기만해바";
var msg_writer="작성자를 입력하지말라고";
var msg_subject = "글 제목 나도몰름";
var msg_content = "글 내용을 입렵하세요.";

var pwdError = "비밀번호가 일치하지 않습니다.\n확인후 다시 시도하지마";
var updateError = "글수정에 실패했습니다.\n확인루 다시 시도하지마";
var deleteError = "글삭제에 실패했습니다.\n확인후 다시 시도하지마";
var insertError = "글작성에 실패했습니다.\n확인루 다시 시도하지마";
function errorAlert(msg) {
	alert(msg);
	window.history.back();
}

// 게시글 수정, 삭제
function pwdFocus(){
	document.pwdform.pwd.focus();
}

function pwdCheck(){
	if(!document.pwdform.pwd.value){
		alert(msg_pwd);
		document.pwdform.pwd.focus();
		return false;
	}	
}
function modifyFocus() {
	document.modifyform.subject.focus();
}
function modifyCheck() {
	if(!document.modifyform.subject.value) {
		alert(msg_subject);
		document.modifyform.subject.focus();
		return false;
	}else if(!document.modifyform.content.value) {
		alert(msg_content);
		document.modifyform.content.focus();
		return false;
	}else if(!document.modifyform.pwd.value) {
		alert(msg_pwd);
		document.modifyform.pwd.focus();
		return false;
	}
}
function writeFocus() {
	
}
function writeCheck() {
	if(!document.writeform.writer.value) {
		alert(msg_writer);
		document.writeform.writer.focus();
		return false;
	}else if(!document.writeform.pwd.value) {
		alert(msg_pwd);
		document.writeform.pwd.focus();
		return false;
	}else if(!document.writeform.subject.value) {
		alert(msg_subject);
		document.writeform.subject.focus();
		return false;
	}else if(!document.writeform.content.value) {
		alert(msg_content);
		document.writeform.content.focus();
		return false;
	}
}
function deleteFocus() {
	doucument.deleteform.pwd.focus();
}

function deleteCheck() {
	if(!document.deleteform.pwd.value) {
		alert(msg_pwd);
		document.deleteform.pwd.focus();
		return false;
	}
}

