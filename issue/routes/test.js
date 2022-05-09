const express = require('express');
const router = express.Router();

const db_test = require('../public/SQL/test_sql')();

const success_response = {res : true, msg : 'success'};
const failed_response = {res : false, msg : "failed"};


router.post('/test', function(req, res, next) {

    let id='aa';
    let school='aa';
    let room='aa';
    let number='aa';
    var date = new DateTime.now();
    var newDate = new DateTime(date.year, date.month, date.day+1);

    db_test.test(id, school, room, number, newDate,function(err,result){
         if(err){
                console.log(err);
                res.status(400).send(err);
          }
         else res.send(success_response);
  })
});


module.exports = router;