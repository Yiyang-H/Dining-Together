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
//import Navbar from '../components/navBar/navBar';
import { getUserId } from '../api/login';

export default function Profile(){





  const [userId,setUserId]=useState(0)

  useEffect(()=>{
    if(isLoggedIn()){
      setUserId(getUserId());
      console.log(userId)
    //   setDetails(fetchUserData());
    //   setOrderDetails(fetchUsersOrder())
    }
    else{alert("Please login to see profile");
    document.location.href = '/home';
    }
  })

    return (
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
                  <UserInfo userId={userId}/>
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
                  <ProfileTab userId={userId} />
                </Paper>
              </Grid>
              </Grid>
              </Container>
      
    )
}