db = {
    'user'     : 'root',
    'password' : '[Qwer12345678!]',
    'host'     : '127.0.0.1',
    'port'     : '3306',
    'database' : 'pet_connect'
}

DB_URL = f"mysql+mysqlconnector://{db['user']}:{db['password']}@{db['host']}:{db['port']}/{db['database']}?charset=utf8"