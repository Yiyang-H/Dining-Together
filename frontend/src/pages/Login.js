import React, {useState} from 'react';

import {
    Grid,
    Box,
    Typography,
    Link,
    TextField,
    InputAdornment,
    IconButton,
    OutlinedInput,
    Button,
    CardMedia
} from '@mui/material'

import './Login.css';

import SideImage from '../images/Exlorer_Illustration 1.png';

import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';

export default function LoginPage(props) {
    const [isLogin, setIsLogin] = useState(true);

    return(
    <Grid container direction='row' sx={{width: '100vw', height: '100vh'}}>
        <Grid item xs={6} sx={{display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
            {isLogin ? <Login handleSignUp={() => setIsLogin(false)}/> : <SignUp handleSignIn={() => setIsLogin(true)}/>}
        </Grid>
        <Grid item xs={6} sx={{display: 'flex', alignItems: 'center'}}>
            <CardMedia component="img" src={SideImage} sx={{width: '80%', justifySelf: 'center'}} alt="A nice looking picture!"/>
        </Grid>
    </Grid>
    )
}



function Login(props) {
    const {handleSignUp} = props

    const [username, setUsername] = useState('');

    const [showPassword, setShowPassword] = useState(false);
    const [password, setPassword] = useState('');

    const handleClickShowPassword = function() {
        setShowPassword(!showPassword);
    }

    const handleChangePassowrd = function(e) {
        setPassword(e.target.value);
    }

    // TODO how to remember
    const [rememberMe, setRememberMe] = useState(false);


    //TODO connect backend
    const handleSignIn = function() {
        if(username !== '' && password !== '') {
            alert('Sign in successful!');
        }else {
            alert('Enter username and password!');
        }
        
    }


    return (
    <Box sx={{width: '80%', height: '70%', marginLeft: '20%', paddingLeft: '10%', position: 'relative'}}>
        <Typography sx={{
            fontFamily: 'Overpass',
            fontStyle: 'normal',
            fontWeight: '800',
            fontSize: '36px',
            lineHeight: '46px',
            letterSpacing: '0.1em',
            color: '#444B59',
        }}>
            WELCOME BACK!
        </Typography>
        <Typography sx={{fontFamily: 'Nunito', fontStyle: 'normal', fontSize: '24px', lineHeight: '33px', letterSpacing: '0.1em'}}>
            Don't have a account,&nbsp;
            <Link button onClick={handleSignUp} sx={{fontWeight: 'bold'}}>Sign up</Link>
        </Typography>
        <Box sx={{paddingTop: '40px'}}>
            <form>
                <label>Username</label><br/>

                <input className='inputField' type='text' value={username} onChange={(e)=>{setUsername(e.target.value)}}></input><br/>

                <label>Password</label><br/>
            
                <input className='inputField' type={showPassword ? 'text' : 'password'} value={password} onChange={handleChangePassowrd}></input>

                <IconButton
                    onClick={handleClickShowPassword}
                    sx={{position: 'relative', right: '50px', color: '#F7C531'}}
                >
                    {showPassword ? <VisibilityOff /> : <Visibility />}
                </IconButton>

                <div className='options'>
                    <div style={{
                        width: '50%',
                        textAlign: 'left',
                    }}>
                        <input type='checkbox' checked={rememberMe} onClick={()=>{setRememberMe(!rememberMe)}}></input>
                        Remember me
                    </div>
                    <div onClick={()=>{alert('Oh no, can you try to remember it?')}} style={{
                        width: '50%',
                        textAlign: 'right',
                        fontFamily: 'Nunito',
                        fontStyle: 'normal',
                        fontWeight: '600',
                        letterSpacing: '0.1em',
                        color: '#F7C531'
                    }}>
                        Forget password?
                    </div>
                </div>

                <div className='button' onClick={handleSignIn}>
                    Sign In
                </div>
            </form>
        </Box>
    </Box>
    );
}

function SignUp(props) {
    const {handleSignIn} = props

    const [username, setUsername] = useState('');

    const [password, setPassword] = useState('');
    const handleChangePassowrd = function(e) {
        setPassword(e.target.value);
    }

    const [password2, setPassword2] = useState('');
    const handleChangePassowrd2 = function(e) {
        setPassword2(e.target.value);
    }

    //TODO connect backend
    const handleSignUp = function() {
        if(username !== '' && password !== '') {
            alert('Sign up successful!');
        }else {
            alert('Enter username and password!');
        }
    }    


    return(
        <Box sx={{width: '80%', height: '70%', marginLeft: '20%', paddingLeft: '10%', position: 'relative'}}>
            <Typography sx={{
            fontFamily: 'Overpass',
            fontStyle: 'normal',
            fontWeight: '800',
            fontSize: '36px',
            lineHeight: '46px',
            letterSpacing: '0.1em',
            color: '#444B59',
        }}>
                ENJOY SHARING!
            </Typography>
            <Typography sx={{fontFamily: 'Nunito', fontStyle: 'normal', fontSize: '24px', lineHeight: '33px', letterSpacing: '0.1em'}}>
                Already have an account,&nbsp;
            <Link onClick={handleSignIn} sx={{fontWeight: 'bold'}}>Sign in</Link>
            </Typography>
            <Box sx={{paddingTop: '40px'}}>
                <form>
                    <label>Username</label><br/>

                    <input className='inputField' type='text' value={username} onChange={(e)=>{setUsername(e.target.value)}}></input><br/>

                    <label>Password</label><br/>
                
                    <input className='inputField' value={password} onChange={handleChangePassowrd}></input><br/>

                    <label>Re-enter Password</label><br/>
                
                    <input className='inputField' value={password2} onChange={handleChangePassowrd2}></input><br/>

                    <div className='button' onClick={handleSignUp}>
                        Sign Up
                    </div>
                </form>
            </Box>
        </Box>
    )
}