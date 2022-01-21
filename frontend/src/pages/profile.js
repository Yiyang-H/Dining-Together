import Container from '@mui/material/Container';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import { styled } from '@mui/material/styles';
import Paper from '@mui/material/Paper';
import UserInfo from '../components/userCard';
import ProfileTab from '../components/profileTab';

export default function Profile(){

  //fake data 
  const applylist =[
    {
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
        },

        {
        title:'food2',
        description:'food2',
        location:'2 street',
        foodId:2,
        provider:{
            id:2,
            username:'user2',
            currency:2,
            email:'2@',
            avatar:['asdasdasd']
        },
        endTime:'2pm',
        createdTime:'2pm',
        foodType:'DINING_IN',
        status:'Completed',
        completed:false,
        consumerNumber:2
        },
        {
        title:'food3',
        description:'food3',
        location:'3 street',
        foodId:3,
        provider:{
            id:3,
            username:'user3',
            currency:3,
            email:'3@',
            avatar:['asdasdasd']
        },
        endTime:'3pm',
        createdTime:'3pm',
        foodType:'DINING_IN',
        status:'Rejected',
        completed:true,
        consumerNumber:3
        }
];
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
                  <UserInfo/>
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
                  <ProfileTab applylist={applylist}/>
                </Paper>
              </Grid>
              </Grid>
              </Container>
      
    )
}