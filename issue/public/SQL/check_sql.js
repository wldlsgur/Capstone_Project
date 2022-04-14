var pool = require('../../DB/db_config');

module.exports = function () {
    return {
        get_login_check : function (data,callback) {
            pool.getConnection(function (err, con) {
                let query = `SELECT * FROM user WHERE id='${data[0]}'`;
                con.query(sql, function(err,result,fields){
                    con.release();
                    if(err) callback(err,null);
                    else callback(null,result);
                })
            });
        },
        get_sameid_check : function(data, callback){
            pool.getConnection(function(err, con){
                let query = `SELECT * FROM user WHERE id='?'`;
                con.query(query, data, function(err, result){
                    if(err) callback(err, null);
                    else callback(null, err);
                })
            })
        },
        pool: pool
    }
};