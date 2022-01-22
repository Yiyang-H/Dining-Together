

import { getCookie, setCookie } from "./util";
import axios from 'axios'


const BASE_URL = 'http://localhost:8080/'


function login(username, password) {
    const url = BASE_URL + 'api/v1/auth/login';

    return axios.post(url, {
        'username': username,
        'password': password,
    })
}

export {
    login
}