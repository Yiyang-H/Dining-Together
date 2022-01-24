import React, {useEffect, useState} from 'react';

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

import { isLoggedIn } from '../../api/login';


export default function Navbar(props){
    // const match = useMatch();
    console.log(window.location.pathname.split('/'));
    
    const currentPage = window.location.pathname.split('/')[1];
    
    const [loggedIn, setLoggedIn] = useState(false);

    useEffect(() => {
        if(isLoggedIn()) {
            setLoggedIn(true);
        }else {
            setLoggedIn(false);
        }
    })


    return(
    <Box sx={{display: 'flex', flexDirection: 'row', height: '3vw', px:'5vw'}}>
        <Box sx={{width: '40%', display: 'flex', flexDirection: 'row', alignItems: 'center'}}>
            <img src={favicon} style={{height: '80%'}}/>
            <Typography sx={{fontFamily: 'Quicksand', fontStyle: 'normal', fontWeight: '500', fontSize: '28px', lineHeight: '35px', color: '#272042', ml: '1vw'}}>Dining Together</Typography>
        </Box>
        <Box sx={{width: '60%', display: 'flex', flexDirection: 'row', justifyContent: 'flex-end', alignItems: 'center'}}>
            <div class="topnav">
                <a class={currentPage === 'home' ? 'active' : ''} href="/home/">Home</a>
                <a class={currentPage === 'about' ? 'active' : ''} href="/about">About us</a>
                <a class={currentPage === 'FAQ' ? 'active' : ''} href="/FAQ">FAQ</a>
                
            </div>
            {
                loggedIn ? 
                <a class="yellow-button" href="/profile">Profile</a>
                :
                <a class="yellow-button" href="/login">Login/SignUp</a>
            }
            
        </Box>

    </Box>)
}
