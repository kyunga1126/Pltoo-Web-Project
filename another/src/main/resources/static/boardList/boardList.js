document.addEventListener('DOMContentLoaded', () => {
  const popularPostsElement = document.getElementById('popularPosts');
  const latestPostsElement = document.getElementById('latestPosts');
  const paginationElement = document.getElementById('pagination');
  const searchInputElement = document.getElementById('searchInput');
  const searchButtonElement = document.getElementById('searchButton');
  const writeButtonElement = document.getElementById('writeButton');
  const sortOptionsElement = document.getElementById('sortOptions');



  const postsPerPage = 2;
  let currentPage = 1;
  let filteredPosts = posts;

  const renderPosts = (page = 1) => {
    const start = (page - 1) * postsPerPage;
    const end = start + postsPerPage;

    const sortValue = sortOptionsElement.value;
    let sortedPosts = [...filteredPosts];

    switch (sortValue) {
      case 'popular':
        sortedPosts = sortedPosts.sort((a, b) => b.likes - a.likes);
        break;
      case 'views':
        sortedPosts = sortedPosts.sort((a, b) => b.views - a.views);
        break;
      case 'latest':
      default:
        sortedPosts = sortedPosts.sort((a, b) => b.date - a.date);
        break;
    }

    const popularPosts = posts
      .sort((a, b) => b.views - a.views)
      .slice(0, 4);

    popularPostsElement.innerHTML = '';
    popularPosts.forEach((post) => {
      const postItem = document.createElement('li');
      postItem.innerHTML = `
        <img src="${post.attachment}" alt="Attachment">
        <div class="content">
          <h3>${post.title}</h3>
          <p>👤 ${post.nickname}&emsp;
          🕘 ${post.date.toDateString()}&emsp;
          👁‍🗨 ${post.views}&emsp;
          ❤ ${post.likes}</p>
        </div>
      `;
      popularPostsElement.appendChild(postItem);
    });

    const latestPosts = sortedPosts.slice(start, end);

    latestPostsElement.innerHTML = '';
    latestPosts.forEach((post) => {
      const postItem = document.createElement('li');
      postItem.innerHTML = `
        <img src="${post.attachment}" alt="Attachment">
        <div class="content">
          <h3>${post.title}</h3>
          <p>👤 ${post.nickname}&emsp;
          🕘 ${post.date.toDateString()}&emsp;
          👁‍🗨 ${post.views}&emsp;
          ❤ ${post.likes}</p>
        </div>
      `;
      latestPostsElement.appendChild(postItem);
    });

    renderPagination(page);
  };

  const renderPagination = (page) => {
    const totalPages = Math.ceil(filteredPosts.length / postsPerPage);

    paginationElement.innerHTML = '';

    if (page > 1) {
      const prevButton = document.createElement('button');
      prevButton.innerText = 'Previous';
      prevButton.onclick = () => renderPosts(page - 1);
      paginationElement.appendChild(prevButton);
    }

    for (let i = 1; i <= totalPages; i++) {
      const pageButton = document.createElement('button');
      pageButton.innerText = i;
      pageButton.disabled = i === page;
      pageButton.onclick = () => renderPosts(i);
      paginationElement.appendChild(pageButton);
    }

    if (page < totalPages) {
      const nextButton = document.createElement('button');
      nextButton.innerText = 'Next';
      nextButton.onclick = () => renderPosts(page + 1);
      paginationElement.appendChild(nextButton);
    }
  };

  searchButtonElement.onclick = () => {
    const query = searchInputElement.value.toLowerCase();
    filteredPosts = posts.filter(post => 
      post.title.toLowerCase().includes(query) || 
      post.content.toLowerCase().includes(query) ||
      post.nickname.toLowerCase().includes(query)
    );
    renderPosts(1);
  };

  sortOptionsElement.onchange = () => {
    renderPosts(1);
  };

  writeButtonElement.onclick = () => {
    window.location.href = './write_post.html'; // 글쓰기 버튼 클릭 시 게시글 등록 페이지로 이동
  };

  // 초기 데이터 렌더링
  renderPosts();
});

function toggleSearch() {
  var searchInput = document.getElementById('searchInput');
  if (searchInput.style.display === 'none') {
      searchInput.style.display = 'block';
  } else {
      searchInput.style.display = 'none';
  }
}
