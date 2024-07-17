const video = document.getElementById('myVideo');
const videoContainer = document.getElementById('videoContainer');
const videoList = document.getElementById('videoList');

function playVideo(sourceId, targetId) {
    const sourceVideo = document.getElementById(sourceId);
    const targetVideo = document.getElementById(targetId);
    targetVideo.innerHTML = sourceVideo.innerHTML;
    videoContainer.style.height = (video.duration * 250) + 'px'; // adjust the speed constant here
    window.requestAnimationFrame(playVideo);
}

video.addEventListener('loadedmetadata', function() {
    videoContainer.style.height = (video.duration * 250) + 'px'; // adjust the speed constant here
});

window.addEventListener('scroll', function() {
    const scrollY = window.scrollY;
    const height = document.documentElement.scrollHeight - window.innerHeight;
    const percentage = scrollY / height;
    video.currentTime = video.duration * percentage;
});