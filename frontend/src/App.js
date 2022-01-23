import logo from './logo.svg';
import './App.css';
import React, { Component } from 'react';
import { Link } from "react-router-dom";
import { BrowserRouter } from "react-router-dom";
import Login from './pages/Login/Login';
import { createTheme } from '@mui/material/styles';
import {ThemeProvider} from '@mui/material/styles'
import Homepage from './pages/Homepage/homepage';

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
      {/* <AboutUs/> */}
      <Link to="/about">About Us</Link> |{" "}
      <Link to="/login">Login</Link> |{" "}
      <Link to="/home"> Home</Link> |{" "}

    </div>
  );
}

export default App;
