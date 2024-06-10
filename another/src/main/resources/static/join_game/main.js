document.addEventListener('DOMContentLoaded', function() {
    const cards = document.querySelectorAll('.game-card');
    cards.forEach(card => {
        const checkbox = card.querySelector('.game-checkbox');
        card.addEventListener('click', function() {
            checkbox.checked = !checkbox.checked; // 체크박스 상태 토글
        });
    });

    const joinButton = document.getElementById('btnJoin');
    joinButton.addEventListener('click', function() {
        window.location.href = '../main/login_main.html'; // 페이지 이동
    });
});