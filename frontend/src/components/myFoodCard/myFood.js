import react, {useState} from 'react';
import IMmgHolder from '../imgHolder';
import {
    Card,
    CardHeader,
    CardMedia,
    CardContent,
    CardActions,
    Collapse,
    Box,
    List,
    Avatar,
    IconButton
} from '@mui/material';
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";

import './myFood.css';

const data = [  {
    "title": "string",
    "description": "string",
    "location": "string",
    "endTime": "2022-11-12 13:02:56.12345678",
    "foodType": "DINING_IN",
    "category": "MAIN_MEAL",
    "consumerNumber": 1,
    "pictureBase64": "string",
    "price": 0,
    "foodId": 0,
    "provider": {
      "phoneNumber": "string",
      "suburb": "string",
      "avatarBase64": "string",
      "id": 0,
      "username": "string",
      "currency": 0,
      "email": "string"
    },
    "createdTime": "string",
    "completed": true
  },
  {
    "title": "string",
    "description": "string",
    "location": "string",
    "endTime": "2022-11-12 13:02:56.12345678",
    "foodType": "DINING_IN",
    "category": "MAIN_MEAL",
    "consumerNumber": 1,
    "pictureBase64": "string",
    "price": 0,
    "foodId": 0,
    "provider": {
      "phoneNumber": "string",
      "suburb": "string",
      "avatarBase64": "string",
      "id": 0,
      "username": "string",
      "currency": 0,
      "email": "string"
    },
    "createdTime": "string",
    "completed": true
  }];


const candidates = [
    {
      "candidate": {
        "phoneNumber": "string",
        "suburb": "string",
        "avatarBase64": "string",
        "id": 0,
        "username": "string",
        "currency": 0,
        "email": "string"
      },
      "status": "PENDING",
      "createdTime": "string"
    }]

  const style = {
    fontFamily: 'Cabin',
    fontStyle: 'normal',
    fontWeight: 'bold',
    fontSize: '18px',
    lineHeight: '27px',
    color: '#262626'};


export default function MyFoodCard() {
    
    return(
    <div>
        
        {data.map(elem => {
            
            return (
            <EachCard elem={elem}/>
            
            )
        })}
        

    </div>)
}

function EachCard(props) {
    const {elem} = props;
    const [showMore, setShowMore] = useState(false);
    return(
        <Card sx={{margin:"15px"}}>
            <Box sx={{display: 'flex', height: '100%', flexDirection: 'row', alignItems: 'center', padding: '5px'}}>
                <IMmgHolder src={"https://images.unsplash.com/photo-1565958011703-44f9829ba187?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1965&q=80"}/>
                <Box sx={{border: '1px solid black', flexGrow: 1, height: '100%', margin: '10px'}}>
                    <Box sx={{display: 'flex', flexDirection: 'row'}}>
                        <Box width='80%' sx={style}>
                            Name:{elem.title}
                        </Box>
                        <Box width='20%' sx={style}>
                            {elem.price}
                        </Box>
                    </Box>

                    <Box sx={{display: 'flex', flexDirection: 'row'}}>
                        <Box width='50%' sx={style}>
                            Address: {elem.location}
                        </Box>
                        <Box width='50%' sx={style}>
                            Pick up time: {(new Date(elem.endTime)).toLocaleString()}
                        </Box>
                    </Box>

                    <Box sx={style}>Description</Box>
                    <Box>{elem.description}</Box>
                </Box>
            </Box>
            <CardActions disableSpacing>
                <IconButton
                expand={showMore}
                onClick={()=>setShowMore(!showMore)}
                >
                <ExpandMoreIcon sx={showMore ? {transform: 'rotate(180deg)'} : {}} />
                </IconButton>
            </CardActions>
            <Collapse in={showMore} timeout="auto" unmountOnExit>
                <Box sx={{my: '5px'}}>
                    {candidates.map(each => {
                        return (
                        <Card sx={{mx: '2%', width: '96%',  border: '1px solid black', display: 'flex', flexDirection: 'row', alignItems: 'center', height:'50px'}}>
                            <Avatar></Avatar>
                            <Box sx={{flexGrow:1}}>{each.candidate.username}</Box>
                            <Box sx={{background: '#0CC863', color: 'white', width: '90px', height:'70%', textAlign: 'center', m:'3px', borderRadius: '5px'}}>Accept</Box>
                            <Box sx={{background: '#FA404B', color: 'white', width: '90px', height:'70%', textAlign: 'center', m:'3px', borderRadius: '5px'}}>Reject</Box>
                        </Card>)
                    })}
                </Box>
                
                
            </Collapse>

            
        </Card>
    )
}