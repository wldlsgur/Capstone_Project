const express = require('express');
const router = express();
const db_user_sql = require('../public/SQL/user_sql')();
const check_element = require('../Function/check_require_element');
const make_query = require('../Function/make_query');

const element_msg = "plz send require elements";
const sucess_response = {res : true, msg : 'success'};
const failed_response = {res : false, msg : "failed"};

router.get('/info/:id', function(req, res){
	let json_data = {
		id : req.params.id
	}
	let target_array = [
		'name',
		'job'
	]
	if(check_element.check_require_element(json_data) === false){
		res.send(element_msg);
		return;
	}

	let query = make_query.SELECT(target_array, 'user', json_data, '', 0);
	db_user_sql.SELECT(query, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(result[0]);
	})
})

// router.post('/update/info', function(req, res){//같은 컬럼 정보를 가진 다른 테이블도 다 업데이트??

// })

router.post('/delete/info', function(req, res){//다른 테이블 연쇄 삭제 고려
	let json_data = {
		id : req.body.id,
	}
	if(check_element.check_require_element(json_data) === false){
		res.sned(element_msg);
		return;
	}

	let query = make_query.DELETE('user', json_data, '', 0);
	db_user_sql.DELETE(query, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(sucess_response);
	})
})

module.exports = router;
