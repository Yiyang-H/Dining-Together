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
import { red } from '@mui/material/colors';
import Button from '@mui/material/Button';
import CardActions from '@mui/material/CardActions';
import CheckCircleOutlineOutlinedIcon from '@mui/icons-material/CheckCircleOutlineOutlined';
import IconButton from '@mui/material/IconButton';
import CloseRoundedIcon from '@mui/icons-material/CloseRounded';
import FoodConfirm from './foodConfirm';
import Modal from '@mui/material/Modal';


export default function FoodInfo(props) {
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

  const popupBox={
    position: "fixed",
    background: '#00000050',
    width: '552px',
    height: '100vh',
    top: '0',
    left: '50%',
    transform: 'translate(-50%, 0)'
    
  }

//   const contanierBgColor={
//       backgroundColor:"grey"
//   }

  const [isOpen, setIsOpen] = React.useState(false);

  const togglePopup = () => {
    setIsOpen(!isOpen);
  }

  //modal
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
            <IconButton style={crossButton}>
                <CloseRoundedIcon onClick={handleClose}/>
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
                <CardHeader
                    avatar={
                    <Avatar sx={{ bgcolor: "#F7C531" }} aria-label="recipe">
                        R
                    </Avatar>
                    }
                    action={
                       <Typography gutterBottom variant="h5" component="div">100</Typography> 
                    }
                    title="Shrimp and Chorizo Paella"
                    subheader="September 14, 2016"
                />
                <Rating name="read-only" value={value} readOnly style={foodInfoRatingStyle}/>
                <Typography gutterBottom variant="h4" component="div" style={foodInfoStyle}>
                Lizard
                </Typography>
                <Typography variant="body1" color="text.secondary" style={foodInfoStyle}>
                Lizards are a widespread group of squamate reptiles, with over 6,000
                species, ranging across all continents except Antarctica Lizards are a widespread group of squamate reptiles, with over 6,000
                species, ranging across all continents except Antarctica Lizards are a widespread group of squamate reptiles, with over 6,000
                species, ranging across all continents except Antarctica
                </Typography>
            </CardContent>
            <CardActions style={foodInfoStyle}>
                {/* <Button variant="contained" size="medium" startIcon={<CheckCircleOutlineOutlinedIcon/>} style={buttonColor} onClick={togglePopup}>{isOpen&&<FoodConfirm handleClose={togglePopup}/>}Apply</Button> */}
                <Button variant="contained" size="medium" startIcon={<CheckCircleOutlineOutlinedIcon/>} style={buttonColor} onClick={handleOpen}>Apply</Button>
                <Modal
                    hideBackdrop
                    open={open}
                    onClose={handleClose}
                    aria-labelledby="child-modal-title"
                    aria-describedby="child-modal-description"
                >
                    <FoodConfirm></FoodConfirm>
                </Modal>

            </CardActions>
            </Card>
       </Box> 
    </Container>    
  );
}