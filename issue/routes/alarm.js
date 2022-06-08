const express = require('express');
const router = express.Router();

const sucess_response = {res : true, msg : 'success'};
const failed_response = {res : false, msg : "failed"};

const db_alarm = require('../public/SQL/alarm_sql')();

//const db_ = require('../public/SQL/medicine_sql')();

const admin = require('firebase-admin')

let serAccount = require('../firebase-pk.json')

admin.initializeApp({
  credential: admin.credential.cert(serAccount),
})

router.get('/push_send', function (req, res, next) {
  let aaa;
    
  db_alarm.selectTokenTest(function(err,result){
    if(err){
        console.log(err);
        res.status(400).send(err);
    }
    else{
      aaa = result[0].token;
      res.send(sucess_response);
    } 
  })

    let target_token = aaa;
      //target_token은 푸시 메시지를 받을 디바이스의 토큰값입니다
  
    let message = {
      notification: {
        title: '테스트 데이터 발송',
        body: '데이터가 잘 가나요?'
      },
      token: target_token,
      android: {
        priority: "high"
      }
    }

    console.log(target_token);

    admin
      .messaging()
      .send(message)
      .then(function (response) {
        console.log('Successfully sent message: : ', response)
        res.send(sucess_response);
      })
      .catch(function (err) {
        console.log('Error Sending message!!! : ', err)
        res.send(failed_response);
      })
  })

  module.exports = router;