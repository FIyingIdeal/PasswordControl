/**
 * Created by Administrator on 2016/1/26.
 */
$(document).ready(function() {
    $("#subject").on("input", function() {
       var subject = $(this).val();
        $.ajax({
            type : 'GET',
            url : 'http://localhost:8080/blurredpassword/reletedSubject/' + subject,
            success : function(data) {
                if(data.length <= 0) {
                    $(".result").hide();
                    return false;
                }
                $(".result").show();
                for (i in data) {

                }
            },
            error : function(data) {
                console.log(data);
            }
        });
    });

    $("#subject").keyup(function() {
        $.get(
            "/blurredpassword/reletedSubject/" + $("#subject").val(),
            function(data){
                if (data.length) {
                    for (var i = 0; i < data.length; i++){
                        $("#subjects").html("<a href=\"/blurredpassword/blurredPassword/"+ data[i] +"\">"+ data[i] +"</a><br/>");
                    }
                } else {
                    $("#subjects").html("");
                }
            }
        );
    });
})