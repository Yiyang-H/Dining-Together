import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import Rating from '@mui/material/Rating';
import CardHeader from '@mui/material/CardHeader';
import Avatar from '@mui/material/Avatar';
import CardActions from '@mui/material/CardActions';
import CheckCircleOutlineOutlinedIcon from '@mui/icons-material/CheckCircleOutlineOutlined';
import IconButton from '@mui/material/IconButton';
import CloseRoundedIcon from '@mui/icons-material/CloseRounded';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router-dom';
import submitApplication from '../api/submitApplication';
import { getUserId } from '../api/login';

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

function ChildModal(props) {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

  const imgType={
    "MAIN_MEAL":"https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F43%2F2019%2F11%2F6505068-baked-lemon-butter-chicken-thighs-photo-by-france-c-2000.jpg",
    "DRINKING":"https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/cocktails-1594319263.jpg",
    "DESSERT":"https://images-gmi-pmc.edge-generalmills.com/7c1096c7-bfd0-4806-a794-1d3001fe0063.jpg",
    "FAST_FOOD":"https://img.jakpost.net/c/2016/09/29/2016_09_29_12990_1475116504._large.jpg"
}
  
  

  // const navigate = useNavigate();

  const [textFieldValue, setTFvalue] = React.useState('');

  const [isDisable, setDisable] = React.useState(false);

  const handleConfirm=()=>{
    if(props.loggedIn){
      submitApplication(props.foodItem.foodId,getUserId(),textFieldValue)
      setDisable(true);
    }
    else{
      alert ("Please login/sign up first!")
    }
  }

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
                image={imgType[props.foodItem.category]}
                style={cardMediaStyle}
            />

            <CardContent>
                {/* {Get Data from array} */}
              <Typography gutterBottom variant="h6" component="div" style={foodInfoStyle}>
                Order Details
                </Typography>
                <Typography variant="body1" color="text.primary" style={inlineText}>
                    Name:{props.foodItem.title}
                </Typography>
                <Typography variant="body1" color="text.primary" style={inlineText}>
                  {props.foodItem.price}
                </Typography>
                <Typography variant="body1" color="text.secondary" style={inlineText}>
                    Address:{props.foodItem.location}
                </Typography>
                <Typography variant="body1" color="text.secondary" style={inlineText}>
                    Pick up time:{props.foodItem.endTime}
                </Typography>

                <TextField
                id="outlined-multiline-static"
                multiline
                fullWidth={true}
                rows={2}
                placeholder="Notes for provider"
                style={textInputArea}
                onChange={(event) => {setTFvalue(event.target.value)}}
                value={textFieldValue}

                />
            </CardContent>
            <CardActions style={foodInfoStyle}>
                <Button variant="contained" size="medium" startIcon={<CheckCircleOutlineOutlinedIcon/>} style={buttonColor} onClick={handleConfirm} disabled={isDisable}>Confirm</Button>
            </CardActions>
            </Card>
       </Box> 
      </Modal>
    </React.Fragment>
  );
}

export default function FoodCard(props) {
  const [open, setOpen] = React.useState(false);
  const [value, setValue] = React.useState(2);

  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

  const buttonStyle={
    position:"flex",
    top: '0',
    left: '50%',
    transform: 'translate(-50%, 0)'
  }
  
  

  const ratingStyle={
    width: '100%',
    height: '30%',
    justifyContent: 'center',
    alignItems: 'center'
  }

  const imgType={
    "MAIN_MEAL":"https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F43%2F2019%2F11%2F6505068-baked-lemon-butter-chicken-thighs-photo-by-france-c-2000.jpg",
    "DRINKING":"https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/cocktails-1594319263.jpg",
    "DESSERT":"https://images-gmi-pmc.edge-generalmills.com/7c1096c7-bfd0-4806-a794-1d3001fe0063.jpg",
    "FAST_FOOD":"https://img.jakpost.net/c/2016/09/29/2016_09_29_12990_1475116504._large.jpg"
}
  return (
    <Card sx={{ maxWidth: 200 }} >
        <CardMedia
          component="img"
          height="140"
          image={imgType[props.foodItem.category]}
          alt="green iguana"
        />
        <CardContent>
          <Typography gutterBottom variant="h7" component="div" textAlign="center">
          {props.foodItem.title}
          </Typography>

          <Typography variant="body2" color="text.secondary" textAlign="center">
          {props.foodItem.description}
          </Typography>

          <Rating name="read-only" value={value} readOnly style={ratingStyle}/>
          <Button size="medium" style={buttonStyle} onClick={handleOpen}>View More</Button>
        </CardContent>

        <Modal
            open={open}
            onClose={handleClose}
            aria-labelledby="parent-modal-title"
            aria-describedby="parent-modal-description"
          >
            <Box sx={{height: '100vh'}} style={popupBox}> 
                  <Card sx={{ height: '100vh' }} >
                    <IconButton style={crossButton} onClick={handleClose}>
                        <CloseRoundedIcon />
                    </IconButton>
                    <CardMedia
                        component="img"
                        height="200"
                        alt="green iguana"
                        image={imgType[props.foodItem.category]}
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
                              <Typography gutterBottom variant="h5" component="div">{props.foodItem.price}</Typography> 
                            }
                            title={props.foodItem.provider.username}
                            subheader={props.foodItem.createdTime}
                        />
                        <Rating name="read-only" value={value} readOnly style={foodInfoRatingStyle}/>

                        <Typography gutterBottom variant="h4" component="div" style={foodInfoStyle}>
                        {props.foodItem.title}
                        </Typography>

                        <Typography variant="body1" color="text.secondary" style={foodInfoStyle}>
                        {props.foodItem.description}
                        </Typography>

                        <Typography variant="body1" color="text.secondary" style={foodInfoStyle}>
                        {props.foodItem.location}
                        </Typography>
                        
                        <Typography variant="body1" color="text.secondary" style={foodInfoStyle}>
                        {props.foodItem.endTime}
                        </Typography>


                    </CardContent>
                    <CardActions style={foodInfoStyle}>
                        <ChildModal foodItem={props.foodItem} loggedIn={props.loggedIn}/>
                    </CardActions>
                  </Card>
            </Box> 
        </Modal>
    </Card>
  );
}