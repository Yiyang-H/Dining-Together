import logo from './logo.svg';
import './App.css';
import React, { Component } from 'react';

import { createTheme } from '@mui/material/styles';
import Navbar from './components/navBar/navBar';
import pageNav from './components/navBar/pageNav';
import Layout from './components/navBar/layout';
const theme = createTheme({
    palette: {
        primary: {
            main: '#f7c531',
        },
        secondary: {
            main: '#f50057',
        },
    },
});

function App() {
  return (
    <div className="App">
       <Navbar/>
       <pageNav/>
       
    </div>
  );
}

export default App;
