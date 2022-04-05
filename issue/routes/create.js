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
    let room = req.body.room;
    let title = req.body.title;
    let content = req.body.content;
    let month = req.body.month;
    let year = req.body.year;
    let day = req.body.day;
   
    if(!menu || !school || !room || !title || !content || !month || !year || !day){
	res.send("plz send require elements");
	return;
    }

    db.query(`INSERT INTO schoolmanagement VALUES('${menu}', '${school}', '${room}', '${title}', '${content}', '${month}', '${year}', '${day}')`, function(err, result){
	if(err){
	    res.status(400).send(err);
	    return;
	}
	res.send({res : true, msg : 'success'});
    })
});

router.post('/presidentinfo', function(req, res){
	let id = req.body.id;
	let school = req.body.school;
	let room = req.body.room;
	let presi_num = req.body.number;
	let presi_image = req.body.image;
	
	if(!id || !school || !room || !presi_num || !presi_image){
		res.send("plz send require elements");
		return;
	} 
	let query = `INSERT INTO presidentinfo VALUES('${id}', '${school}', '${room}', '${presi_num}', '${presi_image}')`;
	db.query(query, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send({res : true, msg : 'success'});
	})
});

router.post('/parentinfo', function(req, res){
	let id = req.body.id;
	let school = req.body.school;
	let room = req.body.room;
	let parent_num = req.body.number;
	let child_name = req.body.name;
	let child_age = req.body.age;
	let child_image = req.body.image;
	let spec = req.body.spec;

	if(!id || !room || !parent_num || !child_name || !child_age || !child_image || !spec){
		res.send("plz send require elements");
		return;
	}
	
	let query = `INSERT INTO parentinfo VALUES('${id}', '${school}', '${room}', '${parent_num}', '${child_name}', '${child_age}', '${child_image}', '${spec}', false)`;
	db.query(query, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send({res : true, msg : 'success'});
	})
});

router.post('teacherinfo', function(req, res){
	let id = req.body.id;
	let school = req.body.school;
	let room = req.body.room;
	let teacher_num = req.body.number;
	let teacher_image = req.body.image;

	if(!id || !school || !room || !teacher_num || !teacher_image){
		res.send('plz send require elements');
		return;
	}

	let query = `INSERT INTO teacherinfo VALUES('${id}', '${school}', '${room}', '${teacher_num}', '${teacher_image}', false)`;
	db.query(query, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send({res : true, msg : 'success'});
	})
});
module.exports = router;
