const express = require('express');
const router = express.Router();
const db_check_sql = require('../public/SQL/check_sql')();
const check_element = require('../Function/check_require_element');
const make_query = require('../Function/make_query');

const element_msg = "plz send require elements";
const sucess_response = {res : true, msg : 'success'};
const failed_response = {res : false, msg : "failed"};

router.get('/login', function(req, res){
    let json_data = {
        id : req.query.id
    }
    let pw = req.query.pw;
	let target_array = [
		'*'
	]

    if(check_element.check_require_element(json_data) === false && !pw){
        res.send(element_msg);
        return;
    }
	let query = make_query.SELECT(target_array, 'user', json_data, '', 0);//target, table, json, or_and, cnt
    db_check_sql.SELECT(query, function(err, result){
        if(err){
            res.status(400).send(err);
            return;
        }
        if(!result[0]){
            res.send({res : false, msg : "not found"});
            return;
        }
        if(result[0].pw === pw){
            res.send(sucess_response);
        }
        else{
            res.send(failed_response);
        }
    })
});

router.get('/sameid', function(req, res){
    let json_data = {
        id : req.query.id
    };
	let target_array = [
		'*'
	]

    if(check_element.check_require_element(json_data) === false){
        res.send(element_msg);
        return;
    }
    let query = make_query.SELECT(target_array, 'user', json_data, '', 0);//target, table, json, or_and, cnt
    db_check_sql.SELECT(query, function(err, result){
        if(err){
            res.status(400).send(err);
            return;
        }
        if(!result[0]){
            res.send({res : false, msg : 'not found'});
            return;
        }
        res.send({res : true, msg : 'found'});
    })
})
module.exports = router;
