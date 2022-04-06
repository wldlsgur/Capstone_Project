const express = require('express');
const router = express.Router();
const db = require('../DB/db');
const db_query = require('../function/query');

router.post('/user', function(req, res){
	let data_array = [
		req.body.id,
		req.body.pw,
		req.body.name,
		req.body.job
	];
    if(!data_array[0] || !data_array[1] || !data_array[2] || !data_array[3]){
        res.send("plz send require elements");
        return;
    }
	res.send(db_query.insert('user', data_array));
});

router.post('/schoolmanagement', function(req, res){
	let data_array = [
		req.body.menu,
		req.body.school,
		req.body.room,
		req.body.title,
		req.body.content,
		req.body.month,
		req.body.year,
		req.body.day
	];
    if(!data_array[0] || !data_array[1] || !data_array[2] || !data_array[3] || !data_array[4] || !data_array[5] || !data_array[6] || !data_array[7]){
	res.send("plz send require elements");
	return;
    }
	res.send(db_query.insert('schoolmanagement', data_array));
});

router.post('/presidentinfo', function(req, res){
	let image_url = `/image/president/${req.body.image}.jpg`;
	let data_array = [
		req.body.id,
		req.body.school,
		req.body.room,
		req.body.number,
		image_url
	];
	if(!data_array[0] || !data_array[1] || !data_array[2] || !data_array[3] || !data_array[4]){
		res.send("plz send require elements");
		return;
	} 
	res.send(db_query.insert('presidentinfo', data_array));
});

router.post('/parentinfo', function(req, res){
	let image_url = `/image/parent/${req.body.image}.jpg`;
	let data_array = [
		req.body.id,
		req.body.school,
		req.body.room,
		req.body.number,
		req.body.name,
		req.body.age,
		image_url,
		req.body.spec,
		false
	];
	if(!data_array[0] || !data_array[1] || !data_array[2] || !data_array[3] || !data_array[4] || !data_array[5] || !data_array[6] || !data_array[7]){
		res.send("plz send require elements");
		return;
	}
	res.send(db_query.insert('parentinfo', data_array));
});

router.post('teacherinfo', function(req, res){
	let image_url = `/image/teacher/${req.body.image}.jpg`;
	let data_array = [
		req.body.id,
		req.body.school,
		req.body.room,
		req.body.number,
		image_url,
		false
	];
	if(!data_array[0] || !data_array[1] || !data_array[2] || !data_array[3] || !data_array[4]){
		res.send('plz send require elements');
		return;
	}
	res.send(db_query.insert('teacherinfo', data_array));
});
module.exports = router;
