const express = require('express');
const router = express();
const db = require('../DB/db');

router.get('/group', function(req, res){
    let id = req.query.id;

    if(!id){
        res.send('plz send require elements'); 
        return;
    }

    let query = `SELECT school, room FROM parentinfo WHERE id='${id}'`;
    db.query(query, function(err, result){
        if(err){
            res.status(400).send(err);
            return;
        }
        res.send(result[0]);
    })
})