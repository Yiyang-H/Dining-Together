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

import SideImage from '../../images/Exlorer_Illustration 1.png';

import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';

import {login, signUp} from '../../api/login';

export default function LoginPage(props) {
    const [isLogin, setIsLogin] = useState(true);

    return(
    <Grid container direction='row' sx={{width: '100vw', height: '100vh'}}>
        <Grid item xs={7} sx={{display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
            {isLogin ? <Login handleSignUp={() => setIsLogin(false)}/> : <SignUp handleSignIn={() => setIsLogin(true)}/>}
        </Grid>
        <Grid item xs={5} sx={{display: 'flex', alignItems: 'center'}}>
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
            login(username, password);
            
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
            <Link button onClick={handleSignUp} sx={{fontWeight: 'bold', cursor:'pointer'}}>Sign up</Link>
        </Typography>
        <Box sx={{paddingTop: '40px'}}>
            <form class='login-form'>
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

    const [email, setEmail] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');

    const [postcode, setPostcode] = useState('');


    const [step, setStep] = useState(0);

    //TODO connect backend
    const handleSignUp = function() {
        if(username !== '' && password !== '') {
            if(password === password2) {
                signUp(username, password, email, phoneNumber, postcode)
                .then(res => res.json())
                .then(data => console.log(data));
            }else {
                alert('Double check your password!');
            }
            
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
            <Link onClick={handleSignIn} sx={{fontWeight: 'bold', cursor:'pointer'}}>Sign in</Link>
            </Typography>
            <Box sx={{paddingTop: '40px'}}>
                <form class='login-form'>
                    {
                        step == 0 ? 
                        <div >
                            <label>Username</label><br/>

                            <input className='inputField' type='text' value={username} onChange={(e)=>{setUsername(e.target.value)}}></input><br/>

                            <label>Password</label><br/>

                            <input className='inputField' type='password' value={password} onChange={handleChangePassowrd}></input><br/>

                            <label>Re-enter Password</label><br/>

                            <input className='inputField' type='password' value={password2} onChange={handleChangePassowrd2}></input><br/>
                            <div className='navigate-button' style={{textAlign: 'right'}} onClick={()=>setStep(1)}>{'next >>'}</div>
                        </div> :
                        <div>
                            <div className='navigate-button' style={{textAlign: 'left'}} onClick={()=>setStep(0)}>{'<< go back'}</div>
                            <label>E-mail</label><br/>

                            <input className='inputField' type='text' value={email} onChange={(e)=>{setEmail(e.target.value)}}></input><br/>

                            <label>Phone Number</label><br/>

                            <input className='inputField' type='text' value={phoneNumber} onChange={(e)=>{setPhoneNumber(e.target.value)}}></input><br/>

                            <label>Postcode</label><br/>

                            <input className='inputField' type='text' value={postcode} onChange={(e)=>{setPostcode(e.target.value)}}></input><br/>

                            <div className='button' onClick={handleSignUp}>
                                Sign Up
                            </div>
                        </div>
                    }
                </form>
            </Box>
        </Box>
    )
}