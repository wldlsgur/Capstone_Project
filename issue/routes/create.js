const express = require('express');
const router = express.Router();
const db = require('./db');

router.post('/user', function(req, res){
    let id = req.body.id;
    let pw = req.body.pw;
    let name = req.body.name;

    if(!id || !pw || !name){
        res.send("plz send require elements");
        return;
    }

    let connection = db.connection;
    connection.connect();
    connection.query(`INSERT INTO user VALUES('${id}', '${pw}', '${name}')`, function(err , result){
        if(err){
            res.status(400).send(err);
            return;
        }
        res.send({res : true, msg : 'success'});
    })
});

module.exports = router;