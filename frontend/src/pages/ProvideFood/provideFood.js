import React, {useState} from 'react';
import './provideFood.css';

import {
    Box,
    TextField,
    Select,
    MenuItem,
    IconButton,
} from '@mui/material';

import {
    Remove,
    Add
} from '@mui/icons-material';

import { FileDrop } from 'react-file-drop';
import CloseRoundedIcon from '@mui/icons-material/CloseRounded';

export default function ProvideFood(props) {
    const [category, setCategory] = useState('');
    const [numOfSup, setNumOfSup] = useState(0);

    const handleSubmit = function() {
        alert('form submitted');
    }

      const crossButton={
        float: 'right',
        top:0
      }
    
    return (
    <div style={{height: '100%', width: '100%', display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
       

        <form class='submit-form'>
        <IconButton style={crossButton} onClick={props.handleClose}>
                <CloseRoundedIcon/>
        </IconButton>


            <div style={{display: 'flex', flexDirection: 'row'}}>
                <div class='form-field' style={{width: '50%'}}>
                    <label class='form-title'>Name</label>
                    <TextField fullWidth placeholder='The name of the food to share'/>
                </div>

                <div class='form-field' style={{width: '50%'}}>
                    <label class='form-title'>Category</label>
                    <Select fullWidth 
                    value={category}
                    onChange={(e) => {setCategory(e.target.value)}}
                    >
                    <MenuItem value='Breakfast'>Breakfast</MenuItem>
                    <MenuItem value='Lunch'>Lunch</MenuItem>
                    <MenuItem value='Afternoon tea'>Afternoon tea</MenuItem>
                    <MenuItem value='Dinner'>Dinner</MenuItem>
                    </Select>
                </div>
            </div>

            <div class='form-field'>
                <label class='form-title'>Description</label>
                <TextField multiline fullWidth rows={4} placeholder='Enter the details of the food you are going to share'/>
            </div>

            {/* TODO try add Google api */}
            <div class='form-field'>
                <label class='form-title'>Address</label>
                <TextField fullWidth placeholder='Start typing you address'/>
            </div>

            <div style={{display: 'flex', flexDirection:'row'}}>
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


                <div class='form-field'>
                    <label class='form-title'>Pick up time</label> <br/>
                    <TextField
                        type="datetime-local"
                        defaultValue="2017-05-24T10:30"
                        sx={{ width: 250 }}
                        InputLabelProps={{
                        shrink: true
                        }}
                    />
                </div>
            </div>

            <div style={{display: 'flex', flexDirection:'row', alignItems: 'center', marginTop:'20px'}}>
                {/* Drag and drop file area */}
                <div style={{ border: '1px solid black', width: '40%', height: '80px', color: 'black', margin: '0px 50px', borderRadius:'5px' }}>
                    <FileDrop
                    onFrameDragEnter={(event) => console.log('onFrameDragEnter', event)}
                    onFrameDragLeave={(event) => console.log('onFrameDragLeave', event)}
                    onFrameDrop={(event) => console.log('onFrameDrop', event)}
                    onDragOver={(event) => console.log('onDragOver', event)}
                    onDragLeave={(event) => console.log('onDragLeave', event)}
                    onDrop={(files, event) => console.log('onDrop!', files, event)}
                    >
                    Add file or drop it right here
                    </FileDrop>
                </div>

                <div style={{marginLeft: '20%'}}>
                    <button class='submit-button' onClick={handleSubmit}>Submit</button>
                </div>
            </div>

            
        </form>
    {/* // </Box> */}
    </div>
    )
}