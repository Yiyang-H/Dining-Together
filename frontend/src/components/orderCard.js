import react, {useEffect, useState} from 'react';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardMedia from '@mui/material/CardMedia';
import Grid from '@mui/material/Grid';
import IMmgHolder from './imgHolder';



export default function OrderCard(props){

    const obj={
        "ACCEPTED":"green",
        "PENDING":"primary",
        "DECLINED":"red"
    }

    const imgType={
        "MAIN_MEAL":"https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F43%2F2019%2F11%2F6505068-baked-lemon-butter-chicken-thighs-photo-by-france-c-2000.jpg",
        "DRINKING":"https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/cocktails-1594319263.jpg",
        "DESSERT":"https://images-gmi-pmc.edge-generalmills.com/7c1096c7-bfd0-4806-a794-1d3001fe0063.jpg",
        "FAST_FOOD":"https://img.jakpost.net/c/2016/09/29/2016_09_29_12990_1475116504._large.jpg"
    }
   

    return(
        <Card sx={{ display: 'flex' ,height:160,margin:"15px"}}>
            <Grid container spacing={2}>
                <Grid item xs={3} sx={{ width: 151, height: 151 }}>
                    <IMmgHolder src={imgType[props.foodItem.food.category]}/>
                </Grid>
                <Grid item xs={12} sm container>
                    <Grid item xs container direction="column" spacing={2}>
                        <Grid item xs>
                        <Typography gutterBottom variant="h6" component="div">
                            Name:  {props.foodItem.food.title}
                        </Typography>
                        <Typography variant="body1" gutterBottom>
                            Address: {props.foodItem.food.location}
                        </Typography>
                        <Typography variant="body1" gutterBottom>
                            Pick up time: {(new Date(props.foodItem.food.endTime)).toLocaleString()}
                        </Typography>
                        {/* <Typography style={{ wordWrap: "break-word"}} variant="body2" gutterBottom>
                            Description: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et lorem quis purus Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et lorem quis purus
                        </Typography> */}
                        <Typography variant="h6" gutterBottom color={obj[props.foodItem.status]}>
                            Status: {props.foodItem.status}
                        </Typography>
                        </Grid>
                    </Grid>
                    <Grid item xs={2}>
                        <Typography variant="subtitle1" component="div">
                        ${props.foodItem.food.consumerNumber}
                        </Typography>
                    </Grid>
                </Grid>
            </Grid>
        </Card>
    )
}