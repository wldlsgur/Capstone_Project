const express = require('express');
const router = express.Router();
const db_album_sql = require('../public/SQL/album_sql')();
const check_element = require('../Function/check_require_element');
const make_query = require('../Function/make_query');

const element_msg = "plz send require elements";
const sucess_response = {res : true, msg : 'success'};
const failed_response = {res : false, msg : "failed"};

router.get('/info', function(req, res){
    const json_data = req.query;
    if(check_element.check_require_element(json_data) === false){
        res.send(element_msg);
        return;
    }
    
    let array2 = [];
    let query1 = `SELECT distinct title, date FROM album WHERE school='${json_data.school}' AND room='${json_data.room}'`;
    db_album_sql.SELECT(query1, function(err, result){
        if(err){
            res.status(400).send(err);
            return;
        }        
        for(let i=0 ; i<Object.keys(result).length ; i++){
            let array1 = {};
            array1.title = result[i].title
            array1.date = result[i].date

            let query2 = `SELECT image_url FROM album WHERE title = ${result[i].title} AND date=${result[i].date}`;
            db_album_sql.SELECT(query2,  function(err, result){
                if(err){
                    res.status(400).send(err);
                    return;
                }
                let img_url = [];
                for(let j =0 ; j < result.length; ++j)
                {    
                    img_url.push(result[j].image_url);
                }
                array1.image_url = img_url;
            })
            console.log(array1);
            array2.push(array1);
        }
        res.status(200).json(array2);


        
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
