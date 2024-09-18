document.addEventListener('DOMContentLoaded', () => {
  const postsContainer = document.getElementById('posts-container');
  
  // Function to render posts
  function renderPosts() {
    postsContainer.innerHTML = ''; // Clear any existing posts

    posts.forEach(post => {
      const postElement = document.createElement('div');
      postElement.classList.add('post');

      let mediaContent = '';
      if (post.type === 'video') {
        mediaContent = `<iframe width="660" height="315" src="${post.media}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>`;
      } else if (post.type === 'image') {
        mediaContent = `<img src="${post.media}" alt="Post image">`;
      }
postElement.innerHTML = `
  <div class="post-header">
    <img src="https://i.imgur.com/4FbD7mF.png" alt="" class="post-img">
    <span class="post-user">${post.user}</span>
  </div>
  <div class="post-content-container">
    <div class="post-content">
      ${post.content}
    </div>
    ${mediaContent ? `<div class="post-media">${mediaContent}</div>` : ''}
  </div>
  <div class="post-actions">
    <button class="like-btn"><i class="fas fa-bookmark"></i> Favorite</button>
    <button class="response-btn"><i class="fas fa-reply-all"></i> Response</button>
  </div>`;

    
    

      postsContainer.appendChild(postElement);
    });
  }

  // Render posts when the page loads
  renderPosts();
});
