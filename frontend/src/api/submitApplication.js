import { getCookie } from '../api/util';

const BASE_URL = process.env.REACT_APP_BASE_URL


export default function submitApplication(foodId, candidateID, notes) {
    const url = BASE_URL + '/api/v1/foods/'+foodId+'/applications/'+candidateID;
    const request = {
        method: 'POST',
        headers: {'Content-Type': 'application/json', 'Origin': process.env.ORIGIN_URL,'Authorization':'Bearer '+getCookie('token')},
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
            return Promise.reject();
        }
    })
    .then(data => {
       alert("Successfully submit,you can check your applications in profile!")
    })
    .catch(() => alert('Submit error, please try again!'));
}