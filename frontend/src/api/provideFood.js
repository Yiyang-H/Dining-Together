import { getCookie } from '../api/util';
import { getUserId } from './login';

const BASE_URL = 'https://stark-ocean-44226.herokuapp.com'

function uploadFood(title, description, location, endTime, foodType, category, consumerNumber, picture, price) {
    const formatedTime = endTime.replace('T', ' ') + ':00';
    const formatedPicture = picture.split('base64,')[1];
    const url = BASE_URL + '/api/v1/foods/';
    const request = {
        method: 'POST',
        headers: {'Content-Type': 'application/json', 'Origin': process.env.ORIGIN_URL,'Authorization':'Bearer '+getCookie('token')},
        body: JSON.stringify({
            title: title,
            description: description,
            location: location, 
            endTime: formatedTime,
            foodType: foodType,
            category: category,
            consumerNumber: consumerNumber,
            pictureBase64: formatedPicture,
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