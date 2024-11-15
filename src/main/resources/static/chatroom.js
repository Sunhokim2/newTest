let stompClient = null;
let roomId = new URLSearchParams(window.location.search).get('roomId');


// WebSocket 연결
function connectToRoom() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);


    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe(`/topic/room.${roomId}`, function (message) {
            showMessage(JSON.parse(message.body));
        });
        loadUserList();
    });
}


// 메시지 전송
function sendMessage() {
    const messageContent = document.getElementById("message").value;
    const username = sessionStorage.getItem("username");


    if (messageContent && stompClient) {
        const chatMessage = {
            sender: username,
            content: messageContent,
            type: 'CHAT'
        };
        stompClient.send(`/app/chat.sendMessage/${roomId}`, {}, JSON.stringify(chatMessage));
        document.getElementById("message").value = '';
    }
}


// 채팅 메시지 표시
function showMessage(message) {
    const chatArea = document.getElementById("chatArea");
    const messageElement = document.createElement('p');
    messageElement.textContent = `${message.sender}: ${message.content}`;
    chatArea.appendChild(messageElement);
}


// 사용자 목록 로드
async function loadUserList() {
    const response = await fetch(`/chatrooms/${roomId}/users`);
    const users = await response.json();


    const userList = document.getElementById("userList");
    userList.innerHTML = "";
    users.forEach(user => {
        const userItem = document.createElement("div");
        userItem.innerText = user.username;
        userItem.onclick = () => selectUser(user.id);
        userList.appendChild(userItem);
    });
}


// 선택된 사용자에게 채팅 권한 부여
function grantPermission() {
    // API 호출로 특정 사용자에게 권한 부여
}


// 페이지 로드 시 WebSocket 연결
window.onload = connectToRoom;





