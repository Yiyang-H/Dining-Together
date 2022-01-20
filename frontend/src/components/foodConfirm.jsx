import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { CardActionArea } from '@mui/material';
import Rating from '@mui/material/Rating';
import Container from '@mui/material/Container';
import Box from '@mui/material/Box';
import CardHeader from '@mui/material/CardHeader';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CardActions from '@mui/material/CardActions';
import CheckCircleOutlineOutlinedIcon from '@mui/icons-material/CheckCircleOutlineOutlined';
import IconButton from '@mui/material/IconButton';
import CloseRoundedIcon from '@mui/icons-material/CloseRounded';
import TextField from '@mui/material/TextField';
import Modal from '@mui/material/Modal';





export default function FoodConfirm(props) {
  const [value, setValue] = React.useState(2);

  const foodInfoRatingStyle={
    width: '100%',
    height: '30%',
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom:"20px"
  }

  const cardMediaStyle={
          paddingLeft: '15px', 
          paddingRight: '15px',
          backgroundSize: "cover",
          backgroundRepeat: "no-repeat",
          backgroundPosition: "center center",
  }

  const foodInfoStyle={
      display:"flex",
      justifyContent: 'center',
      alignItems: 'center'

  }

  const buttonColor = {
    backgroundColor:'#F7C531'
  }

  const crossButton={
    float: 'right'
  }

  const contanierBgColor={
     
      backgroundColor:"grey"
  }

  const inlineText={
    float: 'inline',
    padding:"10px"

  }

  const textInputArea={
    float: 'inline',
    padding:"10px"

  }
  const popupBox={
    position: "fixed",
    background: '#00000050',
    width: '552px',
    height: '100vh',
    top: '0',
    left: '50%',
    transform: 'translate(-50%, 0)'
    
  }

  const [open, setOpen] = React.useState(false);
  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

 


  return (
    <Container maxWidth="sm">
        <Box sx={{height: '100vh'}} style={popupBox}> 
            <Card sx={{ height: '100vh' }} >
            {/* <IconButton style={crossButton} onClick={props.handleClose}> */}
            <IconButton style={crossButton} onClick={handleClose}>
                <CloseRoundedIcon/>
            </IconButton>
            <CardMedia
                component="img"
                height="200"
                alt="green iguana"
                image="https://picsum.photos/seed/picsum/200/300"
                style={cardMediaStyle}
            />

            <CardContent>
                {/* {Get Data from array} */}
              <Typography gutterBottom variant="h6" component="div" style={foodInfoStyle}>
                Order Details
                </Typography>
                <Typography variant="body1" color="text.primary" style={inlineText}>
                    Name:Double Big Mac Extra Value Meal [870-1300 Cals]
                </Typography>
                <Typography variant="body1" color="text.primary" style={inlineText}>
                    100
                </Typography>
                <Typography variant="body1" color="text.secondary" style={inlineText}>
                    Address:xxxxxxxxxx 
                </Typography>
                <Typography variant="body1" color="text.secondary" style={inlineText}>
                    Pick up time:xxxxxxxxxx 
                </Typography>

                <TextField
                id="outlined-multiline-static"
                multiline
                fullWidth={true}
                rows={2}
                placeholder="Notes for provider"
                style={textInputArea}
                />
            </CardContent>
            <CardActions style={foodInfoStyle}>
                <Button variant="contained" size="medium" startIcon={<CheckCircleOutlineOutlinedIcon/>} style={buttonColor} >Confirm</Button>
            </CardActions>
            </Card>
       </Box> 
    </Container>    
  );
}