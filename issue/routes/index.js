var express = require('express');
var router = express.Router ();

router.get('/', function(req, res, next){
  res.send("좆무중단서버 가나요??")  
});
module.exports = router;
