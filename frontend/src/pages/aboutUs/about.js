import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import React, { useState, useEffect } from 'react';
import Navbar from '../../components/navBar/navBar';
import ProvideFood from '../ProvideFood/provideFood';
import Modal from '@mui/material/Modal';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import { Button, Paper } from '@mui/material';
import samplepic1 from "../../images/samplePic1.png";
import samplepic2 from "../../images/samplePic2.png";
import faq from "../../images/faq.png";
import Faq from 'react-faq-component';
import Footer from '../../components/footer/footer1';
import ProvideFoodModal from '../../components/provideFoodModal';

export default function About(props){
 
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
        fontStyle: 'regular', 
        fontWeight: '400',
        fontSize: '36px',
        textAlign: "center"
    }
    const numberStyle={
        fontFamily: 'Quicksand',
        fontStyle: 'bold', 
        fontWeight: '600',
        fontSize: '32px',
        textAlign: "center"
    }
    const contentStyle={
        fontFamily: 'Quicksand',
        fontStyle: 'regular', 
        fontWeight: '400',
        fontSize: '20px',
        textAlign: "center",
        color:"#8B8BA5"
    }
    const headerStyle={
        fontFamily: 'Quicksand',
        fontStyle: 'bold', 
        fontWeight: '700',
        fontSize: '46px'
    }
    const styles = {
        titleTextColor: "black",
        rowTitleColor: "black",
        rowContentColor: 'grey',
        arrowColor: "black",
    };
    
    const config = {
        animate: true,
        tabFocus: true
    };

    const textOverflowStyle={
        overflowY: 'scroll'
    }
    const data = {
        title: "FAQ",
        rows: [
          {
            title: "How does this app work?",
            content: "In order to provide food, you have to log in first and input food details by clicking start invitation.\nAs regard to applying, you can select whatever you want in the food galary, and view your application status in your profile"
          },
          {
            title: "How to manage the food provided?",
            content: "You can review your food details in your profile and nominate the the applicant in waiting list."
          },
          {
            title: "How does the payment system work?",
            content: "As a provider, as long as the order finishes, you have to click complete in your food list. The money will be transferred automatically."
          },
          {
            title: "Why some features don't work?",
            content: "Some features are still under development, including: food review,marking system and profile editing. Apologies for the inconvenience!"
          },
          {
            title: "Why I can not apply for food?",
            content: `There are several reasons causing this problem:
            - You are not allowed to apply for more than 3 food at the same time(food with pending status)
            - You are not allowed to apply for any food with 0 or less dollars
            - You are not allowed to apply for the food provided by your self`
          },
          {
            title: "Why is there no food in the food gallary?",
            content: "It takes time to load everything in the food list"
          }],
    
      }

    const [open, setOpen] = React.useState(false);

    const handleOpen = () => {
        setOpen(true);
    };
    const handleClose = () => {
        setOpen(false);
    };
    return(
        <div>
        <Navbar/>
                <Grid container spacing={2} sx={{width:"85vw",padding:"5vw"}}>
                    <Grid item xs={6} md={7} >
                        <Box sx={{display:"flex", flexDirection:"column", overflow:"hidden",padding:"5vw 0 0 2vw",alignItems:"center"}}>
                            <Typography gutterBottom variant="h4" component="span" 
                            style={headerStyle}>
                                Share What You Cook With Friend, Together!
                            </Typography>

                            <Box sx={{display:"flex", flexDirection:"row",justifyContent:"center",alignItems:"center",paddingTop:"2vh"}}>
                            <Typography variant="body1" gutterBottom component="div" 
                                        style={{fontFamily: 'Quicksand',
                                        fontStyle: 'bold', 
                                        fontSize: '20px',
                                        color:"#8B8BA5"}}>
                                When you share something that cooked by yourself, the happiness is priceless.
                            </Typography>
                            
                            </Box>
                            <Button onClick={handleOpen} style={buttonStyle}>
                                Start Invitation
                            </Button>
                            <ProvideFoodModal open={open} handleClose={handleClose}/>
                        </Box>
                    </Grid>
                    <Grid item xs={6} md={5}>
                         <Box style={{
                            width: 700, height:500, background: '#D8CBF6',
                            borderRadius: '65px 65px 130px 130px'
                        }}>
                            <Box sx={{width: '95%', height: '95%',background: '#FDFAE5', overflow: 'hidden', display: 'flex', alignItems: 'flex-end', justifyContent: 'flex-end', 
                            borderRadius: '60px 25px 40px 60px'}}>
                            <img style={{objectFit: 'cover', width: '97%', height: '97%', borderRadius: '60px 25px 40px 60px'}} src={samplepic1}></img>
                            </Box>
                        </Box>

                    </Grid>
                </Grid>
                
                <Container sx={{width:"50vw" ,paddingTop:"80px"}}>
                    <Box sx={{display:"flex", flexDirection:"column",justifyContent:"center",alignItems:"center", width:"50vw"}}>
                    <span style={{
                                fontFamily: 'Quicksand', 
                                fontStyle: 'bold', 
                                fontWeight:"700",
                                fontSize: '500px',
                                color: "rgb(236, 236, 236)",
                                position: 'absolute',opacity:1,left: "250px"}}>“</span>
                        <Typography gutterBottom variant="h4" component="div" 
                        sx={{paddingLeft:"2vw"}} 
                            style={fontStyle}>
                           Awalnya saya malu dengan mertua karena tidak bisa memasak, kini mereka lebih suka makan malam di rumah demi menyantap masakan yang saya buat sendiri
                        </Typography>
                        
                        <Box sx={{display:"flex", flexDirection:"row",justifyContent:"center",alignItems:"center",paddingTop:"4vh"}}>
                            <Paper elevation={0} sx={{width: 60, height: 60 }} 
                            style={{backgroundImage: `url(${samplepic2})`,
                                    backgroundRepeat: "no-repeat",
                                    backgroundPosition: 'center center',
                                    backgroundSize:"cover",
                                    borderRadius:"50%"}}></Paper>

                            <Box sx={{display:"flex", flexDirection:"column"}}>
                            <Typography variant="body1" gutterBottom component="div" 
                                        sx={{paddingLeft:"2vw"}} 
                                        style={{fontFamily: 'Quicksand',
                                        fontStyle: 'bold', 
                                        fontSize: '22px'}}>
                                Camella Sarrah
                            </Typography>
                            <Typography variant="body1" gutterBottom component="div" 
                                        sx={{paddingLeft:"2vw"}} 
                                        style={{fontFamily: 'Quicksand',
                                        fontStyle: 'bold', 
                                        fontSize: '16px',
                                        color:"#8B8BA5"}}>
                                Vege Master
                            </Typography>
                            </Box>
                            </Box>
                    </Box>
                </Container>

                <Container sx={{width:"60vw" ,paddingTop:"90px"}}>
                    <Grid container spacing={2}>
                        <Grid item xs={6} md={3} >
                        <Typography gutterBottom variant="h4" component="div" 
                            style={numberStyle}>
                                180,000+
                            </Typography>
                            <Typography gutterBottom variant="h4" component="div" 
                            style={contentStyle}>
                                Food Shared
                            </Typography>
                        </Grid>
                        <Grid item xs={6} md={3} >
                        <Typography gutterBottom variant="h4" component="div" 
                            style={numberStyle}>
                                20,000+
                            </Typography>
                            <Typography gutterBottom variant="h4" component="div" 
                            style={contentStyle}>
                                Active Providers
                            </Typography>
                        </Grid>
                        <Grid item xs={6} md={3} >
                        <Typography gutterBottom variant="h4" component="div" 
                            style={numberStyle}>
                                400,000+
                            </Typography>
                            <Typography gutterBottom variant="h4" component="div" 
                            style={contentStyle}>
                                Places
                            </Typography>
                        </Grid>
                        <Grid item xs={6} md={3} >
                        <Typography gutterBottom variant="h4" component="div" 
                            style={numberStyle}>
                                6,9000,00+
                            </Typography>
                            <Typography gutterBottom variant="h4" component="div" 
                            style={contentStyle}>
                                Users
                            </Typography>
                        </Grid>

                    </Grid>
                </Container>

                <Container sx={{width:"80vw" ,paddingTop:"100px"}}>
                    <Box sx={{display:"flex", flexDirection:"column",justifyContent:"center",alignItems:"center",}}>
                    <Grid container spacing={2}>
                        <Grid item xs={6} md={6}>
                            <Paper elevation={0} sx={{width:"30vw",height:"60vh",display:"flex", flexDirection:"row"}} 
                            style={{backgroundImage: `url(${faq})`,
                                    backgroundSize:"contain",backgroundRepeat: "no-repeat",
                                    backgroundPosition: "center center"}}>
                            </Paper>
                        </Grid>

                        <Grid item xs={6} md={6}>
                            <Faq data = {data}
                                styles = {styles}
                                config={config}
                        />
                        </Grid>
                    
                    </Grid>
                </Box>
                </Container>

                <Footer/>

    </div>
    )
}