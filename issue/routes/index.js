var express = require('express');
var router = express.Router ();

router.get('/', function(req, res, next){
  res.send("술 한단 기기?")  
});
module.exports = router;
