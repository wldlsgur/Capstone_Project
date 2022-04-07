const db = require('../DB/db');

module.exports = {
    response : {res : true, msg : 'success'},
    error : '',
    insert : function(table, data){
        this.error = '';
        let make_query = `INSERT INTO ${table} VALUES(`;

        for(let i=0 ; i<data.length ; i++)
        {
            make_query += '?';
            if(i != data.length-1) make_query += ',';
        }
        make_query += ')';

        db.query(make_query, data, function(err, result){
            if(err) this.error = err;
        })
        if(this.error != '') return this.error; 
        return this.response;
    },
    select : function(target, table, data, AND_OR, order){
        this.error = '';
        let make_query = `SELECT`;
        
        for(let i=0 ; i<target.length ; i++){
            make_query += target[i];
            if(i != target.length-1) make_query += ',';
        }
        make_query += `FROM ${table} WHERE `;

        for(let i=0; i<data.length ; i++){
            make_query += `${data[i]}=?` ;
            if(i != data.length-1) make_query += AND_OR;
        }
        console.log(make_query);

        db.query(make_query, data, function(err, result){
            if(err) this.error = err; 
            
        })
        if(this.error != '') return this.error;
        if(order === 'json') return result[0];
        else if(order === 'json_array') return result;
    }
}
