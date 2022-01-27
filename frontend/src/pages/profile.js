import Container from '@mui/material/Container';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import { styled } from '@mui/material/styles';
import React, { useState, useEffect } from 'react';

import Paper from '@mui/material/Paper';
import UserInfo from '../components/userCard';
import ProfileTab from '../components/profileTab';
import { isLoggedIn } from '../api/login';
import { fetchUserData } from '../api/getUserDetails';
import { fetchUsersOrder } from '../api/getUsersOrder';
import Navbar from '../components/navBar/navBar';

export default function Profile(){

const [userDatail,setDetails]=useState({
  id:0,
  username:'Username',
  currency:0
})

const [orderDatail,setOrderDetails]=useState([{
  title:'food1',
  description:'food1111',
  location:'111 street',
  foodId:1,
  provider:{
      id:1,
      username:'user1',
      currency:1,
      email:'1@',
      avatar:['asdasdasd']
  },
  endTime:'1pm',
  createdTime:'1pm',
  foodType:'DINING_IN',
  status:'Pending',
  completed:false,
  consumerNumber:1
  }])

  useEffect(()=>{
    if(isLoggedIn()){
    //   setDetails(fetchUserData());
    //   setOrderDetails(fetchUsersOrder())
    }
    else{alert("Please login to see profile");
      document.location.href = '/home';
    }
  })

    return (
      <div>
      <Navbar/>
      <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
      
            <Grid container spacing={2}>              
              <Grid item xs={12} md={4} lg={4} >
                <Paper 
                  sx={{
                    p: 2,
                    display: 'flex',
                    flexDirection: 'column',
                    height: 580,
                  }}>
                  <UserInfo userData={userDatail}/>
                </Paper>
              </Grid>

              <Grid item xs={12} md={8} lg={8}>
                <Paper
                  sx={{
                    p: 2,
                    display: 'flex',
                    flexDirection: 'column',
                    height: 580,
                  }}
                >
                  <ProfileTab applylist={orderDatail} />
                </Paper>
              </Grid>
              </Grid>
              </Container>
                </div>
      
    )
}