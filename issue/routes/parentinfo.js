const express = require('express');
const router = express();
const db_parent_sql = require('../public/SQL/parentinfo')();
const check_element = require('../Function/check_require_element');

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

    if(check_element.check_require_element(data_array) === false){
		res.send(element_msg);
		return;
	}
	db_parent_sql.SELECT('parentinfo', data_array, json_data, '', 0, function(err, result){//table, array, json, or_and, cnt, callback
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(result);
	})
})

router.get('/room/allinfo', function(req, res){
	let room = req.query.room;

	if(!room){
		res.send('plz send require elements');
		return;
	}

	let query = `SELECT * FROM parentinfo WHERE room = '${room}'`;
	db.query(query, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(result);
	})
})

router.post('/change/check', function(req, res){
	let id = req.body.id;
	let child_name = req.body.name;

	if(!id || !child_name){
		res.send('plz send require elements');
		return;
	}

	let query = `UPDATE parentinfo SET checked=? WHERE id=? AND child_name=?`;
	db.query(query,[true, id, child_name], function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send({res : true, msg : 'success'});
	})
})

router.post('/delete/info', function(req, res){
	let id = req.body.id;
	let child_name = req.body.name;

	if(!id || !child_name){
		res.send('plz send require elemets');
		return;
	}
	
	let query = `DELETE FROM parentinfo WHERE id=? AND child_name=?`;
	db.query(query, [id, child_name], function(err, reuslt){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send({res : true, msg : 'success'});
	})
})
module.exports = router;
