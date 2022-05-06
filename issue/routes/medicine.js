const express = require('express');
const router = express.Router();
const db_medicine = require('../public/SQL/medicine_sql')();

const sucess_response = {res : true, msg : 'success'};
const failed_response = {res : false, msg : "failed"};

router.post('/insert/data', function(req, res, next) {  //일단 완료.
    let id = req.body.id;
    let child_name = req.body.child_name;
    let m_name = req.body.m_name;
    let morning = req.body.morning;
    let lunch = req.body.lunch;
    let dinner = req.body.dinner;
    let date = req.body.date;
    let mKind = req.body.mKind;
    let mSpecial = req.body.mSpecial;
    let mPlace = req.body.mPlace;
    let mAmount = req.body.mAmount;
    let mSym = req.body.mSym;

    let school = req.body.school;
    let room = req.body.room;

    db_medicine.insertMedicineInfoAll(id, child_name, m_name, morning, lunch, dinner, date, mKind, mSpecial, mPlace, mAmount, mSym, school, room,function(err,result){
         if(err){
                console.log(err);
                res.status(400).send(err);
          }
         else res.send(sucess_response);
  })
});

// medicine, medicinemanagement 통일
module.exports = router;