$(document).ready(function() {
    console.log('loaded');
    $.ajax({
        url: "http://localhost:8080/books"
    }).then(function(data, status, jqxhr) {
        console.log(data);
        createAddressBooksTable(data);
        // $('.greeting-id').append(data.id);
        // $('.greeting-content').append(data.content);
        console.log(jqxhr);
    });
});

// when a new book is added
$(document).on("submit", "#newbookform", function() {
    var formData = {
        name: $("#bookname").val(),
    };
    console.log("Form data: " + JSON.stringify(formData));
    $.ajax({
        type: "POST",
        data: {name: formData.name},
        url: "http://localhost:8080/book/new"
    }).then(function(data, status, jqxhr) {
        console.log(data);
        const bookid = data.id;
        const bookname = data.name;
        const book_row = '<tr><td><button type="button" onclick="getAddressBook(event)" name="idbtn" data-arg1=' + bookid + '>' + bookid + '</button></td><td><button type="button" onclick="getAddressBook(event)" name="idbtn" data-arg1=' + bookid + '>' + bookname + '</button></td></tr>';
        $("#bookstable").find('tbody').append(book_row);
    });
});

// when a new bud is added
$(document).on("click", "#newbudbtn", function() {
    var formData = {
        name: $("#budname").val(),
        phone: $("#budphone").val(),
        book: book_id,
    };
    console.log("Form data: " + JSON.stringify(formData));
    $.ajax({
        type: "POST",
        data: formData,
        url: "http://localhost:8080/bud/save",
    }).then(function(data, status, jqxhr) {
        console.log("new bud data: " + data);
        const book_row = '<tr><td>' + data.id + '</td><td>' + data.name + '</td><td>' + data.phoneNumber + '</td></tr>';
        console.log("book row " + book_row);
        $("#buddiestable").find('tbody').append(book_row);
    });
});

function createAddressBooksTable(data){
    $("#pagetitle").text("My Address Books");
    $("#newbookarea").show();
    $('#buddiestable').hide();
    $('#newbudarea').hide();

    // $("#new-bud-form").hide();
    for (let book in data ){
        // var on_click = 'onclick=getAddressBook(${data[book].id})';
        // onclick='getAddressBook('' + bookid + '')'>
        const bookid = data[book].id;
        const book_row = '<tr><td><button type="button" onclick="getAddressBook(event)" name="idbtn" data-arg1=' + bookid + '>' + data[book].id + '</button></td><td><button type="button" onclick="getAddressBook(event)" name="idbtn" data-arg1=' + bookid + '>' + data[book].name + '</button></td></tr>';
        $("#bookstable").find('tbody').append(book_row);
    }
}

var book_id = 1;
getAddressBook = (event) => {
    console.log("event: " + JSON.stringify(event.target));
    let arg1 = event.target.getAttribute('data-arg1');
    book_id = parseInt(arg1);
    console.log("arg1 : " + arg1);
    $.ajax({
        url: "http://localhost:8080/book/" + book_id
    }).then(function(data, status, jqxhr) {
        console.log(data);
        createBuddiesTable(data);
        // $('.greeting-id').append(data.id);
        // $('.greeting-content').append(data.content);
        console.log(jqxhr);
    });
}

function createBuddiesTable(data){
    console.log("data is " + JSON.stringify(data));
    $("#pagetitle").text(data.name);
    $('#bookstable').hide();
    $('#newbookarea').hide();
    $('#buddiestable').show();
    $("#newbudarea").show();
    for (let buddy in data.buddyInfoList){
        var bud = data.buddyInfoList[buddy];
        console.log("bud: " + bud);
        const book_row = '<tr><td>' + bud.id + '</td><td>' + bud.name + '</td><td>' + bud.phoneNumber + '</td></tr>';
        $("#buddiestable").find('tbody').append(book_row);

    }
}
