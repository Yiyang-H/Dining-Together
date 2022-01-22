import * as React from 'react';
import Typography from '@mui/material/Typography';
import Avatar from '@mui/material/Avatar';
import Container from '@mui/material/Container';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Rating from '@mui/material/Rating';





export default function UserInfo(){
    
    const textOverflowStyle={
        overflowY: 'scroll'
    }

    const userNameStyle={
        display:"flex",
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
    }

    const userInfoStyle={
        padding:"40px 10px 10px 10px"
    }

    const [value, setValue] = React.useState(4);

    return  (
        <Container style={textOverflowStyle}>
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
                <Rating name="read-only" value={value} readOnly />
                {/* <Typography variant="h6" gutterBottom component="div">
                    Rating:2
                </Typography> */}
                <Typography style={{ wordWrap: "break-word" ,}} variant="h6" gutterBottom component="div">
                    Description:Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et lorem quis purus Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et lorem quis purulorem quis purus Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et lorem quis purulorem quis purus Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et lorem quis puru
                </Typography>
            </Box>
    </Container>
   )
}