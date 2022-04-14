const pool = require('../../DB/db_config');
const make_query = require('../../Function/make_query');

module.exports = function(){
    return {
        insert_query : function(tabel, data, callback){
            pool.getConnection(function (err, con) {
                let query = make_query.INSERT(tabel, data);
                con.query(query, data, function(err,result,fields){
                    con.release();
                    if(err) callback(err,null);
                    else callback(null,result);
                })
            });
        },
        pool: pool
    }
};