import * as React from 'react';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardMedia from '@mui/material/CardMedia';
import Grid from '@mui/material/Grid';



export default function OrderCard(props){

    return(
        <Card sx={{ display: 'flex' ,height:160,margin:"15px"}}>
            <Grid container spacing={2}>
                <Grid item xs={3} sx={{ width: 151, height: 151 }}>
                    <CardMedia
                        component="img"
                        sx={{ width: 151 }}
                        image="https://picsum.photos/id/237/200/300"
                        alt="Live from space album cover"
                    />
                </Grid>
                <Grid item xs={12} sm container>
                    <Grid item xs container direction="column" spacing={2}>
                        <Grid item xs>
                        <Typography gutterBottom variant="h6" component="div">
                            Name:  {props.foodItem.title}
                        </Typography>
                        <Typography variant="body1" gutterBottom>
                            Address: {props.foodItem.location}
                        </Typography>
                        <Typography variant="body1" gutterBottom>
                            Pick up time: {props.foodItem.endTime}
                        </Typography>
                        {/* <Typography style={{ wordWrap: "break-word"}} variant="body2" gutterBottom>
                            Description: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et lorem quis purus Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et lorem quis purus
                        </Typography> */}
                        <Typography variant="h6" gutterBottom color={props.foodItem.status==="Completed"?"primary":"black"}>
                            Status: {props.foodItem.status}
                        </Typography>
                        </Grid>
                    </Grid>
                    <Grid item xs={2}>
                        <Typography variant="subtitle1" component="div">
                        ${props.foodItem.consumerNumber}
                        </Typography>
                    </Grid>
                </Grid>
            </Grid>
        </Card>
    )
}