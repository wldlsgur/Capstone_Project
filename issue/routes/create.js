const express = require('express');
const router = express.Router();
const db = require('../DB/db');
const make_query = require('../Function/make_query');
const response = {res : true, msg : 'success'};

router.post('/user', function(req, res){
	let data_array = [
		req.body.id,
		req.body.pw,
		req.body.name,
		req.body.job
	];
    if(!data_array[0] || !data_array[1] || !data_array[2] || !data_array[3]){
        res.send("plz send require elements");
        return;
    }
	db.query(make_query.INSERT('user', data_array), data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(response);
	})
});

router.post('/schoolmanagement', function(req, res){
	let data_array = [
		req.body.menu,
		req.body.school,
		req.body.room,
		req.body.title,
		req.body.content,
		req.body.date
	];
    if(!data_array[0] || !data_array[1] || !data_array[2] || !data_array[3] || !data_array[4] || !data_array[5]){
		res.send("plz send require elements");
		return;
    }
	db.query(make_query.INSERT('schoolmanagement', data_array), data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(response);
	})
});

router.post('/presidentinfo', function(req, res){
	let data_array = [
		req.body.id,
		req.body.school,
		req.body.room,
		req.body.number,
		'default'
	];
	if(!data_array[0] || !data_array[1] || !data_array[2] || !data_array[3] || !data_array[4]){
		res.send("plz send require elements");
		return;
	}
	db.query(make_query.INSERT('presidentinfo', data_array), data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(response);
	})
});

router.post('/parentinfo', function(req, res){
	let data_array = [
		req.body.id,
		req.body.school,
		req.body.room,
		req.body.number,
		req.body.name,
		req.body.age,
		'default',
		req.body.spec,
		false
	];
	if(!data_array[0] || !data_array[1] || !data_array[2] || !data_array[3] || !data_array[4] || !data_array[5] || !data_array[6] || !data_array[7]){
		res.send("plz send require elements");
		return;
	}
	db.query(make_query.INSERT('parentinfo', data_array), data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(response);
	})
});

router.post('teacherinfo', function(req, res){
		let data_array = [
		req.body.id,
		req.body.school,
		req.body.room,
		req.body.number,
		'default',
		false
	];
	if(!data_array[0] || !data_array[1] || !data_array[2] || !data_array[3] || !data_array[4]){
		res.send('plz send require elements');
		return;
	}
	db.query(make_query.INSERT('teacherinfo', data_array), data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(response);
	})
});

router.post('/food_list', function(req, res){
	let data_array = [
		req.body.school,
		req.body.date,
		'default'
	];
	if(!data_array[0] || !data_array[1] || !data_array[2]){
		res.send('plz send require elements');
		return;
	}
	db.query(make_query.INSERT('food_list', data_array), data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(response);
	})
})

router.post('/album', function(req, res){
	let data_array = [
		req.body.school,
		req.body.room,
		req.body.title,
		req.body.date,
		'default'
	];
	if(!data_array[0] || !data_array[1] || !data_array[2] || !data_array[3] || !data_array[4]){
		res.send('plz send require elements');
		return;
	}
	db.query(make_query.INSERT('album', data_array), data_array, function(err, result){
		if(err){
			res.status(400).send(err);
			return;
		}
		res.send(response);
	})
})
module.exports = router;
