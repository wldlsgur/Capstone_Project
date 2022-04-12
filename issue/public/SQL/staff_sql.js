var pool = require('../../DB/db_config');

module.exports = function () {
    return {
        get_teacherinfo_use_school: function (school,callback) {
            pool.getConnection(function (err, con) {
                var sql=`select * from teacherinfo where school='${school}'`;
                con.query(sql,function(err,result,fields){
                    con.release();
                    if(err) callback(err,null);
                    else callback(null,result);
                })
            });
        },
        get_teacherinfo_use_id: function (id,callback) {
            pool.getConnection(function (err, con) {
                var sql=`select * from teacherinfo where id='${id}'`;
                con.query(sql,function(err,result,fields){
                    con.release();
                    if(err) callback(err,null);
                    else callback(null,result);
                })
            })
        },
        get_presidentinfo_use_id: function (id,callback) {
            pool.getConnection(function (err, con) {
                var sql=`select * from presidentinfo where id='${id}'`;
                con.query(sql,function(err,result,fields){
                    con.release();
                    if(err) callback(err,null);
                    else callback(null,result);
                })
            })
        },

        pool: pool
    }
};