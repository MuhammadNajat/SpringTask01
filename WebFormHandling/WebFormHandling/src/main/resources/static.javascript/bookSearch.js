function processBookSearchButtonClick() {
    let bookId = document.getElementById('bookId').innerHTML;
    window.location.href = "http://localhost:8080/bookSearch/" + bookId;
}