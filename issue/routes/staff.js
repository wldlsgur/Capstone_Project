const express = require('express');
const router = express.Router();

var db_staff = require('../public/SQL/staff_sql')();
// teacherinfo, , presidentinfo 통일

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

router.get('/presidentinfo/useid', function(req, res, next) {
  db_staff.get_presidentinfo_use_id(req.query.id,function(err,result){
    if(err){
        console.log(err);
        res.status(400).send(err);
    }
    else res.send(result);
  })
})


module.exports = router;