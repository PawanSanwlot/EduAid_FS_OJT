document.addEventListener("DOMContentLoaded", function() {
    var modal = document.getElementById("profileModal");
    var profilePic = document.getElementById("profilePic");
    var modalProfilePic = document.getElementById("modalProfilePic");
    var span = document.getElementsByClassName("close")[0];
    var changePic = document.getElementById("changePic");
    var deletePic = document.getElementById("deletePic");

    profilePic.onclick = function() {
        modal.style.display = "flex";
    }

    span.onclick = function() {
        modal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

    changePic.onclick = function() {
        alert("Change profile picture functionality.");
    }

    deletePic.onclick = function() {
        alert("Delete profile picture functionality.");
    }
});
