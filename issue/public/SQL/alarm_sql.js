const pool = require('../../DB/db_config');

module.exports = function () {
    return {
        insertTokenInfo: function (id, school, child_name, teacher_name, token, callback) {
            pool.getConnection(function (err, con) {
                let sql=`INSERT INTO token (id, school, child_name, teacher_name, token)
                         VALUES('${id}', '${school}', '${child_name}', '${teacher_name}', '${token}')`;
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