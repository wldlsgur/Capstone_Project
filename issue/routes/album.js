const express = require('express');
const res = require('express/lib/response');
const router = express.Router();
const Promise = require('promise');
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

    let array1 = {};
    let array2 = [];
    let query1 = `SELECT distinct title, date FROM album WHERE school='${json_data.school}' AND room='${json_data.room}'`;
    db_album_sql.SELECT(query1, async function(err, result){
        if(err){
            return res.status(400).send(err);
        }
        for(let i=0 ; i<result.length ; i++){
            array1 = {};
            array1.title = result[i].title;
            array1.date = result[i].date;
            let query2 = `SELECT image_url FROM album WHERE school='${json_data.school}' AND room='${json_data.room}' AND title = '${result[i].title}' AND date='${result[i].date}'`;
            console.log(`첫번째 : ${i}`, array1);
            await job2(query2, i , result.length); 
        }
        console.log("최종",array2);
        res.status(200).send(array2);
    })
    function job2(query2, i, length){
        return new Promise(function(resolve, rejected){
            db_album_sql.SELECT(query2, function(err, result){
                if(err){
                    rejected(err);
                }
                let img_url = [];
                for(let j =0 ; j < result.length; j++)
                {
                    img_url.push(result[j].image_url);
                }
                array1.image_url = img_url;
                console.log(`두번째`, array1);
                array2.push(array1);
                resolve(i, length);
            })
        })
        .then(function(i, length){
            if(i === length) res.status(200).send(array2);
        })
        .catch(function(err){
            res.status(400).send(err);
        })
    }
    // function job1 (){
    //     return new Promise(function(resolve, rejected){
    //         db_album_sql.SELECT(query1, function(err, result){
    //             if(err){
    //                 rejected(err);
    //             }
    //             resolve(result);
    //         })
    //     })
    // }          
    // function job2 (query2){
    //     return new Promise(function(resolve, rejected){
    //         db_album_sql.SELECT(query2, function(err, result){
    //             if(err){
    //                 rejected(err);
    //             }
    //             resolve(result);
    //         })
    //     })
    // }
    // job1()
    // .then(function(result){
    //     for(let i=0 ; i<result.length ; i++){
    //         array1 = {};
    //         array1.title = result[i].title;
    //         array1.date = result[i].date;
    //         let query2 = `SELECT image_url FROM album WHERE school='${json_data.school}' AND room='${json_data.room}' AND title = '${result[i].title}' AND date='${result[i].date}'`;
    //         console.log(`첫번째 : ${i}`, array1);

    //         return job2(query2);
    //     }
    // })
    // .then(function(result){
    //     let img_url = [];
    //     for(let j =0 ; j < result.length; j++)
    //     {
    //         img_url.push(result[j].image_url);
    //     }
    //     array1.image_url = img_url;
    //     console.log(`두번째`, array1);
    //     array2.push(array1);
    //     return;
    // })
    // .then(function(){
    //     console.log("최종",array2);
    //     res.status(200).send(array2);
    // })
    // .catch(function(err){
    //     console.log(err)
    //     res.status(400).send(err);
    // })
})
module.exports = router;
