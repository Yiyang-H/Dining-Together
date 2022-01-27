import React from 'react';  
import ReactDOM from 'react-dom';  
import { Outlet, Link } from "react-router-dom";

const Layout = () => {
  return (
    <>
      <nav>
        <ul>
          <li>
            <Link to="/about">About Us</Link> |{" "}
          </li>
          <li>
            <Link to="/login">Login</Link> |{" "}
          </li>
          <li>
          <Link to="/home"> Home</Link> |{" "}
          </li>
        </ul>
      </nav>

      <Outlet />
    </>
  )
};

ReactDOM.render(Layout, document.getElementById('root'));


