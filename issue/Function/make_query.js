module.exports = {
    INSERT : function(table, data){
        let make_query = `INSERT INTO ${table} VALUES(`
        for(let i=0 ; i<data.length ; i++){
            make_query += `?`;
            if(i != data.length-1) make_query += `,`;
        }
        make_query += `)`;
        console.log(make_query);
        return make_query;
    }
}