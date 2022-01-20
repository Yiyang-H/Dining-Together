import React, {useState} from 'react';

import {
    useMatch
} from 'react-router-dom';

import { 
    Box,
    CardMedia,
    Typography
} from '@mui/material';



import favicon from '../../images/favicon.png';
import './navBar.css';


export default function Navbar(props){
    // const match = useMatch();
    console.log(window.location.pathname.split('/'));
    
    const currentPage = window.location.pathname.split('/')[1];


    return(
    <Box sx={{display: 'flex', flexDirection: 'row', height: '3vw', px:'5vw'}}>
        <Box sx={{width: '40%', display: 'flex', flexDirection: 'row', alignItems: 'center'}}>
            <img src={favicon} style={{height: '80%'}}/>
            <Typography sx={{fontFamily: 'Quicksand', fontStyle: 'normal', fontWeight: '500', fontSize: '28px', lineHeight: '35px', color: '#272042', ml: '1vw'}}>Dining Together</Typography>
        </Box>
        <Box sx={{width: '60%', display: 'flex', flexDirection: 'row', justifyContent: 'flex-end', alignItems: 'center'}}>
            <div class="topnav">
                <a class={currentPage === 'home' ? 'active' : ''} href="/home/">Home</a>
                <a class={currentPage === 'aboutUs' ? 'active' : ''} href="/aboutUs">About us</a>
                <a class={currentPage === 'FAQ' ? 'active' : ''} href="/FAQ">FAQ</a>
                
            </div>
            <a class="yellow-button" href="/login">Login/SignUp</a>
        </Box>

    </Box>)
}
