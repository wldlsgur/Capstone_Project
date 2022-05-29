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
router.get('/updateTeacherinfoAgree', function(req, res, next) {

  let keyId = req.query.keyId;

  db_staff.updateTeacherinfoAgree(keyId,function(err,result){
    if(err){
        console.log(err);
        res.status(400).send(err);
    }
    else res.send(sucess_response);
  })
})
router.get('/deleteTeacherinfo', function(req, res, next) {

  let keyId = req.query.keyId;

  db_staff.deleteTeacherinfo(keyId,function(err,result){
    if(err){
        console.log(err);
        res.status(400).send(err);
    }
    else res.send(sucess_response);
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


//  presidentinfo end


module.exports = router;