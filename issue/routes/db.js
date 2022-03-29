mysql = require('mysql');
exports.connection = function () {
    var connection =  mysql.createConnection({
        host: 'localhost',
        user: 'root',
	password: '0000',
        database: 'issue'
    });

    return connection;
}

