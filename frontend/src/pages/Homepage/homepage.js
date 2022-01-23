import './homepage.css'
import FoodCard from '../../components/foodCard'
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import FoodList from '../../components/foodList'
import Footer from "../../components/footer/footer"
import { Avatar} from '@material-ui/core';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

import Navbar from '../../components/navBar/navBar';
import Modal from '@mui/material/Modal';
import ProvideFood from '../ProvideFood/provideFood';
import Container from '@mui/material/Container';



export default function Homepage(props){

    // const foodlist =[
    //     {
    //         title:'food1',
    //         description:'food1111',
    //         location:'111 street',
    //         foodId:1,
    //         provider:{
    //             id:1,
    //             username:'user1',
    //             currency:1,
    //             email:'1@',
    //             avatar:['asdasdasd']
    //         },
    //         endTime:'1pm',
    //         createdTime:'1pm',
    //         foodType:'DINING_IN',
    //         completed:false,
    //         consumerNumber:1
    //         },

    //         {
    //         title:'food2',
    //         description:'food2',
    //         location:'2 street',
    //         foodId:2,
    //         provider:{
    //             id:2,
    //             username:'user2',
    //             currency:2,
    //             email:'2@',
    //             avatar:['asdasdasd']
    //         },
    //         endTime:'2pm',
    //         createdTime:'2pm',
    //         foodType:'DINING_IN',
    //         completed:false,
    //         consumerNumber:2
    //         },
    //         {
    //         title:'food3',
    //         description:'food3',
    //         location:'3 street',
    //         foodId:3,
    //         provider:{
    //             id:3,
    //             username:'user3',
    //             currency:3,
    //             email:'3@',
    //             avatar:['asdasdasd']
    //         },
    //         endTime:'3pm',
    //         createdTime:'3pm',
    //         foodType:'DINING_IN',
    //         completed:true,
    //         consumerNumber:3
    //         }
    // ];
    
    //get data from backend
    // const [foodList, setFoodList] = useState(foodlist);
    
    const textOverflowStyle={
        overflowY: 'scroll'
    }

    const [foodList, setFoodList] = useState([]);


    useEffect(() => {
      const fetchData = async () => {
        const result = await axios(
          'http://localhost:8080/api/v1/foods/', 
          {mode:"cors",
          headers: {'Content-Type': 'application/json', 'Origin': 'http://localhost:3000'},
        }
        );
        setFoodList(result.data);
      };
  
      fetchData();
    },[]);
    // useEffect(()=>{
    //     setFoodList(displayFood);
    // })

    // const displayFood=foodlist.filter(food => food.completed ===false);

    const [open, setOpen] = React.useState(false);

    const handleOpen = () => {
        setOpen(true);
    };
    const handleClose = () => {
        setOpen(false);
    };

    return(
        <div>
        <Navbar/>
        <div class="home1">

            {/* first section */}
            <div class="home1_left">
                <span class="home1_quotation_mark">“</span>
                <span class="home1_heading">The more we share,</span>
                <span class="home1_heading1">The more we have.</span>
                <div class="home1_person_container">
                        <div class="home1_person_avatar_container">
                            <div class="home1_person_avatar"></div>
                        </div>
                        <span class="home1_person_name">- LEONARD NIMOY</span>
                    </div>
                <div class="food_btn_startInvitation">
                    <button class="food_btn_startInvitation_back" onClick={handleOpen}>Start Invitation</button>
                    <Modal
                        open={open}
                        onClose={handleClose}
                        aria-labelledby="parent-modal-title"
                        aria-describedby="parent-modal-description"
                    >
                        <Container sx={{bgcolor:"white"}} style={textOverflowStyle}>
                        <ProvideFood handleClose={handleClose}/>
                        </Container>
                    </Modal>
                </div>
            </div>
            <div class="home1_right">
                <div class="home1_pic_container">
                    <div class="home1_picture"></div>
                </div>
            </div>
        </div>

        <div class="home2_container">
            <span class="home2_heading">Buy Some Food</span>
            <span class="home2_heading1">Food Gallery</span>
            <FoodList foodlist ={foodList}/>
        </div>


        {/* footer section */}
        <div class="home_footer_container">
            <Footer/>
        </div>
    </div>
    )
}