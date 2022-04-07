const db = require('../DB/db');
const response = {res : true, msg : 'success'};

module.exports = {
    insert : function(table, data){
        let make_query = `INSERT INTO ${table} VALUES(`;

        for(let i=0 ; i<data.length ; i++)
        {
            make_query += '?';
            if(i != data.length-1) make_query += ',';
        }
        make_query += ')';
        console.log(make_query);
        return make_query;
    },
    select : function(target, table, data, AND_OR, order){
        let make_query = `SELECT`;

        for(let i=0 ; i<target.length ; i++){
            make_query += ` ${target[i]}`;
            if(i != target.length-1) make_query += ',';
        }
        make_query += ` FROM ${table} WHERE `;
        for(let key in data){
            make_query += `${key}='${data[key]}'${AND_OR}`;
        }
        console.log(make_query);
        return make_query;
    }
}
