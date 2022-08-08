function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i <ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) === ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) === 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
}

// Set cookie when login
function setCookie(cname, cvalue, expTime) {
    const d = new Date();
    d.setTime(d.getTime() + (expTime * 1000));
    let expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

// Deletes the token of the user
function deleteUserToken() {
    setCookie('token', '', 0);
}

async function addressLookup(address) {

  var requestOptions = {
    method: 'GET',
  };
  return new Promise((resolve) => {
    fetch(`https://api.geoapify.com/v1/geocode/autocomplete?text=${address}&apiKey=${process.env.REACT_APP_AUTOCOMPLETE_APIKEY}`, requestOptions)
    .then(response => response.json())
    .then(result => {
      console.log(result)
      const addresses = []
      for(var i = 0; i < result.features.length; i++) {
        addresses.push(result.features[i].properties.formatted)
      }
      resolve(addresses)
    })
    .catch(error => resolve([]));
  })
}

export {
    getCookie,
    setCookie,
    deleteUserToken,
    addressLookup
}