function toggleSearch() {
    var searchInput = document.getElementById('searchInput');
    if (searchInput.style.display === 'none') {
        searchInput.style.display = 'block';
    } else {
        searchInput.style.display = 'none';
    }
}

function validateForm() {
    var searchInput = document.getElementById('searchInput').value;
    if (searchInput.trim() === '') {
        alert('닉네임을 입력해 주세요.');
        return false;
    }
    return true;
}
function toggleDropdown() {
    var dropdown = document.getElementById("dropdownMenu");
    if (dropdown.style.display === "none" || dropdown.style.display === "") {
        dropdown.style.display = "block";
    } else {
        dropdown.style.display = "none";
    }
}

// To close the dropdown if the user clicks outside of it
window.onclick = function(event) {
    if (!event.target.matches('.fa-globe')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        for (var i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.style.display === "block") {
                openDropdown.style.display = "none";
            }
        }
    }
}