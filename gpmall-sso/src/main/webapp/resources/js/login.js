$(function(){
    $("#btn_submit").click(function(){
        var username=$.trim($("#username").val());
        var password=$.trim($("#password").val());
        if (username== ""||password== "") {
            alert("登录信息不能为空!");
            return;
        }
        $("#password").val(MD5(password));
        var d={};
        d.userName = username;
        d.password = $("#password").val();
        $.ajax({
            url:"/login",
            type:"POST",
            contentType:"application/json;charset=utf-8",
            // data:{username:username,password:$("#password").val()},
            data:JSON.stringify(d),
            success:function(data){
                if(data.msgCd=="000000"){
                    window.location.href=data.body;
                }else{
                    alert(data.msgInfo);
                }
            }
        });
    });
})