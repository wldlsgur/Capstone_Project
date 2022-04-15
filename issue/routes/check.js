const express = require('express');
const router = express.Router();
const db_check_sql = require('../public/SQL/check_sql')();
const check_element = require('../Function/check_require_element');

const element_msg = "plz send require elements";
const sucess_response = {res : true, msg : 'success'};
const failed_response = {res : false, msg : "failed"};

router.get('/login', function(req, res){
    let data_array = [
        req.query.id,
        req.query.pw
    ]
    if(check_element.check_require_element(data_array) === false){
        res.send(element_msg);
        return;
    }

    db_check_sql.get_login_check(data_array, function(err, result){
        if(err){
            res.status(400).send(err);
            return;
        }
        if(!result[0]){
            res.send({res : false, msg : "not found"});
            return;
        }
        if(result[0].pw === data_array[1]){
            res.send(sucess_response);
        }
        else{
            res.send(failed_response);
        }
    })
});

router.get('/sameid', function(req, res){
    let data_array = [
        req.query.id
    ];

    if(check_element.check_require_element(data_array) === false){
        res.send(element_msg);
        return;
    }
    db_check_sql.get_sameid_check(data_array, function(err, result){
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
