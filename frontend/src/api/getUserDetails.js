import { getCookie } from "./util";
import { getUserId } from "./login";

const BASE_URL = process.env.REACT_APP_BASE_URL

function fetchUserData(){
    const url = BASE_URL + '/users/'+ getUserId();
    const request = {
    method: 'GET',
    headers: {'Content-Type': 'application/json', 'Origin': process.env.ORIGIN_URL,'Authorization':'Bearer '+getCookie('token')},
    }

    return fetch(url, request)
    .then(res => {
        if(res.ok) {
            return res.json();
        }
        else {
            return Promise.reject();
        }
    })
    .then(data => {
        console.log(data)
        return data
    })    
}

export {
    fetchUserData
}