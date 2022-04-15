module.exports = {
    INSERT : function(table, data_array){
        let make_query = `INSERT INTO ${table} VALUES(`
        for(let i=0 ; i<data_array.length ; i++){
            make_query += `?`;
            if(i != data_array.length-1) make_query += `,`;
        }
        make_query += `)`;
        console.log(make_query);
        return make_query;
    },
    /*SELECT : function(target_array, table, json_data, or_and){
        let make_query = `SELECT`;

        for(let i=0 ; i<target_array.length ; i++){
            make_query += target_array[i]
            if(i != target_array.length-1) make_query += `,`;
        }
        make_query += `FROM ${table} WHERE`;

        for(var key in json_data){
            make_query += `${key}='${json_data[key]}' ${or_and}`
        }
    }*/
}