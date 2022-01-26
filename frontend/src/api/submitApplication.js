import { getCookie } from '../api/util';

const BASE_URL = 'https://stark-ocean-44226.herokuapp.com'


export default function submitApplication(foodId, candidateID, notes) {
    const url = BASE_URL + '/api/v1/foods/'+foodId+'/applications/'+candidateID;
    const request = {
        method: 'POST',
        headers: {'Content-Type': 'application/json', 'Origin': 'http://localhost:3000','Authorization':'Bearer '+getCookie('token')},
        body: JSON.stringify({
            'candidateNotes': notes
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
       alert("Successfully submit,you can check your applications in profile!")
    })
    .catch(() => console.log('Submit error, please try again!'));
}