var Home = require("./home.js");
var Ap = require("./ap.js");

module.exports = (function () {
    var $el;
    var router;
    var count = 0;

    var init = function(el) {
        $el = el;
        initRouter();
        initEventListener();
    };

    var initRouter = function() {
        router = $.sammy(function () {
            this.get("#/", viewHome );
            this.get("#/ap", viewAp);
            this.notFound = function () {
                this.setLocation('#/');
            };
            this.before(function () {
            });
        });
        router.run("#/");
    };

    var initEventListener = function() {
        PubSub.subscribe("APP_HI", function() {
            console.log("가자");
        });
    };

    var initEvent = function() {
    };

    var render = function() {

    };

    var test = function() {
        count++;
    };

    var getEl = function() {
        return $el;
    };

    var viewHome = function() {
        console.log("하하");
        new Home().init();
    };

    var viewAp = function() {
        console.log("하하1ss");
        var module = new Ap();
        module.init();
    };

    return {
        init: init,
        getEl : getEl
    };
})();