var express = require('express');
var router = express.Router ();

router.get('/', function(req, res, next){
  res.send("무중단test40!!")  
});
module.exports = router;
