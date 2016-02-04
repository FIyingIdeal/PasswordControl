/**
 * Created by Administrator on 2016/1/26.
 */

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