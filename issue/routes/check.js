const express = require('express');
const router = express.Router();
const db = require('./db');

router.get('/login', function(req, res){
    let id = req.query.id;
    let pw = req.query.pw;

    if(!id || !pw){
        res.send("plz send require element!");
        return;
    }

    let connection = db.connection ();
    connection.connect ();
    connection.query(`SELECT pw FROM user WHERE id='${id}'`, function(err, result){
        if(err){
            res.send(err);
            return;
        }
        if(!result[0]){
            res.send({res : false, msg : "존재하지 않는 아이디입니다"});
            return;
        }
        if(result[0].pw === pw){
            res.send({res : true, msg : "loginsuccess"});
        }
        else{
            res.send({res : false, msg : "비밀번호가 틀렸습니다"});
        }
    })
});
module.exports = router;
