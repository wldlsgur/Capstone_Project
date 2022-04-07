const express = require('express');
const router = express();
const db = require('../DB/db');

router.get('/info/:id', function(req, res){
	let id = req.params.id;

	if(!id){
		res.send('plz send require elemets');
		return;
	}

	let query = `SELECT name, job FROM user WHERE id='${id}'`;
	db.query(query, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(result[0]);
	})
})

module.exports = router;
