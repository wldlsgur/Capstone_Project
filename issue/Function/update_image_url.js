const db = require('../DB/db');

const element_msg = "plz send require elements";
const sucess_response = {res : true, msg : 'success'};
const failed_response = {res : false, msg : "failed"};

module.exports = {
    update_image_url : function(target, key, file_url){
		let query = ``;
		switch(target){
			case 'parent':
				query = `UPDATE parentinfo SET image_url='/${file_url}' WHERE key_id = '${key}';`
				break;
			case 'food':
				query = `UPDATE food_list SET image_url='/${file_url}' WHERE key_id = '${key}';`
				break;
			case 'teacher':
				query = `UPDATE teachertinfo SET image_url='/${file_url}' WHERE key_id = '${key}';`
				break;
			case 'president':
				query = `UPDATE presidentinfo SET image_url='/${file_url}' WHERE key_id = '${key}';`
				break;
			case 'album':
				query = `UPDATE album SET image_url='/${file_url}' WHERE key_id = '${key}';`
				break;
			default :
				break;
		}
        console.log(query);
		db.query(query, function(err, result){
			if(err){
				return false;
			}
			return true;
		})
    }
}