var insertError = "회원가입  실패했습니다. \n확인후 다시 시도하세요.";
var updateError = "회원정보 수정에 실패했습니다 \n확인후 다시 시도하세요.";
var deleteError = "회원탈퇴에 실패했습니다. \n확인후 다시 시도하세요.";
var passwdError = "입력하신 비밀번호가 일치하지 않습니다. \n확인후 다시 시도하세요.";


function errorAlert(errorMsg) {
	alert(errorMsg);
	window.history.back();
}
function loginCheck() {
	if (!document.login.id.value) {
		alert("아이디를 입력해주세요");
		document.login.id.focus();
		return false;
	} else if (!document.login.pwd.value) {
		alert("비밀번호를 입력해주세요");
		document.login.pwd.focus();
		return false;
	}
}
function joinCheck() {
	if (!document.joinon.id.value) {
		alert("아이디를 입력해주세요");
		document.joinon.id.focus();
		return false;
	} else if (!document.joinon.pwd.value) {
		alert("패스워드를 입력해주세요");
		document.joinon.pwd.focus();
		return false;
	} else if (!document.joinon.repwd.value) {
		alert("패스워드 재입력해주세요");
		document.joinon.repwd.focus();
		return false;
	} else if (document.joinon.pwd.value != document.joinon.repwd.value) {
		alert("패스워드 끼리 일치하지 않습니다.");
		document.joinon.repwd.focus();
		return false;
	} else if (!document.joinon.name.value) {
		alert("이름을 입력해주세요");
		document.joinon.name.focus();
		return false;
	} else if (!document.joinon.addr2.value) {
		alert("주소를 입력하세요");
		doucment.joinon.addr2.focus();
		return false;
	} else if (!document.joinon.email1.value) {
		alert("이메일을 입력해주세요");
		document.joinon.email1.focus();
		return false;
	} else if (!document.joinon.email2.value
			&& document.joinon.email3.value == 0) {
		alert("이메일을 입력해주세요");
		document.joinon.email2.focus();
		return false;
	} else if (document.joinon.hiddenid.value == 0) {
		alert("중복확인 해주세요");
		document.joinon.id.focus();
		return false;
	}
	function nextHp1() {
		if (document.joinon.hp1.value.length >= 3) {
			document.joinon.hp2.focus();
		}
	}
	function nextHp2() {
		if (document.joinon.hp2.value.length >= 4) {
			document.joinon.hp3.focus();
		}
	}
	function nextHp3() {
		if (document.joinon.hp3.value.length >= 4) {
			document.joinon.email1.focus();
		}
	}
}
function usercheck() {
	if (!document.userre.id.value) {
		alert("아이디를 입력해주세요");
		document.userre.id.focus();
		return false;
	} else if (!document.userre.pwd.value) {
		alert("패스워드를 입력해주세요");
		document.userre.pwd.focus();
		return false;
	} else if (!document.userre.repwd.value) {
		alert("패스워드 재입력해주세요");
		document.userre.repwd.focus();
		return false;
	} else if (document.userre.pwd.value != document.userre.repwd.value) {
		alert("패스워드 끼리 일치하지 않습니다.");
		document.userre.repwd.focus();
		return false;
	} else if (!document.userre.email1.value) {
		alert("이메일을 입력해주세요");
		document.userre.email1.focus();
		return false;
	} else if (!document.userre.email2.value
			&& document.userre.email3.value == 0) {
		alert("이메일을 입력해주세요");
		document.userre.email2.focus();
		return false;
	} else if (document.userre.hiddenid.value == 0) {
		alert("중복확인 해주세요");
		document.userre.id.focus();
		return false;
	}
}
function openAddr() {
	daum.postcode
			.load(function() {
				new daum.Postcode(
						{
							oncomplete : function(data) {
								document.getElementsByName("postnum")[0].value = data.zonecode;
								document.getElementsByName("addr1")[0].value = data.roadAddress;
							}
						}).open();
			});
}
function idok() {
	if (!document.joinon.id.value) {
		alert("아이디를 입력해주세요");
		document.joinon.id.focus();
	} else {
		var url = "confirmId.cu?id=" + document.joinon.id.value;
		window.open(url, "confirm", "menubar=no, width=500, height=300, color=red");
	}
}
function setId(id) {
	opener.document.joinon.id.value = id;
	opener.document.joinon.hiddenid.value = "1";
	self.close();
}

function pwdok() {
	if(!document.joinon.pwd.value) {
		alert("비밀번호를 입력해주세요");
		document.joinon.pwd.focus();
		return false;
	}
}