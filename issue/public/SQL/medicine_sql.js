const pool = require('../../DB/db_config');

module.exports = function () {
    return {
        insertMedicineInfoAll: function (id, child_name, m_name, morning, lunch, dinner, date, mKind, mSpecial, mPlace, mAmount, mSym, school, room,callback) {
                    pool.getConnection(function (err, con) {
                        let insertMedicineInfo = `insert into medicine (id, child_name, m_name, morning, lunch, dinner, date, mKind, mSpecial,mPlace,mAmount,mSym)
                                                  values('${id}','${child_name}','${m_name}','${morning}','${lunch}','${dinner}','${date}','${mKind}','${mSpecial}','${mPlace}','${mAmount}','${mSym}')`;
                        let insertMedicineManagementInfo = `insert into medicinemanagement (id, school, room, child_name, m_name, date, morning, lunch, dinner)
                                                            values('${id}', '${school}', '${room}', '${child_name}','${m_name}',NOW(),'false','false','false')`;
                        let multiQuery = `${insertMedicineInfo};${insertMedicineManagementInfo};`
                        con.query(multiQuery,function(err,result,fields){
                            con.release();
                            if(err) callback(err,null);
                            else callback(null,result);
                        })
                    });
                },
        selectMedicineInfo: function (id,child_name,callback) {
                    pool.getConnection(function (err, con) {
                        let sql=`select * from medicine where id='${id}' AND child_name='${child_name}'`;
                        con.query(sql,function(err,result,fields){
                            con.release();
                            if(err) callback(err,null);
                            else callback(null,result);
                        })
                    })
                },
        selectMedicinemanageInfo: function (school,room,callback) {
                            pool.getConnection(function (err, con) {
                                let sql=`select * from medicinemanagement where school='${school}' AND room='${room}'`;
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