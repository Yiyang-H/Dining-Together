const BASE_URL = 'http://localhost:8080/'


export default function submitApplication(foodId, candidateID, notes) {
    const url = BASE_URL + '/api/v1/foods/'+foodId+'/applications/'+candidateID;
    const request = {
        method: 'POST',
        headers: {'Content-Type': 'application/json', 'Origin': 'http://localhost:3000'},
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
       //Todo1: add api: to fetch all of my applications
       //TODO2: (NOT IN THIS FILE) Once the application has been accepted, update the currency
       console.log("Successfully submit!")
       //navigate to profile
    })
    .catch(() => console.log('Submit error, please try again!'));
}

//axios version
// const config = {
//     url: BASE_URL + '/api/v1/foods/'+foodId+'/applications/'+candidateID,
//     method: 'POST',
//     header: {
//       'Content-Type': 'application/json',
//       "Origin":'http://localhost:3000'
//     },
//     data: {
//         notes:notes
//     }
//   }
//   axios(config)
//   .then((response) => console.log(response))
//   .catch((error) => {
//     console.log('Submit error, please try again!')})