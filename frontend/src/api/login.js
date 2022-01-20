

import { getCookie, setCookie } from "./util";


const BASE_URL = 'http://localhost:8080/'


function login(username, password) {
    const url = BASE_URL + 'api/v1/auth/login';
    const requestInit = {
        method: 'POST',
        headers: {'Content-Type': 'application/json', 'Origin': 'http://localhost:3000'},
        body: JSON.stringify({
            'username': username,
            'password': password
        })
    }

    return fetch(url, requestInit);
}

export {
    login
}