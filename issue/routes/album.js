const express = require('express');
const router = express.Router();
const db_album_sql = require('../public/SQL/album_sql')();
const check_element = require('../Function/check_require_element');
const make_query = require('../Function/make_query');

const element_msg = "plz send require elements";
const sucess_response = {res : true, msg : 'success'};
const failed_response = {res : false, msg : "failed"};

router.get('/info', function(req, res){
    let json_data = {
        school : req.query.school,
        room : req.query.room
    }

    if(check_element.check_require_element(json_data) === false){
        res.send(element_msg);
        return;
    }

    let a = [];
    a.push
    let query = `SELECT title, date, image_url FROM album WHERE school='${school}' AND room='${room}'`;
    db_album_sql.SELECT(query, function(err, result){
        if(err){
            res.status(400).send(err);
            return;
        }
        console.log(result);
        res.send(result);
    })
})

router.get('/image_url', function(req, res){
    let json_data = {
        school : req.query.school,
        room : req.query.room,
        title : req.query.title,
        date : req.query.date
    }
    let target_array = [
        'image_url'
    ]
    
    if(check_element.check_require_element(json_data) === false){
        res.send(element_msg);
        return;
    }

    let query = make_query.SELECT(target_array, 'album', json_data, 'AND', 2);
    db_album_sql.SELECT(query, function(err, result){
        if(err){
            res.status(400).send(err);
        }
        res.send(result);
    })
})

module.exports = router;
