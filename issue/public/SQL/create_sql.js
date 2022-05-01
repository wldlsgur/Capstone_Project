const pool = require('../../DB/db_config');

module.exports = function(){
    return {
        INSERT : function(query, data_array, callback){
            pool.getConnection(function (err, con) {
                con.query(query, data_array, function(err,result,fields){
                    con.release();
                    if(err) callback(err,null);
                    else callback(null,result);
                })
            });
        },
        pool: pool
    }
};