// 로그인 처리
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
        sessionStorage.setItem("username", username); // 로그인 상태 저장
        window.location.href = "/lobby"; // 로비 페이지로 이동
    } else {
        alert("로그인 실패. 다시 시도하세요.");
    }
}


// 회원가입 처리
async function register() {
    const username = document.getElementById("registerUsername").value;
    const password = document.getElementById("registerPassword").value;


    const response = await fetch('/auth/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    });


    if (response.ok) {
        alert("회원가입 성공! 이제 로그인하세요.");
    } else {
        alert("회원가입 실패. 다시 시도하세요.");
    }
}





