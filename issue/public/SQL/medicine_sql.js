const pool = require('../../DB/db_config');

module.exports = function () {
    return {
        insertMedicineInfoAll: function (id, child_name, m_name, morning, lunch, dinner, date, mPlace, content, school, room,callback) {
                    pool.getConnection(function (err, con) {
                        let insertMedicineInfo = `insert into medicine (id, child_name, m_name, morning, lunch, dinner, date, mPlace, content)
                                                  values('${id}','${child_name}','${m_name}','${morning}','${lunch}','${dinner}','${date}','${mPlace}','${content}')`;
                        let insertMedicineManagementInfo = `insert into medicinemanagement (id, school, room, child_name, m_name, date, mor, lun, din)
                                                            values('${id}', '${school}', '${room}', '${child_name}','${m_name}',NOW(),'false','false','false')`;
                        let multiQuery = `${insertMedicineInfo};${insertMedicineManagementInfo};`
                        con.query(multiQuery,function(err,result,fields){
                            con.release();
                            if(err) callback(err,null);
                            else callback(null,result);
                        })
                    });
                },
        selectMedicineInfo: function (id,child_name,m_name,callback) {
                    pool.getConnection(function (err, con) {
                        let sql=`select * from medicine where id='${id}' AND child_name='${child_name}' AND m_name='${m_name}'`;
                        con.query(sql,function(err,result,fields){
                            con.release();
                            if(err) callback(err,null);
                            else callback(null,result);
                        })
                    })
                },
        selectMedicinemanageInfo: function (school,room,callback) {
                    pool.getConnection(function (err, con) {
                        let sql=`SELECT medicinemanagement.id, medicinemanagement.school, medicinemanagement.room, medicinemanagement.child_name,
                                medicinemanagement.m_name, medicinemanagement.date, medicinemanagement.mor, medicinemanagement.lun, medicinemanagement.din,
                                medicine.morning, medicine.lunch, medicine.dinner
                                FROM medicinemanagement, medicine
                                WHERE medicine.id IN (SELECT distinct id
                                                   FROM medicinemanagement
                                                   WHERE school='${school}'
                                                   AND room='${room}') 
                                AND medicine.child_name IN (SELECT distinct child_name
                                                         FROM medicinemanagement
                                                         WHERE school='${school}'
                                                         AND room='${room}')`;
                            con.query(sql,function(err,result,fields){
                                con.release();
                                if(err) callback(err,null);
                                else callback(null,result);
                        })
                    })
                },
        selectMedicinemanageInfo_useId_chNm: function (id,child_name,callback) {
                    pool.getConnection(function (err, con) {
                        let sql=`SELECT distinct m1.id, m1.school, m1.room, m1.child_name, m1.m_name, m1.date, m1.mor, m1.lun, m1.din, m2.morning, m2.lunch, m2.dinner
                         FROM medicine as m2, medicinemanagement as m1
                         WHERE m1.id = '${id}' AND m1.child_name = '${child_name}' AND m2.id = '${id}' AND m2.child_name = '${child_name}' AND m2.m_name=m1.m_name`;
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