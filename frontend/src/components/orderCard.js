import react, {useEffect, useState} from 'react';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardMedia from '@mui/material/CardMedia';
import Grid from '@mui/material/Grid';
import IMmgHolder from './imgHolder';
import { fetchUsersOrder } from '../api/getUsersOrder';



export default function OrderCard(props){

    const obj={
        "Completed":"green",
        "Pending":"primary",
        "Rejected":"red"
    }
    const [orderDatail,setOrderDetails]=useState([])

    useEffect(() => {
        async function fetchUserOrderFunction() {
            const orderData = await fetchUsersOrder();
            if(orderData) {
                setOrderDetails(orderData);
            }
        }
        fetchUserOrderFunction();
    }, [])

    return(
        <Card sx={{ display: 'flex' ,height:160,margin:"15px"}}>
            <Grid container spacing={2}>
                <Grid item xs={3} sx={{ width: 151, height: 151 }}>
                    <IMmgHolder src={"https://picsum.photos/id/237/200/300"}/>
                </Grid>
                <Grid item xs={12} sm container>
                    <Grid item xs container direction="column" spacing={2}>
                        <Grid item xs>
                        <Typography gutterBottom variant="h6" component="div">
                            Name:  {orderDatail.title}
                        </Typography>
                        <Typography variant="body1" gutterBottom>
                            Address: {orderDatail.location}
                        </Typography>
                        <Typography variant="body1" gutterBottom>
                            Pick up time: {orderDatail.endTime}
                        </Typography>
                        {/* <Typography style={{ wordWrap: "break-word"}} variant="body2" gutterBottom>
                            Description: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et lorem quis purus Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et lorem quis purus
                        </Typography> */}
                        <Typography variant="h6" gutterBottom color={obj[orderDatail.status]}>
                            Status: {orderDatail.status}
                        </Typography>
                        </Grid>
                    </Grid>
                    <Grid item xs={2}>
                        <Typography variant="subtitle1" component="div">
                        ${orderDatail.consumerNumber}
                        </Typography>
                    </Grid>
                </Grid>
            </Grid>
        </Card>
    )
}