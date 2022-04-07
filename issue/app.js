var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
const multer = require('multer');

const storage = multer.diskStorage({
	destination: function (req, file, cb) {
		let target = req.params.target;
	
		if(target === "부모님"){
			cb(null, 'uploads/parents');
		}
		else if(target === "선생님"){
			cb(null, 'uploads/teacher');
		}
		else if(target === "원장님"){
			cb(null, 'uploads/president');
		}
		else if(target === "식단표"){
			cb(null, 'uploads/food_menu');
		}
		else{
			cb(null, 'uploads/album');
		}
	},
	filename: function (req, file, cb) {
		let id = req.params.id;
		let name = req.params.name;
		cb(null, id + '_' + name + '.jpg');
	}
})
const upload = multer({ storage: storage })

var indexRouter = require('./routes/index');
let checkRouter = require('./routes/check');
let createRouter = require('./routes/create');
let schoolmanagementRouter = require('./routes/schoolmanagement');
let uploadimageRouter = require('./routes/uploadimage');
let parentinfoRouter = require('./routes/parentinfo');
let presidentinfoRouter = require('./routes/presidentinfo');
let userRouter = require('./routes/user');

//인승 추가(아래)
var staffRouter = require('./routes/staff');
var medicineRouter = require('./routes/medicine');

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
app.use('/check', checkRouter);
app.use('/create', createRouter);
app.use('/user', userRouter);
app.use('/schoolmanagement', schoolmanagementRouter);
app.use('/parentinfo', parentinfoRouter);
app.use('/presidentinfo', presidentinfoRouter);
app.use('/uploadimage/:target/:id/:name', upload.single('image'), uploadimageRouter);

//인승 추가(아래)
app.use('/staff', staffRouter);
app.use('/medicine', medicineRouter);

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
