import * as React from 'react';
import Typography from '@mui/material/Typography';
import Avatar from '@mui/material/Avatar';
import Container from '@mui/material/Container';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';





export default function UserInfo(){

    const userNameStyle={
        display:"flex",
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
    }

    const userInfoStyle={
        padding:"40px 10px 10px 10px"
    }


    return  (
        <Container>
            <Box style={userNameStyle}>
                <Typography variant="h3" gutterBottom component="div" style={{color:"#F7C531"}}>
                    User name
                </Typography>
                <Avatar sx={{ bgcolor: "#F7C531", width: 100, height: 100 }} aria-label="recipe" >
                    R 
                </Avatar>
            </Box>
            <Box style={userInfoStyle}>
                <Typography variant="h6" gutterBottom component="div">
                    Balance:50
                </Typography>
                <Typography variant="h6" gutterBottom component="div">
                    Rating:2
                </Typography>
                <Typography style={{ wordWrap: "break-word" ,}} variant="h6" gutterBottom component="div">
                    Description:ashdjkasdnasdnasjkdaksdhasjdnas,m
                </Typography>
            </Box>
    </Container>
   )
}