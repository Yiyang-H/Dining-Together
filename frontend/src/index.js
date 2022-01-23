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
import Profile from './pages/profile';

import Login from "./pages/Login/Login";
import ProvideFood from './pages/ProvideFood/provideFood';
import IMmgHolder from './components/imgHolder';

const rootElement = document.getElementById("root");
  render(
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<ProvideFood />} />
      <Route path="/login" element={<Login />} />
      <Route path="aboutUs" element={<AboutUs />} />
      <Route path="home" element={<Homepage />} />
      <Route path="profile" element={<Profile />} />
      <Route path="img" element={<IMmgHolder />} />

    </Routes>
  </BrowserRouter>,
  rootElement
  );


// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
