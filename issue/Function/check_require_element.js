module.exports = {
    check_require_element : function(data){
        for(let i=0 ; i<data.length ; i++){
            if(!data[i]) return false;
        }
        return true;
    }
}