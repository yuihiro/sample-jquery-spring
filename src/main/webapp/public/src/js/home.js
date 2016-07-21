module.exports = function () {
    var $main_container = $('.main');
    var name = "home";

    var init = function () {
        $main_container.off("click");
        $main_container.empty();
        $main_container.append("<div>하하</div>");
    };

    return {
        init: init
    }
};

