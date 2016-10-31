/**
 * Created by Administrator on 2016/1/26.
 */
$(document).ready(function() {
    $(".subject").on("input", searchSubjects);
    $(".search").click(searchSubjects);
});

function searchSubjects(event) {
    var subject = $(this).val();
    if (subject == '' || subject == null) {
        return false;
    }
    console.log(subject);
    $.ajax({
        type : 'GET',
        url : 'http://localhost:8080/blurredpassword/reletedSubject/' + subject,
        success : function(data) {
            if(data.length <= 0) {
                $(".result").hide();
                return false;
            }
            $(".result").show();
            var html = '';
            for (i in data) {
                var text = "<span><a onclick='search(data[i])'>" + data[i] + "</a></span><br/>"
                html += text;
            }
            $(".result").html(html);
        },
        error : function(data) {
            console.log(data);
        }
    });
}

function search(text) {
    var url = "XXXXXX/text";
    $.ajax({
        type : GET,
        url : url,
        success : function(data) {

        },
        error : function(data) {

        }
    });
}