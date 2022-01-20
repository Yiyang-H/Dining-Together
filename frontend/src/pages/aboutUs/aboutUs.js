import Faq from 'react-faq-component';
import Footer from "../../components/footer/footer"
import "./aboutUs.css"
import { Avatar} from '@material-ui/core';
import Navbar from '../../components/navBar/navBar';


export default function AboutUs(props) {
    const data = {
        title: "FAQ",
        rows: [
          {
            title: "Lorem ipsum dolor sit amet,",
            content: "Lorem ipsum dolor sit amet, consectetur "
          },
          {
            title: "Nunc maximus, magna at ultricies elementum",
            content: "Nunc maximus, magna at ultricies elementum, risus turpis vulputate quam."
          },
          {
            title: "Curabitur laoreet, mauris vel blandit fringilla",
            content: "Curabitur laoreet, mauris vel blandit fringilla, leo elit rhoncus nunc"
          },
          {
            title: "What is the package version",
            content: "v1.0.5"
          },
          {
            title: "What is the package version",
            content: "v1.0.5"
          }],
    
      }
    
      const styles = {
        titleTextColor: "black",
        rowTitleColor: "black",
        rowContentColor: 'grey',
        arrowColor: "black",
    };
    
    const config = {
        animate: true,
        tabFocus: true
    };
    
    return (
        <div>
            <Navbar/>
            <div class="about1">

                {/* first section */}
                <div class="about1_left">
                    <div class="pink_highlight"></div>
                    <div class="pink_highlight_short"></div>
                    <span class="about1_heading">Share What You Cook With Friend, Together!</span>
                    <span class="about1_content">When you share something that cooked by yourself, the happiness is priceless.</span>
                    <div class="btn_startInvitation">
                        <button class="btn_startInvitation_back">Start Invitation</button>
                    </div>
                </div>
                <div class="about1_right">
                    <div class="about1_pic_container">
                        <div class="about1_picture"></div>
                    </div>
                </div>
            </div>

            {/* second section */}
            <div class="about2_data_container">
                <div class="about2_data_food">
                    <div class="about2_data_food_box"><span class="about2_data_food_data">180,000+</span><span class="about2_data_food_name">Food Shared</span></div>
                </div>
                <div class="about2_data_places">
                    <div class="about2_data_places_box"><span class="about2_data_places_data">400,000+</span><span class="about2_data_places_name">Places</span></div>
                </div>
                <div class="about2_data_providers">
                    <div class="about2_data_providers_box"><span class="about2_data_providers_data">20,000+</span><span class="about2_data_providers_name">Active Providers</span></div>
                </div>
                <div class="about2_data_users">
                    <div class="about2_data_users_box"><span class="about2_data_users_data">6,900,000</span><span class="about2_data_users_name">Users</span></div>
                </div>
            </div>

            <div class="about2_container">
                <div class="about2_paragraph">
                    <span class="about2_quotation_mark">“</span><span class="about2_quotation">Awalnya saya malu dengan mertua karena tidak bisa memasak, kini mereka lebih suka makan malam di rumah demi menyantap masakan yang saya buat sendiri</span>
                </div>
                <div class="about2_person_container">
                    <div class="about2_person_avatar_container">
                        <div class="about2_person_avatar"></div>
                    </div>
                    <span class="about2_person_name">Camella Sarrah</span><span class="about2_person_title">Vege Master</span>
                </div>
            </div>

            {/* faq section */}
            <div class="faq_container">
                <div class="faq_container_left">
                    <div class="faq_pic_container">
                        <div class="faq_pic"></div>
                    </div>
                </div>
                <div class="faq_container_right">
                    <Faq data = {data}
                            styles = {styles}
                            config={config}
                    />
                </div>
            </div>

            {/* footer section */}
            <div class = "about_footer_container">
                <Footer/>
            </div>
        </div>

    )
}