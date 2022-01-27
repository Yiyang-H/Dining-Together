import React, {useState} from 'react';
import './provideFood.css';

import {
    Box,
    TextField,
    Select,
    MenuItem,
    IconButton,
    Autocomplete
} from '@mui/material';

import {
    Remove,
    Add
} from '@mui/icons-material';

import { FileDrop } from 'react-file-drop';
import CloseRoundedIcon from '@mui/icons-material/CloseRounded';
import axios from 'axios';
import IMmgHolder from '../../components/imgHolder';
import imagePlaceholder from '../../images/image_placeholder.jpg';
import { uploadFood } from '../../api/provideFood';

export default function ProvideFood(props) {
    const [name, setName] = useState('');
    const [category, setCategory] = useState('');
    const [description, setDescription] = useState('');
    const [address, setAddress] = useState('');
    const [numOfSup, setNumOfSup] = useState(0);
    const [time, setTime] = useState((new Date()).toISOString().slice(0,16));
    const [image, setImage] = useState(imagePlaceholder);
    const [price, setPrice] = useState();
    

    const handleTimeChange = function(e) {
        setTime(e.target.value);
    }

    const handleSubmit = function() {
        uploadFood(name, description, address, time, "TAKING_AWAY", category, numOfSup, image, price);
        // alert('form submitted');
    }

      const crossButton={
        float: 'right',
        top:0
      }
      function previewFile(e) {
          console.log(e.target.files[0]);
        const file = e.target.files[0];
        const reader = new FileReader();
      
        reader.addEventListener("load", function () {
          // convert image file to base64 string
          setImage(reader.result);
          console.log(reader.result);
        }, false);
      
        if (file) {
          reader.readAsDataURL(file);
        }
      }
    
    return (
    <div style={{height: '100%', width: '100%', display: 'flex', alignItems: 'center', justifyContent: 'center', overflowY: 'scroll'}}>
       

        <form class='submit-form'>
        <IconButton style={crossButton} onClick={props.handleClose}>
                <CloseRoundedIcon/>
        </IconButton>


            <div style={{display: 'flex', flexDirection: 'row'}}>
                <div class='form-field' style={{width: '50%'}}>
                    <label class='form-title'>Name</label>
                    <TextField value={name} onChange={(e)=>setName(e.target.value)} fullWidth placeholder='The name of the food to share'/>
                </div>

                <div class='form-field' style={{width: '50%'}}>
                    <label class='form-title'>Category</label>
                    <Select fullWidth 
                    value={category}
                    onChange={(e) => {setCategory(e.target.value)}}
                    >
                    <MenuItem value='MAIN_MEAL'>Main meal</MenuItem>
                    <MenuItem value='DRINKING'>Drinking</MenuItem>
                    <MenuItem value='DESSERT'>Dessert</MenuItem>
                    <MenuItem value='FAST_FOOD'>Fast Food</MenuItem>
                    </Select>
                </div>
            </div>

            <div class='form-field'>
                <label class='form-title'>Description</label>
                <TextField value={description} onChange={(e)=>setDescription(e.target.value)} multiline fullWidth rows={3} placeholder='Enter the details of the food you are going to share'/>
            </div>

            <div class='form-field'>
                <label class='form-title'>Address</label>
                <TextField value={address} onChange={(e)=>setAddress(e.target.value)} fullWidth placeholder='Enter you address'/>
            </div>

            <div style={{display: 'flex', flexDirection:'row'}}>
                <div class='form-field'>
                    <label class='form-title'>Pick up time</label> <br/>
                    <TextField
                        type="datetime-local"
                        value={time}
                        onChange={handleTimeChange}
                        sx={{ width: 250 }}
                        InputLabelProps={{
                        shrink: true
                        }}
                    />
                </div>

                <div class='form-field' style={{width: '30%'}}>
                    <label class='form-title'>Price</label> <br/>
                    <TextField value={price} type='number' onChange={(e)=>setPrice(e.target.value)} fullWidth placeholder='Enter price for each'/>
                </div>

                <div style={{display: 'flex', flexDirection:'column', alignItems:'center'}}>
                    <label class="myLabel">
                        <input type="file" onChange={previewFile} required/>
                        <IMmgHolder src={image}></IMmgHolder>
                    </label>
                </div>
            </div>

            <div style={{display: 'flex', flexDirection:'row', alignItems: 'center', marginTop:'0px'}}>
                <div style={{display: 'flex', flexDirection: 'row', width: '60%', alignItems: 'center'}}>
                    <div class='form-field' style={{width: '50%'}}>
                        <label class='form-title'>Select number of supplies</label> <br/>
                        <p>Choose the number of slots you need</p>
                    </div>

                    <div style={{display: 'flex', flexDirection: 'row'}}>
                        <IconButton onClick={()=>{setNumOfSup(numOfSup-1)}}>
                            <Remove sx={{color: '#F7C531'}}/>
                        </IconButton>
                        <TextField value={numOfSup} sx={{width: '40px'}}/>
                        <IconButton onClick={()=>{setNumOfSup(numOfSup+1)}}>
                            <Add sx={{color: '#F7C531'}}/>
                        </IconButton>
                    </div>
                </div>
                
                
                {/* <input type="file" onChange={previewFile}/> */}
                
                <div style={{marginLeft: '0%'}}>
                    <button class='submit-button' onClick={handleSubmit}>Submit</button>
                </div>
            </div>

            
        </form>
    {/* // </Box> */}
    </div>
    )
}


