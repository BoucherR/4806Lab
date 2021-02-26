$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: "/getBuddies",
    }).
    then(function(data) {
        var result = "";
        $.each(data, function (buddy) {
            result += '<span>Buddy: </span>' + buddy.name + '<span> , </span>' +
                '<span>ID: </span>' + buddy.id + '<p> </p>';
        })
        $('.buddy-list').append(result);
        console.log(data);
    });
});