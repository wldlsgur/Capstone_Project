const pool = require('../../DB/db_config');

module.exports = function () {
    return {
        insertCalendarInfo: function (id, school, title, content, startDate, dateDays, startTime, endTime , color, callback) {
            pool.getConnection(function (err, con) {
                for(let i = 0; i<=dateDays; ++i){
                    let sql=`INSERT INTO calendar (id, school, title, content, date, startTime, endTime , color)
                             VALUES('${id}', '${school}', '${title}', '${content}', date_format(DATE_ADD('${startDate}', INTERVAL ${i} DAY),'%Y-%m-%d'), '${startTime}', '${endTime}', '${color}')`;
                    con.query(sql,function(err,result,fields){
                        if(i == dateDays){
                            con.release();
                            if(err) callback(err,null);
                            else callback(null,result);
                        } 
                    })
                }
            })
        },





        pool: pool
    }
};