

import { getCookie, setCookie } from "./util";
import jwt_decode from "jwt-decode";

const BASE_URL = process.env.REACT_APP_BASE_URL


function login(username, password) {
    const url = BASE_URL + '/api/v1/auth/login';
    const requestInit = {
        method: 'POST',
        // mode: 'no-cors',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            'username': username,
            'password': password,
        })
    }
    console.log(url);
    fetch(url, requestInit)
    .then(res => {
        if(res.ok) {
            return res.json();
            
        }
        else {
            return Promise.reject();
        }
    })
    .then(data => {
        setCookie('token', data.token, data.expiresIn)
        document.location.href = '/home';
    })
    .catch(() => {
        alert('Failed to sign in, try again');
        document.location.href = '/home';
    });
}

function signUp(username, password, email, phoneNumber, postcode, address) {
    const url = BASE_URL + '/api/v1/auth/register';
    const requestInit = {
        method: 'POST',
        // mode: 'no-cors',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            'username': username,
            'password': password,
            'email': email,
            'phoneNumber': phoneNumber,
            'postcode': postcode,
            'address': address
        })
    }

    fetch(url, requestInit)
    .then(res => {
        console.log(res);
        if(res.ok) {
            return res.json();
        }
        else {
            return Promise.reject();
        }
    })
    .then(data => {
        setCookie('token', data.token, data.expiresIn)
        document.location.href = '/home';
    })
    .catch(() => alert('Failed to sign up, try again'));
}

function isLoggedIn() {
    return getCookie('token') !== '';
}

function getUserId() {
    if(isLoggedIn()) {
        
        const jwt =jwt_decode(getCookie('token'))

        return jwt.userId;
    } 
    return undefined;
}

export {
    login,
    signUp,
    isLoggedIn,
    getUserId
}