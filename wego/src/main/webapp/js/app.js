function getHtmlName() {
    var strUrl = window.location.pathname;
    var arrUrl = strUrl.split("/");
    var page = arrUrl[arrUrl.length - 1];
    var name = page.substr(0, page.lastIndexOf('.'));

    if (name == '') {
        name = 'index';
    }

    return name;
}

function getImagePath() {
    return './img/';
}

requirejs.config({
    //By default load any module IDs from js/lib
    baseUrl: 'js/app/' + getHtmlName(),
    //except, if the module ID starts with "app",
    //load it from the js/app directory. paths
    //config is relative to the baseUrl, and
    //never includes a ".js" extension since
    //the paths config could be for a directory.
    paths: {
        config: '../../config',
        lib: '../..',
        utility: '../utility'
    }
});

// Start the main app logic.
requirejs(['lib/jsi'], function () {
    requirejs(['controller', 'model', 'view', 'config/global'], function (controller) {
        controller.setup();
    });
});

function getCookie(c_name)
{
    if (document.cookie.length>0)
    {
        c_start=document.cookie.indexOf(c_name + "=")
        if (c_start!=-1)
        {
            c_start=c_start + c_name.length+1
            c_end=document.cookie.indexOf(";",c_start)
            if (c_end==-1) c_end=document.cookie.length
            return unescape(document.cookie.substring(c_start,c_end))
        }
    }
    return ""
}

function setCookie(c_name,value,expiredays)
{
    var exdate=new Date()
    exdate.setDate(exdate.getDate()+expiredays)
    document.cookie=c_name+ "=" +escape(value)+
        ((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
}

function checkCookie()
{
    username=getCookie('username')
    if (username!=null && username!="")
    {alert('Welcome again '+username+'!')}
    else
    {
        username=prompt('Please enter your name:',"")
        if (username!=null && username!="")
        {
            setCookie('username',username,365)
        }
    }
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

function dateToString(date) {
    var result = date.getFullYear() + '年' + (date.getMonth() + 1)
        + '月' + date.getDate() + '日 ' + date.getHours() + ':';

    if (date.getMinutes() < 10) {
        result += '0' + date.getMinutes();
    } else {
        result += date.getMinutes();
    }

    return result;
}