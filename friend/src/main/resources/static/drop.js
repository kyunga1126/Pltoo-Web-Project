function showUserProfile(userId) {
    const xhr = new XMLHttpRequest();
    
    xhr.open('GET', `http://your-backend-api.com/user/${userId}`, true);
    
    xhr.onload = function() {
        if (xhr.status === 200) {
            const data = JSON.parse(xhr.responseText);
            
            const profileImage = document.getElementById('profileImage');
            profileImage.src = data.profileImage;

            const nickname = document.getElementById('nickname');
            nickname.textContent = data.nickname;

            const age = document.getElementById('age');
            age.textContent = data.age;

            const location = document.getElementById('location');
            location.textContent = data.location;

            const selfIntroduction = document.querySelector('.self-introduction');
            selfIntroduction.textContent = data.selfIntroduction;

            const gameList = document.querySelector('.game-list'); /* DB에 등록된 게임이 이미지 일 경우 */
			gameList.innerHTML = data.games.map(game => `<img src="${game.imageUrl}" alt="${game.name}">`).join('');

            const tier = document.querySelector('.tier');
            tier.innerHTML = `<img src="${data.tierImage}" alt="티어 이미지">`;

            const headerProfileImage = document.getElementById('headerProfileImage');
            headerProfileImage.src = data.profileImage;

            // 프로필 이미지를 클릭했을 때 mypage.html로 이동하는 이벤트 추가
            profileImage.addEventListener('click', function() {
                window.location.href = "mypage.html";
            });
        } else {
            console.error('Request failed. Status:', xhr.status);
        }
    };
    
    xhr.send();
}

window.onload = function() {
    const userId = getUserIdFromURL();
    showUserProfile(userId);
}
