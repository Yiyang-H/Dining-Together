import logo from './logo.svg';
import './App.css';
import AboutUs from "./pages/aboutUs/aboutUs.js"
import React, { Component } from 'react';
import { Link } from "react-router-dom";
import { BrowserRouter } from "react-router-dom";


function App() {
  return (
    <div className="App">
      {/* <AboutUs/> */}
      <Link to="/aboutUs">About Us</Link> |{" "}
      <Link to="/home"> Home</Link> |{" "}
      <Link to="/test"> test</Link> |{" "}

    </div>
  );
}

export default App;
