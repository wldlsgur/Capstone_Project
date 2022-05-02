const express = require('express');
const router = express.Router();

const element_msg = "plz send require elements";
const sucess_response = {res : true, msg : 'success'};
const failed_response = {res : false, msg : "failed"};

router.post('/uploadimage', function(req, res){
		let file = req.file;
		let target = req.body.target;
		let key = req.body.key;

		if(!file || !target || !key){
			res.send(element_msg);
			return;
		}
		res.send(sucess_response);
});
module.exports = router;
