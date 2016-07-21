var gulp = require('gulp');
var watch = require('gulp-watch');

var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var minifyCss = require('gulp-minify-css')
var copy = require('gulp-copy');

var jshint = require('gulp-jshint');
var stylish = require('jshint-stylish');
var csslint = require('gulp-csslint');

var rename = require('gulp-rename');
var gulpif = require('gulp-if');
var ignore = require('gulp-ignore');
var rimraf = require('gulp-rimraf');
var sourcemaps = require('gulp-sourcemaps');
var gutil = require('gulp-util');
//var plumber = require('gulp-plumber');

var source = require('vinyl-source-stream');
var buffer = require('vinyl-buffer');

var browserify = require('browserify');
//var babelify = require('babelify');

var webpack = require('gulp-webpack');

var connect = require('gulp-connect');
var webserver = require('gulp-webserver');

var config = require('./gulp.config.js');
var config_webpack = require('./webpack.config.js');

gulp.task('clean', function () {
    gulp.src([config.path.dist + '/**/*'], {
            read: false
        })
        //.pipe(ignore.exclude('node_modules/**'))
        .pipe(rimraf({
            force: false
        }));
});

gulp.task('lint:js', function () {
    return gulp.src([config.file.js])
        .pipe(jshint())
        .pipe(jshint.reporter(stylish))
        .pipe(jshint.reporter("fail"))
});

gulp.task('eslint', function() {
    return gulp.src([config.file.js])
        .pipe(eslint({
            baseConfig: {
                "ecmaFeatures": {
                    "jsx": true
                }
            }
        }))
        .pipe(eslint.format())
        .pipe(eslint.failAfterError());
});

gulp.task('lint:css', function () {
    return gulp.src([config.file.css])
        .pipe(csslint())
        .pipe(csslint.reporter())
        .pipe(csslint.reporter('fail'))
});

gulp.task('copy:html', function () {
    return gulp.src(config.file.html)
        .pipe(gulp.dest(config.path.dist))
});

gulp.task('copy:img', function () {
    return gulp.src([config.file.png, config.file.jpg] )
        .pipe(gulp.dest(config.path.dist_img))
});

gulp.task('copy:template', function () {
    return gulp.src(config.file.template)
        .pipe(gulp.dest(config.path.dist_template))
        .pipe(connect.reload());
});

gulp.task('copy:partial', function () {
    return gulp.src(config.file.partial)
        .pipe(gulp.dest(config.path.dist_partial))
        .pipe(connect.reload());
});

gulp.task('compile:css', function () {
    return gulp.src(config.file.css)
        .pipe(concat(config.file.bundle_css))
        .pipe(minifyCss())
        .pipe(gulp.dest(config.path.dist_css))
});

gulp.task('compile:js', function () {
    return browserify({
        entries: [config.file.entry],
        debug: true
    })
        .on('error', function(err) {
            console.log(err);
            this.emit('end');
        })
        //.transform('babelify', {
          //  presets: ['es2015', 'react', 'stage-1']
        //})
        .bundle()
        /*
        .pipe(plumber({
            handleError: function (err) {
                console.log(err);
                this.emit('end');
            }
        }))
        */
        .pipe(source(config.file.bundle_js))
        .pipe(buffer())
        .pipe(gulpif(config.condition.build, sourcemaps.init({loadMaps: true})))
        .pipe(gulpif(config.condition.build, uglify()))
        //.on('error', gutil.log)
        .pipe(gulpif(config.condition.build, sourcemaps.write("./")))
        //.pipe(rename({suffix:".min"}))
        .pipe(gulp.dest(config.path.dist_js))
});

gulp.task('compile:webpack', function () {
    return gulp.src(config.file.entry)
        .pipe(webpack(config_webpack))
        .pipe(gulp.dest(config.path.dist_js));

});

gulp.task('watch', function () {
    watch(config.file.html, function(event) {
        gulp.start('copy:html');
    });
    watch([config.file.jpg, config.file.png] , function(event) {
        gulp.start('copy:img');
    });
    watch(config.file.css, function(event) {
        gulp.start('compile:css');
    });
    watch(config.file.js, function(event) {
        gulp.start('compile:js');
    });
    watch(config.file.template, function(event) {
        gulp.start('compile:js');
    });
    watch(config.file.partial, function(event) {
        gulp.start('compile:js');
    });
    watch("./package.json", function(event) {
        gulp.start('compile:js');
    });
});

/*
gulp.task('server', function () {
    gulp.src( 'app' )
        .pipe(webserver({
            host:             "127.0.0.2",
            port:             35722,
            livereload:       false,
            directoryListing: true,
            path:             "dist"
        }));
});
*/


gulp.task('server', function () {
    connect.server({
        root: 'dist',
        port: 8888,
        livereload: {
            port : 35729
        },
        open: {
            file: 'index.html',
            browser: 'chrome'
        }
    });
});

gulp.task('copy', ['copy:html', 'copy:img']);
gulp.task('compile', ['compile:css', 'compile:js']);
gulp.task('all', ['copy', 'compile', 'watch', 'server']);

gulp.task('default', ['lint:js', 'compile:js']);