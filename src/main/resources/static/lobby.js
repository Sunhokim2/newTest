// 로그인 API 호출
async function login() {
    const username = document.getElementById("loginUsername").value;
    const password = document.getElementById("loginPassword").value;


    const response = await fetch('/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    });


    if (response.ok) {
        alert("로그인 성공!");
        sessionStorage.setItem("username", username);
        loadRooms();
        document.getElementById("authArea").style.display = "none";
        document.getElementById("lobbyArea").style.display = "block";
    } else {
        alert("로그인 실패. 다시 시도하세요.");
    }
}


// 회원가입 API 호출
async function register() {
    const username = document.getElementById("registerUsername").value;
    const password = document.getElementById("registerPassword").value;


    const response = await fetch('/auth/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    });


    if (response.ok) {
        alert("회원가입 성공!");
    } else {
        alert("회원가입 실패. 다시 시도하세요.");
    }
}


// 채팅방 목록 불러오기
async function loadRooms() {
    const response = await fetch('/chatrooms');
    const rooms = await response.json();
    const roomList = document.getElementById("roomList");


    roomList.innerHTML = "";
    rooms.forEach(room => {
        const roomItem = document.createElement("div");
        roomItem.innerText = room.name;
        roomItem.onclick = () => enterRoom(room.id);
        roomList.appendChild(roomItem);
    });
}


// 채팅방 생성 API 호출
async function createRoom() {
    const roomName = document.getElementById("roomName").value;
    const username = sessionStorage.getItem("username");


    if (!username) {
        alert("로그인이 필요합니다.");
        return;
    }


    const response = await fetch(`/chatrooms?name=${roomName}&userId=1`, { method: 'POST' });
    if (response.ok) {
        alert("채팅방 생성 성공!");
        loadRooms();
    } else {
        alert("채팅방 생성 실패.");
    }
}


// 로그아웃
function logout() {
    sessionStorage.clear();
    document.getElementById("authArea").style.display = "block";
    document.getElementById("lobbyArea").style.display = "none";
}


// 초기 화면 로드 시 처리
window.onload = function () {
    if (sessionStorage.getItem("username")) {
        document.getElementById("authArea").style.display = "none";
        document.getElementById("lobbyArea").style.display = "block";
        loadRooms();
    } else {
        document.getElementById("authArea").style.display = "block";
        document.getElementById("lobbyArea").style.display = "none";
    }
};





