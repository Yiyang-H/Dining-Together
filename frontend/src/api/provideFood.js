import { getCookie } from '../api/util';
import { getUserId } from './login';

const BASE_URL = 'https://stark-ocean-44226.herokuapp.com'

function uploadFood(title, description, location, endTime, foodType, category, consumerNumber, picture, price) {
    const formated_time = endTime.replace('T', ' ') + ':00';
    console.log(formated_time);
    const url = BASE_URL + '/api/v1/foods/';
    const request = {
        method: 'POST',
        headers: {'Content-Type': 'application/json', 'Origin': process.env.ORIGIN_URL,'Authorization':'Bearer '+getCookie('token')},
        body: JSON.stringify({
            title: title,
            description: description,
            location: location, 
            endTime: endTime,
            foodType: foodType,
            category: category,
            consumerNumber: getUserId(),
            pictureBase64: picture,
            price: price
        })
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
       alert("Successfully submit!")
    })
    .catch(() => alert('Submit error, please try again!'));
}

export {
    uploadFood
}