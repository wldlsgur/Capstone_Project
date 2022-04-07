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
	    console.log(data);
	    console.log(make_query);
        db.query(make_query, data, function(err, result){
            if(err){
                return err;
            }
            return this.response; 
        })
    }
}