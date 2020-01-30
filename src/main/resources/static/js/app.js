$(function () {
    if ($("#content").length) {
        ClassicEditor
            .create(document.querySelector("#content"))
            .catch(error => {
                console.log(error);
            });
    }

    if ($("#description").length) {
        ClassicEditor
            .create(document.querySelector("#description"))
            .catch(error => {
                console.log(error);
            });
    }
})