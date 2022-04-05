const express = require('express');
const router = express();
const db = require('../DB/db');

router.get('/info', function(req, res){
    let id = req.query.id;

    if(!id){
        res.send('plz send require elements'); 
        return;
    }

    let query = `SELECT * FROM parentinfo WHERE id='${id}'`;
    db.query(query, function(err, result){
        if(err){
            res.status(400).send(err);
            return;
        }
        res.send(result);
    })
})

router.get('/school/allinfo', function(req, res){
	let school = req.query.school;

	if(!school){
		res.send('plz send require elements');
		return;
	}

	let query = `SELECT * FROM parentinfo WHERE school = '${school}'`;
	db.query(query, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(result);
	})
})

router.post('/change/check', function(req, res){
	let id = req.body.id;
	let child_name = req.body.name;

	if(!id || !child_name){
		res.send('plz send require elements');
		return;
	}

	let query = `UPDATE parentinfo SET checked=? WHERE id=? AND child_name=?`;
	db.query(query,[true, id, child_name], function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send({res : true, msg : 'success'});
	})
})

router.post('/delete/info', function(req, res){
	let id = req.body.id;
	let child_name = req.body.name;

	if(!id || !child_name){
		res.send('plz send require elemets');
		return;
	}
	
	let query = `DELETE FROM parentinfo WHERE id=? AND child_name=?`;
	db.query(query, [id, child_name], function(err, reuslt){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send({res : true, msg : 'success'});
	})
})
module.exports = router;
