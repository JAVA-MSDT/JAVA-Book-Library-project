function changeSearchFieldType() {
    let selectOption = document.getElementById("select-option").value;
    let searchField = document.getElementById("search-field");
    let readingPlaceField = document.getElementById("reading-place");
    switch (selectOption) {
        case "email":
            searchField.setAttribute("type", "email");
            searchField.disabled = false;
            searchField.style.display = "block";
            readingPlaceField.style.display = "none";
            break;
        case "orderDate":
            searchField.setAttribute("type", "date");
            searchField.disabled = false;
            searchField.style.display = "block";
            readingPlaceField.style.display = "none";
            break;
        case "returningDate":
            searchField.setAttribute("type", "date");
            searchField.disabled = false;
            searchField.style.display = "block";
            readingPlaceField.style.display = "none";
            break;
        case "readingPlace":
            searchField.disabled = true;
            searchField.style.display = "none";
            readingPlaceField.style.display = "block";
            break;

        default:
            searchField.setAttribute("type", "text");
            searchField.disabled = false;
            searchField.style.display = "block";
            readingPlaceField.style.display = "none";
    }
}