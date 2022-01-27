import { getCookie } from "./util";

async function fetchUsersOrder(userId){
    const url = 'https://stark-ocean-44226.herokuapp.com/users/'+userId+'/applications';
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
            Promise.reject();
        }
    })
    .then(data => {
        console.log(data)
        return data
    })        
}

export {
    fetchUsersOrder
}