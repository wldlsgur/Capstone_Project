const express = require('express');
const router = express.Router();
const db_album_sql = require('../public/SQL/album_sql')();
const check_element = require('../Function/check_require_element');
const make_query = require('../Function/make_query');

const element_msg = "plz send require elements";
const sucess_response = {res : true, msg : 'success'};
const failed_response = {res : false, msg : "failed"};

router.get('/image_url', function(req, res){
    let json_data = {
        school : req.query.school,
        room : req.query.room
    }
    let data_array = [];
    for(key of Object.keys(json_data)){//배열 형식
		data_array.push(json_data[key]);
	}
	let target_array = [
		'*'
	]

    if(check_element.check_require_element(data_array) === false){
        res.send(element_msg);
        return;
    }

    let query = make_query.SELECT(target_array, 'album', json_data, 'AND', 1);
    db_food_list_sql.SELECT(query, data_array, function(err, result){
        if(err){
            res.status(400).send(err);
            return;
        }
        res.send(result[0]);
    })
})
module.exports = router;
