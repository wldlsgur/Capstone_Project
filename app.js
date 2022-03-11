const express = require('express');//express 프레임워크 사용
const app = express();//express를 호출 즉 함수다.
const fs = require('fs');//파일 기능
const bodyParser = require('body-parser');//body 사용
const compression = require('compression');//데이터 압축
const topicRouter = require('./routes/topic')//라우터 모음
const indexRouter = require('./routes/index');//라우터 모음
//모든 라우터는 public의 사진들을 그냥 바로 경로로 사용할수가있다.
app.use(express.static('public'));//public디렉토리에서 정적static을 찾겠다
app.use(compression());//데이터 압축
app.use(bodyParser.urlencoded({ extended: false }));
//app.use(bodyParser.json());//사용자가 json 형식으로 body요청을 할때 사용
app.get('*', function(request, response, next)//get방식에 use로 사용되는 미들웨어
{
  fs.readdir('./data', function(error, filelist)
  {
    request.list = filelist;
    next();
  })
})

app.use('/topic', topicRouter);
///topic으로 들오오는 라우터들에게 topicRouter을 적용하겠따
app.use('/', indexRouter);


app.use(function(request, response, next){//무조건 맨 밑에 미들웨어는 순서대로 실행
  response.status(404).send('sorry cant find that!');
})
app.use(function (err, req, res, next){
  console.error(err.stack);
  res.status(500).send('Someting broke!');
})
app.listen(3000, () => console.log('App Listening on port 3000!'))
//16
