module.exports = {
    INSERT : function(table, json_data){
        let make_query = `INSERT INTO ${table} (`;
        
        for(key of Object.keys(json_data)){
            make_query += `${key},`;
        }
        make_query.substring(0, make_query.length - 1);//마지막, 제거
        make_query += `) VALUES(`;

        for(var key in json_data){
            make_query += `?,`;
        }
        make_query.substring(0, make_query.length - 1);//마지막, 제거
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