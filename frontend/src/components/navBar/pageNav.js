import {React} from 'react';
import { BrowserRouter, Switch, Route, Routes } from 'react-router-dom';
import About from '../../pages/aboutUs/about';
import Login from '../../pages/Login/Login';
import Homepage from '../../pages/Homepage/homepage';
import Profile from '../../pages/profile';
const pageNav =()=> {
    return(
    
        <BrowserRouter>
        
        <Routes>
          <Route path="/" element={<About />} />
          <Route path="/login" element={<Login />} />
          <Route path="about" element={<About />} />
          <Route path="home" element={<Homepage />} />
          <Route path="profile" element={<Profile />} />
    
        </Routes>
     
       
      </BrowserRouter>
    
);
    }
export default pageNav;