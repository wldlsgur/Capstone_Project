const express = require('express');
const router = express.Router();

const db_staff = require('../public/SQL/staff_sql')();

const sucess_response = {res : true, msg : 'success'};
const failed_response = {res : false, msg : "failed"};
//  teacherinfo

router.get('/teacherinfo/useschool', function(req, res, next) {
  db_staff.get_teacherinfo_use_school(req.query.school,function(err,result){
    if(err){
        console.log(err);
        res.status(400).send(err);
    }
    else res.send(result);
  })
});
router.get('/teacherinfo/useid', function(req, res, next) {
  db_staff.get_teacherinfo_use_id(req.query.id,function(err,result){
    if(err){
        console.log(err);
        res.status(400).send(err);
    }
    else res.send(result);
  })
})

//  teacherinfo end
//  presidentinfo
router.get('/presidentinfo/useid', function(req, res, next) {
  db_staff.get_presidentinfo_use_id(req.query.id,function(err,result){
    if(err){
        console.log(err);
        res.status(400).send(err);
    }
    else res.send(result);
  })
})
router.get('/test', function(req, res, next) {
  db_staff.test(id,function(err,result){
    if(err){
        console.log(err);
        res.status(400).send(err);
    }
    else res.send(result);
  })
})

//  presidentinfo end


module.exports = router;