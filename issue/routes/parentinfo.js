const express = require('express');
const router = express();
const db_parent_sql = require('../public/SQL/parentinfo_sql')();
const check_element = require('../Function/check_require_element');
const make_query = require('../Function/make_query');

const element_msg = "plz send require elements";
const sucess_response = {res : true, msg : 'success'};
const failed_response = {res : false, msg : "failed"};

router.get('/info', function(req, res){
	let json_data = {
		id : req.query.id
	}
	let data_array = [];
	for(key of Object.keys(json_data)){//배열 형식
		data_array.push(json_data[key]);
	}
	let target_array = [
		'*'
	]

    if(check_element.check_require_element(data_array) === false){
		res.send(element_msg);
		return;
	}
	let query = make_query.SELECT(target_array, 'parentinfo', json_data, '', 0);//target, table, json, or_and, cnt
	db_parent_sql.SELECT(query, data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(result);
	})
})

router.get('/room/allinfo', function(req, res){
	let json_data = {
		room : req.query.room
	}
	let data_array = [];
	for(key of Object.keys(json_data)){//배열 형식
		data_array.push(json_data[key]);
	}
	let target_array = [
		'*'
	]

	if(check_element.check_require_element(data_array) === false){
		res.send(element_msg);
		return;
	}
	let query = make_query.SELECT(target_array, 'parentinfo', json_data, '', 0);
	db_parent_sql.SELECT(query, data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(result);
	})
})

router.post('/change/check', function(req, res){
	let json_data = {
		id : req.body.id,
		child_name : req.body.name
	}
	let data_array = [];
	for(key of Object.keys(json_data)){//배열 형식
		data_array.push(json_data[key]);
	}

	if(check_element.check_require_element(data_array) === false){
		res.send(element_msg);
		return;
	}
	let target = 'agree=yes';
	let query = make_query.UPDATE(target, 'parentinfo', json_data, 'AND', 1);
	db_parent_sql.UPDATE(query, data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(sucess_response);
	})
})

router.post('/delete/info', function(req, res){
	let json_data = {
		id : req.body.id,
		child_name : req.body.name
	}
	let data_array = [];
	for(key of Object.keys(json_data)){//배열 형식
		data_array.push(json_data[key]);
	}

	if(check_element.check_require_element(data_array) === false){
		res.send(element_msg);
		return;
	}
	
	let query = make_query.DELETE('parentinfo', json_data, 'AND', 1);
	db_parent_sql.DELETE(query, data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(sucess_response);
	})
})
module.exports = router;
