import Container from '@mui/material/Container';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import { styled } from '@mui/material/styles';
import Paper from '@mui/material/Paper';
import UserInfo from '../components/userCard';
import ProfileTab from '../components/profileTab';

export default function Profile(){

  const borderStyle={
    borderColor:'red'
  }
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
                  {/* <Deposits /> */}
                  <ProfileTab/>
                </Paper>
              </Grid>
              </Grid>
              </Container>
      
    )
}