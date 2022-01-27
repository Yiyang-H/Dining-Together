import react, {useEffect, useState} from 'react';
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
import { getCandidates, getMyFoods, updateApplicationStatus } from '../../api/myFood';


  const style = {
    fontFamily: 'Cabin',
    fontStyle: 'normal',
    fontWeight: 'bold',
    fontSize: '18px',
    lineHeight: '27px',
    color: '#262626'};


export default function MyFoodCard() {
   
    const [data, setData] = useState([]);

    useEffect(() => {
        async function getFoodData() {
            
            const foodData = await getMyFoods();
            console.log(foodData);
            if(foodData) {
                setData(foodData);
            }
        }

        getFoodData();
    }, [])


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
    const [candidates, setCandidates] = useState([]);

    const handleAccept = function(candidateId) {
        updateApplicationStatus(elem.foodId, candidateId, 'ACCEPTED')
    }

    const handleReject = function(candidateId) {
        updateApplicationStatus(elem.foodId, candidateId, 'DECLINED')
    }

    useEffect(() => {
        async function getCandidatesData() {
            const candidatesData = await getCandidates(elem.foodId);
            if(candidatesData) {
                setCandidates(candidatesData);
            }
            
        }

        getCandidatesData();
    }, [])

    return(
        <Card sx={{margin:"15px"}}>
            <Box sx={{display: 'flex', height: '100%', flexDirection: 'row', alignItems: 'center', padding: '5px', width:'100%'}}>
                <Box>
                <IMmgHolder src={elem.pictureBase64 ? ('data:image;base64,' + elem.pictureBase64) : "https://images.unsplash.com/photo-1565958011703-44f9829ba187?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1965&q=80"}/>

                </Box>
                <Box sx={{flexGrow: 2, height: '100%', margin: '10px', maxWidth: '70%'}}>
                    <Box sx={{display: 'flex', flexDirection: 'row'}}>
                        <Box width='80%' sx={style}>
                            Name:{elem.title}
                        </Box>
                        <Box width='20%' sx={style}>
                            ${elem.price}
                        </Box>
                    </Box>

                    {/* <Box sx={{display: 'flex', flexDirection: 'row'}}> */}
                    <Box sx={style}>
                        Address: {elem.location}
                    </Box>
                    <Box sx={style}>
                        Pick up time: {(new Date(elem.endTime)).toLocaleString()}
                        </Box>
                    {/* </Box> */}

                    <Box sx={style}>Description:</Box>
                    <Box sx={{width: '100%'}}>{elem.description}</Box>
                </Box>
            </Box>
            <CardActions disableSpacing sx={{display: 'flex'}}>
                <Box sx={{flexGrow: 2, 
                textAlign: 'center',
                fontFamily: 'Cabin',
                fontStyle: 'normal',
                fontWeight: 'bold',
                fontSize: '18px',
                lineHeight: '27px',
                color: '#262626'
                }}>Waiting List</Box>
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
                        <Card raised sx={{mx: '2%', width: '96%', display: 'flex', flexDirection: 'row', alignItems: 'center', height:'8vh'}}>
                            <Avatar sx={{ml: '1vw', width: '3vw', height: '3vw'}} src={each.candidate.pictureBase64 ? ('data:image;base64,' + each.candidate.pictureBase64) : "https://images.unsplash.com/photo-1565958011703-44f9829ba187?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1965&q=80"}></Avatar>
                            <div class='name-style'>{each.candidate.username}</div>
                            {
                                each.status === 'PENDING' ? 
                                <Box sx={{display: 'flex', flexDirection: 'row'}}>
                                    <Box onClick={() => handleAccept(each.candidate.id)} sx={{background: '#0CC863', color: 'white', width: '4.5vw', height:'4vh', mx:'0.5vw', borderRadius: '5px', display: 'flex', alignItems: 'center', justifyContent: 'center', cursor: 'pointer'}}>Accept</Box>
                                    <Box onClick={() => handleReject(each.candidate.id)} sx={{background: '#FA404B', color: 'white', width: '4.5vw', height:'4vh', mx:'0.5vw', borderRadius: '5px', display: 'flex', alignItems: 'center', justifyContent: 'center', cursor: 'pointer'}}>Reject</Box>
                                </Box> :
                                <Box>
                                    NOT PENDING
                                </Box>
                            }
                        </Card>)
                    })}
                </Box>
                
                
            </Collapse>

            
        </Card>
    )
}