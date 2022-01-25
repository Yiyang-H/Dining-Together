import FoodCard from '../../components/foodCard'
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import FoodList from '../../components/foodList'
import Footer1 from "../../components/footer/footer1"
import React, { useState, useEffect } from 'react';
import axios from 'axios';

import Navbar from '../../components/navBar/navBar';
import Modal from '@mui/material/Modal';
import ProvideFood from '../ProvideFood/provideFood';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import homepage_right from "../../images/homepage_right.png";
import { Button, Paper } from '@mui/material';
import nimoy from "../../images/nimoy.png"
import { isLoggedIn } from '../../api/login';
import ProvideFoodModal from '../../components/provideFoodModal';

export default function Homepage(props){
 
    const buttonStyle={
        width: "200px",
        height: "80%",
        fontFamily: "Quicksand",
        fontStyle: "normal",
        fontWeight: "500",
        fontSize: "16px",
        lineHeight: "16px",
        color: "black",
        backgroundColor: "#F7C531",
        borderRadius: "40px 40px 20px 20px",
        padding:"15px",
        marginTop:"30px"
    }
    const fontStyle={
        fontFamily: 'Quicksand',
        fontStyle: 'bold', 
        fontWeight: '700',
        fontSize: '36px'
    }
    const headerStyle={
        fontFamily: 'Quicksand',
        fontStyle: 'bold', 
        fontWeight: '700',
        fontSize: '46px'
    }

  

    // get data from backend
    const [foodList, setFoodList] = useState([]);
    const [open, setOpen] = React.useState(false);
    const [isLogin, setIsLogin] = useState(false);

    const handleOpen = () => {
        setOpen(true);
    };
    const handleClose = () => {
        setOpen(false);
    };

    useEffect(() => {
        const fetchData = async () => {
          const url = 'https://stark-ocean-44226.herokuapp.com/api/v1/foods/';
          const request = {
          method: 'GET',
          headers: {'Content-Type': 'application/json', 'Origin': 'http://localhost:3000'},
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
         console.log(data)
         setFoodList(data);
      },[])    
      };
      
        fetchData();
      });

    useEffect(()=>{
        if(isLoggedIn){
            setIsLogin(true);
        }
        else{setIsLogin(false);}
    })

   

    return(
        <div>
        <Navbar/>
                <Grid container spacing={2} sx={{width:"85vw",padding:"5vw"}}>
                    <Grid item xs={6} md={7} >
                        <Box sx={{display:"flex", flexDirection:"column", overflow:"hidden",padding:"5vw 0 0 8vw",alignItems:"center"}}>
                            <span style={{
                                fontFamily: 'Quicksand', 
                                fontStyle: 'bold', 
                                fontWeight:"700",
                                fontSize: '500px',
                                color: "rgb(236, 236, 236)",
                                position: 'absolute',opacity:1,left: "30px",top:"-30px"}}>“</span>
                            <Typography gutterBottom variant="h4" component="span" 
                            style={headerStyle}>
                                The more we share,
                            </Typography>
                            <Typography gutterBottom variant="h4" component="span" 
                                style={headerStyle}>
                                The more we have.
                            </Typography>
                            <Box sx={{display:"flex", flexDirection:"row",justifyContent:"center",alignItems:"center",paddingTop:"2vh"}}>
                            <Paper elevation={0} sx={{width: 90, height: 90}} 
                            style={{backgroundImage: `url(${nimoy})`,
                                    backgroundRepeat: "no-repeat",
                                    backgroundPosition: 'center center',
                                    backgroundSize:"cover",
                                    borderRadius:"50%"}}></Paper>
                            <Typography variant="body1" gutterBottom component="div" 
                                        sx={{paddingLeft:"2vw"}} 
                                        style={{fontFamily: 'Quicksand',
                                        fontStyle: 'bold', 
                                        fontSize: '18px'}}>
                                -LEONARD NIMOY
                            </Typography>
                            
                            </Box>
                            <Button onClick={handleOpen} style={buttonStyle}>
                                Start Invitation
                            </Button>
                            <ProvideFoodModal open={open} handleClose={handleClose}/>
                        </Box>
                    </Grid>
                    <Grid item xs={6} md={5}>
                        <Paper elevation={0} sx={{width:"50vw",height:"80vh",display:"flex", flexDirection:"row",padding:"5vw"}} 
                        style={{backgroundImage: `url(${homepage_right})`,
                                backgroundSize:"contain",backgroundRepeat: "no-repeat",
                                backgroundPosition: "center center",}}>
                        </Paper>
                    </Grid>
                </Grid>
                
                <Box>
                    <Box sx={{display:"flex", flexDirection:"column",justifyContent:"center",alignItems:"center"}}>
                        <Typography gutterBottom variant="h4" component="div" 
                        sx={{paddingLeft:"2vw"}} 
                            style={fontStyle}>
                            Buy Some Food
                        </Typography>
                        <Typography gutterBottom variant="h4" component="div" 
                        style={{fontFamily: 'Quicksand',
                                fontStyle: 'bold', 
                                fontWeight: '700',
                                fontSize: '22px',
                                color:"rgba(39, 32, 66, 0.58)"}}>
                         Food Gallery
                        </Typography>
                    </Box>
                    <FoodList foodlist ={foodList} loggedIn={isLogin}/>
                </Box>
                <Footer1/>
            
    </div>
    )
}