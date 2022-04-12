const express = require('express');
const router = express.Router();
const db = require('../DB/db');

router.post('/:target/:key/:value1/:value2', function(req, res){
	res.send({res : true, msg : 'success'});
});
module.exports = router;
