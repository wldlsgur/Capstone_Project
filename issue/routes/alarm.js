const express = require('express');
const router = express.Router();

//const db_ = require('../public/SQL/medicine_sql')();

const admin = require('firebase-admin')

let serAccount = require('../firebase-pk.json')

admin.initializeApp({
  credential: admin.credential.cert(serAccount),
})

router.get('/push_send', function (req, res, next) {
    let target_token =
      'dqpk3CdhS0eDe098F4Z3tQ:APA91bE4x3sB_ajXJpsgYdZd6wehYsxQYwg8G-r07mGGn284qnFQKmlQYIbBOygEM7RoeuG4lGjRVNryeAJog-mG9sbBBwjbGdaVWD_lBljPfP9EAyuvTT2fe8OVSy6GEj-uSvhLTw7v'
      //target_token은 푸시 메시지를 받을 디바이스의 토큰값입니다
  
    let message = {
      data: {
        title: '테스트 데이터 발송',
        body: '데이터가 잘 가나요?',
        style: '굳굳',
      },
      token: target_token,
    }
  
    admin
      .messaging()
      .send(message)
      .then(function (response) {
        console.log('Successfully sent message: : ', response)
      })
      .catch(function (err) {
        console.log('Error Sending message!!! : ', err)
      })
  })

  module.exports = router;