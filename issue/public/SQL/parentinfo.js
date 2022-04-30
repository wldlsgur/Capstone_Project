const pool = require('../../DB/db_config');
const make_query = require('../../Function/make_query');

module.exports = function(){
    return {
        SELECT : function(tabel, data_array, json_data, or_and, cnt, callback){
            pool.getConnection(function (err, con) {
                let query = make_query.SELECT('*', tabel, json_data, or_and, cnt);
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