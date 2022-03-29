const express = require('express');
const router = express.Router();
const db = require('../DB/db');

router.get('/info', function(req, res){
	let menu = req.query.menu;
	let school = req.query.school;
	let room = req.query.room;

	if(!menu || !school || !room){
		res.send("plz send require elements!");
		return;
	}
	let query = `SELECT * FROM schoolmanagement WHERE menu=? AND school=? AND room=?`;
	db.query(query,[menu,school,room], function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(result);
	});
});
module.exports = router;
