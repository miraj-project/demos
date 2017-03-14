window.addEventListener('WebComponentsReady', function() {

    // We use Page.js for routing. This is a Micro
    // client-side router inspired by the Express router
    // More info: https://visionmedia.github.io/page.js/

    // Removes end / from app.baseUrl which page.base requires for production
    if (window.location.port === '3000') {  // if production
	page.base(app.baseUrl.replace(/\/$/, ''));
    }
    // Middleware
    function scrollToTop(ctx, next) {
	app.scrollPageToTop();
	next();
    }
    function closeDrawer(ctx, next) {
	app.closeDrawer();
	next();
    }
    // Routes
    page('*', scrollToTop, closeDrawer, function(ctx, next) {
	next();
    });
    page('/', function() {
	app.route = 'home';
    });
    page(app.baseUrl, function() {
	app.route = 'home';
    });
    page('/users', function() {
	app.route = 'users';
    });
    page('/users/:name', function(data) {
	app.route = 'user-info';
	app.params = data.params;
    });
    page('/contact', function() {
	app.route = 'contact';
    });
    // 404
    page('*', function() {
	app.$.toast.text = 'Can\'t find: ' + window.location.href  + '. Redirected you to Home Page';
	app.$.toast.show();
	page.redirect(app.baseUrl);
    });
    // add #! before urls
    page({
	hashbang: true
    });

});

