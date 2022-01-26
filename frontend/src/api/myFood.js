import { getUserId } from "./login";
import { getCookie } from './util';

const BASE_URL = 'https://stark-ocean-44226.herokuapp.com'



async function getMyFoods() {
    const url = 'https://stark-ocean-44226.herokuapp.com/users/' + getUserId() + '/foods';
    const request = {
    method: 'GET',
    headers: {'Content-Type': 'application/json', 'Origin': process.env.ORIGIN_URL,'Authorization':'Bearer ' + getCookie('token')},
    }

    fetch(url, request)
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

async function getCandidates(foodId) {
    const url = `https://stark-ocean-44226.herokuapp.com/api/v1/foods/${foodId}/applications/`;
    const request = {
    method: 'GET',
    headers: {'Content-Type': 'application/json', 'Origin': process.env.ORIGIN_URL,'Authorization':'Bearer ' + getCookie('token')},
    }

    fetch(url, request)
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
    getMyFoods,
    getCandidates
}