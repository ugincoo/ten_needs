// ------------------------ modal method ------------------------
function openModal(){
	document.querySelector('.modal_wrap').style.display = 'flex';
}

function closeModal(){
	document.querySelector('.modal_wrap').style.display = 'none';
}


function closeModal2(){
	document.querySelector('.modal_wrap2').style.display = 'none';
}

function closeModal3(){
	if(document.querySelector('.roundContent').innerHTML == "최종 승리!"){
		location.href = "/ten__needs/tenneeds/jsp/game/gamelist.jsp";
	}
}
