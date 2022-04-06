const db = require('../DB/db');

module.exports = {
    response : {res : true, msg : 'success'},
    insert : function(table, data){
        let make_query = `INSERT INTO ${table} VALUES(`;

        for(let i=0 ; i<data.length ; i++)
        {
            make_query += '?';
            if(i != data.length-1) make_query += ',';
        }
        make_query += ')';
	console.log(make_query, this.response);
        db.query(make_query, data, function(err, result){
            if(err){
                return err;
            }
            else{
                return this.response; 
            }
        })
    }
}
