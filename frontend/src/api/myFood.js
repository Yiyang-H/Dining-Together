import { getUserId } from "./login";
import { getCookie } from './util';

const BASE_URL = process.env.REACT_APP_BASE_URL

async function getMyFoods() {
    const url = BASE_URL + '/users/' + getUserId() + '/foods';
    const request = {
    method: 'GET',
    headers: {'Content-Type': 'application/json', 'Authorization':'Bearer ' + getCookie('token')},
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

async function getCandidates(foodId) {
    const url = BASE_URL + `/api/v1/foods/${foodId}/applications/`;
    const request = {
    method: 'GET',
    headers: {'Content-Type': 'application/json','Authorization':'Bearer ' + getCookie('token')},
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

function updateApplicationStatus(foodId, candidateId, status) {
    const url = BASE_URL + `/api/v1/foods/${foodId}/applications/${candidateId}`;
    const request = {
        method: 'PATCH',
        headers: {'Content-Type': 'application/json', 'Authorization':'Bearer ' + getCookie('token')},
        body: JSON.stringify({
            status: status
        })
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


function confirmFood(foodId){
    const url = BASE_URL + `/api/v1/foods/${foodId}/confirm`;
    const request = {
        method: 'PUT',
        headers: {'Content-Type': 'application/json', 'Authorization':'Bearer ' + getCookie('token')},
        // body: JSON.stringify({
        //     status: status
        // })
    }

    return fetch(url, request)
    .then(res => {
        if(res.ok) {
            alert("Success");
        }
        else {
            alert("Confirm failed!");
        }
    })

}



export {
    getMyFoods,
    getCandidates,
    updateApplicationStatus,
    confirmFood,
}