//var template = require("../template/test.hbs")
var Modal = require("./modal.js")

module.exports = function () {
    var name = "home";
    var a = 0;

    var init = function () {
        a++;
        console.log(a);
        loadData();
    };

    var hi = function () {
        alert("하이" + a);
    }

    var loadData = function () {
        console.log("loadData");

        axios.get('api/sensor/sensor_list')
            .then(function (response) {
                if (response instanceof Error) {
                    console.log('Error', response.message);
                } else {
                    render(response.data);
                }
            })
            .catch(function (response) {
                console.log(response);
            });
    };

    var render = function ($data) {
        console.log("render");

        //var $main_container = $('.main');
        //var html = template({items: $data});
        //$main_container.html(html);

        var $main = App.getEl();
        $main.render("test", {items: $data});

        var $grid = $main.find('#grid');
        console.log($grid);
        $grid.kendoGrid({
            dataSource: {
                data: $data
            },
            columns: [
                {field:"id", title:"ID"},
                {field:"name", title:"이름"},
                {field:"mac_str", title:"MAC"},
                {field:"status_str", title:"상태"}
            ]
        });

        $main.on("click", ".test_btn", test_click);
    };

    var test_click = function(event) {
        PubSub.publish("APP_HI");
        popupModal("WOOBIN");
    }

    var popupModal = function(title) {
        var modal = new Modal();
        var $el = modal.render("modal", {title:title});
        $el.css("opacity" , 0);
        $el.animate({opacity:1}, 500);
    }

    return {
        hi: hi,
        init: init
    }
};
