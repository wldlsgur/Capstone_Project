var pool = require('../../config/db_config');

module.exports = function () {
    return {
        get_teacherinfo: function (school,callback) {
            pool.getConnection(function (err, con) {
                var sql=`select * from teacherinfo where school='${school}'`;
                con.query(sql,function(err,result,fields){
                    con.release();
                    if(err) return;
                    else callback(null,result);
                })
            });
        },
        get_presidentinfo: function (id,callback) {
            pool.getConnection(function (err, con) {
                var sql=`select * from presidentinfo where id='${id}'`;
                con.query(sql,function(err,result,fields){
                    con.release();
                    if(err) return;
                    else callback(null,result);
                })
            })
        },


        pool: pool
    }
};