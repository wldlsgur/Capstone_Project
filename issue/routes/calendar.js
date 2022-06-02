const express = require('express');
const router = express.Router();

const db_calendar = require('../public/SQL/calendar_sql')();

const sucess_response = {res : true, msg : 'success'};
const failed_response = {res : false, msg : "failed"};

router.post('/insertCalendarInfo', function(req, res, next) {  
    let id = req.body.id;
    let calGroup = req.body.calGroup;
    let title = req.body.title;
    let content = req.body.content;
    let startDate = req.body.startDate;
    let endDate = req.body.endDate;
    let startTime = req.body.startTime;
    let endTime = req.body.endTime;
    let color = req.body.color;

    db_calendar.insertCalendarInfo(id, calGroup, title, content, startDate, endDate, startTime, endTime , color,function(err,result){
         if(err){
                console.log(err);
                res.status(400).send(err);
          }
         else res.send(sucess_response);
  })
})

router.get('/selectCalendarInfo', function(req, res, next) {    //calGroup, title, date(년, 월 만줌)
    let calGroup = req.query.calGroup;
    let title = req.query.title;
    let date = req.query.date;

    db_calendar.selectCalendarInfo(calGroup, title, date, function(err,result){
    if(err){
        console.log(err);
        res.status(400).send(err);
    }
    else res.send(result);
  })
})

router.post('/updateCalendarInfo', function(req, res){  //id, calGroup, title, content, startDate, endDate, startTime, endTime , color
    let id = req.body.id;
    let calGroup = req.body.calGroup;
    let title = req.body.title;
    let content = req.body.content;
    let startDate = req.body.startDate;
    let endDate = req.body.endDate;
    let startTime = req.body.startTime;
    let endTime = req.body.endTime;
    let color = req.body.color;
  
    db_medicine.deleteMedicineInfo(id, calGroup, title, content, startDate, endDate, startTime, endTime , color, function(err,result){
      if(err){
        res.status(400).send(err);
      } else{
        res.send(sucess_response);
      }
    })
  })

  router.post('/deleteCalendarInfo', function(req, res){    //id, calGroup, title, content
    let id = req.body.id;
    let calGroup = req.body.calGroup;
    let title = req.body.title;
    let content = req.body.content;

    db_medicine.deleteMedicineInfo(id, calGroup, title, content, function(err,result){
      if(err){
        res.status(400).send(err);
      } else{
        res.send(sucess_response);
      }
    })
  })


module.exports = router;