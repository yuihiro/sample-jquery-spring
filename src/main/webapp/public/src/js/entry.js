global.jQuery = global.$ = require("jquery");
global.PubSub = require("pubsub-js");
global.axios = require("axios");
global.Handlebars = require("handlebars");
//var gsap = require('gsap');
//global.TweenMax = gsap.TweenMax;

require("sammy");
require("swag");
require("jquery-handlebars");

global.App = require("./app.js");

$(function () {
    console.log("ready");
    Swag.registerHelpers(Handlebars);
    Swag.Config.partialsPath = './src/template/'

    $.handlebars({
        templatePath: 'template',
        partialPath: 'partial',
        templateExtension: 'hbs',
        partialExtension: 'hbs',
        partials: ['partial']
    });

    App.init($('.app'));
});

