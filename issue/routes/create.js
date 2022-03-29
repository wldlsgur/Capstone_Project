const express = require('express');
const router = express.Router();
const db = require('../DB/db');

router.post('/user', function(req, res){
    let id = req.body.id;
    let pw = req.body.pw;
    let name = req.body.name;
    let job = req.body.job;

    if(!id || !pw || !name || !job){
        res.send("plz send require elements");
        return;
    }

    db.query(`INSERT INTO user VALUES('${id}', '${pw}', '${name}', '${job}')`, function(err , result){
        if(err){
            res.status(400).send(err);
            return;
        }
        res.send({res : true, msg : 'success'});
    })
});

router.post('/schoolmanagement', function(req, res){
    let menu = req.body.menu;
    let school = req.body.school;
    let group = req.body.group;
    let title = req.body.title;
    let content = req.body.content;
    let month = req.body.month;
    let year = req.body.year;
    let day = req.body.day;
    console.log(menu, school, group, title, content, month, year, day);
    if(!menu || !school || !group || !title || !content || !month || !year || !day){
	res.send("plz send require elements");
	return;
    }

    db.query(`INSERT INTO schoolmanagement VALUES('${menu}', '${school}', '${group}', '${title}', '${content}', '${month}', '${year}', '${day}')`, function(err, result){
	if(err){
	    res.status(400).send(err);
	    return;
	}
	res.send({res : true, msg : 'success'});
    })
});

module.exports = router;
