import { getCookie } from '../api/util';
import { getUserId } from './login';

const BASE_URL = process.env.REACT_APP_BASE_URL

function uploadFood(title, description, location, endTime, foodType, category, consumerNumber, picture, price) {
    const formatedTime = endTime.replace('T', ' ') + ':00';
    const formatedPicture = picture.split('base64,')[1];
    const url = BASE_URL + '/api/v1/foods/';
    const request = {
        method: 'POST',
        headers: {'Content-Type': 'application/json','Authorization':'Bearer '+getCookie('token')},
        body: JSON.stringify({
            title: title,
            description: description,
            location: location, 
            endTime: formatedTime,
            foodType: foodType,
            category: category,
            consumerNumber: consumerNumber,
            pictureBase64: formatedPicture,
            price: Number(price)
        })
    }
    fetch(url, request)
    .then(res => {
        console.log(res)
        if(res.ok) {
            return res.json();
        }
        else {
            return Promise.reject();
        }
    })
    .then(data => {
        console.log(data);
       alert("Successfully submit!")
    })
    .catch(err => {
        console.log(err);
        alert('Something went wrong!');
    });
}

export {
    uploadFood
}