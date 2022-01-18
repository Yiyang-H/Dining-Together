import './homepage.css'
import FoodCard from '../../components/foodCard'
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import FoodList from '../../components/foodList'
import Faq from 'react-faq-component';
import Footer from "../../components/footer/footer"
import { Avatar} from '@material-ui/core';

export default function Homepage(props){
    return(
        <div>
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
                    <button class="food_btn_startInvitation_back">Start Invitation</button>
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
            <FoodList/>
        </div>


        {/* footer section */}
        <div class="home_footer_container">
            <Footer/>
        </div>
    </div>
    )
}