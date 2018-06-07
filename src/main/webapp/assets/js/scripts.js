
jQuery(document).ready(function() {
	
    /*
        Fullscreen background
    */
    $.backstretch("assets/img/backgrounds/1.jpg");
    
    /*
        Form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });

    $('.btn').click(function(e) {
        e.preventDefault();
        login();
    });
    
});

/**
 * 使用form表单提交，不建议采用这种方式了
 * @returns {boolean}
 */
function submit() {
    if (!verify()) {
        return false;
    }

    $('.login-form').submit(function(e) {
        console.log(e);
    });
}

/**
 * 使用ajax请求提交表单，后端有两种处理方式：
 * 1.后端由Spring Controller进行验证，filterChainDefinitions需要将登录请求的URL指定为none，即不让shiro拦截处理，交由Controller处理
 * 2.由shiro自定义Filter(继承自FormAuthenticationFilter)进行身份认证，filterChainDefinitions需要将登录请求的URL指定为authc，这样就将请求交由该Filter处理，Shiro自动调用executeLogin()进行登录验证
 * 但需要注意一点，交由Spring处理的时候，Spring会将提交的json转换为一个对象，但如果是让Shiro处理的话，需要在自定义的Filter中进行处理，或
 */
function login() {
    var username = $('#username').val();
    var password = $('#password').val();

    var loginFlag = verify();

    if (loginFlag) {
        var loginUrl = '/user/login';
        $.ajax({
            type: 'POST',
            url: loginUrl,
            data: {
                "username": username,
                "password": password
            },
            //data: JSON.parse('{"username":"' + username + '", "password":"' + password + '"}'),
            contentType: "application/json;charset=utf-8",
            accept: "application/json;charset=utf-8",
            success: function (e) {
                console.log("success " + e);
            },
            error: function (e) {
                console.log("login error" + e.error);
            }
        });
    }
}

/**
 * 进行表单验证
 */
function verify() {
    var loginFlag = true;
    var username = $('#username');
    var password = $('#password');
    if (username.val() == "") {
        username.addClass('input-error');
        loginFlag &= false;
    }
    if (password.val() == "") {
        password.addClass('input-error');
        loginFlag &= false;
    }
    return loginFlag;
}
