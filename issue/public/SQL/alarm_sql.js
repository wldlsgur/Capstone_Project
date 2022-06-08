const pool = require('../../DB/db_config');

module.exports = function () {
    return {
        selectTokenTest: function (callback) {
            pool.getConnection(function (err, con) {
                let sql=`select token from token where id='test'`;
                con.query(sql,function(err,result,fields){
                    con.release();
                    if(err) callback(err,null);
                    else callback(null,result);
                })
            });
        },


        pool: pool
    }
};