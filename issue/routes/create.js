const express = require('express');
const router = express.Router();
const db_create_sql = require('../public/SQL/create_sql')();
const check_element = require('../Function/check_require_element');
const make_query = require('../../Function/make_query');

const element_msg = "plz send require elements";
const sucess_response = {res : true, msg : 'success'};
const failed_response = {res : false, msg : "failed"};

router.post('/user', function(req, res){
	let json_data = {
		id : req.body.id,
		pw : req.body.pw,
		name : req.body.name,
		job : req.body.job
	};//json 형식
	let data_array = [];
	for(key of Object.keys(json_data)){//배열 형식
		data_array.push(json_data[key]);
	}

    if(check_element.check_require_element(data_array) === false){
		res.send(element_msg);
		return;
	}
	let query = make_query.INSERT('user', json_data);
	db_create_sql.INSERT(query, data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(sucess_response);
	})
});

router.post('/schoolmanagement', function(req, res){
	let json_data = {
		menu : req.body.menu,
		school : req.body.school,
		room : req.body.room,
		title : req.body.title,
		content : req.body.content,
		date : req.body.date
	};

	let data_array = [];
	for(key of Object.keys(json_data)){//배열 형식
		data_array.push(json_data[key]);
	}

    if(check_element.check_require_element(data_array) === false){
		res.send(element_msg);
		return;
	}
	let query = make_query.INSERT('schoolmanagement', json_data);
	db_create_sql.INSERT(query, data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(sucess_response);
	})
});

router.post('/presidentinfo', function(req, res){
	let json_data = {
		id : req.body.id,
		school : req.body.school,
		room : req.body.room,
		number : req.body.number,
		image_url : '/default'
	};
	let data_array = [];
	for(key of Object.keys(json_data)){//배열 형식
		data_array.push(json_data[key]);
	}

	if(check_element.check_require_element(data_array) === false){
		res.send(element_msg);
		return;
	}
	let query = make_query.INSERT('presidentinfo', json_data);
	db_create_sql.INSERT(query, data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(sucess_response);
	})
});

router.post('/parentinfo', function(req, res){
	let json_data = {
		id : req.body.id,
		school : req.body.school,
		room : req.body.room,
		number : req.body.number,
		child_name : req.body.name,
		child_age : req.body.age,
		image_url : '/default',
		spec : req.body.spec,
		agree : 'no'
	};
	let data_array = [];
	for(key of Object.keys(json_data)){//배열 형식
		data_array.push(json_data[key]);
	}

	if(check_element.check_require_element(data_array) === false){
		res.send(element_msg);
		return;
	}
	let query = make_query.INSERT('parentinfo', json_data);
	db_create_sql.INSERT(query, data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(sucess_response);
	})
});

router.post('teacherinfo', function(req, res){
	let json_data = {
		id : req.body.id,
		school : req.body.school,
		room : req.body.room,
		number : req.body.number,
		image_url : '/default',
		agree : 'no'
	};
	let data_array = [];
	for(key of Object.keys(json_data)){//배열 형식
		data_array.push(json_data[key]);
	}

	if(check_element.check_require_element(data_array) === false){
		res.send(element_msg);
		return;
	}
	let query = make_query.INSERT('teacherinfo', json_data);
	db_create_sql.INSERT(query, data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(sucess_response);
	})
});

router.post('/food_list', function(req, res){
	let json_data = {
		schoo : req.body.school,
		date : req.body.date,
		image_url : '/default'
	};
	let data_array = [];
	for(key of Object.keys(json_data)){//배열 형식
		data_array.push(json_data[key]);
	}

	if(check_element.check_require_element(data_array) === false){
		res.send(element_msg);
		return;
	}
	let query = make_query.INSERT('food_list', json_data);
	db_create_sql.INSERT(query, data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(sucess_response);
	})
})

router.post('/album', function(req, res){
	let json_data = {
		school : req.body.school,
		room : req.body.room,
		title : req.body.title,
		date : req.body.date,
		image_url : '/default'
	};
	let data_array = [];
	for(key of Object.keys(json_data)){//배열 형식
		data_array.push(json_data[key]);
	}

	if(check_element.check_require_element(data_array) === false){
		res.send(element_msg);
		return;
	}
	let query = make_query.INSERT('album', json_data);
	db_create_sql.INSERT(query, data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(sucess_response);
	})
})
module.exports = router;
