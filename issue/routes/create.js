const express = require('express');
const router = express.Router();
const db = require('../DB/db');
const make_query = require('../function/make_query');
const response = {res : true, msg : 'success'};

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
});

router.post('/schoolmanagement', function(req, res){
	let data_array = [
		req.body.menu,
		req.body.school,
		req.body.room,
		req.body.title,
		req.body.content,
		req.body.year,
		req.body.month,
		req.body.day
	];
    if(!data_array[0] || !data_array[1] || !data_array[2] || !data_array[3] || !data_array[4] || !data_array[5] || !data_array[6] || !data_array[7]){
	res.send("plz send require elements");
	return;
    }
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
});

router.post('/food_list', function(req, res){
	let image_url = `/image/food_list/${req.body.image}.jpg`;
	let data_array = [
		req.body.school,
		req.body.year,
		req.body.month,
		image_url
	];
	if(!data_array[0] || !data_array[1] || !data_array[2] || !data_array[3]){
		res.send('plz send require elements');
		return;
	}
})

router.post('/album', function(req, res){
	let image_url = `/image/album/${req.body.image}.jpg`;
	let data_array = [
		req.body.school,
		req.body.room,
		image_url
	];
	if(!data_array[0] || !data_array[1] || !data_array[2]){
		res.send('plz send require elements');
		return;
	}
})
module.exports = router;
