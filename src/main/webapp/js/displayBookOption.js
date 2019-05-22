function displayBook() {
    let displayOption = document.getElementById("display-option").value;
    let displayCardDiv = document.getElementById("display-book-card");
    let displayTableDiv = document.getElementById("display-book-table");
    switch (displayOption) {
        case "displayCard":
            displayCardDiv.style.display = "block";
            displayTableDiv.style.display = "none";
            break;
        case "displayTable":
            displayCardDiv.style.display = "none";
            displayTableDiv.style.display = "block";
            break;
        default:
            displayCardDiv.style.display = "block";
            displayTableDiv.style.display = "none";
    }
}