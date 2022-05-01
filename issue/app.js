var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
const multer = require('multer');

const storage = multer.diskStorage({
	destination: function (req, file, cb) {
		let split_array = req.params.data.split('-');
		let target = split_array[0];
	
		if(target === "parent"){
			cb(null, 'uploads/parents');
		}
		else if(target === "teacher"){
			cb(null, 'uploads/teacher');
		}
		else if(target === "president"){
			cb(null, 'uploads/president');
		}
		else if(target === "food"){
			cb(null, 'uploads/food_menu');
		}
		else if(target === "album"){
			cb(null, 'uploads/album');
		}
	},
	filename: function (req, file, cb) {
		let split_array = req.params.data.split('-');
		let key = split_array[1];
		cb(null, `${key}_${file.originalname}`);
	},
	limits: {fileSize: 1 * 512 * 512}
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
let food_listRouter = require('./routes/food_list');
let albumRouter = require('./routes/album');

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
app.use('/food_list', food_listRouter);
app.use('/album', albumRouter);
app.post('/uploadimage/:data', upload.single('image'), uploadimageRouter);

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