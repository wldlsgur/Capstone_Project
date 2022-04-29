const express = require('express');
const router = express.Router();
const db_create_sql = require('../public/SQL/create_sql')();
const check_element = require('../Function/check_require_element');

const element_msg = "plz send require elements";
const sucess_response = {res : true, msg : 'success'};
const failed_response = {res : false, msg : "failed"};

router.post('/user', function(req, res){
	let data_array = [];
	let json_data = {
		id : req.body.id,
		pw : req.body.pw,
		name : req.body.name,
		job : req.body.job
	};

	for(key of Object.keys(json_data)){
		data_array.push(json_data[key]);
	}

    if(check_element.check_require_element(data_array) === false){
		res.send(element_msg);
		return;
	}
	db_create_sql.insert_query('user', data_array, json_data, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(sucess_response);
	})
});

router.post('/schoolmanagement', function(req, res){
	let data_array = [
		req.body.menu,
		req.body.school,
		req.body.room,
		req.body.title,
		req.body.content,
		req.body.date
	];
    if(check_element.check_require_element(data_array) === false){
		res.send(element_msg);
		return;
	}
	db_create_sql.insert_query('schoolmanagement', data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(sucess_response);
	})
});

router.post('/presidentinfo', function(req, res){
	let data_array = [
		req.body.id,
		req.body.school,
		req.body.room,
		req.body.number,
		'/default'
	];
	if(check_element.check_require_element(data_array) === false){
		res.send(element_msg);
		return;
	}
	db_create_sql.insert_query('presidentinfo', data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(sucess_response);
	})
});

router.post('/parentinfo', function(req, res){
	let data_array = [
		req.body.id,
		req.body.school,
		req.body.room,
		req.body.number,
		req.body.name,
		req.body.age,
		'/default',
		req.body.spec,
		false
	];
	if(check_element.check_require_element(data_array) === false){
		res.send(element_msg);
		return;
	}
	db_create_sql.insert_query('parentinfo', data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(sucess_response);
	})
});

router.post('teacherinfo', function(req, res){
		let data_array = [
		req.body.id,
		req.body.school,
		req.body.room,
		req.body.number,
		'/default',
		false
	];
	if(check_element.check_require_element(data_array) === false){
		res.send(element_msg);
		return;
	}
	db_create_sql.insert_query('teacherinfo', data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(sucess_response);
	})
});

router.post('/food_list', function(req, res){
	let data_array = [
		req.body.school,
		req.body.date,
		'/default'
	];
	if(check_element.check_require_element(data_array) === false){
		res.send(element_msg);
		return;
	}
	db_create_sql.insert_query('food_list', data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(sucess_response);
	})
})

router.post('/album', function(req, res){
	let data_array = [
		req.body.school,
		req.body.room,
		req.body.title,
		req.body.date,
		'/default'
	];
	if(check_element.check_require_element(data_array) === false){
		res.send(element_msg);
		return;
	}
	db_create_sql.insert_query('album', data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(sucess_response);
	})
})
module.exports = router;
