import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";
import AboutUs from './pages/aboutUs/aboutUs';
import { render } from "react-dom";
import Homepage from './pages/Homepage/homepage';
import BasicGrid from "./components/foodList"



const rootElement = document.getElementById("root");
  render(
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<App />} />
      <Route path="aboutUs" element={<AboutUs />} />
      <Route path="home" element={<Homepage />} />
      <Route path="test" element={<BasicGrid />} />
    </Routes>
  </BrowserRouter>,
  rootElement
  );


// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
