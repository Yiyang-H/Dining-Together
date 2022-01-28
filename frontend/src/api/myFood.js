import { getUserId } from "./login";
import { getCookie } from './util';

const BASE_URL = 'https://stark-ocean-44226.herokuapp.com'

const data = [  {
    "title": "string",
    "description": "string string string string string string string string string string string string string string string string string string string string string string string string string string string ",
    "location": "string",
    "endTime": "2022-11-12 13:02:56.12345678",
    "foodType": "DINING_IN",
    "category": "MAIN_MEAL",
    "consumerNumber": 1,
    "pictureBase64": `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`,
    "price": 0,
    "foodId": 0,
    "provider": {
      "phoneNumber": "string",
      "suburb": "string",
      "avatarBase64": "string",
      "id": 0,
      "username": "string",
      "currency": 0,
      "email": "string"
    },
    "createdTime": "string",
    "completed": true
  },
  {
    "title": "string",
    "description": "string",
    "location": "string",
    "endTime": "2022-11-12 13:02:56.12345678",
    "foodType": "DINING_IN",
    "category": "MAIN_MEAL",
    "consumerNumber": 1,
    "pictureBase64": null,
    "price": 0,
    "foodId": 0,
    "provider": {
      "phoneNumber": "string",
      "suburb": "string",
      "avatarBase64": "string",
      "id": 0,
      "username": "string",
      "currency": 0,
      "email": "string"
    },
    "createdTime": "string",
    "completed": true
  }];

  const data2 = [
    {
      "candidate": {
        "phoneNumber": "string",
        "suburb": "string",
        "avatarBase64": "string",
        "id": 0,
        "username": "string",
        "currency": 0,
        "email": "string"
      },
      "status": "PENDING",
      "createdTime": "string"
    }]

async function getMyFoods() {
    const url = 'https://stark-ocean-44226.herokuapp.com/users/' + getUserId() + '/foods';
    const request = {
    method: 'GET',
    headers: {'Content-Type': 'application/json', 'Authorization':'Bearer ' + getCookie('token')},
    }

    // return mockData()
    // .then(res => {
    //     return res;
    //     if(res.ok) {
    //         return res.json();
    //     }
    //     else {
    //         Promise.reject();
    //     }
    // })
    // .then(response => {
    //     console.log(response)
    //     return response
    // })  

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
    const url = `https://stark-ocean-44226.herokuapp.com/api/v1/foods/${foodId}/applications/`;
    const request = {
    method: 'GET',
    headers: {'Content-Type': 'application/json','Authorization':'Bearer ' + getCookie('token')},
    }

    // return mockData2()
    // .then(res => {
    //     return res;
    //     if(res.ok) {
    //         return res.json();
    //     }
    //     else {
    //         Promise.reject();
    //     }
    // })

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

<<<<<<< HEAD
<<<<<<< HEAD

    return fetch(url, request)
    .then(res => {
        if(res.ok) {
            return res.json();
        }
        else {
            return Promise.reject();
        }
=======
=======
>>>>>>> parent of 5b07e14 (Update myFood.js)
    if(status=="ACCEPTED"){
        confirmFood(foodId);
    }
    else{
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

}

function mockData() {
    return new Promise(resolve => {
        setTimeout(()=> {
            resolve(data)
        }, 1000)
<<<<<<< HEAD
>>>>>>> parent of 5b07e14 (Update myFood.js)
=======
>>>>>>> parent of 5b07e14 (Update myFood.js)
    })
    
}

function mockData2() {
    return new Promise(resolve => {
        setTimeout(()=> {
            resolve(data2)
        }, 1000)
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
    getCandidates,
<<<<<<< HEAD
<<<<<<< HEAD
    updateApplicationStatus,
    confirmFood,
=======
    updateApplicationStatus
>>>>>>> parent of 5b07e14 (Update myFood.js)
=======
    updateApplicationStatus
>>>>>>> parent of 5b07e14 (Update myFood.js)
}