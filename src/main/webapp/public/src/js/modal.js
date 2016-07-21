module.exports = function () {
    var $el;

    var init = function () {

    };

    var render = function(name, data) {
        var $main = $('.main');
        $main.render(name, data);
        $el = $main.find('#modal');
        return $el;
};

return {
    init: init,
    render: render
}
};

