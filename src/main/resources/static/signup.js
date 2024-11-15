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
        alert("회원가입 실패. 다시 시도해 주세요.");
    }
}

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
        const data = await response.json();

        sessionStorage.setItem("username", username);
        sessionStorage.setItem("userId", data.userId);

        window.location.href = "/lobby.html";
    } else {
        alert("로그인 실패. 아이디나 비밀번호를 확인하세요.");
    }
}



