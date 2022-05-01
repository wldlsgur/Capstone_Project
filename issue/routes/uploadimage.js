const express = require('express');
const router = express.Router();
const db = require('../DB/db');

router.post('/uploadimage/:data', function(req, res){
		let file = req.file;

		let split_array = req.params.data.split('-');
		console.log(split_array);

		let target = split_array[0];
		let key = split_array[1];
		let value1 = split_array[2];
		let value2 = split_array[3];

		if(!file || !target || !key || !value1 || !value2){
			res.send('plz send require elements');
		}
		let file_url = `/${key}_${file.originalname}`;
		let query = ``;
		let data_array =[];
		switch(target){
			case 'parent':
				query = `UPDATE parentinfo SET image_url=? WHERE id=? AND child_name=?;`
				data_array = [
					file_url,
					key,
					value1
				];
				break;
			case 'food':
				query = `UPDATE food_list SET image_url=? WHERE school=? AND date=? AND food_image=?;`
				data_array = [
					file_url,
					value1,
					key,
					'/default'
				];
				break;
			case 'teacher':
				query = `UPDATE teachertinfo SET image_url=? WHERE id=?;`
				data_array = [
					file_url,
					key
				];
				break;
			case 'president':
				query = `UPDATE presidentinfo SET image_url=? WHERE id=?;`
				data_array = [
					file_url,
					key
				];
				break;
			case 'album':
				query = `UPDATE album SET image_url=? WHERE title=? AND school=? AND room=? AND album_image=?;`
				data_array = [
					file_url,
					key,
					value1,
					value2,
					'/default'
				];
				break;
		}
		console.log(query, data_array);
		db.query(query, data_array, function(err, result){
			if(err){
				res.status(400).send(err);
				return;
			}
			res.send({res : true, msg : 'success'});
		})
});
module.exports = router;
