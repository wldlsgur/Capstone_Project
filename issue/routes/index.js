var express = require('express');
var router = express.Router ();
var db = require ('./db');

/* GET home page. */
router.get('/', function(req, res, next) {
var connection = db.connection ();
            connection.connect ();
            connection.query ('SELECT * FROM user',
                              function (err, rows, fields)
                              {
                              if (err) throw err;
                              console.log ('The user is: ', rows[0]);
                              var jsonData =
                              {
                                title: 'MariaDB',
                                user:  rows};
                              res.render ('index',
                                          jsonData);});
            connection.end ();}

);

module.exports = router;
