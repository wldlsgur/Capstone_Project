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
                                let sql=`SELECT mm.id, mm.child_name, mm.m_name, mm.date, mm.mor, mm.lun, mm.din, m.morning, m.lunch, m.dinner 
                                         from medicinemanagement AS mm
                                         LEFT JOIN medicine AS m
                                         ON mm.school='${school}' AND mm.room='${room}' AND mm.id=m.id AND mm.child_name=m.child_name AND mm.m_name=m.m_name`;
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