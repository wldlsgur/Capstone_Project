const express = require('express');
const router = express.Router();

var db_staff = require('../public/SQL/staff_sql')();
// teacherinfo, , presidentinfo 통일

router.get('/gain/teacherinfo', function(req, res, next) {
  db_user.get_teacherinfo(req.query.school,function(err,result){
    if(err) console.log(err);
    else res.send(result);
  })
});

router.get('/gain/presidentinfo', function(req, res, next) {
  db_user.get_presidentinfo(req.query.id,function(err,result){
    if(err) console.log(err);
    else res.send(result);
  })
})


module.exports = router;