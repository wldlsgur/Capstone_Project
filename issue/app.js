var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
const multer = require('multer');

const storage = multer.diskStorage({
	destination: function (req, file, cb) {
		let job = req.params.job;
	
		if(job === "부모님"){
			cb(null, 'uploads/parents');
		}
		else if(job === "선생님"){
			cb(null, 'uploads/teacher');
		}
		else if(job === "원장"){
			cb(null, 'uploads/president');
		}
		else{
			cb(null, 'uploads/foodlist');
		}
	},
	filename: function (req, file, cb) {
		let name = req.params.name;
		cb(null, name + '.jpg');
	}
})
const upload = multer({ storage: storage })

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');
let checkRouter = require('./routes/check');
let createRouter = require('./routes/create');
let schoolmanagementRouter = require('./routes/schoolmanagement');
let uploadimageRouter = require('./routes/uploadimage');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));
app.use('/image', express.static('uploads'));

app.use('/', indexRouter);
app.use('/users', usersRouter);
app.use('/check', checkRouter);
app.use('/create', createRouter);
app.use('/schoolmanagement', schoolmanagementRouter);
app.use('/uploadimage/:job/:name', upload.single('image'), uploadimageRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
