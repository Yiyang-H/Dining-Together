import * as React from 'react';
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import Button from '@mui/material/Button';
import FoodInfo from '../foodInfo';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { CardActionArea } from '@mui/material';
import Rating from '@mui/material/Rating';
import Container from '@mui/material/Container';
import CardHeader from '@mui/material/CardHeader';
import Avatar from '@mui/material/Avatar';
import CardActions from '@mui/material/CardActions';
import CheckCircleOutlineOutlinedIcon from '@mui/icons-material/CheckCircleOutlineOutlined';
import IconButton from '@mui/material/IconButton';
import CloseRoundedIcon from '@mui/icons-material/CloseRounded';
import TextField from '@mui/material/TextField';

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    pt: 2,
    px: 4,
    pb: 3,
};

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

function ChildModal() {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => {
    setOpen(true);

  };
  const handleClose = () => {
    setOpen(false);
    console.log(open);
  };

  return (
    <React.Fragment>
      <Button onClick={handleOpen} variant="contained" size="medium" startIcon={<CheckCircleOutlineOutlinedIcon/>} style={buttonColor}>Apply</Button>
      <Modal
        hideBackdrop
        open={open}
        onClose={handleClose}
        aria-labelledby="child-modal-title"
        aria-describedby="child-modal-description"
      >
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
      </Modal>
    </React.Fragment>
  );
}

export default function NestedModal(props) {
  const [open, setOpen] = React.useState(false);
  const [value, setValue] = React.useState(2);

  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
    
  };
  

  return (
    <div>
    <Button onClick={handleOpen}>Open modal</Button>
    {/* {props.handleOpen} */}
    <Modal
      open={open}
      onClose={handleClose}
      aria-labelledby="parent-modal-title"
      aria-describedby="parent-modal-description"
    >


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
                <ChildModal/>
            </CardActions>
            </Card>
       </Box> 

    </Modal>
  </div>
  );
}