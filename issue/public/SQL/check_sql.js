var pool = require('../../DB/db_config');

module.exports = function () {
    return {
        get_login_check : function (data,callback) {
            pool.getConnection(function (err, con) {
                let get_login_check_query = `SELECT * FROM user WHERE id='${data[0]}'`;
                con.query(get_login_check_query, function(err,result,fields){
                    con.release();
                    if(err) callback(err,null);
                    else callback(null,result);
                })
            });
        },
        get_sameid_check : function(data, callback){
            pool.getConnection(function(err, con){
                let get_sameid_check_query = `SELECT * FROM user WHERE id='${data[0]}'`;
                con.query(get_sameid_check_query, function(err,result,fields){
                    con.release();
                    if(err) callback(err, null);
                    else callback(null, result);
                })
            })
        },
        pool: pool
    }
};