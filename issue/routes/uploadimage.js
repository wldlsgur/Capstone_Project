const express = require('express');
const router = express.Router();
const db = require('../DB/db');

router.post('/:target/:key/:value1/value2', function(req, res){
	let file = req.file;
	let target = req.params.target;
	let key = req.params.key;
	let value1 = req.params.value1;
	let value2 = req.params.value2;

	if(!file || !target || !key || !value1 || !value2){
		res.send('plz send require elements');
	}
	let file_url = `${key}_${file.originalname}`;
	console.log(file_url);
	let query = ``;
	switch(target){
		case '부모님':
			query = `UPDATE parentinfo SET cihld_image='${file_url}' WHERE id='${key}' AND child_name='${value1}';`
			break;
		case '식단표':
			query = `UPDATE food_list SET food_image='${file_url}' WHERE school='${value1}' AND date='${key}';`
			break;
		case '선생님':
			query = `UPDATE teachertinfo SET teacher_image='${file_url}' WHERE id='${key}';`
			break;
		case '원장님':
			query = `UPDATE presidentinfo SET presi_image='${file_url}' WHERE id='${key}';`
			break;
		case '앨범':
			query = `UPDATE album SET album_image='${file_url}' WHERE title='${key}' AND school='${value1}' AND room='${value2}';`
			break;
	}
	console.log(query);
	db.query(query, function(err, result){
		if(err){
			res.status(400).send(err);
		}
		res.send({res : true, msg : 'success'});
	})
});
module.exports = router;
