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
	let target_array = [
		'*'
	]

    if(check_element.check_require_element(json_data) === false){
		res.send(element_msg);
		return;
	}
	let query = make_query.SELECT(target_array, 'parentinfo', json_data, '', 0);//target, table, json, or_and, cnt
	db_parent_sql.SELECT(query, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(result);
	})
})

router.get('/child/info', function(req, res){
	let json_data = {
		id : req.query.id,
		child_name : req.query.name
	}
	let target_array = [
		'*'
	]

	if(check_element.check_require_element(json_data) === false){
		res.send(element_msg);
		return;
	}

	let query = make_query.SELECT(target_array, 'parentinfo', json_data, 'AND', 1);
	db_parent_sql.SELECT(query, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(result[0]);
	})
})

router.get('/room/allinfo', function(req, res){
	let json_data = {
		school : req.query.school,
		room : req.query.room
	}
	let target_array = [
		'*'
	]

	if(check_element.check_require_element(json_data) === false){
		res.send(element_msg);
		return;
	}
	let query = make_query.SELECT(target_array, 'parentinfo', json_data, 'AND', 1);
	db_parent_sql.SELECT(query, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(result);
	})
})
router.get('/agreelist', function(req, res){
	let json_data = {
		school : req.query.school,
		room : req.query.room,
		agree : 'no'
	}
	let target_array = [
		'*'
	];

	if(check_element.check_require_element(json_data) === false){
		res.send(element_msg);
		return;
	}

	let query = make_query.SELECT(target_array, 'parentinfo', json_data, 'AND', 2);
	db_parent_sql.SELECT(query, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(result);
	})
})
router.post('/change/check', function(req, res){
	let json_data = {
		key_id : req.body.key_id
	}

	if(check_element.check_require_element(json_data) === false){
		res.send(element_msg);
		return;
	}
	let target = 'agree="yes"';
	let query = make_query.UPDATE(target, 'parentinfo', json_data, '', 0);
	db_parent_sql.UPDATE(query, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(sucess_response);
	})
})

router.post('/delete/info', function(req, res){
	let json_data = {
		key_id : req.body.key_id
	}

	if(check_element.check_require_element(json_data) === false){
		res.send(element_msg);
		return;
	}
	
	let query = make_query.DELETE('parentinfo', json_data, '', 0);
	db_parent_sql.DELETE(query, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(sucess_response);
	})
})
module.exports = router;
