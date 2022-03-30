const express = require('express');
const router = express.Router();

router.post('/:job/:name', function(req, res){
	res.send({res : true, msg : 'success'});
});

module.exports = router;
