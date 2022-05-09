const pool = require('../../DB/db_config');

module.exports = function () {
    return {
        test: function (id, school, room, number, callback) {
                                  pool.getConnection(function (err, con) {
                                  let sql =`insert into presidentinfo (id, school, room, number, image_url) values('${id}','${school}','${room}','${number}',NOW()+1)`;
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