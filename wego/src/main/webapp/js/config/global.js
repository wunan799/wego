var Global = Class.extend({
    api_url : '/api/',

    init: function () {
        requirejs(['utility/loading'], function () {
            var loading = new LoadingBox(getImagePath() + 'prettyPhoto/default/loader.gif');

            $.ajaxSetup({
                beforeSend: function () {
                    loading.show();
                },

                complete: function (event, xhr, options) {
                    loading.hide();

                    if (event.status != 200) {
                        var dlg = new ModalDlg();
                        dlg.show('errorResult', 'ajax异常', '获取数据失败');
                    }
                }
            });
        });
    },

    getParameter: function (param, defaultValue) {
        var value = window.sessionStorage.getItem(param);

        if (value == undefined) {
            return defaultValue;
        }

        try {
            var obj = JSON.parse(value);

            if (obj != null) {
                return obj;
            } else {
                return value;
            }
        } catch (e) {
            console.log(e);
            return value;
        }
    },

    setParameter: function (param, value) {
        if (value == undefined) {
            return;
        }

        if (value instanceof Object) {
            window.sessionStorage.setItem(param, JSON.stringify(value));
        } else {
            window.sessionStorage.setItem(param, value);
        }
    },

    getLocalParam: function (param, defaultValue) {
        var value = window.localStorage.getItem(param);

        if (value == undefined) {
            return defaultValue;
        }

        try {
            var obj = JSON.parse(value);

            if (obj != null) {
                return obj;
            } else {
                return value;
            }
        } catch (e) {
            console.log(e);
            return value;
        }
    },

    setLocalParam: function (param, value) {
        if (value == undefined) {
            return;
        }

        if (value instanceof Object) {
            window.localStorage.setItem(param, JSON.stringify(value));
        } else {
            window.localStorage.setItem(param, value);
        }
    },


    delParameter: function (param) {
        window.sessionStorage.removeItem(param);
    },

    delLocalParam: function (param) {
        window.localStorage.removeItem(param);
    },
});

var global = new Global();